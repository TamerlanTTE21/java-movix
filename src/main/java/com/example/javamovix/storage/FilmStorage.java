package com.example.javamovix.storage;
import com.example.javamovix.model.Film;
import java.util.Collection;

public interface FilmStorage {
    Collection<Film> findAll();

    Film create(Film film);

    Film update(Film film);

    boolean existsById(Integer id);

    Film getById(Integer id);
}
