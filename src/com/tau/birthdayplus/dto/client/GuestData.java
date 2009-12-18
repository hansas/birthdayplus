package com.tau.birthdayplus.dto.client;

import java.io.Serializable;
import java.util.Date;
import com.google.gwt.user.client.rpc.IsSerializable;


public class GuestData implements IsSerializable,Serializable{

	private String id;
	private String firstName;
	private String lastName;
	private Date birthday;
	private String email;

	/*
     * constructor for isSerializable
     * A user-defined class that serializable  must have a default (zero argument) constructor 
     * (with any access modifier) or no constructor at all.
     */
	 
	protected GuestData(){}

	public GuestData(String googleId, String firstName, String lastName, Date birthday) {
		this(googleId,firstName,lastName,birthday,"");
	}
	
	public GuestData(String googleId, String firstName, String lastName, Date birthday, String email) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday);
		this.id = googleId;
		this.email = email;
	}
	
	public String getId(){
		return this.id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public String toString(){
		return firstName+" "+lastName+" "+birthday;
		
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

}
