package com.bda.skila.controllers;

import com.bda.skila.entities.Customer;
import com.bda.skila.entities.dtos.CustomerDto;
import com.bda.skila.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService service;
    public CustomerController(CustomerService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }

}
