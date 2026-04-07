package com.example.javamovix.controller;

import com.example.javamovix.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping
    public Map<Integer, Film> findAllFilms() {
        return films;
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {

        films.put(film.getId(), film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        films.put(film.getId(), film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            throw new IllegalArgumentException("Film name cannot be empty");
        }

        if (film.getDescription() == null ||film.getDescription().length() > 200) {
            throw new IllegalArgumentException("Film description cannot be longer than 200 characters");
        }

        if (film.getReleaseDate() == null || film.getReleaseDate().isBefore(LocalDate.of(1985, 12, 28))) {

        }

        if (film.getDuration() <= 0) {
            throw new IllegalArgumentException("Film duration cannot be negative");
        }
    }
}


