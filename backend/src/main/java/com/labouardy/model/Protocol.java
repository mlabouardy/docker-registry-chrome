package com.labouardy.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Protocol {
	HTTP("http"), HTTPS("https");
	
	private String value;
	
	Protocol(String value){
		this.value=value;
	}
	
	@JsonValue
	public String getValue(){
		return value;
	}
}
