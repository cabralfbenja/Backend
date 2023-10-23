package com.bda.skila.controllers;

import com.bda.skila.entities.Inventory;
import com.bda.skila.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private InventoryService service;
    public InventoryController(InventoryService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll(){
        List<Inventory> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }
    @PostMapping
    public ResponseEntity<Inventory> add(@RequestBody Inventory inventory){
        Inventory inventory1 = this.service.add(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventory1);
    }
}
