package com.labouardy.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryV1 implements Repository {
	private static final long serialVersionUID = 1L;

	@JsonProperty("query")
	private String query;
	
	@JsonProperty("num_results")
	private int count;
	
	@JsonProperty("results")
	private Collection<Image> repositories;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Collection<Image> getRepositories() {
		return repositories;
	}

	public void setRepositories(Collection<Image> repositories) {
		this.repositories = repositories;
	}
}
