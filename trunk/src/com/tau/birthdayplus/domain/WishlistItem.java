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

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.dto.client.WishlistItemData;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
    public class WishlistItem extends WishlistItemData implements IsSerializable{
	   @PrimaryKey
	   @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	   private Key key;

	/*
	 * constructor for isSerializable
	 * A user-defined class that serializable  must have a default (zero argument) constructor 
	 * (with any access modifier) or no constructor at all.
	 */
	private WishlistItem(){}
	
	public WishlistItem(String name, Integer priority, String link, Integer price,String giverId){
		super(name,priority,link,price,giverId);
	}
	
	/*
	static WishlistItem getItemByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = pm.getObjectById(WishlistItem.class, key);
		return item;
	}  
	*/
	
//	public void setKey(Key key) {
//		this.key = key;
//	}
	public Key getKey() {
		return key;
	}
	



	
}
