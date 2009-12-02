package com.tau.birthdayplus.dto.client;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.domain.WishlistItem;


public class WishlistItemNewData extends WishlistItemData implements  IsSerializable{
	   //owner name , for "I buy" tab, to see who you buy for
	   private String userName;
	   //book this item for the event
	   private String eventId;
	   //event name, for "I buy" tab, to see for what event is this present
	   private String eventName;
	   
	   //people that want to buy this together
	   ArrayList<ParticipatorData> participators;
 
	   //person who will buy this present
	   private BuyerData buyer;
	
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
	public WishlistItemNewData(String wishlistItemId,String userId,String userName,String eventId,String eventName,String name, Integer priority, String link, Integer price,Boolean isActive){
		super(wishlistItemId,userId,name,priority,link,price,isActive);
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

	public void setParticipators(ArrayList<ParticipatorData> p){
		this.participators = p;
	}
	
	public ArrayList<ParticipatorData> getParticipators(){
		return this.participators;
	}
	
	public void setBuyer(BuyerData buyer){
		this.buyer = buyer;
	}
	
	public BuyerData getBuyer(){
		return this.buyer;
	}
	
	
}
