package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.Participator;
import com.tau.birthdayplus.dto.WishlistItem;

/**
 * The async counterpart of <code>WishlistService</code>.
 */

public interface WishlistServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);
	/*
	 * creates new item for the user
	 */
	void createWishlistItem(WishlistItem item, AsyncCallback<Void> callback);
	/*
	 * update the item
	 * check if the user is the item owner
	 */
	void updateWishlistItem(String userId, WishlistItem item,
			AsyncCallback<Void> callback);
	/*
	 * delete the item 
	 * check if the user is the item owner
	 */
	void deleteWishlistItem(String userId, String wishlistItemId,
			AsyncCallback<Void> callback);
	/*
	 * return user's wishlist
	 */
	void getWishlist(String userId,
			AsyncCallback<ArrayList<WishlistItem>> callback);
	/*
	 * delete the participator
	 * check if the user is a participator
	 */
	void deleteParticipator(String userId, Participator participator,
			AsyncCallback<Void> callback);
	/*
	 * update participator (update money only)
	 * check that the user is a participator
	 */
	void updatePartcipator(String userId, Participator participator,
			AsyncCallback<Void> callback);
	/*
	 * add participator
	 */
	void createParticipator(Participator participator,
			AsyncCallback<Void> callback);
	void getParticipators(String wishlistItemId,
			AsyncCallback<ArrayList<Participator>> callback);

}
