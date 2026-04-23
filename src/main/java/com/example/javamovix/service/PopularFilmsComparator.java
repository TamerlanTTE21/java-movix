package com.example.javamovix.service;

import com.example.javamovix.model.Film;

import java.util.Comparator;

public class PopularFilmsComparator implements Comparator<Film> {
    @Override
    public int compare(Film film1, Film film2) {
        return film1.getLikes().size() - film2.getLikes().size();
    }
}
