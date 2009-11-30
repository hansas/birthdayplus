package com.tau.birthdayplus.dto.client;

public class Buyer {
	
	private String userId;
	private String userFirstName;
	private String userLastName;
	//how to make contact with the buyer, his mail or phone ...
	private String contacts;
	
	Buyer(String userId, String userFirstName,String userLastName,String contacts){
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.contacts = contacts;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return this.userId;
		
	}
	
	public void setUserFirstName(String userFirstName){
		this.userFirstName = userFirstName;
	}
	
	public String getUserFirstName(){
		return this.userFirstName;
	}
	
	public void setuserLastName(String userLastName ){
		this.userLastName = userLastName;
	}
	
	public String getUserLastName(){
		return this.userLastName;
	}
	
	public void setContacts(String contacts){
		this.contacts = contacts;
	}
	
	public String getContacts(){
		return this.contacts;
	}

}
