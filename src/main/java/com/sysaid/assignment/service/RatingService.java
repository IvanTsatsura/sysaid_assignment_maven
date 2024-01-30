package com.sysaid.assignment.service;

import com.sysaid.assignment.data.FakeDB;
import com.sysaid.assignment.data.RatingsFakeDB;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private FakeDB dB;
    private RatingsFakeDB ratingsDB;

    public RatingService(){
        dB = FakeDB.getDB();
        ratingsDB = RatingsFakeDB.getRatingsDB();
    }

    /*public void increaseRating(Task task, int points){

    }*/
}
