package com.labouardy.model;

public class URL {

	private Protocol protocol;
	
	private API api;
	
	private String hostname;
	
	private int port;

	public URL(){}
	
	public URL(Protocol protocol, API api, String hostname, int port) {
		super();
		this.protocol = protocol;
		this.api = api;
		this.hostname = hostname;
		this.port = port;
	}

	public URL setProtocol(Protocol protocol) {
		this.protocol = protocol;
		return this;
	}

	public URL setApi(API api) {
		this.api = api;
		return this;
	}

	public URL setHostname(String hostname) {
		this.hostname = hostname;
		return this;
	}

	public URL setPort(int port) {
		this.port = port;
		return this;
	}
	
	public URL build(){
		return new URL(protocol, api, hostname, port);
	}
	
	public String getURI(){
		StringBuffer str=new StringBuffer();
		str.append(protocol);
		str.append("://");
		str.append(hostname);
		str.append(":");
		str.append(port);
		return str.toString();
	}
}
