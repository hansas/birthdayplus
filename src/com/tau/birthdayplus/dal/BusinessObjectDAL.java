package com.tau.birthdayplus.dal;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedMap;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.datanucleus.store.Extent;
import sun.misc.Sort;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.Email.EmailException;
import com.tau.birthdayplus.Email.GroupEmail;
import com.tau.birthdayplus.Email.ParticipatorEmail;
import com.tau.birthdayplus.Email.SendEmail;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.domain.ChatMessage;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData; //import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;
import com.tau.birthdayplus.logic.EventManagement;
import com.tau.birthdayplus.logic.WishlistManagement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BusinessObjectDAL {
	
	public static enum GroupStatus {OPEN,CLOSE,CANCEL}
	
	private static final Logger log = Logger.getLogger(BusinessObjectDAL.class.getName());
	
	public static final Comparator<WishlistItemPolaniData> WISHLISTITEMPOLANI_DATA_ORDER =
        new Comparator<WishlistItemPolaniData>() {
			public int compare(WishlistItemPolaniData e1, WishlistItemPolaniData e2) {
					if ((e1.getEventDate() == null) || (e2.getEventDate()==null)){
						return 0;
					}
					int result = e2.getEventDate().compareTo(e1.getEventDate());
					if(result==-1){
						return -1;
					}
					else if (result==1){
						return 1;
					}
					else{
						return 0;
					}
				}
		};

	
	public static Guest loadGuest(String guestId, PersistenceManager pm) throws UserNotFoundException, UserException {
		Guest guest = null;
		try{
		Key key = KeyFactory.createKey(Guest.class.getSimpleName(), guestId);
		guest = pm.getObjectById(Guest.class, key);
		}catch(JDOObjectNotFoundException ex){
			throw new UserNotFoundException();
		}catch(Exception ex){
			log.log(Level.SEVERE, "the log from loadGuest", ex);
			throw new UserException("can't get your profile");
		}
		return guest;
	}

	// Automatically open and close PMF
	public static Guest loadGuest(String guestId) throws UserNotFoundException, UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest g = null;
		g = loadGuest(guestId, pm);
		pm.close();
		return g;
	}
	
	public static Guest loadGuestByGoogleId(String id,PersistenceManager pm) throws UserException{
		List<Guest> guest = new ArrayList<Guest>();
		try{
			Query query = pm.newQuery(Guest.class);
			query.setFilter("googleId == id");
			query.declareParameters("String id");
			guest = (List<Guest>)query.execute(id);
			if ((guest!=null)&&(!guest.isEmpty())){
				return guest.get(0);
			}
			else{
				return null;
			}
		} catch (Exception ex) {
			log.log(Level.SEVERE, "the log from loadGuestByGoogleId", ex);
			throw new UserException("cannot get your profile by google id");
		}
	}
	
	public static Guest loadGuestByGmail(String gmail,PersistenceManager pm) throws UserException{
		List<Guest> guest = new ArrayList<Guest>();
		try{
			Query query = pm.newQuery(Guest.class);
			query.setFilter("uppercaseEmail == gmail");
			query.declareParameters("String gmail");
			guest =(List<Guest>)query.execute(gmail.toUpperCase());
			if ((guest!=null) && (!guest.isEmpty())){
				return guest.get(0);
			}
			else{
				return null;
			}
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from loadGuestByGmail", ex);

			throw new UserException("cannot get your profile by gmail");
		}
	}

	public static void createProfile(Guest guest,Event e) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			guest.addEvent(e);
			pm.makePersistent(guest);
			pm.makePersistent(e);
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from createProfile", ex);
			throw new UserException("failed to create profile, please try later");
		} finally {
			pm.close();
		}
	}

	public static void updateGuest(GuestData guestData) throws UserNotFoundException, UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest guest = loadGuest(guestData.getId(), pm);
		guest.copyFromGuestData(guestData);
		try {
			pm.makePersistent(guest);
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from updateGuest", ex);
			throw new UserException("an error occured while updating your profile, please try again later");
		} finally {
			pm.close();
		}
	}

	public static void updateEvent(EventData eventD,String eventId) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			Event e;
			if (eventD.getEventId()==null){
				e = pm.getObjectById(Event.class, eventId);
			}
			else{
				e = pm.getObjectById(Event.class, eventD.getEventId());
			}
			e.copyFromEventData(eventD);
			pm.makePersistent(e);
			tx.commit();
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from updateEvent", ex);
			throw new UserException("an error occured while updating the event, please try again later");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
		
	public static void deleteEvent(EventData eventD,ArrayList<WishlistItem> itemParticipatorDelete,Event ev,PersistenceManager pm) throws UserException {
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			Guest parent = BusinessObjectDAL.loadGuest(eventD.getUserId(), pm);
			Event event = pm.getObjectById(Event.class, eventD.getEventId());
			if (mayIDeleteEvent(event,pm,itemParticipatorDelete,ev)){
				parent.removeEvent(event);
				pm.makePersistent(parent);
				pm.deletePersistent(event);
			}
			else{
				event.setIsDeleted(true);
				pm.makePersistent(event);
			}
			tx.commit();
		} 
		catch (RuntimeException e)
        {
         	log.log(Level.SEVERE, "the log from deleteEvent", e);
            throw new UserException("an error occured while deleting the event, please try again later");
        }
        catch (Exception e)
        {
         	log.log(Level.SEVERE, "the log from deleteEvent", e);
            throw new UserException("an error occured while deleting the event, please try again later");
        } 
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	public static Boolean mayIDeleteEvent(Event event,PersistenceManager pm,ArrayList<WishlistItem> itemParticipatorDelete,Event ev) throws UserException{
		List<WishlistItem> items = new ArrayList<WishlistItem>();
		ev.setEventName(event.getEventName());
		ev.setEventDate(ev.getEventDate());
		ev.setIsDeleted(event.getIsDeleted());
		ev.setRecurrence(event.getRecurrence());
		ev.setKey(event.getKey());
		Boolean result = true;
		try {
			Query query = pm.newQuery(WishlistItem.class);
			query.declareImports("import com.google.appengine.api.datastore.Key");
			query.setFilter("eventKey == ekey");
			query.declareParameters("Key ekey");
			items = (List<WishlistItem>) query.execute(event.getKey());
		if (items!=null){
			for (WishlistItem item : items){
					if (item.getBuyerKey()!=null){
						result = false;
					}
					else{
						List<Participator> participators = item.getParticipators();
						if(participators==null){
						}
						else{
							removeChatMessageData(KeyFactory.keyToString(item.getKey()),pm);
							try{
								itemParticipatorDelete.add(item);
								Key k = item.getEventKey();
								item.setEventKey(null);
								pm.makePersistent(item);
							}
							catch (Exception ex) {
								log.severe("Error in second part of mayIDeleteEvent in item "+item.getItemName());
								throw new UserException("Error mayIDeleteEvent in item "+item.getItemName());
							}
						}
					}
				}
			}
		}
		catch (RuntimeException e)
        {
         	log.log(Level.SEVERE, "the log from mayIDeleteEvent", e);
            throw new UserException("Error mayIDeleteEvent");
        }
		catch (Exception ex) 
		{
         	log.log(Level.SEVERE, "the log from mayIDeleteEvent", ex);
			throw new UserException("Error mayIDeleteEvent");
		}
		return result;
	}
	
	public static void cronDeleteEventAndUpdateRecurrent(ArrayList<WishlistItem> itemParticipatorDelete,Event event,PersistenceManager pm) throws UserException{
		List<Guest> guests = new ArrayList<Guest>();
		Query query = pm.newQuery(Guest.class);
		guests = (List<Guest>)query.execute();
		Calendar cal = Calendar.getInstance();
		Calendar eDate = Calendar.getInstance();
		if (guests!=null){
			for (Guest g : guests){
				List<Event> events = g.getEvents();
				if (events!=null){
					for (Event e : events){
						int eMonth = e.getEventDate().getMonth();
						int eDay = e.getEventDate().getDate();
						eDate.clear();
						eDate.set(Calendar.YEAR, e.getEventDate().getYear()+1900);
						eDate.set(Calendar.MONTH, eMonth);
						eDate.set(Calendar.DATE, eDay);
						eDate.add(Calendar.DATE, 1);
						if ((cal.after(eDate))&&(e.getRecurrence()==false)){
							Boolean result = mayIDeleteEvent(e,pm,itemParticipatorDelete,event);
							if (result){
								g.removeEvent(e);
								pm.makePersistent(g);
								pm.deletePersistent(e);
							}
							else{
								e.setIsDeleted(true);
								pm.makePersistent(e);
							}
						}
						else if((e.getRecurrence()==true)&&(cal.after(eDate))){
							Date newEDate = new Date(cal.get(Calendar.YEAR)-1900+1,eMonth,eDay);
							e.setEventDate(newEDate);
							pm.makePersistent(e);
						}
					}
				}
			}
		}
	}
	
	public static void cronItemJob(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Guest> guests = new ArrayList<Guest>();
		Query query = pm.newQuery(Guest.class);
		guests = (List<Guest>)query.execute();
		if (guests!=null){
			for (Guest g : guests){
				List<WishlistItem> items = g.getWishlistItems();
				if (items!=null){
					for (WishlistItem item : items){
						if ((item.getBuyerKey()==null)&&(item.getIsDeleted()==true)){
							g.removeWishlistItem(item);
							pm.makePersistent(g);
							pm.deletePersistent(item);
						}
					}
				}
			}
		}
		pm.close();
	}


	public static Event newCreateEvent(EventData eventD,Guest user,PersistenceManager pm) throws UserException{
		Transaction tx = (Transaction) pm.currentTransaction();
		Event event = new Event(eventD);
		try {
			tx.begin();
			user.addEvent(event);
			eventD.setEventId(KeyFactory.keyToString(event.getKey()));
			pm.makePersistent(user);
			pm.makePersistent(event);
			tx.commit();
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from newCreateEvent", ex);
			throw new UserException("an error occured while creating the event, please try again later");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return event;
	}
	
	public static Event loadEvent(String eventId, PersistenceManager pm) throws UserException {
		Event event = null;
		try {
			Key key = KeyFactory.stringToKey(eventId);
			event = pm.getObjectById(Event.class, key);
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from loadEvent", ex);
			throw new UserException("an error occured while loading the event, probably this event was deleted, try to refresh the page");
		}
		return event;
	}

	// Automatically open and close PMF
	public static Event loadEvent(String eventId) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event event = loadEvent(eventId, pm);
		pm.close();
		return event;
	}

	public static List<Guest> getGuestsById(ArrayList<String> UserIdList,PersistenceManager pm) throws UserException {
		List<Guest> guests = new ArrayList<Guest>();
		List<Key> keys = new ArrayList<Key>();
		for (String userId : UserIdList) {
			Key k = KeyFactory.createKey(Guest.class.getSimpleName(), userId);
			keys.add(k);
		}
		try {

			Query query = pm.newQuery(Guest.class);
			query.setFilter("idKey == :keyList");
			guests = (List<Guest>) query.execute(keys);
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from getGuestsById", ex);
			throw new UserException("an error occured while getting your profile");
		}
		return guests;
	}

	public static void createWishlistItem(WishlistItemData itemData,DALWrapper wrapper) throws UserNotFoundException, UserException {
		String userId = itemData.getUserId();
		Guest user = wrapper.getGuestById(userId);
		wrapper.newCreateWishlistItem(itemData,user);
	}
	
	public static void newCreateWishlistItem(WishlistItemData itemData,Guest user,PersistenceManager pm) throws UserException{
		Transaction tx = (Transaction) pm.currentTransaction();
		WishlistItem item = new WishlistItem(itemData);
		try {
			tx.begin();
			user.addWishlistItem(item); 
			itemData.setWishlistItemId(KeyFactory.keyToString(item.getKey()));
			pm.makePersistent(user);
			pm.makePersistent(item);
			tx.commit();
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from newCreateWishlistItem", ex);
			throw new UserException("an error occured while creating the wishlist item,please try again later");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	public static WishlistItem loadWishlistItem(String wishlistItemId, PersistenceManager pm) throws UserException {
		WishlistItem item = null;
		try {
			Key key = KeyFactory.stringToKey(wishlistItemId);
			item = pm.getObjectById(WishlistItem.class, key);
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from loadWishlistItem", ex);
			throw new UserException("an error occured while loading the wishlist item");
		}
		return item;
	}

	// Automatically open and close PMF
	public static WishlistItem loadWishlistItem(String wishlistItemId) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		pm.close();
		return item;
	}	

	public static WishlistItem updateWishlistItem(WishlistItemData itemD) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			WishlistItem item = pm.getObjectById(WishlistItem.class, itemD
					.getWishlistItemId());
			item.copyFromWishlistItemData(itemD);
			pm.makePersistent(item);
			tx.commit();
			return item;
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from updateWishlistItem", ex);
			throw new UserException("an error occured while updating the wishlist item,please try again later");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public static WishlistItem deleteWishlistItem(WishlistItemData itemD,PersistenceManager pm) throws UserException {
		Transaction tx = (Transaction) pm.currentTransaction();
		WishlistItem item = null;
		try {
			tx.begin();
			Guest parent = BusinessObjectDAL.loadGuest(itemD.getUserId(), pm);
			item = pm.getObjectById(WishlistItem.class, itemD.getWishlistItemId());
			if (item.getBuyerKey()==null){ //User may delete item iff there is no buyers
				if((item.getParticipators()!=null)&&(!item.getParticipators().isEmpty())){
					List<Participator> participators = item.getParticipators();
					for (Participator p : participators){
						pm.deletePersistent(p);
					}
				}
				parent.removeWishlistItem(item);
				pm.makePersistent(parent);
				removeChatMessageData(itemD.getWishlistItemId(), pm);
				pm.deletePersistent(item);
			}
			else{
				item.setIsDeleted(true);
				pm.makePersistent(item);
				//throw new UserException("You may not delete this item, because there are people that want to buy it to you");
			}
			tx.commit();
		} 
		catch (RuntimeException e)
        {
         	log.log(Level.SEVERE, "the log from deleteWishlistItem", e);
            throw new UserException("an error occured while deleting the wishlist item,please try again later");
        }
        catch (Exception e)
        {
         	log.log(Level.SEVERE, "the log from deleteWishlistItem", e);
            throw new UserException("an error occured while deleting the wishlist item,please try again later");
        }
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return item;
	}
	
	public static List<WishlistItem> getWishlist(String userId,PersistenceManager pm) throws UserNotFoundException, UserException {
		List<WishlistItem> itemList = new ArrayList<WishlistItem>();
		Guest user = BusinessObjectDAL.loadGuest(userId, pm);
		try {
			for (WishlistItem item : user.getWishlistItems()){
				if (!item.getIsDeleted()){
					itemList.add(item);
				}
			}
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from getWishlist", ex);

			throw new UserException("can't get your wishlist,please try again later");
		}
		return itemList;
	}
	
	public static List<WishlistItem> getWishlistItemById(List<Key> keys,PersistenceManager pm) throws UserException {
		List<WishlistItem> items = new ArrayList<WishlistItem>();
		try {
			Query query = pm.newQuery(WishlistItem.class);
			query.setFilter("key == :keyList");
			items = (List<WishlistItem>) query.execute(keys);
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from getWishlistItemById", ex);
			throw new UserException("an error occured while getting the wishlist item");
		}
		return items;
	}
	
	public static List<WishlistItem> getBookedWishlistItems2(String userId,PersistenceManager pm)
	throws UserNotFoundException, UserException{
		List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();
		List<Participator> partisipators = new ArrayList<Participator>();
		List<WishlistItem> buyers = new ArrayList<WishlistItem>();
		Guest g = loadGuest(userId, pm);
		try {

			Query query = pm.newQuery(Participator.class);
			query.setFilter("id == gid");
			query.declareParameters("String gid");
			partisipators = (List<Participator>) query.execute(g.getId());
		} catch (Exception ex) {
			log.log(Level.SEVERE,"Error in getBookedWishlistItems2's first query");
			throw new UserException("can't get your booked wishlist items, please try later");
		}
		if (partisipators==null){
		}
		else{
			for (Participator p : partisipators){
				WishlistItem item = loadWishlistItem(KeyFactory.keyToString(p.getIdKey().getParent()),pm);
				wishlistItems.add(item);
			}
		}
		try {
			Query query = pm.newQuery(WishlistItem.class);
			query.declareImports("import com.google.appengine.api.datastore.Key");
			query.setFilter("buyerKey == gkey");
			query.declareParameters("Key gkey");
			buyers = (List<WishlistItem>) query.execute(g.getIdKey());
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "Error in getBookedWishlistItems2's second query", ex);
			throw new UserException("can't get your booked wishlist items, please try later");
		}
		if (buyers==null){
		}
		else{
			Calendar cal = Calendar.getInstance();
			for (WishlistItem buyer : buyers){
				Event e = pm.getObjectById(Event.class, buyer.getEventKey());
				if (!wishlistItems.contains(buyer)){
					if (!buyer.getIsDeleted()||(buyer.getIsDeleted()&&(e.getEventDate().getMonth()==cal.get(Calendar.MONTH)))){
						wishlistItems.add(buyer);
					}
				}
			}
		}
		return wishlistItems;
	}
	
	public static ArrayList<WishlistItemPolaniData> getLastItemsForUser(String myUserId,String anotherUserId,
		PersistenceManager pm) throws UserNotFoundException, UserException {
		Key buyer = KeyFactory.createKey(Guest.class.getSimpleName(), anotherUserId);
		Key myKey = KeyFactory.createKey(Guest.class.getSimpleName(), myUserId);
		Guest guest = loadGuest(myUserId, pm);
		List<WishlistItem> myItems = guest.getWishlistItems();
		List<Event> myEvents = guest.getEvents();
		PriorityQueue<WishlistItemPolaniData> results = new PriorityQueue<WishlistItemPolaniData>(4, WISHLISTITEMPOLANI_DATA_ORDER);

		HashMap<Key, Event> events = new HashMap<Key, Event>();
		for(Event e : myEvents){
			events.put(e.getKey(),e);
		}
		Calendar cal = Calendar.getInstance();
		for (WishlistItem item : myItems){
			if (item.getBuyerKey()!=null){
				if (item.getBuyerKey().equals(buyer)){
					Date eventDate = events.get(item.getEventKey()).getEventDate();
					Calendar eDate = Calendar.getInstance();
					eDate.clear();
					eDate.set(Calendar.YEAR, eventDate.getYear()+1900);
					eDate.set(Calendar.MONTH, eventDate.getMonth());
					eDate.set(Calendar.DATE, eventDate.getDate());
					if (cal.after(eDate)){
						addThreeSortedItems(item,events.get(item.getEventKey()),item.getPrice(), results);
					}
				}
				else{
					List<Participator> participators = item.getParticipators();
					for (Participator p : participators){
						if (p.getId().equals(anotherUserId)){
							addThreeSortedItems(item,events.get(item.getEventKey()),p.getMoney(), results);
							break;
						}
					}
				}
			}
		}
		
		ArrayList<WishlistItemPolaniData> wishlistItems = new ArrayList<WishlistItemPolaniData>(results);
		return wishlistItems;
	}
	
	public static void addThreeSortedItems(WishlistItem item,Event e, double price, PriorityQueue<WishlistItemPolaniData> items){
		WishlistItemPolaniData polani = new WishlistItemPolaniData(KeyFactory.keyToString(item.getKey()),item.getItemName(), item.getPrice(), (int)price, e.getEventName());
		items.add(polani);
		if (items.size()>3)
		{
			items.remove();
		}
	}
	
	public static void bookItemForUser(String wishlistItemId, String eventId,String userId) throws UserNotFoundException, UserException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event event = loadEvent(eventId);
		if ((event==null)||event.getIsDeleted()){
			throw new UserException("The event was deleted, please refresh the page");
		}
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			if (item.getEventKey()==null){
				Key eventKey = KeyFactory.stringToKey(eventId);
				Guest guest = loadGuest(userId, pm);
				item.setEventKey(eventKey);
				item.setBuyerKey(guest.getIdKey());
				item.setIsActive(false);
				tx.begin();
				pm.makePersistent(item);
				tx.commit();
				}
			else{
				throw new UserException("This item have already been booked, you may only participate in it's buying");
	        }
		}
        catch (RuntimeException e)
        {
         	log.log(Level.SEVERE, "the log from getWishlistItemId", e);
            throw new UserException("an error occured while booking the wishlist item for you,please try again later");
        }
        catch (Exception e)
        {
         	log.log(Level.SEVERE, "the log from getWishlistItemId", e);
            throw new UserException("an error occured while booking the wishlist item for you,please try again later");
        }
        finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public static void cancelBookItemForUser(String wishlistItemId, String userId) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		Guest guest = pm.getObjectById(Guest.class, item.getBuyerKey());
		if (guest.getId().equals(userId)){
			Transaction tx = (Transaction) pm.currentTransaction();
			try {
				tx.begin();
				pm.makePersistent(guest);
				item.setBuyerKey(null);
				item.setEventKey(null);
				item.setIsActive(true);
				pm.makePersistent(item);
				tx.commit();
			} catch (Exception ex) {
	         	log.log(Level.SEVERE, "the log from cancelBookItemForUser", ex);
				throw new UserException("an error occured while canceling the booked wishlist item for you,please try again later");
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public static void bookItemForGroup(String itemId, String userId,PersistenceManager pm) throws UserNotFoundException, UserException{
		WishlistItem item = loadWishlistItem(itemId, pm);
		if (item==null){
			log.severe("There is no item with item id "+itemId);
		}
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			if (item.getBuyerKey()==null){
				tx.begin();
				Guest guest = loadGuest(userId, pm);
				item.setBuyerKey(guest.getIdKey());
				item.setIsActive(false);
				pm.makePersistent(item);
				tx.commit();
			}
			else{
				throw new UserException("The buyer for this item already exists");
			}
		}
        catch (Exception e)
        {
         	log.log(Level.SEVERE, "the log from bookItemForGroup", e);
            throw new UserException("an error occured while booking the wishlist item for group,please try again later");
        }
        finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	public static void sendEmailToGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE,PersistenceManager pm,Double actualPrice,GroupStatus status,SendEmail.CancelFor cancelFor,Event ev) throws  UserException{
		try{
			if (participatorsE!=null){
				WishlistItem item = loadWishlistItem(itemId, pm);
				Guest itemUser = pm.getObjectById(Guest.class,item.getKey().getParent());
				Event event;
				if (item.getEventKey()==null){
					event = ev;
				}
				else{
					event = pm.getObjectById(Event.class,item.getEventKey());
				}
				String fullName = itemUser.getFirstName()+" "+itemUser.getLastName();
				GroupEmail group = new GroupEmail(item.getItemName(),fullName,event.getEventName(),event.getEventDate(),item.getPrice(),userId);
				for (ParticipatorEmail p : participatorsE){
					group.addParticipator(p);
				}
				switch(status){
					case OPEN:
						SendEmail.sendEmailOpenGroup(group, message);
						break;
					case CLOSE:
						SendEmail.sendEmailCloseGroup(group, message,actualPrice);
						break;
					case CANCEL:
						SendEmail.sendEmailCancelGroup(group,cancelFor);
						break;
					}
				}
		}
		catch(EmailException e){
         	log.log(Level.SEVERE, "the log from sendEmailToGroup", e);
			throw new UserException("an error occured while sending emails");
		}
	}
	/*
	 * only the buyer can cancel the reservation of the item
	 * check if buyer.userId == userId
	 * return buyer to participators list
	 * isActive = true
	 * buyer = null
	 */
	public static void cancelBookItemForGroup(String itemId, String userId) throws UserNotFoundException, UserException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		WishlistItem item = loadWishlistItem(itemId, pm);
		Guest guest = loadGuest(userId, pm);
		try{
			if (item.getBuyerKey().equals(guest.getIdKey())){
				tx.begin();
				item.setBuyerKey(null);
				item.setIsActive(true);
				pm.makePersistent(item);
				tx.commit();
			}
			else{
				throw new UserException("You can not cancel reservation that you didn't book");
			}
		}catch (Exception e)
        {
         	log.log(Level.SEVERE, "the log from cancelBookItemForGroup", e);
            throw new UserException("an error occured while canceling booked wishlist item for group,please try again later");
        }
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public static List<WishlistItem> getWishlistForEvent(String userId,String eventId,
			PersistenceManager pm) throws UserNotFoundException, UserException{
		Event e = loadEvent(eventId);
		if ((e==null)||e.getIsDeleted()){
			throw new UserException("The event was deleted, please refresh the page");
		}
		Guest guest = loadGuest(userId, pm);
		Key eventKey = KeyFactory.stringToKey(eventId);
		List<WishlistItem> items = guest.getWishlistItems();
		List<WishlistItem> itemsForEvent = new ArrayList<WishlistItem>();
		for(WishlistItem item: items){
			if (((item.getEventKey()==null)||item.getEventKey().equals(eventKey))&&(!item.getIsDeleted())){
				itemsForEvent.add(item);
			}
		}
		return itemsForEvent;
	}
	
	public static void addParticipator(String wishlistItemId, String eventId,
			ParticipatorData participatorD) throws UserNotFoundException, UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event e = loadEvent(eventId);
		if ((e==null)||e.getIsDeleted()){
			throw new UserException("The event was deleted, please refresh the page");
		}
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		List<Participator> partisipators = item.getParticipators();
		Boolean contains = false;
		for(Participator p: partisipators){
			if (p.getId().equals(participatorD.getUserId())){
				contains=true;
				break;
			}
		}
		if (((item.getEventKey()==null)||item.getEventKey().equals(KeyFactory.stringToKey(eventId)))
				&&(item.getIsActive()==true)&&(!contains)){
			Participator participator = new Participator(participatorD.getUserId(),participatorD.getMoney());
			Transaction tx = (Transaction) pm.currentTransaction();
			try {
				tx.begin();
				item.addParticipator(participator);
				item.setEventKey(KeyFactory.stringToKey(eventId));
				pm.makePersistent(item);
				pm.makePersistent(participator);
				tx.commit();
			} catch (Exception ex) {
	         	log.log(Level.SEVERE, "the log from addParticipator", ex);
				throw new UserException("an error occured while adding you to group,please try again later");
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static void updateParticipator(String wishlistItemId,ParticipatorData participator) throws UserException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		List<Participator> partisipators = item.getParticipators();
		if (item.getIsActive()==true){
			Transaction tx = (Transaction) pm.currentTransaction();
			try {
				tx.begin();
				for(Participator p: partisipators){
					if (p.getId().equals(participator.getUserId())){
						p.setMoney(participator.getMoney());
						pm.makePersistent(p);
						break;
					}
				}
				pm.makePersistent(item);
				tx.commit();
			} catch (Exception ex) {
	         	log.log(Level.SEVERE, "the log from updateParticipator", ex);
				throw new UserException("an error occured while updating yor participation details,please try again later");
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static void deleteParticipator(String wishlistItemId, String userId) throws UserNotFoundException, UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		List<Participator> partisipators = item.getParticipators();
		int size = partisipators.size();
		if (item.getIsActive()==true){
			Transaction tx = (Transaction) pm.currentTransaction();
			try {
				tx.begin();
				for(Participator p: partisipators){
					if (p.getId().equals(userId)){
						item.removeParticipator(p);
						pm.deletePersistent(p);
						break;
					}
				}
				if(size==1){
					item.setEventKey(null);
				}
				pm.makePersistent(item);
				tx.commit();
			} catch (Exception ex) {
	         	log.log(Level.SEVERE, "the log from deleteParticipator", ex);
				throw new UserException("an error occured while canceling your participation in group,please try again later");
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static void addChatMessageData(String itemId, ChatMessage message) throws UserException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(itemId, pm);
		Transaction tx = (Transaction) pm.currentTransaction();
		try{
			tx.begin();
			item.addChatMessage(message);
			pm.makePersistent(message);
			pm.makePersistent(item);
			tx.commit();
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from addChatMessageData", ex);
			throw new UserException("can't add a message, please try again later");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public static void removeChatMessageData(String itemId,PersistenceManager pm) throws UserException{
		WishlistItem item = loadWishlistItem(itemId, pm);
		try{
			ArrayList<ChatMessage> messages = item.getMessages();
			if (messages!=null){
				for (ChatMessage m : messages){
					item.removeChatMessage(m);
					pm.deletePersistent(m);
				}
				pm.makePersistent(item);
			}
		} catch (Exception ex) {
         	log.log(Level.SEVERE, "the log from removeChatMessageData", ex);
			throw new UserException("can't remove a messages, please try again later");
		} finally {
		}
	}

	public static Event deleteCalendarEvent(String gmail, String googleUID,PersistenceManager pm) throws UserException {
		Guest g = loadGuestByGmail(gmail, pm);
		if(g == null)
			return null;
		for (Event e : g.getEvents()){
			if (e.getGoogleUID()!=null&&e.getGoogleUID().equals(googleUID)){
				return e;
			}
		}
		return null;
	}

	public static Boolean createOrUpdateEvent(String gmail, String googleUID,EventData eventD,
			PersistenceManager pm) throws UserException {
		Guest g = loadGuestByGmail(gmail, pm);
		if(g == null)
			return null;
		for (Event e : g.getEvents()){
			if (e.getGoogleUID()!=null && e.getGoogleUID().equals(googleUID)){
				updateEvent(eventD,KeyFactory.keyToString(e.getKey()));
				return true;
			}
		}
		return false;
	}


}
