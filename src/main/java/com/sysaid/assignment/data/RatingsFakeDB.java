package com.sysaid.assignment.data;

import com.sysaid.assignment.domain.Task;

import java.util.HashMap;
import java.util.Map;

public class RatingsFakeDB {
    private static RatingsFakeDB ratingsDB;
    private Map<Task, Integer[]> ratings = new HashMap<>();
    private RatingsFakeDB(){
    }

    public static RatingsFakeDB getRatingsDB(){
        if (ratingsDB == null){
            ratingsDB = new RatingsFakeDB();
        }

        return ratingsDB;
    }

    public void addTask(Task task){
        ratings.put(task, new Integer[2]);
    }

    public Integer getRating(Task task){
        return ratings.get(task)[0];
    }

    public Integer getRank(Task task){
        return ratings.get(task)[1];
    }

    public void setRating(Task task, int rating){
        ratings.get(task)[0] = rating;
    }

    public void setRank(Task task, int rank){
        ratings.get(task)[1] = rank;
    }
}
