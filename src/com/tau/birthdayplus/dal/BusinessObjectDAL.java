package com.tau.birthdayplus.dal;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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

	public static void updateEvent(EventData eventD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		try
		{
		    tx.begin();
		    Key eventKey = KeyFactory.stringToKey(eventD.getEventId());
   		    Event e = new Event(eventD);
		    e.setKey(eventKey);
		    pm.makePersistent(e);
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		}
		pm.close();
		throw new RuntimeException(new Exception("You cannot update someone else's event "));
	}
	
	//checks if user with id uId has eventD in his events
//	public static Boolean isMyEvent (String uId, EventData eventD){
//		Key parentKey = KeyFactory.stringToKey(eventD.getEventId());
//		return uId.equals(parentKey);
//	}
	
	public static void createEvent(EventData eventD){
		Event event = new Event(eventD);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(event);
		pm.close();
	}
}
