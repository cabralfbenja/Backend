package com.bda.skila.entities.dtos;

import com.bda.skila.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long cityId;
    private String postalCode;

}

