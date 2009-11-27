package com.tau.birthdayplus.dto.client;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.gwt.user.client.rpc.IsSerializable;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EventData  implements IsSerializable{
	   
	   @Persistent
	   private String eventId;
	   @NotPersistent
	   private String userId;
	   @Persistent
	   private String eventName;
	   @Persistent
	   private Date eventDate;
	   @Persistent
	   private Boolean recurrence;
	   
	   /*
	    * constructor for isSerializable
	    */
	   protected EventData(){}
	   
	   /*
	    * full constructor for the server side
	    */
	   public EventData(String eventId,String userId,String eventName,Date eventDate,Boolean recurrence){
		   this.eventId = eventId;
		   this.userId = userId;
		   this.eventName = eventName;
		   this.eventDate = eventDate;
		   this.recurrence = recurrence;
	   }
	   /*
	    * constructor for client side-don't have event id
	    */
	   public EventData(String eventName,String userId,Date eventDate,Boolean recurrence){
		   this("",userId,eventName,eventDate,recurrence);
		   
	   }
	   
  	   public String getEventId(){
		   return this.eventId;
		   
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

		public String getUserId() {
			return userId;
		}


}
