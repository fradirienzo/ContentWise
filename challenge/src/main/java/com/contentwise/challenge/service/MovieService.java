package com.contentwise.challenge.service;

import com.contentwise.challenge.entity.Movie;
import com.contentwise.challenge.repository.MovieRepository;
import com.contentwise.challenge.repository.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;

    private final RatingRepository ratingRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository){
        this.movieRepository=movieRepository;
        this.ratingRepository=ratingRepository;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public void computeAvgRating(){
        List<Movie> movies = movieRepository.findAll();
        movies.forEach((e) -> e.setAvgRating(ratingRepository.computeAvgRating(e.getId())));
        movieRepository.saveAll(movies);
    }

    public List<Movie> getMoviesWithParams(String genre, Double maxRating, Double minRating){
        log.info("calling the repository for retrieving movies by params");
        List<Movie> movies = movieRepository.getMoviesWithParams(genre,maxRating, minRating);
        return movies;
    }

    public List<Movie> getMoviesByTitleAndGenres(String title, List<String> genreNames){
        List<Movie> moviesResult = new ArrayList<>();
        List<String> words = new ArrayList<>();
        if(title == null && genreNames == null){
            return null;
        }
        if(title != null) {
            moviesResult = movieRepository.selectMovieByTitle(title);
            words = List.of(title.split(" "));
        }
        if(moviesResult.isEmpty()){
            //missing perfect correspondence, proceeding in retrieving weak correspondence
            for(String word : words){
                moviesResult.addAll(movieRepository.getMoviesByWord(word));
            }
            moviesResult.addAll(movieRepository.getMoviesByGenre(genreNames));
            return moviesResult;
        } else {
            return moviesResult;
        }
    }
}
