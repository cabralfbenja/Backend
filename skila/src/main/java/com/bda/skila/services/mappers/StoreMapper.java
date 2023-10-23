package com.bda.skila.services.mappers;

import com.bda.skila.entities.Store;
import com.bda.skila.entities.dtos.StoreDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
@Service
public class StoreMapper implements Function<StoreDto, Store> {
    @Override
    public Store apply(StoreDto storeDto) {
        return new Store(
                storeDto.getStoreId(),
                storeDto.getManagerStaffId(),
                storeDto.getAddress(),
                new Date(),
                new ArrayList<>(),
                new ArrayList<>()
                );
    }

}
