package com.tau.birthdayplus.Email;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EmailException extends Exception implements IsSerializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public EmailException(String message){
		this.message = message ;
	}
	
	private EmailException(){
		
	}
	
	public String getMessage(){
		return this.message;
		
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	

}
