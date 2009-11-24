package com.tau.birthdayplus.dal;

import javax.jdo.PersistenceManager;

import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.EventData;



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

	public static void updateEvent(EventData event) {
		// TODO Auto-generated method stub
		
	}
	
	public static void createEvent(EventData eventD){
		Event event = new Event(eventD);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(event);
		pm.close();
	}
}
