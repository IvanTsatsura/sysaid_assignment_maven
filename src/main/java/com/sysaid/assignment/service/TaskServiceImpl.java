package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.data.FakeDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements  ITaskService{
    private FakeDB dB;
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;
    public TaskServiceImpl(){
        this.dB = FakeDB.getDB();
    }

    public ResponseEntity<List<Task>> getUncompletedTasks(String user, String type){
        List<Task> wishlist = dB.findUserWishlist(user);
        List<Task> completedTasks = dB.findUserCompleted(user);
        if (wishlist == null || completedTasks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Task> uncompletedTasks = wishlist
                .stream()
                .filter(x -> !completedTasks.contains(x)/* && x.getType() == type*/)
                .collect(Collectors.toList());
        return new ResponseEntity<>(uncompletedTasks, HttpStatus.OK);
    }

    public ResponseEntity<Task> getRandomTask() {
        List<Task> tasks = dB.getAllTasks();
        if (tasks == null || tasks.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int length = tasks.size();
        int randomIndex = new Random().nextInt(length);
        Task randomTask = tasks.get(randomIndex);
        return new ResponseEntity<>(randomTask, HttpStatus.OK);
    }

    public ResponseEntity<Task> addNewTask() {
        String endpointUrl = String.format("%s/activity", baseUrl);
        RestTemplate template = new RestTemplate();
        Task newTask = template.getForObject(endpointUrl, Task.class);
        dB.addTask(newTask);
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = dB.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
