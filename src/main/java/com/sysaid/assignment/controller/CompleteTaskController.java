package com.sysaid.assignment.controller;

import com.sysaid.assignment.service.CompleteTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.sysaid.assignment.domain.Task;
import java.util.List;

@RestController
public class CompleteTaskController {
    private CompleteTaskService completeTaskService;
    public CompleteTaskController(CompleteTaskService completeTaskService){
        this.completeTaskService = completeTaskService;
    }

    @GetMapping("/completed-tasks/{user}")
    public ResponseEntity<List<Task>> getCompletedTasks(@PathVariable ("user") String user){
        return completeTaskService.findUserCompleted(user);
    }

    @GetMapping("/completed-tasks/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToCompleted(@PathVariable ("user") String user,
                                                @PathVariable ("taskKey") String taskKey){
        return completeTaskService.addTaskToCompleted(user, taskKey);
    }
}
