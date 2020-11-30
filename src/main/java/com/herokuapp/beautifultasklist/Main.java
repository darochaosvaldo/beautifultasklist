/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.herokuapp.beautifultasklist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herokuapp.beautifultasklist.model.Task;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Controller
@SpringBootApplication
public class Main {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping("/testdb")
	String testdb(Map<String, Object> model) {
		// EntityManagerFactory factory = Persistence.createEntityManagerFactory("beautifultasklist");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		Task task = new Task();
		task.setTitle("Teste");
		task.setStatus(Boolean.FALSE);
		task.setCreation(new Date());

		entityManager.persist(task);

		return "testdb";
	}

	@RequestMapping("/db")
	String db(Map<String, Object> model) {
		try (Connection connection = this.dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

			ArrayList<String> output = new ArrayList<>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getTimestamp("tick"));
			}

			model.put("records", output);
			return "db";
		} catch (Exception e) {
			model.put("message", e.getMessage());
			return "error";
		}
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		if (this.dbUrl == null || this.dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(this.dbUrl);
			return new HikariDataSource(config);
		}
	}

}
