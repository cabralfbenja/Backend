package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.OrderController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Customer;
import ar.edu.frc.utn.bda3k4.northwind.entities.Employee;
import ar.edu.frc.utn.bda3k4.northwind.entities.Order;
import ar.edu.frc.utn.bda3k4.northwind.entities.Shipper;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.OrderRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.*;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.*;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class OrderControllerTest {
    private OrderController orderController;
    private OrderRepository orderRepository;
    private ShipperRepository shipperRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;
    private final LocalDateTimeAttributeConverter localDateTimeAttributeConverter = new LocalDateTimeAttributeConverter();
    private final Customer CUSTOMER = new Customer("AAAA", "Aluminio",
            "Maria Anders", "Sales Representative", "Obere Str. 57",
            "Berlin", null, "12209", "Germany", "030-0074321",
            "030-0076545");
    private final Employee EMPLOYEE = new Employee(
            1,
            "Davolio",
            "Nancy",
            "Sales Representative",
            "Ms.",
            localDateTimeAttributeConverter.convertToEntityAttribute("1948-12-08"),
            localDateTimeAttributeConverter.convertToEntityAttribute("1960-05-01"),
            "507 - 20th Ave. E. Apt. 2A",
            "Seattle",
            "WA",
            "98122",
            "USA",
            "(206) 555-9857",
            "5467",
            null,
            "Education includes a BA in psychology from Colorado State University in 1970.  She also completed \"The Art of the Cold Call.\"  Nancy is a member of Toastmasters International.",
            null,
            "http://accweb/emmployees/davolio.bmp",
            null
    );
    private final Shipper SHIPPER = new Shipper(1, "Speedy Express", "(503) 555-9831", null);
    private final Order ORDER = new Order(
            1,
            localDateTimeAttributeConverter.convertToEntityAttribute("1948-12-08"),
            localDateTimeAttributeConverter.convertToEntityAttribute("1960-05-01"),
            localDateTimeAttributeConverter.convertToEntityAttribute("1960-05-01"),
            2.0,
            "ShipName",
            "ShipAddress",
            "ShipCity",
            "ShipRegion",
            "ShipPostalCode",
            "ShipCountry",
            CUSTOMER,
            EMPLOYEE,
            SHIPPER,
            null
    );

    private final OrderRequest ORDER_REQUEST = new OrderRequest(
            "AAAA",
            1,
            "1960-05-01",
            "1960-05-01",
            "1948-12-08",
            1, 10.0,
            "ShipCity",
            "ShipRegion",
            "ShipPostalCode",
            "ShipCountry",
            "AAAA",
            "A");

    @BeforeEach
    void setup(){
        orderRepository = Mockito.mock(OrderRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        shipperRepository = Mockito.mock(ShipperRepository.class);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        supplierRepository = Mockito.mock(SupplierRepository.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        SupplierServiceImpl supplierService = new SupplierServiceImpl(supplierRepository);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService, supplierService);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository, productService);
        ShipperServiceImpl shipperService = new ShipperServiceImpl(shipperRepository);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);
        orderController = new OrderController(orderService,
                customerService,
                shipperService,
                employeeService, productService);
    }

    @Test
    void testFindAll(){
        List<Order> orders = new ArrayList<>();
        orders.add(ORDER);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        Assertions.assertEquals(
                HttpStatus.OK,
                orderController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty(){
        Mockito.when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                orderController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById(){
        Mockito.when(orderRepository.findById(1)).thenReturn(java.util.Optional.of(ORDER));
        Assertions.assertEquals(
                HttpStatus.OK,
                orderController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound(){
        Mockito.when(orderRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                orderController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testAdd(){
        Mockito.when(customerRepository.findById("AAAA")).thenReturn(java.util.Optional.of(CUSTOMER));
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.of(SHIPPER));
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(EMPLOYEE));
        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(ORDER);
        Mockito.when(orderRepository.saveAndFlush(any(Order.class))).thenReturn(ORDER);
        Assertions.assertEquals(
                HttpStatus.OK,
                orderController.add(ORDER_REQUEST).getStatusCode()
        );
    }

    @Test
    void testUpdate(){
        Mockito.when(orderRepository.findById(1)).thenReturn(java.util.Optional.of(ORDER));
        Mockito.when(customerRepository.findById("AAAA")).thenReturn(java.util.Optional.of(CUSTOMER));
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.of(SHIPPER));
        Mockito.when(orderRepository.save(ORDER)).thenReturn(ORDER);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                orderController.update(1, ORDER_REQUEST).getStatusCode()
        );
    }

    @Test
    void testDelete(){
        Mockito.when(orderRepository.findById(1)).thenReturn(java.util.Optional.of(ORDER));
        Mockito.when(orderRepository.save(ORDER)).thenReturn(ORDER);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                orderController.delete(1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound(){
        Mockito.when(orderRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                orderController.delete(1).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound(){
        Mockito.when(orderRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                orderController.update(1, ORDER_REQUEST).getStatusCode()
        );
    }

}