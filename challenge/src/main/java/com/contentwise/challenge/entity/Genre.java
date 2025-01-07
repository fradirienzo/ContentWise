package com.contentwise.challenge.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Genre implements Serializable {


    @Id
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    public Genre() {
    }


    public String getName() {
        return name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
