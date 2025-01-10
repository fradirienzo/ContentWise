package com.contentwise.challenge.service;

import com.contentwise.challenge.entity.Interaction;
import com.contentwise.challenge.entity.User;
import com.contentwise.challenge.repository.RatingRepository;
import com.contentwise.challenge.repository.UserRepository;
import com.contentwise.challenge.repository.ViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private ViewRepository viewRepository;

    @Autowired
    public UserService(UserRepository userRepository, RatingRepository ratingRepository, ViewRepository viewRepository){
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.viewRepository = viewRepository;
    }

    public List<User> getAllUsers(){
        log.info("service all users");
        return userRepository.findAll();
    }

    public List<Interaction> getInteractions(int ratings, int userId){
        List<Interaction> res = new ArrayList<>();
        if(ratings == 0){//retrive both
            res.addAll(ratingRepository.retrieveUserRatings(userId));
            res.addAll(viewRepository.retrieveViews(userId));
        } else if(ratings == 1){//only ratings
            res.addAll(ratingRepository.retrieveUserRatings(userId));
        } else if(ratings == 2){//only views
            res.addAll(viewRepository.retrieveViews(userId));
        }
        return res;
    }
}
