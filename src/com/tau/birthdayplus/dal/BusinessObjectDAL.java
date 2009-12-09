package com.tau.birthdayplus.dal;


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
import com.tau.birthdayplus.client.UserNotFoundException;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData; //import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.logic.EventManagement;
import com.tau.birthdayplus.logic.WishlistManagement;
import java.util.logging.Logger;

public class BusinessObjectDAL {
	
	private static final Logger log = Logger.getLogger(BusinessObjectDAL.class.getName());
	
	public static Guest loadGuest(String guestId, PersistenceManager pm) throws UserNotFoundException {
		Guest guest = null;
		Key key = KeyFactory.createKey(Guest.class.getSimpleName(), guestId);
		guest = pm.getObjectById(Guest.class, key);
		if (guest==null){
			throw new UserNotFoundException();
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

	public static void deleteEvent(EventData eventD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			int i = 1;
			Guest parent = BusinessObjectDAL.loadGuest(eventD.getUserId(), pm);
			Event event = pm.getObjectById(Event.class, eventD.getEventId());
			if (mayIDeleteEvent(event)){
				parent.removeEvent(event);
				pm.makePersistent(parent);
				pm.deletePersistent(event);
			}
			tx.commit();
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: deleteEvent");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public static Boolean mayIDeleteEvent(Event event){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<WishlistItem> items = new ArrayList<WishlistItem>();
		try {
			Query query = pm.newQuery(WishlistItem.class);
			query.declareImports("import com.google.appengine.api.datastore.Key");
			query.setFilter("eventKey == ekey");
			query.declareParameters("Key ekey");
			items = (List<WishlistItem>) query.execute(event.getKey());
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: mayIDeleteEvent", ex);
		}
		return items.isEmpty();
	}

	public static void createEvent(EventData eventD) {
		String userId = eventD.getUserId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		Event event = new Event(eventD);
		try {
			tx.begin();
			Guest user = BusinessObjectDAL.loadGuest(userId, pm);
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
			pm.close();
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

	public static void createWishlistItem(WishlistItemData itemData) {
		String userId = itemData.getUserId();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		WishlistItem item = new WishlistItem(itemData);
		try {
			tx.begin();
			Guest user = BusinessObjectDAL.loadGuest(userId, pm);
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
			pm.close();
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

	public static void updateWishlistItem(WishlistItemData itemD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			WishlistItem item = pm.getObjectById(WishlistItem.class, itemD
					.getWishlistItemId());
			item.copyFromWishlistItemData(itemD);
			pm.makePersistent(item);
			tx.commit();
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: updateWishlistItem");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public static void deleteWishlistItem(WishlistItemData itemD) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = (Transaction) pm.currentTransaction();
		try {
			tx.begin();
			Guest parent = BusinessObjectDAL.loadGuest(itemD.getUserId(), pm);
			WishlistItem item = pm.getObjectById(WishlistItem.class, itemD
					.getWishlistItemId());
			if (item.getParticipators().isEmpty() && item.getBuyerKey()==null){ //User may delete item iff there is no buyers and no participators
				parent.removeWishlistItem(item);
				pm.makePersistent(parent);
				pm.deletePersistent(item);
			}
			tx.commit();
		} catch (Exception ex) {
			throw new RuntimeException("error in data base: deleteWishlistItem");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
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
		log.info("first query was successful");
		if (partisipators==null){
			log.info("getBookedWishlistItems2: there is no partisipators");
		}
		for (Participator p : partisipators){
			WishlistItem item = loadWishlistItem(KeyFactory.keyToString(p.getIdKey().getParent()),pm);
			wishlistItems.add(item);
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
		log.info("second query was successful");
		if (buyers==null){
			log.info("getBookedWishlistItems2: there is no buyers");
		}
		for (WishlistItem buyer : buyers){
			if (!wishlistItems.contains(buyer)){
				wishlistItems.add(buyer);
			}
		}
		return wishlistItems;
	}
	
	public static void bookItemForUser(String wishlistItemId, String eventId,String userId) throws UserNotFoundException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		WishlistItem item = loadWishlistItem(wishlistItemId, pm);
		if (item.getEventKey()==null){
			Key eventKey = KeyFactory.stringToKey(eventId);
			Guest guest = loadGuest(userId, pm);
			item.setEventKey(eventKey);
			item.setBuyerKey(guest.getIdKey());
			item.setIsActive(false);
			Transaction tx = (Transaction) pm.currentTransaction();
			try {
				tx.begin();
				//guest.addIBuyItem(item);
				//pm.makePersistent(guest);
				pm.makePersistent(item);
				tx.commit();
			} catch (Exception ex) {
				throw new RuntimeException("error in data base: bookItemForUser");
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
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
				//guest.removeIBuyItem(item);
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
			if (((item.getEventKey()==null)||item.getEventKey().equals(eventKey))
					&&(item.getIsActive()==true)){
				itemsForEvent.add(item);
			}
		}
		return itemsForEvent;
	}
	
	public static void addParticipator(String wishlistItemId, String eventId,
			ParticipatorData participatorD) throws UserNotFoundException {
		int i=0;
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
			//Guest user = loadGuest(userId, pm);
			try {
				tx.begin();
				int i=1;
				for(Participator p: partisipators){
					if (p.getId().equals(userId)){
						item.removeParticipator(p);
						break;
					}
				}
				if(size==1){
					item.setEventKey(null);
				}
				//user.removeIBuyItem(item);
				//pm.makePersistent(user);
				pm.makePersistent(item);
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
