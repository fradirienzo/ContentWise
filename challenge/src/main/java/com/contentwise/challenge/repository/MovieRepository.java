package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m " +
            "JOIN m.genres g " +
            "WHERE (:genre IS NULL OR g.name = :genre) " +
            "AND (:maxRating IS NULL OR m.avgRating <= :maxRating) " +
            "AND (:minRating IS NULL OR m.avgRating >= :minRating)")
    public List<Movie> getMoviesWithParams(@Param("genre") String genre,
                                           @Param("maxRating") Double maxRating,
                                           @Param("minRating") Double minRating);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.name = :genre")
    public List<Movie> suggestedMovies(@Param("genre") String genre);
}
