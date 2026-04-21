package com.example.javamovix.Interface;

import com.example.javamovix.model.Film;

import java.util.Collection;

public interface FilmStorage {
    Collection<Film> findAll();

    Film create(Film film);

    Film update(Film film);

    void delete(Integer id);

    boolean existsById(Integer id);
}
