package com.tau.birthdayplus.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.Participator;
import com.tau.birthdayplus.dto.WishlistItem;

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
	void createWishlistItem(WishlistItem item);
	/*
	 * update the item
	 * check if the user is the item owner
	 */
	void updateWishlistItem(String userId,WishlistItem item);
	/*
	 * delete the item 
	 * check if the user is the item owner
	 */
	void deleteWishlistItem(String userId,String wishlistItemId);
	/*
	 * return user's wishlist
	 */
	ArrayList<WishlistItem> getWishlist(String userId);
	/*
	 * add participator
	 */
	void createParticipator(Participator participator);
	/*
	 * update participator (update money only)
	 * check that the user is a participator
	 */
	void updatePartcipator(String userId,Participator participator);
	/*
	 * delete the participator
	 * check if the user is a participator
	 */
	void deleteParticipator(String userId,Participator participator);
	/*
	 * return participators in the given wishlist item
	 */
	ArrayList<Participator> getParticipators(String wishlistItemId);

}
