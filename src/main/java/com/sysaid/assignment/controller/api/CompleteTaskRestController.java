package com.sysaid.assignment.controller.api;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.CompleteTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class CompleteTaskRestController {
    private CompleteTaskService completeTaskService;
    public CompleteTaskRestController(CompleteTaskService completeTaskService){
        this.completeTaskService = completeTaskService;
    }

    @GetMapping("/api/completed-tasks/{user}")
    public ResponseEntity<Collection<Task>> getCompletedTasks(@PathVariable("user") String user){
        Collection<Task> wishlist = new ArrayList<>(completeTaskService.findUserCompleted(user));
        ResponseEntity<Collection<Task>> responseEntity =
                new ResponseEntity<>(wishlist, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/api/completed-tasks/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToCompleted(@PathVariable ("user") String user,
                                                @PathVariable ("taskKey") String taskKey){
        completeTaskService.addTaskToCompleted(user, taskKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
