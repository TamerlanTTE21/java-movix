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
    public void addLike(@PathVariable Integer id,
                        @PathVariable Integer likeId) {
         filmService.addLike(id, likeId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike (@PathVariable Integer id,
                            @PathVariable Integer likeId) {
        filmService.removeLike(id, likeId);
    }
}


