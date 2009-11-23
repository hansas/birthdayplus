package com.tau.birthdayplus.domain;

import java.util.ArrayList;
import java.util.List;

//import com.google.appengine.api.datastore.Key;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
    public class WishlistItem implements IsSerializable{
	   @PrimaryKey
	   @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	   private Long key;
	 //  private Key key;
	   @Persistent
	   private String itemName;
	   @Persistent
	   private Integer priority;
	   @Persistent
	   private String link;
	   @Persistent
	   private Integer price;
	   @Persistent
	   List<Participator> participators;
	   @Persistent
	   private Boolean isActive;
	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	private WishlistItem(){
		
	}
	public WishlistItem(String name, Integer priority, String link, Integer price){
		this.itemName = name;
		this.priority = priority;
		this.link = link;
		this.price = price;
		this.participators = new ArrayList<Participator>();
		this.setIsActive(true);
	}
	
	
	/*
	static WishlistItem getItemByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = pm.getObjectById(WishlistItem.class, key);
		return item;
	}
	  
	   */
	/*
	public void setKey(Key key) {
		this.key = key;
	}
	public Key getKey() {
		return key;
	}
	*/
	public void addParticipator(Participator p){
		this.participators.add(p);
	}
	
	public void removeParticipator(Participator p){
		this.participators.remove(p);
	}
	
	public void setKey(Long key) {
		this.key = key;
	}
	public Long getKey() {
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
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActive() {
		return isActive;
	}

	
}
