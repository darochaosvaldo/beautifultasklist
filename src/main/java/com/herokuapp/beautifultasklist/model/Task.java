package com.herokuapp.beautifultasklist.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@Column(name="ds_title")
	private String title;
	@Column(name="ds_description")
	private String description;
	@Column(name="st_status")
	private Boolean status;
	@Column(name="dt_creation")
	private Date creation;
	@Column(name="dt_last_edition")
	private Date lastEdition;
	@Column(name="dt_removal")
	private Date removal;
	@Column(name="dt_conclusion")
	private Date conclusion;

	public Integer getId() {
    	return id;
  	}

  	public void setId(Integer id) {
    	this.id = id;
  	}

  	public String getTitle() {
    	return title;
  	}

  	public void setTitle(String title) {
    	this.title = title;
  	}

  	public String getDescription() {
    	return description;
  	}

  	public void setDescription(String description) {
    	this.description = description;
  	}

  	public Boolean getStatus() {
    	return status;
  	}

  	public void setStatus(Boolean status) {
    	this.status = status;
  	}


  	public Date getCreation() {
    	return creation;
  	}

  	public void setCreation(Date creation) {
    	this.creation = creation;
  	}

  	public Date getLastEdition() {
    	return lastEdition;
  	}

  	public void setLastEdition(Date lastEdition) {
    	this.lastEdition = lastEdition;
  	}

  	public Date getRemoval() {
    	return removal;
  	}

  	public void setRemoval(Date removal) {
    	this.removal = removal;
  	}

  	public Date getConclusion() {
    	return conclusion;
  	}

  	public void setConclusion(Date conclusion) {
    	this.conclusion = conclusion;
  	}

  
}