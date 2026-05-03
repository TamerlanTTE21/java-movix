package com.example.javamovix.service;

import com.example.javamovix.storage.FilmStorage;
import com.example.javamovix.exception.NotFoundException;
import com.example.javamovix.exception.ValidationException;
import com.example.javamovix.model.Film;
import com.example.javamovix.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserService userService;
    private final UserStorage userStorage;

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
            throw new NotFoundException("Film not found");
        }
        return filmStorage.update(film);
    }

    public void filmsAddLike(Integer userId, Integer filmId) {
        if (userId == null || filmId == null) {
            throw new ValidationException("userId and filmId is null");
        }

        Film film = filmStorage.getById(filmId);

        if (film == null) {
            throw new ValidationException("Film not found");
        }

        if (!userStorage.existsById(userId)) {
            throw new ValidationException("User not found");
        }

        film.addLike(userId);
    }

    public void userAddLike(Integer userId, Integer likedId) {
        if (userId == null || likedId == null) {
            throw new ValidationException("userId and likeId is null");
        }

        if (userId.equals(likedId)) {
            throw new ValidationException("can't add yourself");
        }

        if (!filmStorage.existsById(userId)) {
            throw new NotFoundException("userId not found");
        }
        if (!filmStorage.existsById(likedId)) {
            throw new NotFoundException("friendId not found");
        }
        Film user = filmStorage.getById(userId);
        Film friend = filmStorage.getById(likedId);

        user.getLikes().add(likedId);
        friend.getLikes().add(userId);
    }

    public void userRemoveLike(Integer userId, Integer likeId) {
        if (userId == null || likeId == null) {
            throw new ValidationException("userId and likeId is null");
        }

        if (userId.equals(likeId)) {
            throw new ValidationException("can't remove yourself");
        }

        if (!filmStorage.existsById(userId)) {
            throw new NotFoundException("userId not found");
        }
        if (!filmStorage.existsById(likeId)) {
            throw new NotFoundException("friendId not found");
        }
        Film user = filmStorage.getById(userId);
        Film friend = filmStorage.getById(likeId);

        user.getLikes().remove(likeId);
        friend.getLikes().remove(userId);
    }

    public Collection<Film> getPopularFilms(Integer count) {

        if (count == null) {
            count = 10;
        }
        return findAllFilms().stream()
                .sorted((film1, film2) -> film1.getLikes().size() - film1.getLikes().size())
                .limit(count)
                .toList();
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
