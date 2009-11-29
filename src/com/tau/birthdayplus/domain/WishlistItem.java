package com.tau.birthdayplus.domain;

import java.util.ArrayList;
import java.util.List;

//import com.google.appengine.api.datastore.Key;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
    public class WishlistItem
   {
	   @PrimaryKey
	   @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	   private Key key;
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
	
	public WishlistItem(String userId, String name, Integer priority, String link, Integer price,String giverId){
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.participators = new ArrayList<ParticipatorData>();
		this.setIsActive(true);
		this.giverId=giverId;
	}
	
	public WishlistItem(WishlistItemData itemData){
		this(itemData.getUserId(),itemData.getItemName(),itemData.getPriority(),itemData.getLink(),itemData.getPrice(),itemData.getGiverId());
	}
	
	public void copyFromWishlistItemData(WishlistItemData itemData){
		setItemName(itemData.getItemName());
		setLink(itemData.getLink());
		setPriority(itemData.getPriority());
		setPrice(itemData.getPrice());
		setGiverId(itemData.getGiverId());
	}
	
	public Key getKey() {
		return key;
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
	
}
