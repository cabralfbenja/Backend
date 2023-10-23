package com.bda.skila.entities;

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
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private long storeId;

    @Column(name="manager_staff_id")
    private short managerStaffId;

    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    private Address address;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Customer> customers;
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Inventory> inventories;

}
