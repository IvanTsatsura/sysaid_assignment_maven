package com.sysaid.assignment.data;

import com.sysaid.assignment.domain.Task;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class FakeDB {
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;
    private static FakeDB dataBase;
    private Set<String> users = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();
    private Map<String, ArrayList<Task>> usersCompletedTasks = new HashMap<>();
    private Map<String, ArrayList<Task>> usersWishLists = new HashMap<>();
    private Map<Task, Integer> ratings = new HashMap<>();
    private FakeDB(){
    }

    public static synchronized FakeDB getDB(){
        if (dataBase == null){
            dataBase = new FakeDB();
        }

        return dataBase;
    }

    public boolean addTask(Task task){
        if (!tasks.contains(task)){
            tasks.add(task);
            ratings.put(task, 0);
            return true;
        }

        return false;
    }

    public void addUser(String user){
        if (!users.contains(user)){
            users.add(user);
            usersCompletedTasks.put(user, new ArrayList<>());
            usersWishLists.put(user, new ArrayList<>());
        }
    }

    public List<Task> getAllTasks(){
        return new ArrayList<>(tasks);
    }

    public void addToCompleted(String user, Task task){
        usersCompletedTasks.get(user).add(task);
    }

    public void addToWishlist(String user, Task task){
        usersWishLists.get(user).add(task);
    }

    public Collection<Task> findUserCompleted(String user){
        if (!users.contains(user)){
            addUser(user);
        }

        return new HashSet<>(usersCompletedTasks.get(user));
    }

    public Collection<Task> findUserWishlist(String user){
        if (!users.contains(user)){
            addUser(user);
        }

        return new HashSet<>(usersWishLists.get(user));
    }
}
