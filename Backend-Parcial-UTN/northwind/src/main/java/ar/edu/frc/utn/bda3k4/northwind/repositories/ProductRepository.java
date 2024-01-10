package ar.edu.frc.utn.bda3k4.northwind.repositories;

import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 AND p.supplier.id = ?2 AND (p.unitsInStock+p.unitsOnOrder)< ?3 AND p.discontinued != true ORDER BY (p.unitsInStock+p.unitsOnOrder)")
    List<Product> findProductsByCategoryAndSupplierAndSafetyStock(Integer categoryId, Integer supplierId, Integer stockMin);
}
