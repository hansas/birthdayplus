package com.tau.birthdayplus.dal;

import java.io.Console;
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
//import com.tau.birthdayplus.dto.client.GuestData;

public class BusinessObjectDAL {
	
	public static Guest loadGuest(String guestId, PersistenceManager pm){
		Guest guest = null;
		try{
			guest = pm.getObjectById(Guest.class, guestId);
		}
		catch (Exception ex){
			System.out.print(ex.getMessage());
		}
		return guest; 
	}
	
	// Automatically open and close PMF
	public static Guest loadGuest(String guestId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest g = loadGuest(guestId, pm);
		pm.close();
		return g;
	}
	
	/*public static Guest loadGuest(String guestId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest guest = pm.getObjectById(Guest.class, guestId);
		pm.close();
		return guest;
	}*/
	
	public static void createProfile(Guest guest) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
           	pm.makePersistent(guest);
        } catch (Exception ex){
        	System.out.println(ex.getMessage());
        }
		finally {
        	pm.close();
        }
	}
	
	
	/*
	 *  We don't have a reference to the Event we want to update.
		 So we have to look it up first,
	 */
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
		}catch (Exception ex) {
		//	pm.currentTransaction().rollback();
			throw new RuntimeException("error in data base");
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    pm.close();
		}
		
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
		}catch (Exception ex) {
		//	pm.currentTransaction().rollback();
			throw new RuntimeException("error in data base");
		}finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    pm.close();
		}
		
	}
	
	public static void createEvent(EventData eventD){
		String userId = eventD.getUserId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		Event event = new Event(eventD);
		try
		{
		    tx.begin();
		    Guest user = pm.getObjectById(Guest.class, userId);
		    user.addEvent(event);
		    //event.setEventId(KeyFactory.keyToString(event.getKey()));
		  	pm.makePersistent(user);
		    pm.makePersistent(event);
		    tx.commit();
		}catch (Exception ex) {
			throw new RuntimeException("error in data base", ex);
		}finally
		{
		    if (tx.isActive()){
		        tx.rollback();
		    }
		    pm.close();
		}
	}
	
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
