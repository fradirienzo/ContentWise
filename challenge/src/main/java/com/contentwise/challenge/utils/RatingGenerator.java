package com.contentwise.challenge.utils;

public class RatingGenerator {

    public static Integer generateRating(Double percentage){
        if(percentage >= 80){
            return 5;
        } else if (percentage < 80 && percentage >= 60){
            return 4;
        } else if (percentage < 60 && percentage >= 40){
            return 3;
        } else if (percentage < 40 && percentage >= 20){
            return 2;
        } else {
            return 1;
        }
    }
}
