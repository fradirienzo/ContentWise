package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie m " +
            "WHERE (:genre IS NULL OR :genre MEMBER OF m.genres) " +
            "AND (:maxRating IS NULL OR m.avg_rating <= :maxRating) " +
            "AND (:minRating IS NULL OR m.avg_rating >= :minRating)",
            nativeQuery = true)
    public List<Movie> getMoviesWithParams(@Param("genre") String genre,
                                           @Param("maxRating") Double maxRating,
                                           @Param("minRating") Double minRating);
}
