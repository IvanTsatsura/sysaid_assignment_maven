package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.TaskServiceImpl;
import com.sysaid.assignment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * the controller is a basic structure and save some time on "dirty" work.
 */

@Controller
public class TaskController {
	private final TaskServiceImpl taskService;
	private final UserService userService;
	/**
	 * constructor for dependency injection
	 * @param taskService
	 */
	public TaskController(TaskServiceImpl taskService, UserService userService) {
		this.taskService = taskService;
		this.userService = userService;
	}

	@GetMapping("/{user}")
	public String taskOfTheDay(@PathVariable ("user") String user, Model model) {
		userService.addUser(user);
		model.addAttribute("userName", user);
		Task taskOfTheDay = taskService.getRandomTask();
		model.addAttribute("taskOfTheDay", taskOfTheDay);
		return "taskOfTheDay.html";
	}

	/**
	 * will return uncompleted tasks for given user
	 * @param user the user which the tasks relevant for
	 * @param type type of the task
	 * @return list uncompleted tasks for the user
	 */
	@GetMapping("/tasks/{user}")
	public String getUncompletedTasks(@PathVariable ("user") String user,
									  @RequestParam(name = "type",required = false) String type,
									  Model model){
		List<Task> tasks = taskService.getUncompletedTasks(user, type);
		model.addAttribute("userName", user);
		model.addAttribute("tasks", tasks);
		model.addAttribute("type", type);
		return "tasks.html";
	}
	@GetMapping("/task-of-the-day")
	public String getTaskOfTheDay(Model model){
		Task taskOfTheDay = taskService.getRandomTask();
		model.addAttribute("taskOfTheDay", taskOfTheDay);
		return "taskOfTheDay";
	}

	@GetMapping("/add-new-task")
	public ResponseEntity<Task> addNewTask(){
		return taskService.addNewTask();
	}

	@GetMapping("/all-tasks")
	public ResponseEntity<List<Task>> getAllTasks(){
		return taskService.getAllTasks();
	}
}

