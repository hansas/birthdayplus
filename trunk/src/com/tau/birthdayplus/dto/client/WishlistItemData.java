package com.tau.birthdayplus.dto.client;

import java.util.ArrayList;

import javax.jdo.annotations.Persistent;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.domain.Participator;


public class WishlistItemData implements  IsSerializable{
	   //owner of this wishlist item
		@Persistent
	   private String userId;
	   //item id
	   @Persistent
	   private String wishlistItemId;
	   @Persistent
	   private String itemName;
	   @Persistent
	   private Integer priority;
	   @Persistent
	   private String link;
	   @Persistent
	   private Integer price;
	   //people that want to buy this together
	   @Persistent
	   ArrayList<ParticipatorData> participators;
       //if booked or not
	   @Persistent
	   private Boolean isActive;
	   //id of the person that booked this item
	   @Persistent
	   private String giverId;
	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	protected WishlistItemData(){
		
	}
	/*
	 * constructor for server side
	 */
	public WishlistItemData(String userId,String wishlistItemId,String name, Integer priority, String link, Integer price, String giverId){
		this.userId=userId;
		this.wishlistItemId=wishlistItemId;
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.participators = new ArrayList<ParticipatorData>();
		this.setIsActive(true);
		this.setGiverId(giverId);
	}
	/*
	 * constructor for client side (?) , don't know iem id, can generate(?)
	 */
	public WishlistItemData(String userId,String name,Integer priority,String link,Integer price,String giverId){
		this(userId,"",name,priority,link,price,giverId);
	}
	
	
	public String getUserId(){
		return this.userId;
	}
	
	public String getWishlistItemId(){
		return this.wishlistItemId;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPrice() {
		return price;
	}
	
	public void addParticipator(ParticipatorData p){
		this.participators.add(p);
	}
	
	public void removeParticipator(ParticipatorData p){
		this.participators.remove(p);
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	
	public ArrayList<ParticipatorData> getParticipators(){
		return this.participators;
	}
	
	private void setGiverId(String giverId) {
		this.giverId = giverId;
	}

	private String getGiverId() {
		return giverId;
	}

}
