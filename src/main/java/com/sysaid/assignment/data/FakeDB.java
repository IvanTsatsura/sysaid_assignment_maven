package com.sysaid.assignment.data;

import com.sysaid.assignment.domain.Task;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeDB {
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;
    private static FakeDB dataBase;
    private List<String> users = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private Map<String, ArrayList<Task>> usersCompletedTasks = new HashMap<>();
    private Map<String, ArrayList<Task>> usersWishLists = new HashMap<>();
    private FakeDB(){
        /*users.add("Alex");
        users.add("Bob");
        users.add("John");
        users.add("Sasha");
        users.add("Anna");
        users.add("Diana");
        for (String user : users){
            usersCompletedTasks.put(user, new ArrayList<>());
            usersWishLists.put(user, new ArrayList<>());
        }*/
    }

    public static synchronized FakeDB getDB(){
        if (dataBase == null){
            dataBase = new FakeDB();
        }

        return dataBase;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void addUser(String user) throws Exception {
        if(users.contains(user)){
            throw new Exception("sddsd");
        }
        users.add(user);
        usersCompletedTasks.put(user, new ArrayList<>());
        usersWishLists.put(user, new ArrayList<>());
    }

    public List<Task> getAllTasks(){
        return new ArrayList<>(tasks);
    }

    public List<String> getAllUsers(){
        return new ArrayList<>(users);
    }

    public void addToCompleted(String user, Task task){
        usersCompletedTasks.get(user).add(task);
    }

    public void addToWishlist(String user, Task task){
        usersWishLists.get(user).add(task);
    }

    public List<Task> findUserCompleted(String user){
        List<Task> completedTasks = null;
        if (usersCompletedTasks.containsKey(user)){
            completedTasks = new ArrayList<>(usersCompletedTasks.get(user));
        }

        return completedTasks;
    }

    public List<Task> findUserWishlist(String user){
        List<Task> wishlist = null;
        if (usersWishLists.containsKey(user)){
            wishlist = new ArrayList<>(usersWishLists.get(user));
        }

        return wishlist;
    }
}
