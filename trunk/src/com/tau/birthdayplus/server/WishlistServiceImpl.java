package com.tau.birthdayplus.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.logic.EventManagement;
import com.tau.birthdayplus.logic.UserManagement;
import com.tau.birthdayplus.logic.WishlistManagement;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WishlistServiceImpl extends RemoteServiceServlet implements
WishlistService  {

	public void printHello() {
		System.out.println("Hello from the WishlistService");
		
	}
	/*
	 * creates new item for the user
	 */
	public void createWishlistItem(WishlistItemData item) {
		WishlistManagement.createWishlistItem(item);
		
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
	 * user don't want to buy it 
	 * GUI - user can cancel only from "i'm buying " tab
	 */
	public void setActive(String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * user wants to buy this item
	 * Server - check that item is active
	 */
	public void setInactive(String userId, String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * add participator
	 * Server - check if the item is active and the user isn't already participate
	 */
	public void createParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * update participator (update money only)
	 * Server - check if exists
	 */
	public void updateParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * delete  participator from the list
	 * 
	 */
	public void deleteParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * return all the items this user booked
	 */
	public ArrayList<WishlistItemData> getBookedWishlistItems(String usetId) {
		// TODO Auto-generated method stub
		return null;
	}

}
