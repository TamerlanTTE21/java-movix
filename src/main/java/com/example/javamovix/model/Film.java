package com.example.javamovix.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Film {
    private int id;
    private String name;
    private String description;
    private Date releaseDate;
    private String duration;

    public Film(int id, String name, String description, Date releaseDate, String duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
