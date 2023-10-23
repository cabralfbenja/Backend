package com.bda.skila.services;

import com.bda.skila.entities.Inventory;
import com.bda.skila.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository repository;
    public InventoryServiceImpl(InventoryRepository repository){
        this.repository = repository;
    }

    @Override
    public Inventory add(Inventory entity) {
        this.repository.save(entity);
        return entity;
    }

    @Override
    public Inventory update(Inventory entity) {
        this.repository.save(entity);
        return entity;
    }

    @Override
    public Inventory delete(Long id) {
        Inventory inventory = this.getById(id);
        if(inventory != null)
            this.repository.delete(inventory);
        return inventory;
    }

    @Override
    public Inventory getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<Inventory> getAll() {
        return this.repository.findAll();
    }
}
