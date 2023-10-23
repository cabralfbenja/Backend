package com.bda.skila.services;

import com.bda.skila.entities.Customer;
import com.bda.skila.entities.Film;
import com.bda.skila.entities.dtos.CustomerDto;
import com.bda.skila.entities.dtos.FilmDto;
import com.bda.skila.repositories.CustomerRepository;
import com.bda.skila.repositories.FilmRepository;
import com.bda.skila.services.mappers.CustomerDtoMapper;
import com.bda.skila.services.mappers.CustomerMapper;
import com.bda.skila.services.mappers.FilmDtoMapper;
import com.bda.skila.services.mappers.FilmMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private final CustomerDtoMapper dtoMapper;
    private final CustomerMapper entityMapper;

    public CustomerServiceImpl(CustomerRepository repository,
                           CustomerDtoMapper dtoMapper,
                           CustomerMapper entityMapper){
        this.repository = repository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public CustomerDto add(CustomerDto entity) {
        Optional<Customer> customer = Stream.of(entity).map(entityMapper).findFirst();
        customer.ifPresent(repository::save);
        return customer.map(dtoMapper).orElseThrow();

    }

    @Override
    public CustomerDto update(CustomerDto entity) {
        Optional<Customer> customer = Stream.of(entity).map(entityMapper).findFirst();
        customer.ifPresent(repository::save);
        return customer.map(dtoMapper).orElseThrow();
    }

    @Override
    public CustomerDto delete(Long id) {
        CustomerDto customer = this.getById(id);
        if(customer!= null){
            Optional<Customer> entity = Stream.of(customer).map(entityMapper).findFirst();
            entity.ifPresent(repository::delete);

        }
        return customer;
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> film = this.repository.findById(id);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> films = this.repository.findAll();
        return films
                .stream()
                .map(dtoMapper)
                .toList();
    }
}
