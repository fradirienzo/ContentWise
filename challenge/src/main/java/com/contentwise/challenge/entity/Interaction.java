package com.contentwise.challenge.entity;

import jakarta.persistence.*;
import java.util.Objects;


@MappedSuperclass
public class Interaction {

    @EmbeddedId
    private InteractionId interactionId;

    public Interaction() {}

    public Interaction(Long userId, Long movieId) {
        this.interactionId = new InteractionId(userId, movieId);
    }

    public InteractionId getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(InteractionId interactionId) {
        this.interactionId = interactionId;
    }

    public Long getUserId() {
        return interactionId.getUserId();
    }

    public void setUserId(Long userId) {
        if (interactionId == null) interactionId = new InteractionId();
        this.interactionId.setUserId(userId);
    }

    public Long getMovieId() {
        return interactionId.getMovieId();
    }

    public void setMovieId(Long movieId) {
        if (interactionId == null) interactionId = new InteractionId();
        this.interactionId.setMovieId(movieId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interaction that = (Interaction) o;
        return Objects.equals(interactionId, that.interactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interactionId);
    }
}
