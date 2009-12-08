package com.tau.birthdayplus.dto.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChatMessageData implements IsSerializable{
	
	private String userId;
	private String userName;
	private Date timeStamp;
	private String message;
	
	
	//constructor for isSerializable
	private ChatMessageData(){
		
	}
	
	/*
	 *  constructor for server
	 */
	public ChatMessageData(String userId,String userName,Date timeStamp,String message){
		this.userId = userId;
		this.userName = userName;
		this.timeStamp = timeStamp;
		this.message = message;
	}
	
    /*
     * constructor for client
     */
	public ChatMessageData(String userId,String userName,String message){
		this(userId,userName , new Date(),message);
		
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public Date getTimeStamp(){
		return this.timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp){
		this.timeStamp = timeStamp;
	}
	
	public String getMesssage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}

}
