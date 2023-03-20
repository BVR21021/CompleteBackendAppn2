package com.venky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venky.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByUsersId(long userId);


}
