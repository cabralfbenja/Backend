package com.bda.skila.services;

import com.bda.skila.entities.Film;
import com.bda.skila.entities.dtos.FilmDto;
import com.bda.skila.repositories.FilmRepository;
import com.bda.skila.services.mappers.FilmDtoMapper;
import com.bda.skila.services.mappers.FilmMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FilmServiceImpl implements FilmService{

    private final FilmRepository repository;
    private final FilmDtoMapper dtoMapper;
    private final FilmMapper entityMapper;

    public FilmServiceImpl(FilmRepository repository,
                              FilmDtoMapper dtoMapper,
                              FilmMapper entityMapper){
        this.repository = repository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }
    @Override
    public FilmDto add(FilmDto entity) {
        Optional<Film> film = Stream.of(entity).map(entityMapper).findFirst();
        film.ifPresent(repository::save);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public FilmDto update(FilmDto entity) {
        Optional<Film> film = Stream.of(entity).map(entityMapper).findFirst();
        film.ifPresent(repository::save);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public FilmDto delete(Long id) {
        FilmDto film = this.getById(id);
        if(film!= null){
            Optional<Film> entity = Stream.of(film).map(entityMapper).findFirst();
            entity.ifPresent(repository::delete);

        }
        return film;
    }

    @Override
    public FilmDto getById(Long id) {
        Optional<Film> film = this.repository.findById(id);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<FilmDto> getAll() {
        List<Film> films = this.repository.findAll();
        return films
                .stream()
                .map(dtoMapper)
                .toList();
    }
}
