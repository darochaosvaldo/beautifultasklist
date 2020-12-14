package com.herokuapp.beautifultasklist.service;

import java.util.List;

import com.herokuapp.beautifultasklist.model.Task;

public interface TaskService {

	public List<Task> list();

	public void save(Task task);

}
