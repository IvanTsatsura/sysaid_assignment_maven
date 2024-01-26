package com.sysaid.assignment;

import com.sysaid.assignment.domain.Task;

import java.util.Enumeration;
import java.util.HashMap;

public class RatingSystem {
    private HashMap<Task, Integer> ratings = new HashMap<>();
    public RatingSystem(Iterable<Task> tasks){
        for (Task task : tasks){
            ratings.put(task, 0);
        }
    }
    public void setRating(Task task, int points){
        ratings.put(task, points);
    }
    public void increaseRating(Task task, int points){
        ratings.merge(task, points, Integer::sum);
    }

}
