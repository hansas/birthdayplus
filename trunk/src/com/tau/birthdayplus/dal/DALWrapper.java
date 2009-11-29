package com.tau.birthdayplus.dal;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.WishlistItem;

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
	
}
