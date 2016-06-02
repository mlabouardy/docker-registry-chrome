package com.labouardy.model;

import java.io.Serializable;

public class RepositoryInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Registry registry;
	
	private String repoName;

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	
	

}
