package com.contentwise.challenge.controller;

import com.contentwise.challenge.entity.Interaction;
import com.contentwise.challenge.entity.Movie;
import com.contentwise.challenge.entity.User;
import com.contentwise.challenge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        log.info("calling all users API");
        return userService.getAllUsers();
    }

    @GetMapping("/userHistory")
    public List<Interaction> retriveInteractions(@RequestParam(required = true) int ratings, @RequestParam(required = true) int userId){
        return userService.getInteractions(ratings, userId);
    }

    @PostMapping("/addInteractions")
    public void addInteraction(@Validated @RequestBody(required = true) Interaction interaction){
        log.info("Adding interaction");
        userService.addInteraction(interaction);
        log.info("Interaction added");
    }

    @GetMapping("/getRecommendations")
    public List<Movie> getRecommendations(@Validated @RequestBody(required = true) User u){
        log.info("Recommendation start");

    }
}
