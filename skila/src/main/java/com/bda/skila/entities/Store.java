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
@Table(name = "store_tmp")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "store_tmp")
    @TableGenerator(name = "store_tmp", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="store_tmp",
            initialValue=1, allocationSize=1)
    private long storeId;

    @Column(name="manager_staff_id")
    private short managerStaffId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id", nullable=false)
    private Address address;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Inventory> inventories;


}
