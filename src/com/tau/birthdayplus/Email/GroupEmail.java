package com.tau.birthdayplus.Email;

import java.util.ArrayList;
import java.util.Date;

import com.tau.birthdayplus.domain.Participator;

public class GroupEmail {
	//user full name of user that the present is for him
	private String userName;
	//event name
	private String eventName;
	//eventDate
	private Date eventDate;
	//price of this present
	private Double price;
	
	private String buyerId;
	private ArrayList<ParticipatorEmail> participators;
	
	public GroupEmail(String userName,String eventName,Date eventDate,Double price,String buyerId){
		this.userName = userName;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.price = price;
		this.buyerId = buyerId;
		participators = new ArrayList<ParticipatorEmail>();
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setEventName(String eventName){
		this.eventName = eventName;
	}
	
	public String getEventName(){
		return this.eventName;
	}
	
	public void setEventDate(Date eventDate){
		this.eventDate = eventDate;
	}
	
	public Date getEventDate(){
		return this.eventDate;
	}
	
	public void setPrice(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}
	
	public void setBuyerId(String buyerId){
		this.buyerId  = buyerId;
	}
	
	public String getBuyerId(){
		return this.buyerId;
	}
	
	
	public void addParticipator(ParticipatorEmail p){
		if (this.participators == null){
			this.participators = new ArrayList<ParticipatorEmail>();
		}
		this.participators.add(p);
	}
	
	public void removeParticipator(Participator p){
		if ((participators!=null) && (participators.contains(p))){
			this.participators.remove(p);
		}
	}
	
	public ArrayList<ParticipatorEmail> getParticipators(){
		return this.participators;
	}

}
