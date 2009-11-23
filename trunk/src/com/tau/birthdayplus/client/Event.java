package com.tau.birthdayplus.client;

import com.google.appengine.api.datastore.Key;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)public class Event {
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
	   List<Participator> participators;
	   
	public Event(String name, Date date, Boolean recurrence){
		this.eventName = name;
		this.eventDate = date;
		this.recurrence = recurrence;
	}
	
	static Event getEventByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event e = pm.getObjectById(Event.class, key);
		return e;
	}
	
	public void setKey(Key key) {
		this.key = key;
	}
	public Key getKey() {
		return key;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setRecurrence(Boolean recurrence) {
		this.recurrence = recurrence;
	}
	public Boolean getRecurrence() {
		return recurrence;
	}
	
}
