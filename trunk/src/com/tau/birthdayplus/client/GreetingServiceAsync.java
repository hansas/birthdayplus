package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void createGuest(Guest guest, AsyncCallback<Guest> callback);

	void updateGuest(Guest guest, AsyncCallback<Guest> callback);

	void getEventList(String uid, AsyncCallback<ArrayList<Event>> callback);

	void loadGuest(String myid, AsyncCallback<Guest> callback);

	void createWishlistItem(WishlistItem w_item, String uid,
			AsyncCallback<WishlistItem> callback);

	void updateWishlistItem(WishlistItem w_item, String uid,
			AsyncCallback<WishlistItem> callback);

	void createEvent(Event event, String uid, AsyncCallback<Event> callback);

	void deleteWishlistItem(WishlistItem w_item, String uid,
			AsyncCallback<WishlistItem> callback);

	void updateEvent(Event event, String uid, AsyncCallback<Event> callback);

	void deleteEvent(Event event, String uid, AsyncCallback<Event> callback);
}
