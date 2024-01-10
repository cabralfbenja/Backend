package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.CategoryRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.CategoryResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.CategoryService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/category")
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val categories = categoryService.findAll()
                    .stream()
                    .map(CategoryResponse::from)
                    .toList();
            return ResponseEntity.ok(categories);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CategoryRequest aRequest) {
        try {
            val category = categoryService.add(aRequest.toCategory());
            return ResponseEntity.ok(CategoryResponse.from(category));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody CategoryRequest aRequest) {
        try {
            val category = categoryService.update(id, aRequest.toCategory());
            return ResponseEntity.accepted().body(CategoryResponse.from(category));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            Category category = categoryService.delete(id);
            return ResponseEntity.ok().body(CategoryResponse.from(category));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            val category = categoryService.findById(id);
            return ResponseEntity.ok(CategoryResponse.from(category));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
