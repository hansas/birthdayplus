package com.tau.birthdayplus.dto.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;



public class WishlistItemData implements IsSerializable,Serializable{
	   //item id
	   private String wishlistItemId;
	   //owner of this item 
	   private String userId;
	   //the item name 
	   private String itemName;
	   //priority
	   private Boolean priority;
	   //link
	   private String link;
	   //price
	   private Double price;

	   private Boolean isActive;

	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	public WishlistItemData(){	
		
	}
	/*
	 * constructor for server side
	 */
	public WishlistItemData(String wishlistItemId,String userId,String name, Boolean priority, String link, Double price,Boolean isActive){
		this.wishlistItemId=wishlistItemId;
		this.userId = userId;
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.isActive = isActive;
	}
	

	/*
	 * constructor for client side - create new item 
	 */
	public WishlistItemData(String userId){
		this(null,userId,null,true,null,0.0,false);
	}
	
	public String getWishlistItemId(){
		return this.wishlistItemId;
	}
	
	public void setWishlistItemId(String itemId){
		this.wishlistItemId = itemId;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setPriority(Boolean priority) {
		this.priority = priority;
	}
	public Boolean getPriority() {
		return priority;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPrice() {
		return price;
	}
	

	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	
	public String getUserId() {
		return userId;
	}

}
