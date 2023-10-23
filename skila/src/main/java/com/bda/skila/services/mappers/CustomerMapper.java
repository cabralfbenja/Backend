package com.bda.skila.services.mappers;

import com.bda.skila.entities.Address;
import com.bda.skila.entities.Customer;
import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerMapper implements Function<CustomerDto, Customer> {
    @Override
    public Customer apply(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        return getCustomer(customerDto);
    }

    private static Customer getCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());

        Address address = new Address();
        address.setAddress(customerDto.getAddress());
        address.setCityId(customerDto.getCityId());
        address.setPostalCode(customerDto.getPostalCode());
        customer.setAddress(address);

        Store store = new Store();
        customer.setStore(store);
        return customer;
    }
}
