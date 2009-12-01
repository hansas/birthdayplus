package com.tau.birthdayplus.dal;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.datanucleus.store.Extent;
import sun.misc.Sort;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
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
			int guestDom = guest.getBirthday().getDate();
			int guestMonth = guest.getBirthday().getMonth();
			Calendar cal = Calendar.getInstance();
			Date birthday = new Date(cal.get(Calendar.DAY_OF_YEAR),guestMonth,guestDom);
			int currentDom = cal.get(Calendar.DAY_OF_MONTH);
			int currentMonth = cal.get(Calendar.MONTH) + 1;
			if ((currentMonth > guestMonth)||((currentMonth == guestMonth)&&(currentDom > guestDom))){
				birthday.setYear(cal.get(Calendar.DAY_OF_YEAR)+1);
			}
			Event e = new Event("Birthday", guest.getId(), birthday, true);
			guest.addEvent(e);
           	pm.makePersistent(guest);
           	pm.makePersistent(e);
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
			throw new RuntimeException("error in data base: updateEvent");
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
			throw new RuntimeException("error in data base: deleteEvent");
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
		    eventD.setEventId(KeyFactory.keyToString(event.getKey()));
		  	pm.makePersistent(user);
		    pm.makePersistent(event);
		    tx.commit();
		}catch (Exception ex) {
			throw new RuntimeException("error in data base: createEvent", ex);
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
			System.out.println(ex.getMessage());
		}
		return guests;
	}
	
	public static void createWishlistItem(WishlistItemData itemData) {
		String userId = itemData.getUserId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		WishlistItem item = new WishlistItem(itemData);
		try
		{
		    tx.begin();
		    Guest user = BusinessObjectDAL.loadGuest(userId, pm);
		    user.addWishlistItem(item);
			itemData.setWishlistItemId(KeyFactory.keyToString(item.getKey()));
		  	pm.makePersistent(user);
		  	Key k = KeyFactory.createKey(Guest.class.getSimpleName(), "123");
		  	if (item.getKey().getParent().equals(k)){
		  		System.out.println("equals");
		  	}
		  	if (item.getKey().getParent().toString().equals(k.toString())){
		  		System.out.println("equals - toString");
		  	}
		  	else{
		  		System.out.println(item.getKey().getParent().toString());
		  		System.out.println(item.getKey().getParent());
		  		System.out.println(k);
		  	}
		    pm.makePersistent(item);
		  	k = KeyFactory.createKey(Guest.class.getSimpleName(), "123");
		  	if (item.getKey().getParent().equals(k)){
		  		System.out.println("equals");
		  	}
		  	if (item.getKey().getParent().toString().equals(k.toString())){
		  		System.out.println("equals - toString");
		  	}
		  	if (k==item.getKey().getParent()){
		  		System.out.println("ok");
		  	}
		  	if (item.getKey().getParent().toString()==k.toString()){
		  		System.out.println("ok toString");
		  	}
		  	else{
		  		System.out.println(item.getKey());
		  		System.out.println(item.getKey().getParent().toString());
		  		System.out.println(item.getKey().getParent());
		  		System.out.println(k);
		  	}
		    tx.commit();
		}catch (Exception ex) {
			throw new RuntimeException("error in data base: createWishlistItem", ex);
		}finally
 		{
		    if (tx.isActive()){
		        tx.rollback();
		    }
		    pm.close();
		}
	}
	
	public static void updateWishlistItem(WishlistItemData itemD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		try
		{
		    tx.begin();
   		    WishlistItem item = pm.getObjectById(WishlistItem.class, itemD.getWishlistItemId());
   		    item.copyFromWishlistItemData(itemD);
		    pm.makePersistent(item);
		    tx.commit();
		}catch (Exception ex) {
			throw new RuntimeException("error in data base: updateWishlistItem");
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
	
	public static void deleteWishlistItem(WishlistItemData itemD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction)pm.currentTransaction();
		try
		{
		    tx.begin();
		    int i =1;
		    Guest parent = BusinessObjectDAL.loadGuest(itemD.getUserId(), pm);
   		    WishlistItem item = pm.getObjectById(WishlistItem.class, itemD.getWishlistItemId());
		    parent.removeWishlistItem(item);
		    pm.makePersistent(parent);
		    pm.deletePersistent(item);
		    tx.commit();
		}catch (Exception ex) {
			throw new RuntimeException("error in data base: deleteWishlistItem");
		}finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    pm.close();
		}
	}
	
	public static List<WishlistItem> getWishlist(String userId,PersistenceManager pm) {
		List<WishlistItem> itemList = new ArrayList<WishlistItem>();
		Guest user = BusinessObjectDAL.loadGuest(userId, pm);
		try{
			itemList = user.getWishlistItems();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return itemList;
	}
	

}
