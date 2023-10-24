package com.bda.skila.services.mappers;

import com.bda.skila.entities.Address;
import com.bda.skila.entities.Customer;
import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.AddressDto;
import com.bda.skila.entities.dtos.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
@Service
public class CustomerMapper implements Function<CustomerDto, Customer> {
    private AddressMapper entityMapper;


    @Autowired
    public CustomerMapper(AddressMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    @Override
    public Customer apply(CustomerDto customerDto){
        Customer customer = new Customer();

        if (customerDto.getAddress() != null) {
            Optional<Address> addressOptional = Stream.of(customerDto.getAddress()).map(entityMapper).findFirst();
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                customer.setAddress(address);
            }
        }

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setCreatedDate(new Date());
        customer.setLastUpdate(new Date());



        return customer;
    }
}


