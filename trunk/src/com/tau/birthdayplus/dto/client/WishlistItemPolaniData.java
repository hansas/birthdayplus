package com.tau.birthdayplus.dto.client;

import com.google.gwt.user.client.rpc.IsSerializable;



public class WishlistItemPolaniData implements IsSerializable{
	   //item id
	   private String wishlistItemId;
	   //the item name 
	   private String itemName;
	   //price
	   private Double price;
	   //user's participation in this item
	   private Integer participation;
	   //event for which item was bought
	   private String eventName;

	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	public WishlistItemPolaniData(){	
		
	}
	/*
	 * constructor for server side
	 */
	public WishlistItemPolaniData(String wishlistItemId,String itemName, Double price,Integer participation,String eventName){
		this.setWishlistItemId(wishlistItemId);
		this.setItemName(itemName);
		this.setPrice(price);
		this.setParticipation(participation);
		this.setEventName(eventName);
	}
	
	public void setWishlistItemId(String wishlistItemId) {
		this.wishlistItemId = wishlistItemId;
	}
	public String getWishlistItemId() {
		return wishlistItemId;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPrice() {
		return price;
	}
	public void setParticipation(Integer participation) {
		this.participation = participation;
	}
	public Integer getParticipation() {
		return participation;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventName() {
		return eventName;
	}
	
}
