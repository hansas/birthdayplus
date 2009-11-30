package com.tau.birthdayplus.dto.client;

public class BuyerData extends ParticipatorData{
	

	//how to make contact with the buyer, his mail or phone ...
	private String contacts;
	
	BuyerData(String userId,String userFirstName,String userLastName,Integer money,String contacts){
		super(userId,userFirstName,userLastName,money);
		this.contacts = contacts;
	}
	

	
	public void setContacts(String contacts){
		this.contacts = contacts;
	}
	
	public String getContacts(){
		return this.contacts;
	}

}
