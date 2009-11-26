package com.tau.birthdayplus.dal;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;

public class BusinessObjectDAL {
	
	public static GuestData loadGuestData(String guestId, PersistenceManager pm){
		return pm.getObjectById(GuestData.class, guestId);
	}
	
	// Automatically open and close PMF
	public static GuestData loadGuestData(String guestId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		GuestData g = loadGuestData(guestId, pm);
		pm.close();
		return g;
	}
	
	public static void createProfile(Guest guest, GuestData guestData) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
        	pm.makePersistent(guestData);
        	pm.makePersistent(guest);
        } finally {
        	pm.close();
        }
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
	}
	
	public static void deleteEvent(EventData eventD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		try
		{
		    tx.begin();
		    Key eventKey = KeyFactory.stringToKey(eventD.getEventId());
   		    Key parentKey = eventKey.getParent();
   		    Guest parent = pm.getObjectById(Guest.class, parentKey);
   		    Event event = pm.getObjectById(Event.class, eventKey);
		    parent.removeEvent(event);
		    pm.makePersistent(parent);
		    pm.deletePersistent(event);
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
	}
	
	public static void createEvent(EventData eventD){
		Key eventKey = KeyFactory.stringToKey(eventD.getEventId());
		Event event = new Event(eventD);
		event.setKey(eventKey);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(event);
		Key parentKey = eventKey.getParent();
		Guest parent = pm.getObjectById(Guest.class, parentKey);
		parent.addEvent(event);
		pm.makePersistent(parent);
		pm.close();
	}
	
	//checks if user with id uId has eventD in his events
//	public static Boolean isMyEvent (String uId, EventData eventD){
//		Key parentKey = KeyFactory.stringToKey(eventD.getEventId());
//		return uId.equals(parentKey);
//	}
	public static ArrayList<EventData> getEvents(ArrayList<String> UserIdList) {
		ArrayList<EventData> events;
		PersistenceManager pm = PMF.get().getPersistenceManager();    
		Query query = pm.newQuery(EventData.class);
		query.declareImports("import com.google.appengine.api.datastore.KeyFactory;");
	    query.setFilter("KeyFactory.keyToString((KeyFactory.stringToKey(this.eventId)).getParent()) == :keyList");
	    query.setOrdering("eventDate asceding");
	    events = (ArrayList<EventData>)query.execute(UserIdList);
	    events.size();
		pm.close();
		return events;
	}
	

}
