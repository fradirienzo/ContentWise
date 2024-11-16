package com.contentwise.challenge.entity;

import jakarta.persistence.Entity;

@Entity
public class View extends Interaction{
    private Double percentage;

    public View() {
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
