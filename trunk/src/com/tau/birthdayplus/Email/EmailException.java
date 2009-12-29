package com.tau.birthdayplus.Email;

public class EmailException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public EmailException(String message){
		this.message = message ;
	}
	
	public String getMessage(){
		return this.message;
		
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	

}
