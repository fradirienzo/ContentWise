package com.contentwise.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.List;

@Entity
public class Movie implements Serializable {

    @Id
    Long id;

    String title;

    List<String> genres;

    public Movie() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }
}
