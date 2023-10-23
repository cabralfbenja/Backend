package com.bda.skila.services;

import com.bda.skila.entities.Customer;
import com.bda.skila.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    public CustomerServiceImpl(CustomerRepository repository){
        this.repository = repository;
    }

    @Override
    public Customer add(Customer entity) {
        this.repository.save(entity);
        return entity;

    }

    @Override
    public Customer update(Customer entity) {
        this.repository.save(entity);
        return entity;
    }

    @Override
    public Customer delete(Long id) {
        Customer customer = this.getById(id);
        if(customer != null)
            this.repository.delete(customer);
        return customer;
    }

    @Override
    public Customer getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAll() {
        return this.repository.findAll();
    }
}
