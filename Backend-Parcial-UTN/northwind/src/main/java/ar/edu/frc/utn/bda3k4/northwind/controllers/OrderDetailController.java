package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.OrderDetail;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.create.OrderDetailCreateRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.update.OrderDetailUpdateRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.OrderDetailResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.OrderDetailService;
import ar.edu.frc.utn.bda3k4.northwind.support.OrderDetailPK;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val details = orderDetailService.findAll()
                    .stream()
                    .map(OrderDetailResponse::from)
                    .toList();
            return ResponseEntity.ok(details);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody OrderDetailCreateRequest aRequest) {
        try {
            OrderDetail detail = orderDetailService.add(aRequest.toOrderDetail());
            return ResponseEntity.ok(OrderDetailResponse.from(detail));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{orderId}/")
    public ResponseEntity<Object> update(@PathVariable Integer orderId, @RequestParam Integer productId,
                                         @RequestBody OrderDetailUpdateRequest aRequest) {
        try {
            OrderDetail detail = orderDetailService
                    .update(new OrderDetailPK(orderId, productId), aRequest.toOrderDetail());
            return ResponseEntity.ok().body(OrderDetailResponse.from(detail));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{orderId}/")
    public ResponseEntity<Object> findOne(@PathVariable Integer orderId, @RequestParam Integer productId) {
        try {
            OrderDetail detail = orderDetailService.findById(new OrderDetailPK(orderId, productId));
            return ResponseEntity.ok(OrderDetailResponse.from(detail));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{orderId}/")
    public ResponseEntity<Object> delete(@PathVariable Integer orderId, @RequestParam Integer productId) {
        try {
            OrderDetail detail = orderDetailService.delete(new OrderDetailPK(orderId, productId));
            return ResponseEntity.ok(OrderDetailResponse.from(detail));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
