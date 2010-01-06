package com.tau.birthdayplus.domain;

//import com.google.appengine.api.datastore.Key;
import java.util.Date;
import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.tau.birthdayplus.dto.client.EventData;

 

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Event  {
	   @PrimaryKey
	   @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	   private Key key;
	   
	   @Persistent
	   private String eventName;
	   @Persistent
	   private Date eventDate;
	   @Persistent
	   private Boolean recurrence;
	   @Persistent
	   private Boolean isDeleted;
	   
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	public Event(){   
		
	}
	   
	public Event(String eventName,String userId,Date eventDate,Boolean recurrence,Boolean isDeleted){
		   this.eventName = eventName;
		   this.eventDate = eventDate;
		   this.recurrence = recurrence;
		   this.isDeleted = isDeleted;
	}
	
	public Event(EventData eventD){
		this(eventD.getEventName(),eventD.getUserId(),eventD.getEventDate(),eventD.getRecurrence(),eventD.getIsDeleted());
	}
	
	public void copyFromEventData(EventData eventD){
		setEventName(eventD.getEventName());
		setEventDate(eventD.getEventDate());
		setRecurrence(eventD.getRecurrence());
		setIsDeleted(eventD.getIsDeleted());
	}
	/*
	static Event getEventByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event e = pm.getObjectById(Event.class, key);
		return e;
	}
	*/
	
	public void setKey(Key key) {
		this.key = key;
	}
	public Key getKey() {
		return key;
	}
	
	   public void setEventName(String eventName){
		   this.eventName=eventName;
	   }
	   
	   public String getEventName(){
		   return this.eventName;
	   }
	   
	   public void setEventDate(Date eventDate){
		   this.eventDate=eventDate;
	   }
	   
	   public Date getEventDate(){
		   return this.eventDate;
		   
	   }
	   
	   public void setRecurrence(Boolean recurrence){
		   this.recurrence=recurrence;
	   }
	   
	   public Boolean getRecurrence(){
		   return this.recurrence;
	   }

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}


	
}
