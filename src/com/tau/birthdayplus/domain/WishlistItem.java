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
	   private Boolean priority;
	   @Persistent
	   private String link;
	   @Persistent
	   private Double price;
	   //people that want to buy this together
	   @Persistent (defaultFetchGroup="true")
	   ArrayList<Participator> participators;
       //if booked or not
	   @Persistent
	   private Boolean isActive;
	   @Persistent
	   private String thumbnail;
	   //key of the person that buys this item
	   @Persistent 
	   private Key buyerKey;
	   @Persistent
	   private Boolean isDeleted;
	   @Persistent (defaultFetchGroup="true")
	   ArrayList<ChatMessage> messages;
	   
	
	public WishlistItem(String userId, String name, Boolean priority, String link, Double price,String thumbnail,Boolean isDeleted){
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.participators = new ArrayList<Participator>();
		this.messages = new ArrayList<ChatMessage>();
		this.setIsActive(true);
		this.thumbnail = thumbnail;
		this.buyerKey=null; 
		this.setEventKey(null);
		this.isDeleted = isDeleted;
	}
	
	public WishlistItem(WishlistItemData itemData){
		this(itemData.getUserId(),itemData.getItemName(),itemData.getPriority(),itemData.getLink(),itemData.getPrice(),itemData.getThumbnail(),itemData.getIsDeleted());
	}
	
	public void copyFromWishlistItemData(WishlistItemData itemData){
		setItemName(itemData.getItemName());
		setLink(itemData.getLink());
		setPriority(itemData.getPriority());
		setPrice(itemData.getPrice());
		setThumbnail(itemData.getThumbnail());
		setIsDeleted(itemData.getIsDeleted());
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
	
	public void addChatMessage(ChatMessage m){
		if (this.messages == null){
			this.messages = new ArrayList<ChatMessage>();
		}
		this.messages.add(m);
	}
	
	public void removeChatMessage(ChatMessage m){
		if ((messages!=null) && (messages.contains(m))){
			this.messages.remove(m);
		}
	}
	
	public ArrayList<ChatMessage> getMessages(){
		return this.messages;
	}
	
	public String getThumbnail(){
		return this.thumbnail;
	}
	
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}
	
}
