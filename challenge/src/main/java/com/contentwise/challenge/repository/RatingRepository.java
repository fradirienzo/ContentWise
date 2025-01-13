package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.InteractionId;
import com.contentwise.challenge.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RatingRepository extends JpaRepository<Rating, InteractionId> {

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.interactionId.movieId = :movieId")
    public Double computeAvgRating(@Param("movieId")Long movieId);

    @Query("SELECT r FROM Rating r WHERE r.interactionId.userId = :userId")
    public List<Rating> retrieveUserRatings(@Param("userId") int userId);

    @Query("SELECT FROM Rating r WHERE r.interactionId.userId =:userId AND r.rating >= 4")
    public List<Long> getLikedMoviesIds(@Param("userId") Long userId);

}
