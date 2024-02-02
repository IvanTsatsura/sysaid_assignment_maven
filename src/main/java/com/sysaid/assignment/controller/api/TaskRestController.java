package com.sysaid.assignment.controller.api;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController

@RequestMapping("/api")
public class TaskRestController {
    private final TaskServiceImpl taskService;

    /**
     * constructor for dependency injection
     *

     * @param taskService
     */
    public TaskRestController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    /**
     * will return uncompleted tasks for given user
     *
     * @param user the user which the tasks relevant for
     * @param type type of the task
     * @return list uncompleted tasks for the user
     */
    @GetMapping("/uncompleted-tasks/{user}")
    public ResponseEntity<Collection<Task>> getUncompletedTasks(@PathVariable("user") String user, @RequestParam(name = "type", required = false) String type) {

        Collection<Task> uncompletedTasks = taskService.getUncompletedTasks(user, type);
        return new ResponseEntity<>(uncompletedTasks, HttpStatus.OK);
    }

    @GetMapping("/random-task")
    public ResponseEntity<?> getRandomTask() {
        Task randomTask = taskService.getRandomTask();
        return new ResponseEntity<>(randomTask, HttpStatus.OK);
    }

    @GetMapping("/add-new-task")
    public ResponseEntity<Task> addNewTask() {
        return taskService.addNewTask();
    }

    @GetMapping("/add-task-by-key/{key}")
    public ResponseEntity<?> addTaskByKey(@PathVariable("key") String key) {
        if (taskService.addTaskByKeyFromExternal(key)) {
            return new ResponseEntity<>("Task successfully added by key from external API",
                    HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add-task")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        if (taskService.addTask(task)) {
            return new ResponseEntity<>("Task successfully added", HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/delete-task/{taskKey}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskKey") String taskKey) {
        if (taskService.deleteTask(taskKey)) {
            return new ResponseEntity<>("Task successfully deleted", HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update-task/{user}/{taskKey}")
    public ResponseEntity<?> updateTask(@RequestBody Task updatedTask) {
        if (taskService.updateTask(updatedTask)) {
            return new ResponseEntity<>("Task successfully updated", HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

}
