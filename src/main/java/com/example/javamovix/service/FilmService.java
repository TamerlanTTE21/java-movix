package com.example.javamovix.service;
import com.example.javamovix.Interface.FilmStorage;
import com.example.javamovix.exception.ValidationException;
import com.example.javamovix.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class FilmService {
    private final FilmStorage filmStorage;

    public Collection<Film> findAllFilms() {
        return filmStorage.findAll();
    }

    public Film createFilm(Film film) {
        validateFilm(film);
        return filmStorage.create(film);
    }

    public Film updateFilm(Film film) {
        validateFilm(film);
        if (film.getId() == 0) {
            throw new ValidationException("Film id is required");
        }

        if (!filmStorage.existsById(film.getId())) {
            throw new ValidationException("Film not found");
        }
        return filmStorage.update(film);
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException("Film name cannot be empty");
        }

        if (film.getDescription() == null || film.getDescription().length() > 200) {
            throw new ValidationException("Film description cannot be longer than 200 characters");
        }

        if (film.getReleaseDate() == null || film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Film release date cannot be in the past");
        }

        if (film.getDuration() <= 0) {
            throw new ValidationException("Film duration must be positive");
        }
    }
}
