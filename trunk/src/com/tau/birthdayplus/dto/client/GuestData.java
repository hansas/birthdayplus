package com.tau.birthdayplus.dto.client;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class GuestData implements IsSerializable{
	@PrimaryKey
	@Persistent
	private String id;
	@Persistent
	private String firstName;
	@Persistent
	private String lastName;
	@Persistent
	private Date birthday;

	/*
     * constructor for isSerializable
     * A user-defined class that serializable  must have a default (zero argument) constructor 
     * (with any access modifier) or no constructor at all.
     */
	 
	protected GuestData(){}

	public GuestData(String googleId, String firstName, String lastName, Date birthday) {
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
