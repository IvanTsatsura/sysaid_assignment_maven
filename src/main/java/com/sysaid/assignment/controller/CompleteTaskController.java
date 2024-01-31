package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.CompleteTaskService;
import com.sysaid.assignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class CompleteTaskController {
    private CompleteTaskService completeTaskService;
    private final UserService userService;
    public CompleteTaskController(CompleteTaskService completeTaskService,
                                  UserService userService){
        this.completeTaskService = completeTaskService;
        this.userService = userService;
    }

    @GetMapping("/completed/{user}")
    public String getCompletedTasks(@PathVariable ("user") String user,
                                                        Model model){
        Collection<Task> completedTasks = completeTaskService.findUserCompleted(user);
        model.addAttribute("userName", user);
        model.addAttribute("completedTasks", completedTasks);
        return "completed.html";
    }

    @GetMapping("/completed-tasks/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToCompleted(@PathVariable ("user") String user,
                                                @PathVariable ("taskKey") String taskKey){
        completeTaskService.addTaskToCompleted(user, taskKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
