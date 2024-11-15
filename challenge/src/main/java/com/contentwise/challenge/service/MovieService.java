package com.contentwise.challenge.service;

import com.contentwise.challenge.entity.Movie;
import com.contentwise.challenge.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
}
