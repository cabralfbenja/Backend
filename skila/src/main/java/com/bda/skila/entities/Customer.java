package com.bda.skila.entities;

import com.bda.skila.entities.dtos.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer_dg_tmp")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "customer_dg_tmp")
    @TableGenerator(name = "customer_dg_tmp", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="customer_dg_tmp",
            initialValue=1, allocationSize=1)
    private long customerId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="store_id")
    private Store store;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Basic
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id")
    private Address address;

    @Basic
    private boolean active;

    @Column(name = "create_date")
    private Date createdDate;

    @Column(name = "last_update")
    private Date lastUpdate;

}
