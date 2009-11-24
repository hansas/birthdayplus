package com.tau.birthdayplus.dto;

import java.util.ArrayList;
import java.util.List;



import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.domain.Participator;

public class WishlistItem implements  IsSerializable{
	   private String userId;
	   
	   private String wishlistItemId;

	   private String itemName;

	   private Integer priority;
	
	   private String link;
	
	   private Integer price;

	   private Boolean isActive;
	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	private WishlistItem(){
		
	}
	
	public WishlistItem(String userId,String wishlistItemId,String name, Integer priority, String link, Integer price){
		this.userId=userId;
		this.wishlistItemId=wishlistItemId;
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.setIsActive(true);
	}
	
	public WishlistItem(String userId,String name,Integer priority,String link,Integer price){
		this(userId,"",name,priority,link,price);
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
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActive() {
		return isActive;
	}

}