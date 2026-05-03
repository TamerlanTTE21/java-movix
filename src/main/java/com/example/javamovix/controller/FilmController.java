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

    @PutMapping("/{id}/like/{userId}")
    public void userAddLike(@PathVariable Integer id,
                        @PathVariable Integer userId) {
        filmService.userAddLike(id, userId);
    }

    @PutMapping("{id}/like/{filmId}")
    public void filmsAddLike(@PathVariable Integer userId,
                             @PathVariable Integer filmId) {
        filmService.filmsAddLike(userId, filmId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void userRemoveLike(@PathVariable Integer id,
                           @PathVariable Integer userId) {
        filmService.userRemoveLike(id, userId);
    }

    @GetMapping("/popular")
    public Collection<Film>  getPopularFilms(@RequestParam(required = false) Integer count) {
    return filmService.getPopularFilms(count);
    }
}



