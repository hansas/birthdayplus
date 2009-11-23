package com.tau.birthdayplus.client;

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
	   List<Proposal> proposals;
	
	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	private WishlistItem(){
		
	}
	public WishlistItem(String name, Integer priority){
		this.itemName = name;
		this.priority = priority;
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

	
}
