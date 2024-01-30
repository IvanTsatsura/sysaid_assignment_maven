package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.data.FakeDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompleteTaskService {
    private FakeDB dB;

    public CompleteTaskService(){
        this.dB = FakeDB.getDB();
    }

    public ResponseEntity<List<Task>> findUserCompleted(String user){
        List<Task> completedTasks = dB.findUserCompleted(user);
        if (completedTasks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completedTasks, HttpStatus.OK);
    }

    public ResponseEntity<String> addTaskToCompleted(String user, String taskKey){
        Task task = dB.getAllTasks()
                .stream()
                .filter(x -> (x.getKey()).equals(taskKey))
                .findFirst()
                .orElse(null);
        if (task != null){
            dB.addToCompleted(user, task);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>("Task has been added to completed", HttpStatus.OK);
    }
}
