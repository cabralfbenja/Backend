package com.oveja.poc.controllers;


import com.oveja.poc.entities.Address;
import com.oveja.poc.entities.Customer;
import com.oveja.poc.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class CustomerController {

    private final BusinessService businessService;

    public CustomerController(@Autowired BusinessService businessService) {
        this.businessService = businessService;
    }


    @GetMapping("/health")
    ResponseEntity<String> health() {
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }


    @PostMapping("/customer")
    ResponseEntity addCustomer(@RequestBody Customer customer) {
        try {
            var result = businessService.addCustomer(customer);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Algo salio mal :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/customer/{id}")
    ResponseEntity getCustomer(@PathVariable("id") String id) {
        try {
            var result = businessService.getCustomer(Long.valueOf(id));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Algo salio mal :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customer/{id}/address")
    ResponseEntity<HttpStatus> addCustomerAddress(@RequestBody Address address, @PathVariable("id") String id) {
        try {
            businessService.addCustomerAddress(address, Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customer/{id}/addresses")
    ResponseEntity<HttpStatus> addCustomerAddresses(@RequestBody List<Address> address, @PathVariable("id") String id) {
        try {
            businessService.addCustomerAddresses(address, Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{id}/address")
    ResponseEntity getCustomerAddress(@PathVariable("id") String id) {
        try {
            var result = businessService.getCustomerAddress(Long.valueOf(id));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Algo salio mal :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
