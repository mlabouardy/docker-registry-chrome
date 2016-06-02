package com.labouardy.model;

import java.io.Serializable;

public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String description;
	
	public Image(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
