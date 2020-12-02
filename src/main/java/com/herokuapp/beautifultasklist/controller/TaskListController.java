package com.herokuapp.beautifultasklist.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herokuapp.beautifultasklist.model.Task;
import com.herokuapp.beautifultasklist.model.TaskRepository;

@Controller
@Component
public class TaskListController {

	private TaskRepository taskRepo;

	@Autowired
	public TaskListController(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}

	@RequestMapping("/testdb")
	@ResponseBody
	String testdb(Map<String, Object> model) {
		Task task = new Task();
		task.setTitle("Teste");
		task.setStatus(Boolean.FALSE);
		task.setCreation(new Date());

		this.taskRepo.save(task);

		return "testdb";
	}

}
