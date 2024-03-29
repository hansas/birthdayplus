package com.tau.birthdayplus.client.Services;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;



/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("wishlist")
public interface WishlistService extends RemoteService{

	////////////////for managing my events\\\\\\\\\\\\\\\\\\\\\\
	/*
	 * creates new item for the user
	 */
	void createWishlistItem(WishlistItemData item) throws UserNotFoundException, UserException;
	/*
	 * update the item, don't update isActive
	 * GUI-user can update only his wishlist
	 */
	void updateWishlistItem(WishlistItemData item) throws UserException;
	/*
	 * delete the item 
	 * GUI - user can delete only his wishlist items
	 */
	void deleteWishlistItem(WishlistItemData item) throws UserException, Exception;
	/*
	 * return user's wishlist
	 */
	ArrayList<WishlistItemData> getMyWishlist(String userId) throws UserNotFoundException, UserException;
	/////////////////////////////////////////////////////////////////
	/*
	 * user will buy this item
	 * Server - check that item in db doesn't have eventId(==null) (open group or someone booked it)
	 * set isActive == false
	 * add this item to guest "I buy" list (you can get userId from Buyer)
	 */
	void bookItemForUser(String wishlistItemId, String EventId, String userId) throws UserNotFoundException, UserException;
	/*
	 * user doesn't want to buy this item
	 * GUI - user can cancel only from "i'm buying " tab
	 * Server - change item status to active,Buyer = null, eventid =null and remove this item from user's 
	 * "I buy" list 
	 * check that in db "buyer" for this item has userId == userId(?)
	 */
	void cancelBookItemForUser(String wishlistItemId,String userId) throws UserException;
	/*
	 *  return wishlist for this user , for the event
	 *  server - all the items that belong to the user and their (eventId == eventId, or
	 *  eventId == null ) and isActive == true
	 */
	ArrayList<WishlistItemNewData> getWishlistForEvent(String uId,String eventId) throws UserNotFoundException, UserException;

	/*
	 * add participator
	 * Server - check if the item in db has the same eventId or eventId==null 
	 * (two people can join to the new group from different events)
	 * check that item is Active 
	 * check that user doesn't participate already in this group
	 * add this item to user's "I buy " items
	 */
	void addParticipator(String wishlistItemId,String eventId,ParticipatorData participator) throws UserNotFoundException, UserException;
	/*
	 * update participator (update money only)
	 * Server - check if the item is active (group is not closed yet) 
	 */
	void updateParticipator(String wishlistItemId,ParticipatorData participator) throws UserException;
	
	void deleteParticipator(String wishlistItemId ,String userId) throws UserNotFoundException, UserException;
	
	/*
	 * return all the items this user has booked
	 */
	ArrayList<WishlistItemNewData> getBookedWishlistItems(String usetId) throws UserNotFoundException, UserException;
	/*
	 * remove this item from this user's "I buy " list , check if item isActive == false
	 * (won't remove item with open group)
	 */
	//void deleteBookedWishlistItem(String userId, String wishlistItemId) throws UserNotFoundException;
	
	/*
	 * the group will buy this item
	 * ietmId - id of the item
	 * buyer - the information about the buyer
	 * server - check if current buyer == null
	 * remove buyer from participators list and  update Buyer for this item
	 * setActive = false
	 */
	void bookItemForGroup(String itemId, String userId,String message,Double actualPrice) throws UserNotFoundException, UserException, Exception;
	/*
	 * only the buyer can cancel the reservation of the item
	 * check if buyer.userId == userId
	 * return buyer to participators list
	 * isActive = true
	 * buyer = null
	 */
	void cancelBookItemForGroup(String itemId, String userId,String message) throws UserNotFoundException, UserException, Exception;
	/*
	 * add new message to the chat 
	 */
	void addChatMessageData(String itemId,ChatMessageData message) throws UserException;
	/*
	 * return item by id
	 */
	WishlistItemNewData getWishlistItem(String itemId) throws UserNotFoundException, UserException;
	/*
	 * Three last items that this person bought for me
	 * 
	 */
	ArrayList<WishlistItemPolaniData> getLastItemsForUser(String myUserId,String anotherUserId ) throws UserNotFoundException, UserException;
	/*
	 * get chat messages for the item
	 */
	ArrayList<ChatMessageData> getChatMessages(String itemId) throws UserNotFoundException, UserException;
	
	

}
