package ar.edu.frc.utn.bda3k4.northwind.services.implementations;

import ar.edu.frc.utn.bda3k4.northwind.entities.Supplier;
import ar.edu.frc.utn.bda3k4.northwind.repositories.SupplierRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    public SupplierServiceImpl(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }
    @Override
    public Supplier add(Supplier entity) {
        this.supplierRepository.save(entity);
        return entity;
    }

    @Override
    public Supplier update(Integer id, Supplier entity) {
        Supplier existingSupplier = this.supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier does not exist"));
        existingSupplier.update(entity);
        return this.supplierRepository.save(existingSupplier);
    }

    @Override
    public Supplier delete(Integer id) {
        Supplier supplier = this.supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier does not exist"));
        this.supplierRepository.delete(supplier);
        return supplier;

    }

    @Override
    public Supplier findById(Integer id) {
        return this.supplierRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Supplier does not exist"));
    }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        if(suppliers.isEmpty()){throw new IllegalArgumentException("No suppliers found");}
        return suppliers;
    }
}
