package com.bda.skila.controllers;


import com.bda.skila.entities.dtos.FilmDto;
import com.bda.skila.services.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {
    private FilmService service;

    public FilmController(FilmService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAll(){
        List<FilmDto> values = this.service.getAll();
        return ResponseEntity.ok(values);
    }
    @PostMapping
    public ResponseEntity<FilmDto> add(@RequestBody FilmDto film){
        FilmDto filmDto = this.service.add(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmDto);
    }

}
