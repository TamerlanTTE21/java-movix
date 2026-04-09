package com.example.javamovix.controller;

import com.example.javamovix.exception.ValidationException;
import com.example.javamovix.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    final Collection<Film> films = new ArrayList<>();
    private int id = 1;

    @GetMapping
    public Collection<Film> findAllFilms() {
        return films;
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        validateFilm(film);
        film.setId(id++);
        films.add(film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        validateFilm(film);
        films.add(film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException("Film name cannot be empty");
        }

        if (film.getDescription().length() > 200) {
            throw new ValidationException("Film description cannot be longer than 200 characters");
        }

        if (film.getReleaseDate() == null || film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Film release date cannot be in the past");
        }

        if (film.getDuration() <= 0) {
            throw new ValidationException("Film duration cannot be negative");
        }
    }
}


