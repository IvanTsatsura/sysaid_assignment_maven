package com.sysaid.assignment.service;

import com.sysaid.assignment.data.FakeDB;
import com.sysaid.assignment.domain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl{
    private final FakeDB dB;
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;
    public TaskServiceImpl(){
        this.dB = FakeDB.getDB();
    }

    public List<Task> getUncompletedTasks(String user, String type){
        List<Task> uncompletedTasks = new ArrayList<>();
        if (type != null && !type.isEmpty()){
            Set<Task> completedTasks = new HashSet<>(dB.findUserCompleted(user));
            Set<Task> wishlist = new HashSet<>(dB.findUserWishlist(user));
            uncompletedTasks = dB.getAllTasks()
                    .stream()
                    .filter(x -> x.getType().equals(type) &&
                            !completedTasks.contains(x) &&
                            !wishlist.contains(x))
                    .limit(10)
                    .collect(Collectors.toList());
            while (uncompletedTasks.size() < 10){
                Task temp = addTask();
                if (temp != null && temp.getType().equals(type)){
                    uncompletedTasks.add(temp);
                }
            }
        }
        return uncompletedTasks;
    }

    public Task getRandomTask() {
        List<Task> tasks = dB.getAllTasks();
        if (tasks == null || tasks.size() == 0){
            Task temp = addTask();
            return temp;
        }
        int length = tasks.size();
        int randomIndex = new Random().nextInt(length);
        Task randomTask = tasks.get(randomIndex);
        return randomTask;
    }

    public ResponseEntity<Task> addNewTask() {
        String endpointUrl = String.format("%s/activity", baseUrl);
        RestTemplate template = new RestTemplate();
        Task newTask = template.getForObject(endpointUrl, Task.class);
        if (!dB.addTask(newTask)){
            newTask = null;
        }
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    public Task addTask() {
        String endpointUrl = String.format("%s/activity", baseUrl);
        RestTemplate template = new RestTemplate();
        Task newTask = template.getForObject(endpointUrl, Task.class);
        if (!dB.addTask(newTask)){
            newTask = null;
        }
        return newTask;
    }

    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = dB.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
