package com.tau.birthdayplus.dto.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class WishlistItemNewData extends WishlistItemBaseData implements  IsSerializable{
	   //owner name , for "I buy" tab, to see who you buy for
	   private String userName;
	   //book this item for the event
	   private String eventId;
	   //event name, for "I buy" tab, to see for what event is this present
	   private String eventName;
	   
	   //people that want to buy this together
	   ArrayList<ParticipatorData> participators;
    
	   //person who will buy this present
	   private Buyer buyer;
	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	public WishlistItemNewData(){	
		
	}
	/*
	 * constructor for server side
	 */
	public WishlistItemNewData(String wishlistItemId,String userId,String userName,String itemName,String eventId,String eventName,String name, Integer priority, String link, Integer price){
		super(wishlistItemId,userId,name,priority,link,price);
		this.userName = userName;
		this.eventId = eventId;
		this.eventName = eventName;
		this.participators = new ArrayList<ParticipatorData>();
		this.buyer = null;
	}
	

	
	public String getUserName(){
		return this.userName;
	}
	
	public void setEventId(String eventId){
		this.eventId=eventId;
	}
	
	public String getEventId(){
		return this.eventId;
	}
	
	public String getEventName(){
		return this.eventName;
	}
	
	public void addParticipator(ParticipatorData p){
		this.participators.add(p);
	}
	
	public void removeParticipator(ParticipatorData p){
		this.participators.remove(p);
	}
	

	
	public ArrayList<ParticipatorData> getParticipators(){
		return this.participators;
	}
	
	public void setBuyer(Buyer buyer){
		this.buyer = buyer;
	}
	
	public Buyer getBuyer(){
		return this.buyer;
	}
	
	

}
