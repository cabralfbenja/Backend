package com.bda.skila.controllers;

import com.bda.skila.entities.Address;
import com.bda.skila.entities.City;
import com.bda.skila.entities.Customer;
import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.AddressDto;
import com.bda.skila.entities.dtos.CustomerDto;
import com.bda.skila.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService service;
    public CustomerController(CustomerService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        List<CustomerDto> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }

}
