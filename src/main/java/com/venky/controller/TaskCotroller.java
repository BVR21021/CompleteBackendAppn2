package com.venky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.venky.payload.TaskDto;
import com.venky.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskCotroller {

	@Autowired
	private TaskService taskService;

	@GetMapping("/home")
	@ResponseBody
	public String home() {
		return "welcome to  task management system...";
	}

	@PostMapping("/{user_Id}/tasks")
	public ResponseEntity<TaskDto> saveTask(@PathVariable(name = "user_Id") long userId, @RequestBody TaskDto taskDto) {

		return new ResponseEntity<>(taskService.saveTask(userId, taskDto), HttpStatus.CREATED);

	}

	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable(name = "userId") long userId) {

		return new ResponseEntity<>(taskService.getAllTasks(userId), HttpStatus.OK);
	}

	@GetMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<TaskDto> getTask(@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskId) {
		return new ResponseEntity<>(taskService.getTask(userId, taskId), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskId) {
		taskService.deleteTask(userId, taskId);
		return new ResponseEntity<>("task deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskId, @RequestBody TaskDto taskDto) {
		return new ResponseEntity<>(taskService.updateTask(userId, taskId, taskDto), HttpStatus.OK);

	}

}
