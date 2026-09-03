package com.example.javamovix.model;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Film {
    private Integer id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private Integer mpaRating;
    private final Set<Genre> genres = new HashSet<>();
    private final Set<Integer> likes = new HashSet<>();


    public Film(Integer id, String name, String description, LocalDate releaseDate, Integer duration, Integer mpaRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpaRating = mpaRating;
    }

    public void addLike(Integer userId) {
        likes.add(userId);
    }

    public void removeLike(Integer userId) {
        likes.remove(userId);
    }
}
