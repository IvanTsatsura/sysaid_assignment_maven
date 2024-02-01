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
            return true;
        }

        return false;
    }

    public boolean deleteTask(Task task){
        return tasks.remove(task);
    }

    public boolean updateTask(Task task){
        boolean successfully = false;
        if (tasks.remove(task)){
            successfully = tasks.add(task);
        }

        return successfully;
    }

    public void addUser(String user){
        if (!users.contains(user)){
            users.add(user);
            usersCompletedTasks.put(user, new ArrayList<>());
            usersWishLists.put(user, new ArrayList<>());
        }
    }

    public ArrayList<Task> getAllTasks(){
        return new ArrayList<>(tasks);
    }

    public boolean addToCompleted(String user, Task task){
        return usersCompletedTasks.get(user).add(task);
    }

    public boolean deleteFromCompleted(String user, Task task){
        return usersCompletedTasks.get(user).remove(task);
    }

    public boolean updateInCompleted(String user, Task task){
        int index = usersCompletedTasks.get(user).indexOf(task);
        if (index == -1){
            return false;
        }
        usersCompletedTasks.get(user).set(index, task);

        return true;
    }

    public boolean addToWishlist(String user, Task task){
        return usersWishLists.get(user).add(task);
    }

    public boolean deleteFromWishlist(String user, Task task){
        return usersWishLists.get(user).remove(task);
    }

    public boolean updateInWishlist(String user, Task task){
        int index = usersWishLists.get(user).indexOf(task);
        if (index == -1){
            return false;
        }
        usersWishLists.get(user).set(index, task);

        return true;
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
