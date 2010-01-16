package com.tau.birthdayplus.client.widgets;

public enum Action {
    	CREATE("Add"), 
    	UPDATE("Update"),
    	NONE("None");
    	private String description;
    	
    	private Action(String description){
    		this.description = description;
    	}
    	
    	public String toString(){
    		return description;
    	}
    

}
