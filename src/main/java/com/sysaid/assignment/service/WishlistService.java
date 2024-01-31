package com.sysaid.assignment.service;

import com.sysaid.assignment.data.FakeDB;
import com.sysaid.assignment.domain.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WishlistService {
    private final FakeDB dB;

    public WishlistService(){
        this.dB = FakeDB.getDB();
    }

    public Collection<Task> findUserWishlist(String user){
        return dB.findUserWishlist(user);
    }

    public void addTaskToWishlist(String user, String taskKey){
        Task task = dB.getAllTasks()
                .stream()
                .filter(x -> (x.getKey()).equals(taskKey))
                .findFirst()
                .orElse(null);

        if (task != null){
            dB.addToWishlist(user, task);
        }
    }
}
