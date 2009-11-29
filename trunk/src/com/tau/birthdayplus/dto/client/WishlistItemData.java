package com.tau.birthdayplus.dto.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;


public class WishlistItemData implements  IsSerializable{
	   //item id
	   private String wishlistItemId;
	   private String userId;
	   private String itemName;
	   private Integer priority;
	   private String link;
	   private Integer price;
	   //people that want to buy this together
	   ArrayList<ParticipatorData> participators;
       //if booked or not
	   private Boolean isActive;
	   //id of the person that booked this item
	   private String giverId;
	
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
	public WishlistItemData(String wishlistItemId,String userId,String name, Integer priority, String link, Integer price, String giverId){
		this.wishlistItemId=wishlistItemId;
		this.userId = userId;
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.participators = new ArrayList<ParticipatorData>();
		this.setIsActive(true);
		this.setGiverId(giverId);
	}
	
	public WishlistItemData(String userId,String name,Integer priority,String link,Integer price,String giverId){
		this("",userId,name,priority,link,price,giverId);
	}
	
	public WishlistItemData(String userId,String name,Integer priority,String link,Integer price){
		this("",userId,name,priority,link,price,"");
	}
	
	//without userId?
	public WishlistItemData(String name,Integer priority,String link,Integer price){
		this("","",name,priority,link,price,"");
	}
	/*
	 * constructor for client side - create new item 
	 */
	public WishlistItemData(String userId){
		this("",userId,"",5,"",0,"");
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
	
	public void setGiverId(String giverId) {
		this.giverId = giverId;
	}

	public String getGiverId() {
		return giverId;
	}
	
	public String toString(){
		return "name is: "+ itemName +" "+"priority is: "+priority+" "+"link is :" +link+"price is : "+ price;
	}
	
	public String getUserId() {
		return userId;
	}

}
