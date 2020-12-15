package com.herokuapp.beautifultasklist.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.beautifultasklist.model.Task;
import com.herokuapp.beautifultasklist.model.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repo;

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> list() {
		Query query = this.em.createNativeQuery("select * from tb_task order by id", Task.class);
		return query.getResultList();
	}

	@Override
	public void save(Task task) {
		if (task.getId() == null) {
			task.setCreation(new Date());
		}

		task.setLastEdition(new Date());

		// TODO - Controle para data de remoção e conclusão

		this.repo.save(task);
	}

	@Override
	public void delete(Long id) {
		this.repo.deleteById(id);
	}

}
