package com.sysaid.assignment.controller.api;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TaskRestController {
    private final TaskServiceImpl taskService;
    /**
     * constructor for dependency injection
     * @param taskService
     */
    public TaskRestController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    /**
     * will return uncompleted tasks for given user
     * @param user the user which the tasks relevant for
     * @param type type of the task
     * @return list uncompleted tasks for the user
     */
    @GetMapping("/api/uncompleted-tasks/{user}")
    public ResponseEntity<Collection<Task>> getUncompletedTasks(@PathVariable("user") String user, @RequestParam(name = "type",required = false) String type){
        Collection<Task> uncompletedTasks = taskService.getUncompletedTasks(user, type);
        return new ResponseEntity<>(uncompletedTasks, HttpStatus.OK);
    }

    @GetMapping("/random-task")
    public ResponseEntity<?> getTaskOfTheDay(){
        Task randomTask = taskService.getRandomTask();
        return new ResponseEntity<>(randomTask, HttpStatus.OK);
    }

    @GetMapping("/api/add-new-task")
    public ResponseEntity<Task> addNewTask(){
        return taskService.addNewTask();
    }
}
