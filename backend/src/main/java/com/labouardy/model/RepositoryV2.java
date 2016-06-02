package com.labouardy.model;

import java.util.Collection;

public class RepositoryV2 implements Repository {

	private static final long serialVersionUID = 1L;
	
	private Collection<String> repositories;

	public Collection<String> getRepositories() {
		return repositories;
	}

	public void setRepositories(Collection<String> repositories) {
		this.repositories = repositories;
	}
}
