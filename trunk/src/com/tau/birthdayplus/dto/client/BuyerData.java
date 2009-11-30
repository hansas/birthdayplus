package com.tau.birthdayplus.dto.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BuyerData extends ParticipatorData implements IsSerializable{
	

	//how to make contact with the buyer, his mail or phone ...
	private String contacts;
	
	private BuyerData(){
		
	}
	
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
