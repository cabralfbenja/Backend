package com.oveja.poc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private long addressId;

    private String address;

    private String district;

    @Column(name = "city_id")
    private long cityId;

    @Column(name = "postal_code")
    private String postalCode;

}
