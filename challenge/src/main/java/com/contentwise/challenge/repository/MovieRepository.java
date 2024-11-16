package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie " +
            "WHERE (:genre IS NULL OR FIND_IN_SET(:genre, genres) > 0) " +
            "AND (:maxRating IS NULL OR avg_rating <= :maxRating) " +
            "AND (:minRating IS NULL OR avg_rating >= :minRating)",
            nativeQuery = true)
    public List<Movie> getMoviesWithParams(@Param("genre") String genre,
                                           @Param("maxRating") Double maxRating,
                                           @Param("minRating") Double minRating);
}
