package com.bda.skila.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_dg_tmp")
public class Address {
    @Id
    @GeneratedValue(generator = "address_dg_tmp")
    @TableGenerator(name = "address_dg_tmp", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="address_dg_tmp",
            initialValue=1, allocationSize=1)
    private long addressId;

    @Basic
    private String address;

    @Basic
    private String address2;

    @Basic
    private String district;

    @Column(name = "city_id")
    private long cityId;

    @Column(name = "postal_code")
    private String postalCode;

    @Basic
    private String phone;

    @Column(name = "last_update")
    private Date lastUpdate;


    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Store> stores;

}
