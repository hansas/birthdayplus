package com.tau.birthdayplus.dal;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;

public class DALWrapper {
	
	private PersistenceManager pm;
	
	public DALWrapper(){
		pm = PMF.get().getPersistenceManager();
	}

	public void close(){
		pm.close();
		pm=null;
	}
	
	public List<Guest> getGuestsById(ArrayList<String> UserIdList){
		return BusinessObjectDAL.getGuestsById(UserIdList, this.pm);
	}
	
	public Guest getGuestByKey(Key parentKey){
		return pm.getObjectById(Guest.class, parentKey);
	}
	
	public List<WishlistItem> getWishlist(String userId){
		return BusinessObjectDAL.getWishlist(userId, this.pm);
	}
	
	public List<WishlistItem> getWishlistForEvent(String userId,String eventId){
		return BusinessObjectDAL.getWishlistForEvent(userId,eventId,this.pm);
	}
	
	public Guest getGuestById(String userId){
		return BusinessObjectDAL.loadGuest(userId, pm);
	}
	
	public Event getEventByKey(Key eventKey){
		return pm.getObjectById(Event.class, eventKey);
	}
	
	public List<WishlistItem> getWishlistItemById(List<Key> keys){
		return  BusinessObjectDAL.getWishlistItemById(keys, pm);
	}
	
	
}
