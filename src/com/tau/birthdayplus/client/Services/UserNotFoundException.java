package com.tau.birthdayplus.client.Services;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserNotFoundException extends Exception implements IsSerializable{
	
	public UserNotFoundException(){
		
	}
	
	public String getMessage(){
		return "can't get your profile";
	}

}
