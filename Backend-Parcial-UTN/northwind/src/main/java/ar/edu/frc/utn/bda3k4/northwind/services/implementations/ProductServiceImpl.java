package ar.edu.frc.utn.bda3k4.northwind.services.implementations;

import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import ar.edu.frc.utn.bda3k4.northwind.entities.Supplier;
import ar.edu.frc.utn.bda3k4.northwind.entities.exceptions.EmptyListException;
import ar.edu.frc.utn.bda3k4.northwind.repositories.ProductRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.CategoryService;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.ProductService;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, SupplierService supplierService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @Override
    public Product add(Product entity) {
        return this.productRepository.save(entity);
    }

    @Override
    public Product update(Integer id, Product entity) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));
        product.update(entity);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Product delete(Integer id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));
        this.productRepository.delete(product);
        return product;
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Product not found"));
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = this.productRepository.findAll();
        if(products.isEmpty()){throw new IllegalArgumentException("No products found");}
        return products;
    }

    @Override
    public List<Product> findProductsByCategoryAndSupplierAndSafetyStock(Integer categoryId, Integer supplierId, Integer stockMin) {
        Category category = this.categoryService.findById(categoryId);
        Supplier supplier = this.supplierService.findById(supplierId);
        List<Product> products = this.productRepository.findProductsByCategoryAndSupplierAndSafetyStock(categoryId, supplierId, stockMin);
        if(products.isEmpty()) throw new EmptyListException("No products which meet the conditions found");
        return products;
    }
}
