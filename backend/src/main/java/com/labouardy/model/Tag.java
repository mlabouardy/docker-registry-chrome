package com.labouardy.model;

import java.util.Collection;

public class Tag {
	private String name;
	
	private Collection<String> tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
}
