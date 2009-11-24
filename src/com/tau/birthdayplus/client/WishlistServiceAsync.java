package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;


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
	void createWishlistItem(WishlistItemData item, AsyncCallback<Void> callback);
	/*
	 * update the item
	 * user can update only his wishlist items
	 */
	void updateWishlistItem( WishlistItemData item,
			AsyncCallback<Void> callback);
	/*
	 * delete the item 
	 * user can delete only his items
	 */
	void deleteWishlistItem( String wishlistItemId,
			AsyncCallback<Void> callback);
	/*
	 * return user's wishlist
	 */
	void getWishlist(String userId,
			AsyncCallback<ArrayList<WishlistItemData>> callback);
	/*
	 * delete the participator
	 * check if the user is a participator
	 */
	void deleteParticipator(String wishlistItem, ParticipatorData participator,
			AsyncCallback<Void> callback);
	/*
	 * update participator (update money only)
	 * check that the user is a participator
	 */
	void updatePartcipator(String wishlistItemId, ParticipatorData participator,
			AsyncCallback<Void> callback);
	/*
	 * add participator
	 */
	void createParticipator(String wishlistItemId,ParticipatorData participator,
			AsyncCallback<Void> callback);
	
	void setInactive(String userId, String wishlistItemId,
			AsyncCallback<Void> callback);
	void setActive( String wishlistItemId,
			AsyncCallback<Void> callback);
	void getBookedWishlistItems(String usetId,
			AsyncCallback<ArrayList<WishlistItemData>> callback);

}
