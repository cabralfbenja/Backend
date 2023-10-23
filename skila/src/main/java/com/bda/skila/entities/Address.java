package com.bda.skila.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Basic
    private String address;

    @Basic
    private String address2;

    @Basic
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

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
