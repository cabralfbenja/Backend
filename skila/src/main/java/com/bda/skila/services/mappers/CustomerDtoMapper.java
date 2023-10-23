package com.bda.skila.services.mappers;

import com.bda.skila.entities.Customer;
import com.bda.skila.entities.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer customer){
        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress().getAddress(),
                customer.getAddress().getCityId(),
                customer.getAddress().getPostalCode()
        );
    }
}
