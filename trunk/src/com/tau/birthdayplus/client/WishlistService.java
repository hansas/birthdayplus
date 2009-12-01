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
	////////////////for managing my events\\\\\\\\\\\\\\\\\\\\\\
	/*
	 * creates new item for the user
	 */
	void createWishlistItem(WishlistItemData item);
	/*
	 * update the item, don't update isActive
	 * GUI-user can update only his wishlist
	 */
	void updateWishlistItem(WishlistItemData item);
	/*
	 * delete the item 
	 * GUI - user can delete only his wishlist items
	 */
	void deleteWishlistItem(WishlistItemData item);
	/*
	 * return user's wishlist
	 */
	ArrayList<WishlistItemData> getMyWishlist(String userId);
	/////////////////////////////////////////////////////////////////
	/*
	 * user will buy this item
	 * Server - check that item in db doesn't have eventId(==null) (open group or someone booked it)
	 * set isActive == false
	 * add this item to guest "I buy" list (you can get userId from Buyer)
	 */
	void bookItemForUser(String wishlistItemId, String EventId, String userId);
	/*
	 * user doesn't want to buy this item
	 * GUI - user can cancel only from "i'm buying " tab
	 * Server - change item status to active,Buyer = null, eventid =null and remove this item from user's 
	 * "I buy" list 
	 * check that in db "buyer" for this item has userId == userId(?)
	 */
	void cancelBookItemForUser(String wishlistItemId,String userId);
	/*
	 *  return wishlist for this user , for the event
	 *  server - all the items that belong to the user and their (eventId == eventId, or
	 *  eventId == null ) and isActive == true
	 */
	ArrayList<WishlistItemData> getWishlistForEvent(String uId,String eventId);

	/*
	 * add participator
	 * Server - check if the item in db has the same eventId or eventId==null 
	 * (two people can join to the new group from different events)
	 * check that item is Active 
	 * check that user doesn't participate already in this group
	 * add this item to user's "I buy " items
	 */
	void addParticipator(String wishlistItemId,String eventId,ParticipatorData participator);
	/*
	 * update participator (update money only)
	 * Server - check if the item is active (group is not closed yet) 
	 */
	void updateParticipator(String wishlistItemId,ParticipatorData participator);
	
	void deleteParticipator(String wishlistItemId ,String userId);
	/*
	 * the group will buy this item
	 * isActive == false
	 */
	//void bookItemForGroup(WishlistItemData item);
	/*
	 * return all the items this user has booked
	 */
	ArrayList<WishlistItemData> getBookedWishlistItems(String usetId);
	/*
	 * remove this item from this user's "I buy " list , check if item isActive == false
	 * (won't remove item with open group)
	 */
	void deleteBookedWishlistItem(String userId, String wishlistItemId);

}
