package com.tau.birthdayplus.addItem;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AddItemException extends Exception implements IsSerializable{
	String message;
	
	private AddItemException(){
		
	}
	
	public AddItemException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}

}
