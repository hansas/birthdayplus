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

	
	public static Guest loadGuest(String guestId, PersistenceManager pm) throws UserNotFoundException {
		Guest guest = null;
		try{
		Key key = KeyFactory.createKey(Guest.class.getSimpleName(), guestId);
		guest = pm.getObjectById(Guest.class, key);
		}catch(JDOObjectNotFoundException ex){
			throw new UserNotFoundException();
		}catch(Exception ex){
			log.severe("the error is: " + ex.getMessage());
			throw new RuntimeException("can't get your profile");
		}
		return guest;
	}

	// Automatically open and close PMF
	public static Guest loadGuest(String guestId) throws UserNotFoundException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest g = null;
		g = loadGuest(guestId, pm);
		pm.close();
		return g;
	}
	
	public static Guest loadGuestByGoogleId(String id,PersistenceManager pm){
		List<Guest> guest = new ArrayList<Guest>();
		try{
			Query query = pm.newQuery(Guest.class);
			query.setFilter("googleId == id");
			query.declareParameters("String id");
			guest = (List<Guest>)query.execute(id);
			return guest.get(0);
		} catch (Exception ex) {
			throw new RuntimeException("error in loadGuestByGoogleId"+ex.getMessage());
		}
	}
	
	public static Guest loadGuestByGmail(String gmail,PersistenceManager pm){
		List<Guest> guest = new ArrayList<Guest>();
		try{
			Query query = pm.newQuery(Guest.class);
			query.setFilter("email == gmail");
			query.declareParameters("String gmail");
			guest =(List<Guest>)query.execute(gmail);
			return guest.get(0);
		} catch (Exception ex) {
			throw new RuntimeException("error in loadGuestByGmail"+ex.getMessage());
		}
	}

	/*
	 * public static Guest loadGuest(String guestId){ PersistenceManager pm =
	 * PMF.get().getPersistenceManager(); Guest guest =
	 * pm.getObjectById(Guest.class, guestId); pm.close(); return guest; }
	 */

	public static void createProfile(Guest guest,Event e) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			guest.addEvent(e);
			pm.makePersistent(guest);
			pm.makePersistent(e);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			pm.close();
		}
	}

	public static void updateGuest(GuestData guestData) throws UserNotFoundException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Guest guest = loadGuest(guestData.getId(), pm);
		guest.copyFromGuestData(guestData);
		try {
			pm.makePersistent(guest);
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: updateGuest");
		} finally {
			pm.close();
		}
	}

	public static void updateEvent(EventData eventD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			Event e = pm.getObjectById(Event.class, eventD.getEventId());
			e.copyFromEventData(eventD);
			pm.makePersistent(e);
			tx.commit();
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: updateEvent");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
		
	public static void deleteEvent(EventData eventD) throws UserException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			Guest parent = BusinessObjectDAL.loadGuest(eventD.getUserId(), pm);
			Event event = pm.getObjectById(Event.class, eventD.getEventId());
			if (mayIDeleteEvent(event,pm)){
				parent.removeEvent(event);
				pm.makePersistent(parent);
				pm.deletePersistent(event);
			}
			else{
				event.setIsDeleted(true);
				pm.makePersistent(event);
				throw new UserException("You may not delete this event, because there are people that want to buy something to you for this event");
			}
			tx.commit();
		} 
		catch (RuntimeException e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        catch (Exception e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        } 
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public static Boolean mayIDeleteEvent(Event event,PersistenceManager pm) throws UserException{
		List<WishlistItem> items = new ArrayList<WishlistItem>();
		try {
			Query query = pm.newQuery(WishlistItem.class);
			query.declareImports("import com.google.appengine.api.datastore.Key");
			query.setFilter("eventKey == ekey");
			query.declareParameters("Key ekey");
			items = (List<WishlistItem>) query.execute(event.getKey());
		} catch (Exception ex) {
			log.severe("Error in mayIDeleteEvent");
			throw new UserException(ex);
		}
		if (!items.isEmpty()){
			log.info("There is items for this event "+event.getEventName());
		}
		Boolean result = true;
		try{
			for (WishlistItem item : items){
				removeChatMessageData(KeyFactory.keyToString(item.getKey()),pm);
				if (item.getBuyerKey()!=null){
					log.info("There is buyer for this item "+item.getItemName());
					result = false;
					//item.setIsDeleted(true);//don't sure if it's right
					//pm.makePersistent(item);
				}
				else{
					List<Participator> participators = item.getParticipators();
					if(participators==null){
						log.info("There is no participators at "+ item.getItemName()+" "+event.getEventName());
					}
					else{
						try{
							for (Participator p : participators){
								item.removeParticipator(p);
								pm.deletePersistent(p);
							}
							if(item.getParticipators()!=null){
								for (Participator p : item.getParticipators()){
									log.info(p.getId());
								}
							}
							else{
								log.info("participators for item "+item.getItemName()+" were deleted");
							}
							item.setEventKey(null);
							pm.makePersistent(item);
							log.info("this item was freed: "+item.getItemName()+" "+item.getEventKey());
						}
						catch (Exception ex) {
							log.severe("Error in second part of mayIDeleteEvent in item "+item.getItemName());
							throw new UserException(ex);
						}
					}
				}
			}
		}
		catch (RuntimeException e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
		catch (Exception ex) 
		{
			log.severe("Error in second part of mayIDeleteEvent");
			throw new UserException(ex);
		}
		return result;
	}
	
	public static void cronDeleteEventAndUpdateRecurrent() throws UserException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Guest> guests = new ArrayList<Guest>();
		Query query = pm.newQuery(Guest.class);
		guests = (List<Guest>)query.execute();
		Calendar cal = Calendar.getInstance();
		Calendar eDate = Calendar.getInstance();
		for (Guest g : guests){
			List<Event> events = g.getEvents();
			for (Event e : events){
				int eMonth = e.getEventDate().getMonth();
				int eDay = e.getEventDate().getDate();
				eDate.clear();
				eDate.set(Calendar.YEAR, e.getEventDate().getYear()+1900);
				eDate.set(Calendar.MONTH, eMonth);
				eDate.set(Calendar.DATE, eDay);
				eDate.add(Calendar.DATE, 1);
				if ((cal.after(eDate))&&(e.getRecurrence()==false)){
					Boolean result = mayIDeleteEvent(e,pm);
					if (result){
						log.info("event "+e.getEventName()+" was deleted by cron");
						g.removeEvent(e);
						pm.makePersistent(g);
						pm.deletePersistent(e);
					}
					else{
						log.info("event "+e.getEventName()+" was signed deleted by cron");
						e.setIsDeleted(true);
						pm.makePersistent(e);
					}
				}
				else if((e.getRecurrence()==true)&&(cal.after(eDate))){
					log.info("event "+e.getEventName()+" was updated by cron");
					Date newEDate = new Date(cal.get(Calendar.YEAR)-1900+1,eMonth,eDay);
					e.setEventDate(newEDate);
					pm.makePersistent(e);
				}
			}
		}
	}

	public static void createEvent(EventData eventD,DALWrapper wrapper) throws UserNotFoundException {
		String userId = eventD.getUserId();
		Guest user = wrapper.getGuestById(userId);
		wrapper.newCreateEvent(eventD,user);
	}
		
	public static void newCreateEvent(EventData eventD,Guest user,PersistenceManager pm){
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
			throw new RuntimeException("error in data base: createEvent", ex);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	public static Event loadEvent(String eventId, PersistenceManager pm) {
		Event event = null;
		try {
			Key key = KeyFactory.stringToKey(eventId);
			event = pm.getObjectById(Event.class, key);
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: loadEvent", ex);
		}
		return event;
	}

	// Automatically open and close PMF
	public static Event loadEvent(String eventId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Event event = loadEvent(eventId, pm);
		pm.close();
		return event;
	}

	public static List<Guest> getGuestsById(ArrayList<String> UserIdList,PersistenceManager pm) {
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
			throw new RuntimeException("error in data base: getGuestsById", ex);
		}
		return guests;
	}

	public static void createWishlistItem(WishlistItemData itemData,DALWrapper wrapper) throws UserNotFoundException {
		String userId = itemData.getUserId();
		Guest user = wrapper.getGuestById(userId);
		wrapper.newCreateWishlistItem(itemData,user);
	}
	
	public static void newCreateWishlistItem(WishlistItemData itemData,Guest user,PersistenceManager pm){
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
			throw new RuntimeException(
					"error in data base: createWishlistItem", ex);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		//	pm.close();
		}
	}
	
	public static WishlistItem loadWishlistItem(String wishlistItemId, PersistenceManager pm) {
		WishlistItem item = null;
		try {
			Key key = KeyFactory.stringToKey(wishlistItemId);
			item = pm.getObjectById(WishlistItem.class, key);
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: loadWishlistItem", ex);
		}
		return item;
	}

	// Automatically open and close PMF
	public static WishlistItem loadWishlistItem(String wishlistItemId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		pm.close();
		return item;
	}	

	public static WishlistItem updateWishlistItem(WishlistItemData itemD) {
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
			throw new RuntimeException("error in data base: updateWishlistItem");
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
			removeChatMessageData(itemD.getWishlistItemId(), pm);
			if (item.getBuyerKey()==null){ //User may delete item iff there is no buyers
				if(!item.getParticipators().isEmpty()){
					List<Participator> participators = item.getParticipators();
					for (Participator p : participators){
						pm.deletePersistent(p);
					}
				}
				parent.removeWishlistItem(item);
				pm.makePersistent(parent);
				pm.deletePersistent(item);
			}
			else{
				item.setIsDeleted(true);
				pm.makePersistent(item);
				throw new UserException("You may not delete this item, because there are people that want to buy it to you");
			}
			tx.commit();
		} 
		catch (RuntimeException e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        catch (Exception e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return item;
	}
	
	public static List<WishlistItem> getWishlist(String userId,PersistenceManager pm) throws UserNotFoundException {
		List<WishlistItem> itemList = new ArrayList<WishlistItem>();
		Guest user = BusinessObjectDAL.loadGuest(userId, pm);
		try {
			itemList = user.getWishlistItems();
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: getWishlist");
		}
		return itemList;
	}
	
	public static List<WishlistItem> getWishlistItemById(List<Key> keys,PersistenceManager pm) {
		List<WishlistItem> items = new ArrayList<WishlistItem>();
		try {

			Query query = pm.newQuery(WishlistItem.class);
			query.setFilter("key == :keyList");
			items = (List<WishlistItem>) query.execute(keys);
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: getWishlistItemById");
		}
		return items;
	}
	
	public static List<WishlistItem> getBookedWishlistItems2(String userId,PersistenceManager pm)
	throws UserNotFoundException{
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
			log.severe("Error in getBookedWishlistItems2's first query");
			throw new RuntimeException("error in data base: getBookedWishlistItems2");
		}
		if (partisipators==null){
			log.info("getBookedWishlistItems2: there is no partisipators");
		}
		for (Participator p : partisipators){
			WishlistItem item = loadWishlistItem(KeyFactory.keyToString(p.getIdKey().getParent()),pm);
			wishlistItems.add(item);
		}
		for (WishlistItem i : wishlistItems){
			log.info(i.getItemName());
		}
		try {
			Query query = pm.newQuery(WishlistItem.class);
			query.declareImports("import com.google.appengine.api.datastore.Key");
			query.setFilter("buyerKey == gkey");
			query.declareParameters("Key gkey");
			buyers = (List<WishlistItem>) query.execute(g.getIdKey());
		} catch (Exception ex) {
			log.severe("Error in getBookedWishlistItems2's second query"+ex.getMessage());
			throw new RuntimeException("error in data base: getBookedWishlistItems2");
		}
		if (buyers==null){
			log.info("getBookedWishlistItems2: there is no buyers");
		}
		else{
			for (WishlistItem buyer : buyers){
				log.info(buyer.getItemName());
			}
		}
		for (WishlistItem buyer : buyers){
			if (!wishlistItems.contains(buyer)){
				wishlistItems.add(buyer);
			}
		}
		return wishlistItems;
	}
	
	public static ArrayList<WishlistItemPolaniData> getLastItemsForUser(String myUserId,String anotherUserId,
		PersistenceManager pm) throws UserNotFoundException {
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
		for (WishlistItem item : myItems){
			if (item.getBuyerKey()!=null){
				if (item.getBuyerKey().equals(buyer)){
					addThreeSortedItems(item,events.get(item.getEventKey()),item.getPrice(), results);
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
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        catch (Exception e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public static void cancelBookItemForUser(String wishlistItemId, String userId) {
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
				throw new RuntimeException("error in data base: cancelBookItemForUser");
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
		catch (RuntimeException e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        catch (Exception e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
//	public static void sendEmailCloseGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE,Double actualPrice,PersistenceManager pm) throws EmailException{
//		WishlistItem item = loadWishlistItem(itemId, pm);
//		Guest itemUser = pm.getObjectById(Guest.class,item.getKey().getParent());
//		Event event = pm.getObjectById(Event.class,item.getEventKey());
//		String fullName = itemUser.getFirstName()+" "+itemUser.getLastName();
//		GroupEmail group = new GroupEmail(item.getItemName(),fullName,event.getEventName(),event.getEventDate(),item.getPrice(),userId);
//		for (ParticipatorEmail p : participatorsE){
//			group.addParticipator(p);
//		}
//		try{
//			SendEmail.sendEmailCloseGroup(group, message,actualPrice);
//		}
//		catch(EmailException e){
//			throw new EmailException(e.getMessage());
//		}
//	}
//	
//	public static void sendEmailOpenGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE,PersistenceManager pm) throws EmailException{
//		WishlistItem item = loadWishlistItem(itemId, pm);
//		Guest itemUser = pm.getObjectById(Guest.class,item.getKey().getParent());
//		Event event = pm.getObjectById(Event.class,item.getEventKey());
//		String fullName = itemUser.getFirstName()+" "+itemUser.getLastName();
//		GroupEmail group = new GroupEmail(item.getItemName(),fullName,event.getEventName(),event.getEventDate(),item.getPrice(),userId);
//		for (ParticipatorEmail p : participatorsE){
//			group.addParticipator(p);
//		}
//		try{
//			SendEmail.sendEmailOpenGroup(group, message);
//		}
//		catch(EmailException e){
//			throw new EmailException(e.getMessage());
//		}
//	}
	
	public static void sendEmailToGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE,PersistenceManager pm,Double actualPrice,GroupStatus status) throws EmailException{
		WishlistItem item = loadWishlistItem(itemId, pm);
		Guest itemUser = pm.getObjectById(Guest.class,item.getKey().getParent());
		Event event = pm.getObjectById(Event.class,item.getEventKey());
		String fullName = itemUser.getFirstName()+" "+itemUser.getLastName();
		GroupEmail group = new GroupEmail(item.getItemName(),fullName,event.getEventName(),event.getEventDate(),item.getPrice(),userId);
		for (ParticipatorEmail p : participatorsE){
			group.addParticipator(p);
		}
		try{
			switch(status){
			case OPEN:
				SendEmail.sendEmailOpenGroup(group, message);
				break;
			case CLOSE:
				SendEmail.sendEmailCloseGroup(group, message,actualPrice);
				break;
			case CANCEL:
				SendEmail.sendEmailCancelGroup(group);
				break;
			}
		}
		catch(EmailException e){
			throw new EmailException(e.getMessage());
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
			//	removeChatMessageData(itemId,pm);
				pm.makePersistent(item);
				tx.commit();
			}
			else{
				throw new UserException("You can not cancel reservation that you didn't book");
			}
		}
		catch (RuntimeException e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
        catch (Exception e)
        {
            log.severe(e.getMessage());
            throw new UserException(e);
        }
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}


	//TODO: delete this function
//	public static void deleteBookedWishlistItem(String userId, String wishlistItemId) throws UserNotFoundException{
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
//		Guest guest = loadGuest(userId, pm);
//		if (item.getIsActive()==false){
//			Transaction tx = (Transaction) pm.currentTransaction();
//			try {
//				tx.begin();
//				guest.removeIBuyItem(item);
//				pm.makePersistent(guest);
//				tx.commit();
//			} catch (Exception ex) {
//				throw new RuntimeException("error in data base: deleteBookedWishlistItem");
//			} finally {
//				if (tx.isActive()) {
//					tx.rollback();
//				}
//				pm.close();
//			}
//		}
//	}
	
	public static List<WishlistItem> getWishlistForEvent(String userId,String eventId,
			PersistenceManager pm) throws UserNotFoundException{
		Guest guest = loadGuest(userId, pm);
		Key eventKey = KeyFactory.stringToKey(eventId);
		List<WishlistItem> items = guest.getWishlistItems();
		List<WishlistItem> itemsForEvent = new ArrayList<WishlistItem>();
		for(WishlistItem item: items){
			if ((item.getEventKey()==null)||item.getEventKey().equals(eventKey)){
					//&&(item.getIsActive()==true)){
				itemsForEvent.add(item);
			}
		}
		return itemsForEvent;
	}
	
	public static void addParticipator(String wishlistItemId, String eventId,
			ParticipatorData participatorD) throws UserNotFoundException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
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
			//Guest g = loadGuest(participator.getId(), pm);
			try {
				tx.begin();
				item.addParticipator(participator);
				item.setEventKey(KeyFactory.stringToKey(eventId));
				//g.addIBuyItem(item);
				//pm.makePersistent(g);
				pm.makePersistent(item);
				pm.makePersistent(participator);
				tx.commit();
			} catch (Exception ex) {
				throw new RuntimeException("error in data base: addParticipator", ex);
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static void updateParticipator(String wishlistItemId,ParticipatorData participator){
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
				throw new RuntimeException("error in data base: updateParticipator", ex);
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static void deleteParticipator(String wishlistItemId, String userId) throws UserNotFoundException {
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
				throw new RuntimeException("error in data base: deleteParticipator", ex);
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static void addChatMessageData(String itemId, ChatMessage message){
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
			throw new RuntimeException("error in data base: addChatMessageData", ex);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public static void removeChatMessageData(String itemId,PersistenceManager pm) throws UserException{
		Transaction tx = (Transaction) pm.currentTransaction();
		WishlistItem item = loadWishlistItem(itemId, pm);
		try{
			log.info("Trying to delete all chat messages for " + item.getItemName());
			tx.begin();
			ArrayList<ChatMessage> messages = item.getMessages();
			if (messages!=null){
				for (ChatMessage m : messages){
					item.removeChatMessage(m);
					pm.deletePersistent(m);
				}
				pm.makePersistent(item);
			}
			tx.commit();
		} catch (Exception ex) {
			throw new UserException(ex);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/*public static void checkAllPossibleParticipators(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Guest.class);
			List<Guest> guests = (List<Guest>) query.execute();//query.execute(gid);
			for (Guest p: guests){
				System.out.print(p.getId()+":" + p.getFirstName()+" - ");
				List<WishlistItem> items = p.getWishlistItems();
				for (WishlistItem wi: items){
					System.out.print(wi.getItemName()+" {");
					List<Participator> pars = wi.getParticipators();
					
					for (Participator pr: pars){
						System.out.print(pr.getUserId() + " $" + pr.getMoney()+". ");
					}
					System.out.print("},");
				}
				System.out.println();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally{
			pm.close();
		}
	}
	public static List<WishlistItem> getParticipationWishlist(Guest g) {
		checkAllPossibleParticipators();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();
		List<Participator> p = new ArrayList<Participator>();
		try {

			Query query = pm.newQuery(Participator.class);
			String gid = g.getId();
			query.setFilter("userId == :keyList");
			//guests = (List<Guest>) query.execute(keys);
			//query.setFilter("userId == \"123\"");
			//query.declareParameters("String gid");
			ArrayList<String> keys = new ArrayList<String>();
			keys.add("123");
			p = (List<Participator>) query.execute(keys);//query.execute(gid);
			for (Participator pr: p){
				System.out.print(pr.getUserId() + " $" + pr.getMoney()+". ");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally{
			pm.close();
		}
		return wishlistItems;
	}*/

}
