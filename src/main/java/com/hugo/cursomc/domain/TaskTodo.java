package com.hugo.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;


@Entity
@JsonTypeName("taskTodo")
public class TaskTodo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String description;
	private String state;
	@JsonFormat(pattern="dd/MM/yyyy") private Date tsCreate;	
	@JsonFormat(pattern="dd/MM/yyyy") private Date tsUpdate;
	
	public TaskTodo() {		
	}

	public TaskTodo(Integer id, String description) {
		super();
		this.id = id;
		this.setDescription(description);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getTsCreate() {
		return tsCreate;
	}

	public void setTsCreate(Date stCreate) {
		this.tsCreate = stCreate;
	}

	public Date getTsUpdate() {
		return tsUpdate;
	}

	public void setTsUpdate(Date tsUpdate) {
		this.tsUpdate = tsUpdate;
	}

	
}
