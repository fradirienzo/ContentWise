package com.contentwise.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(RatingId.class)
public class Rating {

    @Id
    private Long userId;

    @Id
    private Long movieId;

    private int rating;
    private double viewPercentage;

    public Rating() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getViewPercentage() {
        return viewPercentage;
    }

    public void setViewPercentage(double viewPercentage) {
        this.viewPercentage = viewPercentage;
    }
}
