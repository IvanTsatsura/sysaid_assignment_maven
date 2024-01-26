package com.sysaid.assignment;

import com.sysaid.assignment.domain.Task;
import org.apache.tomcat.jni.User;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManagementSystem {
    private RatingSystem ratingSystem;
    private HashMap<User, ArrayList<Task>> usersCompletedTasks; //instead of database
    private HashMap<User, ArrayList<Task>> usersWishLists; //instead of database
    public TaskManagementSystem(User[] users, RatingSystem ratingSystem){
        this.usersCompletedTasks = new HashMap<>();
        this.usersWishLists = new HashMap<>();
        for (User user : users){
            usersCompletedTasks.put(user, new ArrayList<>());
            usersWishLists.put(user, new ArrayList<>());
        }
        this.ratingSystem = ratingSystem;
    }
    public ArrayList<Task> getUserCompletedTasks(String user){
        return usersCompletedTasks.get(user);
    }
    public ArrayList<Task> getUserWishList(User user){
        return usersWishLists.get(user);
    }
    public void setCompletedTaskForUser(User user, Task task){
        ArrayList<Task> completedTasks = usersCompletedTasks.get(user);
        completedTasks.add(task);
        ratingSystem.increaseRating(task, 2);
    }
    public void addTaskToUserWishList(User user, Task task){
        ArrayList<Task> wishList = usersWishLists.get(user);
        int countOfType = 0;
        for (Task tempTask : wishList){
            countOfType += tempTask.getType() == task.getType() ? 1 : 0;
        }
        if (countOfType < 10){
            wishList.add(task);
            ratingSystem.increaseRating(task, 1);
        }
    }
}
