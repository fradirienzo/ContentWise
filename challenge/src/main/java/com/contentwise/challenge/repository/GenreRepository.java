package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

    public Genre findByName(String name);

    @Query("SELECT g FROM Movie m JOIN m.genres g WHERE m.id = :movieId")
    public Set<Genre> likedGenresForMovie(@Param("movieId") Long movieId);
}
