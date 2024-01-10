package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.Supplier;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.SupplierRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.SupplierResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.SupplierService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/supplier")
@RestController
public class SupplierController {
    private final SupplierService supplierService;
    public SupplierController (SupplierService supplierService)
    {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val suppliers = supplierService.findAll()
                    .stream()
                    .map(SupplierResponse::from)
                    .toList();
            return ResponseEntity.ok(suppliers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SupplierRequest aRequest) {
        try {
            Supplier supplier = supplierService.add(aRequest.toSupplier());
            return ResponseEntity.ok(SupplierResponse.from(supplier));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody SupplierRequest aRequest) {
        try {
            Supplier supplier = supplierService.update(id, aRequest.toSupplier());
            return ResponseEntity.accepted().body(SupplierResponse.from(supplier));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            Supplier supplier = supplierService.delete(id);
            return ResponseEntity.accepted().body(SupplierResponse.from(supplier));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            Supplier supplier = supplierService.findById(id);
            return ResponseEntity.ok(SupplierResponse.from(supplier));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
