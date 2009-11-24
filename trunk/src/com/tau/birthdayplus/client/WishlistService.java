package com.tau.birthdayplus.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;



/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("wishlist")
public interface WishlistService extends RemoteService{
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello();
	/*
	 * creates new item for the user
	 */
	void createWishlistItem(WishlistItemData item);
	/*
	 * update the item
	 * check if the user is the item owner
	 */
	void updateWishlistItem(String userId,WishlistItemData item);
	/*
	 * delete the item 
	 * check if the user is the item owner
	 */
	void deleteWishlistItem(String userId,String wishlistItemId);
	/*
	 * return user's wishlist
	 */
	ArrayList<WishlistItemData> getWishlist(String userId);
	/*
	 * add participator
	 */
	void createParticipator(ParticipatorData participator);
	/*
	 * update participator (update money only)
	 * check that the user is a participator
	 */
	void updatePartcipator(String userId,ParticipatorData participator);
	/*
	 * delete the participator
	 * check if the user is a participator
	 */
	void deleteParticipator(String userId,ParticipatorData participator);
	/*
	 * return participators in the given wishlist item
	 */
	ArrayList<ParticipatorData> getParticipators(String wishlistItemId);

}
