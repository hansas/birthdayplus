package com.tau.birthdayplus.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ChatMessage {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key idKey;
	@Persistent
	private String id;
	@Persistent
	private Date timeStamp;
	@Persistent
	private String message;
	
	public ChatMessage(String userId,Date timeStamp,String message){
		this.id = userId;
		this.setTimeStamp(timeStamp);
		this.setMessage(message);
	}

	public Key getIdKey(){
		return idKey;
	}

	public String getId() {
		return id;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
