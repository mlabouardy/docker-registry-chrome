package com.labouardy.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum API {
	V1("v1"), V2("v2");
	
	private String value;
	
	API(String value){
		this.value=value;
	}
	
	@JsonValue
	public String getValue(){
		return value;
	}
}
