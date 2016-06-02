package com.labouardy.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Registry implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Protocol protocol;
	
	private API api;
	
	private String hostname;
	
	private int port;
	
	private String username;
	
	private String password;
	
	@JsonProperty(value="protected")
	private boolean protectedRegistry;

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public API getApi() {
		return api;
	}

	public void setApi(API api) {
		this.api = api;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isProtectedRegistry() {
		return protectedRegistry;
	}

	public void setProtectedRegistry(boolean protectedRegistry) {
		this.protectedRegistry = protectedRegistry;
	}
}
