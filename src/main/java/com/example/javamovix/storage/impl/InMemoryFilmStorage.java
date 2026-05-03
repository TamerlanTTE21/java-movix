package com.example.javamovix.storage.impl;
import com.example.javamovix.model.Film;
import com.example.javamovix.storage.FilmStorage;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    final Map<Integer, Film> films = new HashMap<>();
    private int id = 1;

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    @Override
    public Film create(Film film) {
        film.setId(id++);
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public boolean existsById(Integer id) {
        return films.containsKey(id);
    }

    @Override
    public Film getById(Integer id) {
        return films.get(id);
    }

}
