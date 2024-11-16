package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.InteractionId;
import com.contentwise.challenge.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, InteractionId> {

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.movieId = :movieId")
    public Double computeAvgRating(@Param("movieId")Long movieId);

}
