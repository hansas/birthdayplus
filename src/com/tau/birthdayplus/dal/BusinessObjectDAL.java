package com.tau.birthdayplus.dal;

import javax.jdo.PersistenceManager;

import com.tau.birthdayplus.domain.Guest;

public class BusinessObjectDAL {
	
	public static Guest loadGuest(String guestId, PersistenceManager pm){
		return pm.getObjectById(Guest.class, guestId);
	}
	
	// Automatically open and close PMF
	public static Guest loadGuest(String guestId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest g = loadGuest(guestId, pm);
		pm.close();
		return g;
	}
}
