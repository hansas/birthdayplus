package com.tau.birthdayplus.dto.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EventData  implements IsSerializable{
	   private String eventId;
	   private String userId;

	   private String eventName;
	   private Date eventDate;
	   private Boolean recurrence;
	   private Boolean isDeleted;
	   /*
	    * constructor for isSerializable
	    */
	   public EventData(){
		   this(null,null,null,null,false,false);
	   }
	   
	   /*
	    * full constructor for the server side
	    */
	   public EventData(String eventId,String userId,String eventName,Date eventDate,Boolean recurrence,Boolean isDeleted){
		   this.eventId = eventId;
		   this.userId = userId;
		   this.eventName = eventName;
		   this.eventDate = eventDate;
		   this.recurrence = recurrence;
		   this.isDeleted = isDeleted;
	   }
	   /*
	    * constructor for client side , knows only user id
	    */
	   public EventData(String userId){
		   this(null,userId,null,null,false,false);
		   
	   }
	   
  	   public String getEventId(){
		   return this.eventId;
		   
	   }
  	   
	   public void setEventId(String eventId){
		   this.eventId = eventId;
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

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Boolean getIsDeleted() {
			return isDeleted;
		}
		
		


}
