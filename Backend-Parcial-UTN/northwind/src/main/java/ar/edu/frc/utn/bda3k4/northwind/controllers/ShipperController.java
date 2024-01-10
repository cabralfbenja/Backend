package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.Shipper;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.ShipperRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.ShipperResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.ShipperService;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippers")
public class ShipperController {
    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val shippers = shipperService.findAll()
                    .stream()
                    .map(ShipperResponse::from)
                    .toList();
            return ResponseEntity.ok(shippers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ShipperRequest aRequest) {
        try {
            Shipper shipper = shipperService.add(aRequest.toShipper());
            return ResponseEntity.status(HttpStatus.CREATED).body(ShipperResponse.from(shipper));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ShipperRequest aRequest) {
        try {
            Shipper shipper = shipperService.update(id, aRequest.toShipper());
            return ResponseEntity.accepted().body(ShipperResponse.from(shipper));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            Shipper shipper = shipperService.delete(id);
            return ResponseEntity.accepted().body(ShipperResponse.from(shipper));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            Shipper shipper = shipperService.findById(id);
            return ResponseEntity.ok(ShipperResponse.from(shipper));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
