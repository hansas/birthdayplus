package com.tau.birthdayplus.dto;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;



public class Guest implements IsSerializable{
	private String id;

	private String firstName;

	private String lastName;

	private Date birthday;


	
	/*
     * constructor for isSerializable
     * A user-defined class that serializable  must have a default (zero argument) constructor 
     * (with any access modifier) or no constructor at all.
     */
	 
	private Guest(){
		
	}

	
	public Guest(String googleId, String firstName, String lastName, Date birthday) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday);
		this.id = googleId;
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

	

}
