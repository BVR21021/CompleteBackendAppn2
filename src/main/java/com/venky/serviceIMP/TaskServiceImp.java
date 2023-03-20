package com.venky.serviceIMP;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venky.exception.ApiException;
import com.venky.exception.TaskNotFound;
import com.venky.exception.UserNotFound;
import com.venky.model.Task;
import com.venky.model.Users;
import com.venky.payload.TaskDto;
import com.venky.repository.TaskRepository;
import com.venky.repository.UserRepository;
import com.venky.service.TaskService;
@Service
public class TaskServiceImp implements TaskService {
	
	@Autowired
	private ModelMapper modelapper;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public TaskDto saveTask(long userId, TaskDto taskDto) {
		Users users=userRepository.findById(userId).orElseThrow(
				()->new UserNotFound(String.format("User Id %d Not  Found",userId)));
		Task  task=modelapper.map(taskDto, Task.class);
		task.setUsers(users);
		Task savedTask=taskRepository.save(task);
		return taskDto=modelapper.map(savedTask, TaskDto.class); 
	}

	@Override
	public List<TaskDto> getAllTasks(long userId) {
		userRepository.findById(userId).orElseThrow(
				()->new UserNotFound(String.format("User Id %d Not  Found",userId)));
		
		List<Task> tasks=taskRepository.findAllByUsersId(userId);
		return tasks.stream().map(
	task->modelapper.map(task, TaskDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public TaskDto getTask(long userId, long taskId) {
		Users user=userRepository.findById(userId).orElseThrow(
				()->new UserNotFound(String.format("User Id %d Not  Found",userId)));
		Task task=taskRepository.findById(taskId).orElseThrow(
				()->new TaskNotFound( String.format("Task Id %d Not  Found",taskId)));
		if(user.getId()!=task.getUsers().getId()) {
			throw new ApiException (String.format("Task Id %d is not belongs to User Id %d", userId,taskId));
		}
		
		return modelapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(long userId, long taskId) {

		Users user=userRepository.findById(userId).orElseThrow(
				()->new UserNotFound(String.format("User Id %d Not  Found",userId)));
		Task task=taskRepository.findById(taskId).orElseThrow(
				()->new TaskNotFound( String.format("Task Id %d Not  Found",taskId)));
		if(user.getId()!=task.getUsers().getId()) {
			throw new ApiException (String.format("Task Id %d is not belongs to User Id %d", userId,taskId));
		}
		
		taskRepository.deleteById(taskId);
		
	}

	@Override
	public TaskDto updateTask(long userId,long taskId, TaskDto taskDto) {

		Users user=userRepository.findById(userId).orElseThrow(
				()->new UserNotFound(String.format("User Id %d Not  Found",userId)));
		Task task=taskRepository.findById(taskId).orElseThrow(
				()->new TaskNotFound( String.format("Task Id %d Not  Found",taskId)));
		if(user.getId()!=task.getUsers().getId()) {
			throw new ApiException (String.format("Task Id %d is not belongs to User Id %d", userId,taskId));
		}
		   task.setTaskName(taskDto.getTaskName());
			Task savedTask=taskRepository.save(task);
			return taskDto=modelapper.map(savedTask, TaskDto.class); 
		}

}
