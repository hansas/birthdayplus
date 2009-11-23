package com.tau.birthdayplus.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	/**
	 * Load existing user info from server  
	 * @param guest
	 * @return
	 */
	Guest loadGuest(String myid);
	/**
	 * Create gadget's user
	 * @param guest
	 * @return
	 */
	Guest createGuest(Guest guest);
	/**
	 * Update gadget's user
	 * @param guest
	 * @return
	 */
	Guest updateGuest(Guest guest);	
	/**
	 * Create user event 
	 * @param guest
	 * @return
	 */
	Event createEvent(Event event, String uid);
	/**
	 * Update user event
	 * @param guest
	 * @return
	 */
	
	Event updateEvent(Event event, String uid);
	/**
	 * Delete user's private event
	 * @param event
	 * @return true if event was deleted successfully, else return false
	 */
	Event deleteEvent(Event event, String uid);
	/**
	 * Return user's friends event list
	 * @param uid google unique id 
	 * @return list of user's events
	 */
	ArrayList<Event> getEventList(String uid);
	/**
	 * Create user wishlist item 
	 * @param guest
	 * @return
	 */
	WishlistItem createWishlistItem(WishlistItem w_item, String uid);
	/**
	 * Update user wishlist item
	 * @param guest
	 * @return
	 */
	
	WishlistItem updateWishlistItem(WishlistItem w_item, String uid);
	/**
	 * Delete user's wishlist item
	 * @param event
	 * @return true if event was deleted successfully, else return false
	 */
	WishlistItem deleteWishlistItem(WishlistItem w_item, String uid);
	
	
}
