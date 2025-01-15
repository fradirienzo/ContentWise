package com.contentwise.challenge.controller;

import com.contentwise.challenge.entity.Interaction;
import com.contentwise.challenge.entity.Movie;
import com.contentwise.challenge.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies(){
        log.info("calling get all movies API");
        return movieService.getAllMovies();
    }

    @GetMapping("/getMovies")
    public List<Movie> getMovieWithParams(@RequestParam(required = false) String genre, @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating){
        log.info("updating the average rating for movie");
        movieService.computeAvgRating();//sort of update in case we added some event of rating
        log.info("retrieving movies according to the parameters genre " + genre + " maxRating " + maxRating + " minRating " + minRating);
        return movieService.getMoviesWithParams(genre, maxRating, minRating);
    }

    @GetMapping("/getMoviesWeak")
    public List<Movie> getMoviesWeakParam(@RequestParam(required = false) String title,@RequestParam(required = false) Optional<ArrayList<String>> genreNames){
        log.info("title {}",title);
        genreNames.ifPresent(strings -> strings.forEach((g) -> log.info("Genre {}", g)));
        return movieService.getMoviesByTitleAndGenres(title, genreNames.orElseGet(ArrayList::new));
    }

}
