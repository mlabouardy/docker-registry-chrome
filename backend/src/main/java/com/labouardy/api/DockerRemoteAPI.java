package com.labouardy.api;

public class DockerRemoteAPI {
	
	public String pingRegistryV1(String basePath){
		return basePath+"/v1/_ping";
	}
	
	public String repositoriesResourceV1(String basePath){
		return basePath+"/v1/search";
	}
	
	public String tagsResourceV1(String basePath, String repository){
		return basePath+"/v1/repositories/"+repository+"/tags";
	}
	
	public String pingRegistryV2(String basePath){
		return basePath+"/v2";
	}
	
	public String repositoriesResourceV2(String basePath){
		return basePath+"/v2/_catalog";
	}
	
	public String tagsResourceV2(String basePath, String repository){
		return basePath+"/v2/"+repository+"/tags/list";
	}

	
	
}
