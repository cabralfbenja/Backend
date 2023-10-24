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
        Address address = new Address();
        address.setAddress(addressDto.getAddress());
        address.setCityId(addressDto.getCityId());
        address.setPostalCode(address.getPostalCode());
        address.setAddress2("");
        address.setDistrict("");
        address.setPhone("");
        address.setLastUpdate(new Date());

        return address;
    }
}
