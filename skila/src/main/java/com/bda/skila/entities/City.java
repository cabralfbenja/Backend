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
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private long id;

    @Basic
    private String city;
    @Column(name = "country_id")
    private long country_id;
    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
