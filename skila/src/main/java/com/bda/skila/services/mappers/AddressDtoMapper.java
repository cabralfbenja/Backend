package com.bda.skila.services.mappers;

import com.bda.skila.entities.Address;
import com.bda.skila.entities.dtos.AddressDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddressDtoMapper implements Function<Address, AddressDto> {
    @Override
    public AddressDto apply(Address address){
        return new AddressDto(
                address.getAddress(),
                address.getCityId(),
                address.getPostalCode()
        );
    }
}
