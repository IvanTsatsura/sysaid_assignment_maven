package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.data.FakeDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private FakeDB dB;

    public WishlistService(){
        this.dB = FakeDB.getDB();
    }

    public ResponseEntity<List<Task>> findUserWishlist(String user){
        List<Task> wishlist = dB.findUserWishlist(user);
        if (wishlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    public ResponseEntity<String> addTaskToWishlist(String user, String taskKey){
        String responseMessage = "Task doesn't exist";

        Task task = dB.getAllTasks()
                .stream()
                .filter(x -> (x.getKey()).equals(taskKey))
                .findFirst()
                .orElse(null);

        if (task != null){
            List<Task> wishlist = dB.findUserWishlist(user);
            int countOfType = 0;
            for (Task userTask : wishlist){
                countOfType += userTask.getType() == task.getType() ? 1 : 0;
            }

            if (countOfType < 10){
                dB.addToWishlist(user, task);
                responseMessage = "Task has been added to your wishlist";
            }else{
                responseMessage = "You already have 10 task of this type";
            }
        }

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
