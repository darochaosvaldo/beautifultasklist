package com.herokuapp.beautifultasklist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "ds_title")
	private String title;
	@Column(name = "ds_description")
	private String description;
	@Column(name = "st_status")
	private Boolean status;
	@Column(name = "dt_creation")
	private Date creation;
	@Column(name = "dt_last_edition")
	private Date lastEdition;
	@Column(name = "dt_removal")
	private Date removal;
	@Column(name = "dt_conclusion")
	private Date conclusion;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreation() {
		return this.creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getLastEdition() {
		return this.lastEdition;
	}

	public void setLastEdition(Date lastEdition) {
		this.lastEdition = lastEdition;
	}

	public Date getRemoval() {
		return this.removal;
	}

	public void setRemoval(Date removal) {
		this.removal = removal;
	}

	public Date getConclusion() {
		return this.conclusion;
	}

	public void setConclusion(Date conclusion) {
		this.conclusion = conclusion;
	}

}