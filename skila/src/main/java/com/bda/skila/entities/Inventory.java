package com.bda.skila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private long inventoryId;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @ManyToOne
    @JoinColumn(name="film_id", nullable=false)
    private Film film;

    @Column(name = "last_update")
    private Date lastUpdate;
}
