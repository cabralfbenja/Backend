package com.bda.skila.services.mappers;

import com.bda.skila.entities.Film;
import com.bda.skila.entities.dtos.FilmDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class FilmDtoMapper implements Function<Film, FilmDto> {

    @Override
    public FilmDto apply(Film film) {
        return new FilmDto(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguageId(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures());
    }
}














