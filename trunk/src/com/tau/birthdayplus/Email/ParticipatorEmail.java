package com.tau.birthdayplus.Email;


import com.tau.birthdayplus.dto.client.ParticipatorData;

public class ParticipatorEmail extends ParticipatorData{
	String email;
	
	public ParticipatorEmail(String userId,String userFirstName,String userLastName,Integer money,String email){
		super(userId,userFirstName,userLastName,money);
		this.email = email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	
	
	

}
