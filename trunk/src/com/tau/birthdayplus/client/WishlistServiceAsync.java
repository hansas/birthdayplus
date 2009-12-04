package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;


/**
 * The async counterpart of <code>WishlistService</code>.
 */

public interface WishlistServiceAsync {

    //functions for myWishlsitTab\\\\\\\\\\\\\\\\\\\\
	RequestBuilder createWishlistItem(WishlistItemData item, AsyncCallback<Void> callback);

	RequestBuilder updateWishlistItem( WishlistItemData item,
			AsyncCallback<Void> callback);

	RequestBuilder deleteWishlistItem(WishlistItemData item,
			AsyncCallback<Void> callback);
	
	RequestBuilder getMyWishlist(String userId,
			AsyncCallback<ArrayList<WishlistItemData>> callback);
   
	
	/////////////////friend's wishlist\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	RequestBuilder getWishlistForEvent(String uId, String eventId,
			AsyncCallback<ArrayList<WishlistItemNewData>> callback);
	
	

	
	
	
	//group managment
	RequestBuilder addParticipator(String wishlistItemId,String eventId, ParticipatorData participator,
			AsyncCallback<Void> callback);
	
	

	RequestBuilder updateParticipator(String wishlistItemId, ParticipatorData participator,
			AsyncCallback<Void> callback);
	
//	void bookItemForGroup(WishlistItemData item, AsyncCallback<Void> callback);

	
	RequestBuilder getBookedWishlistItems(String usetId,
			AsyncCallback<ArrayList<WishlistItemNewData>> callback);

	RequestBuilder deleteBookedWishlistItem(String userId, String wishlistItemId,
			AsyncCallback<Void> callback);

	RequestBuilder bookItemForUser(String wishlistItemId, String EventId, String userId,
			AsyncCallback<Void> callback);

	RequestBuilder cancelBookItemForUser(String wishlistItemId, String userId,
			AsyncCallback<Void> callback);

	RequestBuilder deleteParticipator(String wishlistItemId, String userId,
			AsyncCallback<Void> callback);
	

	

	
	

}
