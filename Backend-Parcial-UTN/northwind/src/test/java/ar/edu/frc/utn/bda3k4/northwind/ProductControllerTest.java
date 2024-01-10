package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.ProductController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Category;
import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import ar.edu.frc.utn.bda3k4.northwind.entities.Supplier;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.ProductRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.CategoryRepository;
import ar.edu.frc.utn.bda3k4.northwind.repositories.ProductRepository;
import ar.edu.frc.utn.bda3k4.northwind.repositories.SupplierRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.CategoryServiceImpl;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.ProductServiceImpl;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.SupplierServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

public class ProductControllerTest {
    private ProductController productController;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;

    private final Category CATEGORY = new Category(1, "Nada", "Nada", null, null);
    private final Supplier SUPPLIER = new Supplier(
            1,
            "Exotic Liquids",
            "Charlotte Cooper",
            "Purchasing Manager",
            "49 Gilbert St.",
            "London",
            null,
            "EC1 4SD",
            "UK",
            "(171) 555-2222",
            null,
            null
    );
    private final Product PRODUCT = new Product(1, "Nada", "Description", 0.0, 0, 0, 0, false, SUPPLIER, CATEGORY, null);

    private final ProductRequest PRODUCT_REQUEST = new ProductRequest(
            "Nada", "", 0.0, 0, 0, 0,
            false, 1, 1);
    @BeforeEach
    public void setup(){
        productRepository = Mockito.mock(ProductRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        supplierRepository = Mockito.mock(SupplierRepository.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        SupplierServiceImpl supplierService = new SupplierServiceImpl(supplierRepository);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService, supplierService);
        productController = new ProductController(productService, categoryService, supplierService);
    }

    @Test
    void testFindAll(){
        List<Product> productList = new ArrayList<>();
        productList.add(PRODUCT);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(CATEGORY);
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(SUPPLIER);
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        Mockito.when(supplierRepository.findAll()).thenReturn(supplierList);
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        Assertions.assertEquals(
                HttpStatus.OK,
                productController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty(){
        Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                productController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(PRODUCT));
        Assertions.assertEquals(
                HttpStatus.OK,
                productController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                productController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testAdd(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Mockito.when(supplierRepository.findById(1)).thenReturn(Optional.of(SUPPLIER));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(PRODUCT);
        Assertions.assertEquals(
                HttpStatus.OK,
                productController.add(PRODUCT_REQUEST).getStatusCode()
        );
    }

    @Test
    void testUpdate(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(PRODUCT));
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Mockito.when(supplierRepository.findById(1)).thenReturn(Optional.of(SUPPLIER));
        Mockito.when(productRepository.save(PRODUCT)).thenReturn(PRODUCT);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                productController.update(1, PRODUCT_REQUEST).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                productController.update(1, PRODUCT_REQUEST).getStatusCode()
        );
    }

    @Test
    void testDelete(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(PRODUCT));
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                productController.delete(1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                productController.delete(1).getStatusCode()
        );
    }
    @Test
    void testFindByCategoryAndSupplierAndStockmin(){
        List<Product> productList = new ArrayList<>();
        productList.add(PRODUCT);
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Mockito.when(supplierRepository.findById(1)).thenReturn(Optional.of(SUPPLIER));
        Mockito.when(productRepository.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10)).thenReturn(productList);
        Assertions.assertEquals(
                HttpStatus.OK,
                productController.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10).getStatusCode()
        );
    }

    @Test
    void testFindByCategoryAndSupplierAndStockminNotFoundCategory(){
        List<Product> productList = new ArrayList<>();
        productList.add(PRODUCT);
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(supplierRepository.findById(1)).thenReturn(Optional.of(SUPPLIER));
        Mockito.when(productRepository.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10)).thenReturn(productList);
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                productController.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10).getStatusCode()
        );
    }
    @Test
    void testFindByCategoryAndSupplierAndStockminNotFoundSupplier(){
        List<Product> productList = new ArrayList<>();
        productList.add(PRODUCT);
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Mockito.when(supplierRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.when(productRepository.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10)).thenReturn(productList);
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                productController.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10).getStatusCode()
        );
    }
    @Test
    void testFindByCategoryAndSupplierAndStockminEmptyList(){
        List<Product> productList = new ArrayList<>();
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(CATEGORY));
        Mockito.when(supplierRepository.findById(1)).thenReturn(Optional.of(SUPPLIER));
        Mockito.when(productRepository.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10)).thenReturn(productList);
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                productController.findProductsByCategoryAndSupplierAndSafetyStock(1, 1, 10).getStatusCode()
        );
    }



}