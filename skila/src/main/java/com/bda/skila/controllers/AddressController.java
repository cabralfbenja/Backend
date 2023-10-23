package com.bda.skila.controllers;

import com.bda.skila.entities.dtos.AddressDto;
import com.bda.skila.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private AddressService service;
    public AddressController(AddressService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll(){
        List<AddressDto> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }
    @PostMapping
    public ResponseEntity<AddressDto> add(@RequestBody AddressDto address){
        AddressDto addressDto = this.service.add(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressDto);
    }

}
