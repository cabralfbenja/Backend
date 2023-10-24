package com.bda.skila.controllers;

import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.CustomerDto;
import com.bda.skila.repositories.StoreRepository;
import com.bda.skila.services.CustomerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService service;
    private final StoreRepository storeRepository;
    public CustomerController(CustomerService service, StoreRepository storeRepository){
        this.service = service;
        this.storeRepository = storeRepository;
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        List<CustomerDto> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }
    //@PostMapping
    //public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto customer){
      //  CustomerDto customerDto = this.service.add(customer);
        //return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(customerDto);
    //}

    @PostMapping
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto customer){
        CustomerDto customerDto = this.service.add(customer);
        if(customerDto != null){
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(customerDto);
        }
        Store store = storeRepository.findFirstByAddress_CityId(customer.getAddress().getCityId());
        if(store == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(customerDto);
        }

    }
}
