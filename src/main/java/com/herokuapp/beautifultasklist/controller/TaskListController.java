package com.herokuapp.beautifultasklist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herokuapp.beautifultasklist.model.Task;
import com.herokuapp.beautifultasklist.service.TaskService;

@Controller
@Component
public class TaskListController {

	private TaskService service;
	private static final String INDEX = "index";

	@Autowired
	public TaskListController(TaskService service) {
		this.service = service;
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
		return this.service.list();
	}

	/**
	 * Método responsável por persistir uma nova Task, recebendo um JSON do frontend
	 */
	@RequestMapping("/savetask")
	public String save(@RequestBody Task task) {
		this.service.save(task);
		return INDEX;
	}

}
