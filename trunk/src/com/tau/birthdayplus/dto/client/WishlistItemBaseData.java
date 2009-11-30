package com.tau.birthdayplus.dto.client;

import java.util.ArrayList;

public class WishlistItemBaseData {
	 //item id
	   private String wishlistItemId;
	   //owner of this item 
	   private String userId;
	   //the item name 
	   private String itemName;
	   //priority
	   private Integer priority;
	   //link
	   private String link;
	   //price
	   private Integer price;

	   private Boolean isActive;

	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	public WishlistItemBaseData(){	
		
	}
	/*
	 * constructor for server side
	 */
	public WishlistItemBaseData(String wishlistItemId,String userId,String name, Integer priority, String link, Integer price){
		this.wishlistItemId=wishlistItemId;
		this.userId = userId;
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.setIsActive(true);
	}
	

	/*
	 * constructor for client side - create new item 
	 */
	public WishlistItemBaseData(String userId){
		this(null,userId,null,5,null,0);
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
