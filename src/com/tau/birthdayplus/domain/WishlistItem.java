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
	   private Key eventKey;
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
	   ArrayList<Participator> participators;
       //if booked or not
	   @Persistent
	   private Boolean isActive;
	   //key of the person that buys this item
	   @Persistent
	   private Key buyerKey;
	
	public WishlistItem(String userId, String name, Integer priority, String link, Integer price){
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.participators = new ArrayList<Participator>();
		this.setIsActive(true);
		this.buyerKey=null;
		this.setEventKey(null);
	}
	
	public WishlistItem(WishlistItemData itemData){
		this(itemData.getUserId(),itemData.getItemName(),itemData.getPriority(),itemData.getLink(),itemData.getPrice());
	}
	
	public void copyFromWishlistItemData(WishlistItemData itemData){
		setItemName(itemData.getItemName());
		setLink(itemData.getLink());
		setPriority(itemData.getPriority());
		setPrice(itemData.getPrice());
		//setBuyerKey(itemData.getBuyerKey());
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
	
	public void addParticipator(Participator p){
		if (this.participators == null){
			this.participators = new ArrayList<Participator>();
		}
		this.participators.add(p);
	}
	
	public void removeParticipator(Participator p){
		if ((participators!=null) && (participators.contains(p))){
			this.participators.remove(p);
		}
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	
	public ArrayList<Participator> getParticipators(){
		return this.participators;
	}
	
	public void setBuyerKey(Key buyerKey) {
		this.buyerKey = buyerKey;
	}

	public Key getBuyerKey() {
		return this.buyerKey;
	}
	
	public String toString(){
		return "name is: "+ itemName +" "+"priority is: "+priority+" "+"link is :" +link+"price is : "+ price;
	}

	public void setEventKey(Key eventKey) {
		this.eventKey = eventKey;
	}

	public Key getEventKey() {
		return eventKey;
	}
	
}
