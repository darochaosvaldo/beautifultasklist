package com.herokuapp.beautifultasklist.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Utilização do CrudRepository que o Spring provém para simplificar a manipulação da base de dados
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

}
