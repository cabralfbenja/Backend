package com.bda.skila.services;

import com.bda.skila.entities.Address;
import com.bda.skila.entities.dtos.AddressDto;
import com.bda.skila.repositories.AddressRepository;
import com.bda.skila.services.mappers.AddressDtoMapper;
import com.bda.skila.services.mappers.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final AddressDtoMapper dtoMapper;
    private final AddressMapper entityMapper;

    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressDtoMapper dtoMapper,
                              AddressMapper entityMapper){
        this.addressRepository = addressRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public AddressDto add(AddressDto entity) {
        Optional<Address> company = Stream.of(entity).map(entityMapper).findFirst();
        company.ifPresent(addressRepository::save);
        return company.map(dtoMapper).orElseThrow();
    }

    @Override
    public AddressDto update(AddressDto entity) {
        Optional<Address> company = Stream.of(entity).map(entityMapper).findFirst();
        company.ifPresent(addressRepository::save);
        return company.map(dtoMapper).orElseThrow();
    }

    @Override
    public AddressDto delete(Long id) {
        AddressDto address = this.getById(id);
        if(address!= null){
            Optional<Address> entity = Stream.of(address).map(entityMapper).findFirst();
            entity.ifPresent(addressRepository::delete);

        }
        return address;
    }

    @Override
    public AddressDto getById(Long id) {
        Optional<Address> address = this.addressRepository.findById(id);
        return address.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<AddressDto> getAll() {
        List<Address> companies = this.addressRepository.findAll();
        return companies
                .stream()
                .map(dtoMapper)
                .toList();
    }
}

