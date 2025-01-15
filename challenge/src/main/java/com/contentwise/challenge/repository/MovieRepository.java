package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Genre;
import com.contentwise.challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g IN :genres AND m.id NOT IN :movies")
    public List<Movie> suggestedMovies(@Param("genres") Set<Genre> genres, @Param("movies") Set<Long> movies);

    @Query("SELECT m FROM Movie m WHERE :title IS NULL OR m.title = :title")
    public List<Movie> selectMovieByTitle(@Param("title") String title);

    @Query("SELECT m FROM Movie m WHERE :words IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :words, '%'))")
    public List<Movie> getMoviesByWord(@Param("words") String words);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE :genres IS NULL OR g.name in :genres")
    public List<Movie> getMoviesByGenre(@Param("genres") List<String> genres);
}
