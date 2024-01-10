package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.*;
import ar.edu.frc.utn.bda3k4.northwind.entities.exceptions.EmptyListException;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.OrderRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.create.OrderWithFiltersCreateRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.OrderResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.*;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import lombok.val;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ShipperService shipperService;
    private final EmployeeService employeeService;
    private final ProductService productService;
    public OrderController(OrderService orderService, CustomerService customerService, ShipperService shipperService, EmployeeService employeeService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.shipperService = shipperService;
        this.employeeService = employeeService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val orders = orderService.findAll()
                    .stream()
                    .map(OrderResponse::from)
                    .toList();
            return ResponseEntity.ok(orders);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody OrderRequest aRequest) {
        try{
            Customer customer = customerService.findById(aRequest.getCustomerId());
            Shipper shipper = this.validateShipper(aRequest.getShippedDate(), aRequest.getShipVia());
            Employee employee = employeeService.findById(aRequest.getEmployeeId());
            Order order = aRequest.toOrder();
            order.setCustomer(customer);
            order.setShipper(shipper);
            order.setEmployee(employee);
            order = orderService.add(order);
            return ResponseEntity.ok(OrderResponse.from(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody OrderRequest aRequest) {
        try {
            Order modifications = aRequest.toOrder();
            Shipper shipper = this.validateShipper(aRequest.getShippedDate(), aRequest.getShipVia());
            modifications.setShipper(shipper);
            Order order = orderService.update(id, modifications);
            return ResponseEntity.accepted().body(OrderResponse.from(order));
        } catch (IllegalArgumentException e) {
            if(e.getMessage() == "Order not found") return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            Order order = orderService.delete(id);
            return ResponseEntity.accepted().body(OrderResponse.from(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            val order = orderService.findById(id);
            return ResponseEntity.ok(OrderResponse.from(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new-order")
    @Transactional
    public ResponseEntity<Object> addWithFilters(@RequestBody OrderWithFiltersCreateRequest aRequest, @RequestParam Integer categoryId, @RequestParam Integer supplierId,@RequestParam  Integer stockmin){
        try {
            Customer customer = this.customerService.findById(aRequest.getCustomerId());
            Employee employee = this.employeeService.findById(aRequest.getEmployeeId());
            Shipper shipper = this.shipperService.findById(aRequest.getShipperId());
            LocalDate date = LocalDate.now();
            Order orderSinId = new Order(null, date, date, date, (double) 0, customer.getContactName(), customer.getAddress(), customer.getCity(), customer.getRegion(), customer.getPostalCode(), customer.getCountry(), customer, employee, shipper, null);
            Order order = this.orderService.add(orderSinId);
            List<Product> products = this.productService.findProductsByCategoryAndSupplierAndSafetyStock(categoryId, supplierId, stockmin);
            val ods = createOrderDetailsListWithFilter(products, order.getId(), aRequest.getRequiredStock());
            order.setOrderDetails(ods);
            order = this.orderService.update(order.getId(), order);
            return ResponseEntity.ok(OrderResponse.from(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (EmptyListException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    private OrderDetail createOrderDetailsWithFilter(Product product, Integer orderId, Integer requiredStock){
        Integer quantity = requiredStock - (product.getUnitsInStock()+product.getUnitsOnOrder());
        Double discount = (double) 0;
        if (quantity>=100) discount = 0.10;
        OrderDetail od = new OrderDetail(orderId, product.getId(), product.getUnitPrice(), quantity, discount);
        return od;
    }

    private List<OrderDetail> createOrderDetailsListWithFilter(List<Product> products, Integer orderId, Integer requiredStock){
        List<OrderDetail> ods = new ArrayList<>();
        for(int i = 0; i < products.size(); i++)
        {
            Product product = products.get(i);
            boolean add = ods.add(createOrderDetailsWithFilter(product, orderId, requiredStock));
        }
        return ods;
    }

    private Shipper validateShipper(String shippedDate, Integer shipper){
        if(!shippedDate.isBlank() && shipper != null)return shipperService.findById(shipper);
        else if(shippedDate.isBlank() && shipper == null)return null;
        else throw new IllegalArgumentException("Shipped date and shipper must be both null or not null");
    }

}
