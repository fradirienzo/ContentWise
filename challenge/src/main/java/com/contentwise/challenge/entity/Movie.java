package com.contentwise.challenge.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "movie")
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
    List<Genre> genres;

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
