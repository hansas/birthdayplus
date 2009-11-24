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
	 * update the item, don't update isActive
	 * user can update only his wishlist
	 */
	void updateWishlistItem(WishlistItemData item);
	/*
	 * delete the item 
	 * user can delete only his wishlist items
	 */
	void deleteWishlistItem(String wishlistItemId);
	/*
	 * user wants to buy this item
	 * check that item is active
	 */
	void setInactive(String userId,String wishlistItemId);
	/*
	 * user don't want to buy it 
	 * user can cancel only from "i'm buying " tab
	 */
	void setActive(String wishlistItemId);
	/*
	 * return user's wishlist
	 */
	ArrayList<WishlistItemData> getWishlist(String userId);
	/*
	 * add participator
	 * check if the item is active and the user isn't already participate
	 */
	void createParticipator(String wishlistItemId,ParticipatorData participator);
	/*
	 * update participator (update money only)
	 * check if exists
	 */
	void updatePartcipator(String wishlistItemId,ParticipatorData participator);
	/*
	 * delete  participator from the list
	 * 
	 */
	void deleteParticipator(String wishlistItemId ,ParticipatorData participator);
	/*
	 * return all the items this user booked
	 */
	ArrayList<WishlistItemData> getBookedWishlistItems(String usetId);

}
