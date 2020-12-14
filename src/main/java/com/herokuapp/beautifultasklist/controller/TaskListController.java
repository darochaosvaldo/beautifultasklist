package com.herokuapp.beautifultasklist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herokuapp.beautifultasklist.model.Task;
import com.herokuapp.beautifultasklist.model.TaskRepository;

@Controller
@Component
public class TaskListController {

	private TaskRepository taskRepo;
	private static final String INDEX = "index";

	@Autowired
	public TaskListController(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}

	@RequestMapping("/")
	String index() {
		return INDEX;
	}

	/**
	 * Método responsável por listar todas as Tasks salvas em banco de dados
	 */
	@RequestMapping("/tasklist")
	@ResponseBody
	public Iterable<Task> list(Map<String, Object> model) {
		return this.taskRepo.findAll();
	}

	/**
	 * Método responsável por persistir uma nova Task, recebendo um JSON do frontend
	 */
	@RequestMapping("/savetask")
	public String save(@RequestBody Task task) {
		this.taskRepo.save(task);
		return INDEX;
	}

}
