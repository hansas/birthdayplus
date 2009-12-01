package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;


/**
 * The async counterpart of <code>WishlistService</code>.
 */

public interface WishlistServiceAsync {

    //functions for myWishlsitTab\\\\\\\\\\\\\\\\\\\\
	void createWishlistItem(WishlistItemData item, AsyncCallback<Void> callback);

	void updateWishlistItem( WishlistItemData item,
			AsyncCallback<Void> callback);

	void deleteWishlistItem(WishlistItemData item,
			AsyncCallback<Void> callback);
	
	void getMyWishlist(String userId,
			AsyncCallback<ArrayList<WishlistItemData>> callback);
   
	
	/////////////////friend's wishlist\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	void getWishlistForEvent(String uId, String eventId,
			AsyncCallback<ArrayList<WishlistItemNewData>> callback);
	
	

	
	
	
	//group managment
	void addParticipator(String wishlistItemId,String eventId, ParticipatorData participator,
			AsyncCallback<Void> callback);
	
	

	void updateParticipator(String wishlistItemId, ParticipatorData participator,
			AsyncCallback<Void> callback);
	
//	void bookItemForGroup(WishlistItemData item, AsyncCallback<Void> callback);

	
	void getBookedWishlistItems(String usetId,
			AsyncCallback<ArrayList<WishlistItemNewData>> callback);

	void deleteBookedWishlistItem(String userId, String wishlistItemId,
			AsyncCallback<Void> callback);

	void bookItemForUser(String wishlistItemId, String EventId, String userId,
			AsyncCallback<Void> callback);

	void cancelBookItemForUser(String wishlistItemId, String userId,
			AsyncCallback<Void> callback);

	void deleteParticipator(String wishlistItemId, String userId,
			AsyncCallback<Void> callback);
	

	

	
	

}
