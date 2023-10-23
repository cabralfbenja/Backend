package com.bda.skila.services.mappers;

import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.StoreDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
@Service
public class StoreDtoMapper implements Function<Store, StoreDto> {
    @Override
    public StoreDto apply(Store store) {
        return new StoreDto(
                store.getStoreId(),
                store.getManagerStaffId(),
                store.getAddress()
        );
    }
}
