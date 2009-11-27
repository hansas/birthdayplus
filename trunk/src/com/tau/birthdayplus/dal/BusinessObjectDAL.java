package com.tau.birthdayplus.dal;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import sun.misc.Sort;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.EventData;
//import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.logic.EventManagement;

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
	
	
	public static void updateEvent(EventData eventD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		try
		{
		    tx.begin();
   		    Event e = pm.getObjectById(Event.class, eventD.getEventId());
   		    e.copyFromEventData(eventD);
		    pm.makePersistent(e);
		    tx.commit();
		}catch (Exception ex) {
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
		    Guest parent = pm.getObjectById(Guest.class, eventD.getUserId());
   		    Event event = pm.getObjectById(Event.class, eventD.getEventId());
		    parent.removeEvent(event);
		    pm.makePersistent(parent);
		    pm.deletePersistent(event);
		    tx.commit();
		}catch (Exception ex) {
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
		    eventD.setEventId(KeyFactory.keyToString(event.getKey()));
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
		ArrayList<EventData> events = new ArrayList<EventData>();
		List<Guest> guests = null;
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		try{
			
			Query query = pm.newQuery(Guest.class);
			//query.declareImports("import com.google.appengine.api.datastore.KeyFactory; import com.google.appengine.api.datastore.Key;");
		    //query.setFilter("KeyFactory.keyToString(this.getKey().getParent()) == :keyList");
		    //query.setFilter("parent.id == :keyList");
		    query.setFilter("id == :keyList");
		    guests = (List<Guest>)query.execute(UserIdList);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		for (Guest guest: guests){
			List<Event> guestEvents = guest.getEvents();
			if ((guest!=null) && (!guestEvents.isEmpty())){
				for (Event event: guestEvents){
					events.add(EventManagement.eventToEventData(event));
				}
			}
		}
		pm.close();
		Collections.sort(events, EventManagement.EVENT_DATA_ORDER);
		return events;
	}
	

}
