package com.example.javamovix.controller;

import com.example.javamovix.model.Film;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmController {
    @RestController
    @RequestMapping("/films")
    public class FilmControllerResource {
        private final Map<Integer, Film> films = new HashMap<>();

        @GetMapping
        public Map<Integer, Film> findAll() {
            return films;
        }

        @PostMapping
        public Film create(@RequestBody Film film) {
            films.put(film.getId(), film);
            return film;
        }

        @PutMapping
        public Film update(@RequestBody Film film) {
            films.put(film.getId(), film);
            return film;
        }
    }


}
