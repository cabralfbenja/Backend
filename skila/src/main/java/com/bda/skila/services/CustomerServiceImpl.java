package com.bda.skila.services;

import com.bda.skila.entities.Customer;
import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.CustomerDto;
import com.bda.skila.repositories.CustomerRepository;
import com.bda.skila.repositories.StoreRepository;
import com.bda.skila.services.mappers.CustomerDtoMapper;
import com.bda.skila.services.mappers.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final StoreRepository storeRepository;
    private final CustomerDtoMapper dtoMapper;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository repository,
                               StoreRepository storeRepository, CustomerDtoMapper dtoMapper,
                               CustomerMapper mapper){
        this.repository = repository;
        this.storeRepository = storeRepository;
        this.dtoMapper = dtoMapper;
        this.mapper = mapper;
    }

    @Override
    public CustomerDto add(CustomerDto entity) {
        Optional<Customer> customer =  Stream.of(entity).map(mapper).findFirst();

        if(storeRepository.findFirstByAddress_CityId(entity.getAddress().getCityId()) != null){
            customer.ifPresent(repository::save);
            return customer.map(dtoMapper).orElseThrow();
        }
        return null;

    }

    @Override
    public CustomerDto update(CustomerDto entity) {
        Optional<Customer> customer =  Stream.of(entity).map(mapper).findFirst();
        customer.ifPresent(repository::save);
        return customer.map(dtoMapper).orElseThrow();
    }

    @Override
    public CustomerDto delete(Long id) {
        CustomerDto customer = this.getById(id);
        if(customer != null){
            Optional<Customer> entity =  Stream.of(customer).map(mapper).findFirst();
            entity.ifPresent(repository::delete);
        }

        return customer;
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> customer = this.repository.findById(id);
        return customer.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = this.repository.findAll();
        return customers
                .stream()
                .map(dtoMapper)
                .toList();
    }
}
