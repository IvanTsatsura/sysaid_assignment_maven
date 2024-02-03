package com.sysaid.assignment.service;

import com.sysaid.assignment.data.FakeDB;
import com.sysaid.assignment.domain.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CompleteTaskService {
    private FakeDB dB;

    public CompleteTaskService() {
        this.dB = FakeDB.getDB();
    }

    public Collection<Task> findUserCompleted(String user) {
        return dB.findUserCompleted(user);
    }

    public boolean addTaskToCompleted(String user, String taskKey) {
        Task task = dB.getAllTasks()
                .stream()
                .filter(x -> (x.getKey()).equals(taskKey))
                .findFirst()
                .orElse(null);
        if (task != null) {
            task.setRating(task.getRating() + 2);
        }

        return dB.addToCompleted(user, task);
    }

    public boolean deleteTaskFromCompleted(String user, String taskKey) {
        Task task = dB.findUserCompleted(user)
                .stream()
                .filter(x -> (x.getKey()).equals(taskKey))
                .findFirst()
                .orElse(null);

        return dB.deleteFromCompleted(user, task);
    }

    public boolean updateTaskInWishlist(String user, Task task) {
        return dB.updateInCompleted(user, task);
    }
}
