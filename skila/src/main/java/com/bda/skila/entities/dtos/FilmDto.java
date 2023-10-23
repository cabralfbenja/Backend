package com.bda.skila.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {
    private long filmId;
    private String title;
    private String description;
    private Date releaseYear;
    private short languageId;
    private short originalLanguageId;
    private short rentalDuration;
    private double rentalRate;
    private short length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;

}
