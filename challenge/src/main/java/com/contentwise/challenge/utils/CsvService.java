package com.contentwise.challenge.utils;

import com.contentwise.challenge.entity.Movie;
import com.contentwise.challenge.entity.Rating;
import com.contentwise.challenge.entity.User;
import com.contentwise.challenge.entity.View;
import com.contentwise.challenge.repository.MovieRepository;
import com.contentwise.challenge.repository.RatingRepository;
import com.contentwise.challenge.repository.UserRepository;
import com.contentwise.challenge.repository.ViewRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class CsvService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final ViewRepository viewRepository;

    @Autowired
    public CsvService(UserRepository userRepository, MovieRepository movieRepository, RatingRepository ratingRepository, ViewRepository viewRepository) {
        this.userRepository = userRepository;
        this.movieRepository=movieRepository;
        this.ratingRepository=ratingRepository;
        this.viewRepository=viewRepository;

    }



    @Transactional
    public void loadCsvDataUser(String filePath) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()))) {
            String[] nextLine;
            List<User> users = new ArrayList<>();
            reader.readNext(); // Skip the header row
            while ((nextLine = reader.readNext()) != null) {
                User user = new User();
                user.setId(Long.parseLong(nextLine[0])); // First column
                user.setUsername(nextLine[1]); // Second column
                users.add(user);
            }
            userRepository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the CSV file", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException("Error validating the CSV file", e);
        }
    }

    @Transactional
    public void loadCsvDataMovie(String filePath) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()))) {
            String[] nextLine;
            List<Movie> movies = new ArrayList<>();
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                Movie movie = new Movie();
                movie.setId(Long.parseLong(nextLine[0]));
                movie.setTitle(nextLine[1]);
                List<String> genres = new ArrayList<>();
                for(int i = 2; i < nextLine.length; ++i){
                    genres.add(nextLine[i]);
                }
                //CORRRREGGO
                movie.setGenres(genres);
                movies.add(movie);
            }
            movieRepository.saveAll(movies);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the Movie CSV file", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException("Error validating the Movie CSV file", e);
        }
    }

    //VERY UGLY FUNCTION....I KNOW
    @Transactional
    public void loadCsvDataRating(String filePath) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()))) {
            String[] nextLine;
            List<Rating> ratings = new ArrayList<>();
            List<View> views = new ArrayList<>();

            // Skip the header
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 4) {
                    // Handle rows with missing columns (less than 4 values)
                    log.warn("Skipping malformed row: " + Arrays.toString(nextLine));
                    continue;
                }

                Rating rating = new Rating();
                View view = new View();
                // Parse userId
                if (!nextLine[0].isEmpty()) {
                    rating.setUserId(Long.parseLong(nextLine[0]));
                    view.setUserId(Long.parseLong(nextLine[0]));
                } else {
                    log.warn("Missing userId for row: " + Arrays.toString(nextLine));
                    continue; // Skip rows with missing userId
                }

                // Parse movieId
                if (!nextLine[1].isEmpty()) {
                    rating.setMovieId(Long.parseLong(nextLine[1]));
                    view.setMovieId(Long.parseLong(nextLine[1]));
                } else {
                    log.warn("Missing movieId for row: " + Arrays.toString(nextLine));
                    continue; // Skip rows with missing movieId
                }

                // Parse rating
                int ratingValue = nextLine[2].isEmpty() ? -1 : Integer.parseInt(nextLine[2]);

                // Parse viewPercentage
                double viewValue = nextLine[3].isEmpty() ? -1 : Double.parseDouble(nextLine[3]);

                if(ratingValue == -1 && viewValue != -1){
                    ratingValue = RatingGenerator.generateRating(viewValue);
                }
                if(ratingValue == -1 && viewValue == -1){
                    log.warn("Detected malformed row in ratings.csv !");
                    continue;//malformed interaction
                }

                rating.setRating(ratingValue);

                ratings.add(rating);

                if(viewValue != -1){
                    view.setPercentage(viewValue);
                    views.add(view);
                }
            }
            log.info("Parsed ratings: " + ratings.size());
            log.info("Parsed views: " + views.size());
            // Save all ratings to the repository
            ratings.forEach((v) -> log.info(v.getMovieId() + ""));
            ratings.forEach((v) -> log.info(v.getUserId() + ""));
            ratingRepository.saveAll(ratings);
            views.forEach((v) -> log.info(v.getMovieId() + ""));
            views.forEach((v) -> log.info(v.getUserId() + ""));
            viewRepository.saveAll(views);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the Rating CSV file", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException("Error validating the Rating CSV file", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing a numeric value in the Rating CSV file", e);
        }
    }


}
