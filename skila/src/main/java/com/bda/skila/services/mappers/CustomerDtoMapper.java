package com.bda.skila.services.mappers;

import com.bda.skila.entities.Customer;
import com.bda.skila.entities.dtos.AddressDto;
import com.bda.skila.entities.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer customer){
        CustomerDto customerDto = new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                null
        );

        if (customer.getAddress() != null) {
            customerDto.setAddress(new AddressDto(
                    customer.getAddress().getAddress(),
                    customer.getAddress().getCityId(),
                    customer.getAddress().getPostalCode()
            ));
        }
        return customerDto;
    }
}

