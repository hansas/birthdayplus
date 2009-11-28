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
import com.tau.birthdayplus.dto.client.GuestData;
//import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.logic.EventManagement;

public class BusinessObjectDAL {
	
	public static Guest loadGuest(String guestId, PersistenceManager pm){
		Guest guest = null;
		try{
			Key key = KeyFactory.createKey(Guest.class.getSimpleName(), guestId);
			guest = pm.getObjectById(Guest.class, key);
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
	
	public static void updateGuest(GuestData guestData) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest guest = loadGuest(guestData.getId(), pm);
		guest.copyFromGuestData(guestData);
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
		    int i =1;
		    Guest parent = BusinessObjectDAL.loadGuest(eventD.getUserId(), pm);
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
		    Guest user = BusinessObjectDAL.loadGuest(userId, pm);//pm.getObjectById(Guest.class, userId);
		    user.addEvent(event);
		    System.out.println(event.getKey());
		    eventD.setEventId(KeyFactory.keyToString(event.getKey()));
		    System.out.println(eventD.getEventId());
		    System.out.println(KeyFactory.stringToKey(eventD.getEventId()));
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
	
	public static List<Guest> getGuestsById(ArrayList<String> UserIdList, PersistenceManager pm) {
		List<Guest> guests = new ArrayList<Guest>();
		List<Key> keys = new ArrayList<Key>();
		for(String userId: UserIdList){
			Key k = KeyFactory.createKey(Guest.class.getSimpleName(), userId);
			keys.add(k);
		}
		try{
			
			Query query = pm.newQuery(Guest.class);
		    query.setFilter("idKey == :keyList");
		    guests = (List<Guest>)query.execute(keys);
		}
		catch(Exception ex){
			int i =0;
			System.out.println(ex.getMessage());
		}
		return guests;
	}
	

}
