package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.CompleteTaskService;
import com.sysaid.assignment.service.TaskServiceImpl;
import com.sysaid.assignment.service.UserService;
import com.sysaid.assignment.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * the controller is a basic structure and save some time on "dirty" work.
 */

@Controller
public class TaskController {
    private final TaskServiceImpl taskService;
    private final UserService userService;
    private final WishlistService wishlistService;
    private final CompleteTaskService completeTaskService;

    /**
     * constructor for dependency injection
     *
     * @param taskService
     */
    public TaskController(TaskServiceImpl taskService,
                          UserService userService,
                          WishlistService wishlistService,
                          CompleteTaskService completeTaskService) {
        this.taskService = taskService;
        this.userService = userService;
        this.wishlistService = wishlistService;
        this.completeTaskService = completeTaskService;
    }

    @GetMapping("/{user}")
    public String taskOfTheDay(@PathVariable("user") String user, Model model) {
        userService.addUser(user);
        model.addAttribute("userName", user);
        Task taskOfTheDay = taskService.getRandomTask();
        model.addAttribute("taskOfTheDay", taskOfTheDay);
        return "taskOfTheDay.html";
    }

    /**
     * will return uncompleted tasks for given user
     *
     * @param user the user which the tasks relevant for
     * @param type type of the task
     * @return list uncompleted tasks for the user
     */
    @GetMapping("/tasks/{user}")
    public String getUncompletedTasks(@PathVariable("user") String user,
                                      @RequestParam(name = "type", required = false) String type,
                                      Model model) {
        List<Task> tasks = taskService.getUncompletedTasks(user, type);
        if (tasks == null) {
            String noTypeMessage = String.format("Type: \'%s\' doesn't exist. Please enter another type!",
                    type);
            model.addAttribute("noTypeMessage", noTypeMessage);
        }
        model.addAttribute("userName", user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("type", type);
        return "tasks.html";
    }

    @GetMapping("/task-of-the-day")
    public String getTaskOfTheDay(Model model) {
        Task taskOfTheDay = taskService.getRandomTask();
        model.addAttribute("taskOfTheDay", taskOfTheDay);
        return "taskOfTheDay";
    }

    @PostMapping("/tasks/{user}")
    public String addTaskToUser(@PathVariable("user") String user,
                                @RequestParam(name = "taskKey") String taskKey,
                                @RequestParam(name = "buttonType") String buttonType,
                                @RequestParam(name = "taskType") String taskType,
                                Model model) {
        if (buttonType.equals("wishlist")) {
            wishlistService.addTaskToWishlist(user, taskKey);
        } else if (buttonType.equals("complete")) {
            completeTaskService.addTaskToCompleted(user, taskKey);
        }

        List<Task> tasks = taskService.getUncompletedTasks(user, taskType);
        model.addAttribute("userName", user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("type", taskType);
        return "tasks.html";
    }

    @GetMapping("/add-new-task")
    public ResponseEntity<Task> addNewTask() {
        return taskService.addNewTask();
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }
}

