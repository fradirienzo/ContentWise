package com.contentwise.challenge.entity;

import jakarta.persistence.Entity;


@Entity
public class Rating extends Interaction{


    private Integer rating = null;

    public Rating() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
