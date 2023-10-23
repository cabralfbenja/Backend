package com.bda.skila.entities;

import com.bda.skila.entities.dtos.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Basic
    private String email;

    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    private Address address;

    @Basic
    private boolean active;

    @Column(name = "create_date")
    private Date createdDate;

    @Column(name = "last_update")
    private Date lastUpdate;

}
