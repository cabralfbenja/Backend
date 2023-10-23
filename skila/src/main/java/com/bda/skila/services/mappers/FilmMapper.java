package com.bda.skila.services.mappers;

import com.bda.skila.entities.Film;
import com.bda.skila.entities.dtos.FilmDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
@Service
public class FilmMapper implements Function<FilmDto, Film> {
    @Override
    public Film apply(FilmDto filmDto) {
        return new Film(
                filmDto.getFilmId(),
                filmDto.getTitle(),
                filmDto.getDescription(),
                filmDto.getReleaseYear(),
                filmDto.getLanguageId(),
                filmDto.getOriginalLanguageId(),
                filmDto.getRentalDuration(),
                filmDto.getRentalRate(),
                filmDto.getLength(),
                filmDto.getReplacementCost(),
                filmDto.getRating(),
                filmDto.getSpecialFeatures(),
                new Date(),
                new ArrayList<>());
    }

}
