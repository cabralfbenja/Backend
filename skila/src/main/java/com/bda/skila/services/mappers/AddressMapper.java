package com.bda.skila.services.mappers;

import com.bda.skila.entities.Address;
import com.bda.skila.entities.Customer;
import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.AddressDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

@Service
public class AddressMapper implements Function<AddressDto, Address> {
    @Override
    public Address apply(AddressDto addressDto){
        return new Address(
                addressDto.getAddressId(),
                addressDto.getAddress(),
                addressDto.getAddress2(),
                addressDto.getDistrict(),
                addressDto.getCityId(),
                addressDto.getPostalCode(),
                addressDto.getPhone(),
                new Date(),
                new ArrayList<Customer>(),
                new ArrayList<Store>()
        );
    }
}
