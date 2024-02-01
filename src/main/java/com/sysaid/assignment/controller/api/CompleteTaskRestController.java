package com.sysaid.assignment.controller.api;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.CompleteTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/completed-tasks")
public class CompleteTaskRestController {
    private CompleteTaskService completeTaskService;
    public CompleteTaskRestController(CompleteTaskService completeTaskService){
        this.completeTaskService = completeTaskService;
    }

    @GetMapping("/{user}")
    public ResponseEntity<Collection<Task>> getCompletedTasks(@PathVariable("user") String user){
        Collection<Task> wishlist = new ArrayList<>(completeTaskService.findUserCompleted(user));

        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @GetMapping("/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToCompleted(@PathVariable ("user") String user,
                                                @PathVariable ("taskKey") String taskKey){
        if (completeTaskService.addTaskToCompleted(user, taskKey)){
            return new ResponseEntity<>("Task successfully added to completed",
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/delete-task/{user}/{taskKey}")
    public ResponseEntity<?> deleteTaskFromWishList(@PathVariable ("user") String user,
                                                    @PathVariable ("taskKey") String taskKey){
        if (completeTaskService.deleteTaskFromCompleted(user, taskKey)){
            return new ResponseEntity<>("Task successfully deleted from completed",
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update-task/{user}/{taskKey}")
    public ResponseEntity<?> updateTaskInWishlist(@PathVariable ("user") String user,
                                                  @RequestBody Task updatedTask){
        if (completeTaskService.updateTaskInWishlist(user, updatedTask)){
            return new ResponseEntity<>("Task successfully updated in completed",
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}
