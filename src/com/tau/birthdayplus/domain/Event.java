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
import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.dto.client.EventData;



@PersistenceCapable(identityType = IdentityType.APPLICATION)public class Event extends EventData implements IsSerializable  {
    	private static final long serialVersionUID = 1L;

	   @PrimaryKey
	   @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	   private Key key;
	   
	   
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	public Event(){
		
	}
	   
	public Event(String eventName,Date eventDate,Boolean recurrence){
		super(eventName,eventDate,recurrence);
	}
	
	public Event(EventData eventD){
		this(eventD.getEventName(),eventD.getEventDate(),eventD.getRecurrence());
	}
	/*
	static Event getEventByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event e = pm.getObjectById(Event.class, key);
		return e;
	}
	*/
	
//	public void setKey(Key key) {
//		this.key = key;
//	}
	public Key getKey() {
		return key;
	}
	


	
}
