package com.bda.skila.entities.dtos;

import com.bda.skila.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private long storeId;
    private short managerStaffId;
    private Address address;
}
