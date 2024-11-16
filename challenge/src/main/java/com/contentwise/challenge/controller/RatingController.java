package com.contentwise.challenge.controller;

import com.contentwise.challenge.entity.Rating;
import com.contentwise.challenge.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/rating")
public class RatingController {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingController(RatingRepository ratingRepository){
        this.ratingRepository=ratingRepository;
    }

    /*@GetMapping("/getUserHistory")
    public ResponseEntity<List<Rating>> getUserHistory(@RequestParam(defaultValue = "both") String filter,
                                                       @RequestParam Long id){
        if(!filter.equals("both") && !filter.equals("rating") && !filter.equals("view")){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        } else {

        }
    }*/
}
