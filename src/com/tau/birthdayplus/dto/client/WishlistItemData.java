package com.tau.birthdayplus.dto.client;

import java.util.ArrayList;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.NotPersistent;

import com.google.gwt.user.client.rpc.IsSerializable;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class WishlistItemData implements  IsSerializable{
	   //item id
	   @Persistent
	   private String wishlistItemId;
	   @NotPersistent
	   private String userId;
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
	public WishlistItemData(){
		participators=new ArrayList<ParticipatorData>();
		
	}
	/*
	 * constructor for server side
	 */
	public WishlistItemData(String wishlistItemId,String userId,String name, Integer priority, String link, Integer price, String giverId){
		this.wishlistItemId=wishlistItemId;
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
	/*
	 * constructor for client side 
	 */
	public WishlistItemData(String userId,String name,Integer priority,String link,Integer price){
		this("",userId,name,priority,link,price,"");
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
	
	public String toString(){
		return "name is: "+ itemName +" "+"priority is: "+priority+" "+"link is :" +link+"price is : "+ price;
	}
	
	public String getUserId() {
		return userId;
	}

}
