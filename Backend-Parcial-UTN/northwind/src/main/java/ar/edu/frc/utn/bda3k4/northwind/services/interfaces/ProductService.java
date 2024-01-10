package ar.edu.frc.utn.bda3k4.northwind.services.interfaces;

import ar.edu.frc.utn.bda3k4.northwind.entities.Product;

import java.util.List;

public interface ProductService extends Service<Product, Integer>{
    List<Product> findProductsByCategoryAndSupplierAndSafetyStock(Integer categoryId, Integer supplierId, Integer stockMin);
}
