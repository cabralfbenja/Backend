package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.Customer;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.create.CustomerCreateRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.update.CustomerUpdateRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.CustomerResponse;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.OrderResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.CustomerService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customer")
@RestController
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) chequear
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val customers = customerService.findAll()
                    .stream()
                    .map(CustomerResponse::from)
                    .toList();
            return ResponseEntity.ok(customers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CustomerCreateRequest aRequest) {
        try {
            val customer = customerService.add(aRequest.toCustomer());
            return ResponseEntity.ok(CustomerResponse.from(customer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody CustomerUpdateRequest aRequest) {
        try {
            val customer = customerService.update(id, aRequest.toCustomer());
            return ResponseEntity.accepted().body(CustomerResponse.from(customer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        try {
            Customer customer = customerService.findById(id);
            return ResponseEntity.ok(CustomerResponse.from(customer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            Customer customer = customerService.delete(id);
            return ResponseEntity.ok(CustomerResponse.from(customer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/orders/{productID}")
    public ResponseEntity<Object> findOrdersWithProduct(@PathVariable String id, @PathVariable Integer productID) {
        try {
            val orders = customerService.findOrdersWithProduct(id, productID)
                    .stream()
                    .map(OrderResponse::from)
                    .toList();
            return ResponseEntity.ok(orders);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
