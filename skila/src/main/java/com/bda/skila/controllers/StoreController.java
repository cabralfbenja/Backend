package com.bda.skila.controllers;

import com.bda.skila.entities.dtos.StoreDto;
import com.bda.skila.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    private StoreService service;
    public StoreController(StoreService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll(){
        List<StoreDto> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }
    @PostMapping
    public ResponseEntity<StoreDto> add(@RequestBody StoreDto store){
        StoreDto storeDto = this.service.add(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeDto);
    }
}
