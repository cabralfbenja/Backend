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
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private long filmId;

    @Basic
    private String title;

    @Basic
    private String description;

    @Column(name = "release_year")
    private String releaseYear;

    @Column(name = "language_id")
    private short languageId;

    @Column(name = "original_language_id")
    private Long originalLanguageId;


    @Column(name = "rental_duration")
    private short rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Basic
    private short length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Basic
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Inventory> inventories;

}
