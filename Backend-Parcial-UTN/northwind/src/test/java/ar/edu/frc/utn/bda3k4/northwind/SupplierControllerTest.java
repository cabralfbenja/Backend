package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.SupplierController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Supplier;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.SupplierRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.SupplierRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.SupplierServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class SupplierControllerTest {
    private SupplierController supplierController;
    private SupplierRepository supplierRepository;
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

    @BeforeEach
    public void setup() {
        supplierRepository = Mockito.mock(SupplierRepository.class);
        SupplierServiceImpl supplierService = new SupplierServiceImpl(supplierRepository);
        supplierController = new SupplierController(supplierService);
    }

    @Test
    void testFindAll() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(SUPPLIER);
        Mockito.when(supplierRepository.findAll()).thenReturn(suppliers);
        Assertions.assertEquals(
                HttpStatus.OK,
                supplierController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty() {
        Mockito.when(supplierRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                supplierController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById() {
        Mockito.when(supplierRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(SUPPLIER));
        Assertions.assertEquals(
                HttpStatus.OK,
                supplierController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound() {
        Mockito.when(supplierRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                supplierController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testAdd() {
        Mockito.when(supplierRepository.save(SUPPLIER)).thenReturn(SUPPLIER);
        Assertions.assertEquals(
                HttpStatus.OK,
                supplierController.add(new SupplierRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdate() {
        Mockito.when(supplierRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(SUPPLIER));
        Mockito.when(supplierRepository.save(SUPPLIER)).thenReturn(SUPPLIER);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                supplierController.update(1, new SupplierRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound() {
        Mockito.when(supplierRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                supplierController.update(1, new SupplierRequest()).getStatusCode()
        );
    }

    @Test
    void testDelete() {
        Mockito.when(supplierRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(SUPPLIER));
        Mockito.when(supplierRepository.save(SUPPLIER)).thenReturn(SUPPLIER);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                supplierController.delete(1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound() {
        Mockito.when(supplierRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                supplierController.delete(1).getStatusCode()
        );
    }
}