package com.contentwise.challenge.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Movie implements Serializable {

    @Id
    Long id;

    String title;

    @ManyToMany
    @JoinTable(
            name = "movie_genre", // Nome della tabella intermedia
            joinColumns = @JoinColumn(name = "movie_id"), // Colonna che collega a Movie
            inverseJoinColumns = @JoinColumn(name = "genre_id") // Colonna che collega a Genre
    )
    Set<Genre> genres;

    Double avgRating;

    public Movie() {
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
