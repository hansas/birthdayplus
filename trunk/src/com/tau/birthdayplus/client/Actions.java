package com.tau.birthdayplus.client;

public enum Actions {
	CREATE("Add"), 
	REMOVE("Delete"), 
	UPDATE("Update");
	
	private String description;
	
	private Actions(String description){
		this.description = description;
	}
	
	public String toString(){
		return description;
	}
}
