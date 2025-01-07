package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

    public Genre findByName(String name);
}
