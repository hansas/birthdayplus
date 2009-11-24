package com.tau.birthdayplus.dto.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;



public class EventData  implements IsSerializable{
	   //the owner of event
	   private String userId;
	   
	   private String eventId;
	  
	   private String eventName;
	   
	   private Date eventDate;
	   
	   private Boolean recurrence;
	   
	   /*
	    * constructor for isSerializable
	    */
	   private EventData(){
		   
	   }
	   
	   /*
	    * full constructor for the server side
	    */
	   public EventData(String userId,String eventId,String eventName,Date eventDate,Boolean recurrence){
		   this.eventId=userId;
		   this.eventId=eventId;
		   this.eventDate=eventDate;
		   this.recurrence=recurrence;
	   }
	   /*
	    * constructor for client side-don't have event id
	    */
	   public EventData(String userId,String eventName,Date eventDate,Boolean recurrence){
		   this(userId,"",eventName,eventDate,recurrence);
		   
	   }
	   
	   
	   public void setUserId(String userId){
		   this.userId=userId;;
	   }
	   
	   public String getUserId(){
		   return this.userId;
	   }
	   
	   public void setEventId(String eventId){
		   this.eventId=eventId;
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


}
