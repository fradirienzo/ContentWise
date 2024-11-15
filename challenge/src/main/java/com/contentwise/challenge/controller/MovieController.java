package com.contentwise.challenge.controller;

import com.contentwise.challenge.entity.Movie;
import com.contentwise.challenge.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies(){
        log.info("calling get all movies API");
        return movieService.getAllMovies();
    }

}
