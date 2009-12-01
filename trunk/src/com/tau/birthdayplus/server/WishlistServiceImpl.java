package com.tau.birthdayplus.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.logic.EventManagement;
import com.tau.birthdayplus.logic.UserManagement;
import com.tau.birthdayplus.logic.WishlistManagement;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WishlistServiceImpl extends RemoteServiceServlet implements
WishlistService  {
   
	/*
	 * return user's wishlist
	 */
	public ArrayList<WishlistItemData> getMyWishlist(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * creates new item for the user
	 */
	public void createWishlistItem(WishlistItemData item) {
		WishlistManagement.createWishlistItem(item);
		GuestData guestData = new GuestData("123","Ira","Let",new Date(17,9,85));
		UserManagement.createProfile(guestData);
		GuestData savedGuest = UserManagement.loadGuestData("123"); 
		WishlistItemData itemData = new WishlistItemData("","123","Car",4,"No link",500000,true);
		WishlistManagement.createWishlistItem(itemData);
		List<WishlistItemData> itemDataList = WishlistManagement.getWishlist("123");
		for (WishlistItemData itemD : itemDataList){
			System.out.println(itemD.getItemName());
			System.out.println(itemD.getWishlistItemId());
		}
		Guest g = UserManagement.loadGuest("123");
		List<WishlistItem> items = g.getWishlistItems();
		Participator p = new Participator(g,300);
		items.get(0).addParticipator(p);
		List<WishlistItemData> items2 = WishlistManagement.getParicipationWishlist("123");
		
		itemData.setItemName("Iphone");
		WishlistManagement.updateWishlistItem(itemData);
		itemDataList = WishlistManagement.getWishlist("123");
		for (WishlistItemData itemD : itemDataList){
			System.out.println(itemD.getItemName());
			System.out.println(itemD.getWishlistItemId());
			WishlistManagement.deleteWishlistItem(itemD);
		}
		itemDataList = WishlistManagement.getWishlist("123");
		if (itemDataList.isEmpty()){
			System.out.println("there is no events");
		}
		
//		System.out.println("creating new item : "+item);
//		GuestData guestData = new GuestData("123","Ira","Let",new Date(17,9,85));
//		UserManagement.createProfile(guestData);
//		GuestData savedGuest = UserManagement.loadGuestData("123"); 
//		EventData eventData = new EventData("","123","Test1",new Date(1,1,1), false);
//		EventManagement.createEvent(eventData);
//		eventData = new EventData(eventData.getEventId(),"123","Test2", new Date(1,2,3),false);
//		EventManagement.updateEvent(eventData);
//		savedGuest = new GuestData("123", "Ira", "Letuchaya", new Date(17,9,85));
//		UserManagement.updateProfile(savedGuest);
//		Guest g = UserManagement.loadGuest(savedGuest.getId());
//		System.out.println(g.getLastName());
//		List<Event> events = g.getEvents();
//		for (Event e: events){
//			System.out.print(e.getEventName());
//		}
//		ArrayList<String> users = new ArrayList<String>();
//		users.add("123");
//		ArrayList<EventData> eventsData = new ArrayList<EventData>();
//		eventsData = EventManagement.getEvents(users);
//		for (EventData eD: eventsData){
//			System.out.println(eD.getEventName());
//			System.out.println(eD.getUserId());
//		}
//		eventsData = EventManagement.getEvents(users);
//		if (eventsData.isEmpty()){
//			System.out.println("there is no events");
//		}
	}
	
	/*
	 * update the item, don't update isActive
	 * GUI-user can update only his wishlist
	 */
	public void updateWishlistItem(WishlistItemData item) {
		System.out.println("updating item : "+item);
		WishlistManagement.updateWishlistItem(item);
	}
	/*
	 * delete the item 
	 * GUI - user can delete only his wishlist items
	 */
	public void deleteWishlistItem(WishlistItemData item) {
		System.out.println("deleting item : "+item);
		WishlistManagement.deleteWishlistItem(item);
	}
	/*
	 * return user's wishlist
	 */
	public ArrayList<WishlistItemData> getWishlist(String userId) {
		return WishlistManagement.getWishlist(userId);
	}
	
	
	
	
	
	
	
	/*
	 * user will buy this item
	 * Server - check that item in db doesn't have eventId(==null) (open group or someone booked it)
	 * set isActive == false
	 * add this item to guest "I buy" list (you can get userId from Buyer)
	 */
	public void bookItemForUser(String wishlistItemId, String EventId,
			String userId) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * user doesn't want to buy this item
	 * GUI - user can cancel only from "i'm buying " tab
	 * Server - change item status to active,Buyer = null, eventid =null and remove this item from user's 
	 * "I buy" list 
	 * check that in db "buyer" for this item has userId == userId(?)
	 */
	public void cancelBookItemForUser(String wishlistItemId, String userId) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 *  return wishlist for this user , for the event
	 *  server - all the items that belong to the user and their (eventId == eventId, or
	 *  eventId == null ) and isActive == true
	 */
	public ArrayList<WishlistItemNewData> getWishlistForEvent(String id,
			String eventId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * return all the items this user has booked
	 */
	public ArrayList<WishlistItemNewData> getBookedWishlistItems(String usetId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/*
	 * add participator
	 * Server - check if the item in db has the same eventId or eventId==null 
	 * (two people can join to the new group from different events)
	 * check that item is Active 
	 * check that user doesn't participate already in this group
	 * add this item to user's "I buy " items
	 */
	public void addParticipator(String wishlistItemId, String eventId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * delete participator from the group
	 * check if the item isActive (won't remove participator after the group has closed)
	 * if this participator is the only one - free this item(eventId == null)
	 * remove this item from user's "I buy " list
	 */
	public void deleteParticipator(String wishlistItemId, String userId) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * remove this item from this user's "I buy " list , check if item isActive == false
	 * (won't remove item with open group)
	 */
	public void deleteBookedWishlistItem(String userId, String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}

	

	
	

	
	/*
	 * update participator (update money only)
	 * Server - check if the item is active (group is not closed yet) 
	 */
	public void updateParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}

	


}
