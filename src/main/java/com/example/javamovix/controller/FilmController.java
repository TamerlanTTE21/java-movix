package com.example.javamovix.controller;

import com.example.javamovix.model.Film;
import com.example.javamovix.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public Collection<Film> findAllFilms() {
        return filmService.findAllFilms();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }


    @PutMapping("{filmId}/like/{userId}")
    public void filmsAddLike(@PathVariable Integer userId,
                             @PathVariable Integer filmId) {
        filmService.filmsAddLike(userId, filmId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public void filmsRemoveLike(@PathVariable Integer filmId,
                           @PathVariable Integer userId) {
        filmService.filmsRemoveLike(userId, filmId);
    }

    @GetMapping("/popular")
    public Collection<Film>  getPopularFilms(@RequestParam(required = false) Integer count) {
    return filmService.getPopularFilms(count);
    }
}



