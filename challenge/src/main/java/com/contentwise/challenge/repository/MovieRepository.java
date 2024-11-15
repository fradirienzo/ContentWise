package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
