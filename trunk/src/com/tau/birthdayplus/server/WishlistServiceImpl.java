package com.tau.birthdayplus.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.Email.SendEmail;

import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.client.Services.WishlistService;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.PMF;
import com.tau.birthdayplus.domain.ChatMessage;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;
import com.tau.birthdayplus.logic.EventManagement;
import com.tau.birthdayplus.logic.UserManagement;
import com.tau.birthdayplus.logic.WishlistManagement;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WishlistServiceImpl extends RemoteServiceServlet implements
WishlistService  {
	
	private static final Logger log = Logger.getLogger(WishlistServiceImpl.class.getName());

   

	/*
	 * creates new item for the user
	 */
	public void createWishlistItem(WishlistItemData item) throws UserNotFoundException, UserException {
		WishlistManagement.createWishlistItem(item);
	}
	
	/*
	 * update the item, don't update isActive
	 * GUI-user can update only his wishlist
	 */
	public void updateWishlistItem(WishlistItemData item) {
		System.out.println("updating item : "+item);
		WishlistManagement.updateWishlistItem(item);
	}
	/*
	 * delete the item 
	 * GUI - user can delete only his wishlist items
	 */
	public void deleteWishlistItem(WishlistItemData item) throws Exception {
		System.out.println("deleting item : "+item);
		WishlistManagement.deleteWishlistItem(item);
	}
	/*
	 * return user's wishlist
	 */
	public ArrayList<WishlistItemData> getMyWishlist(String userId) throws UserNotFoundException, UserException {
		return WishlistManagement.getWishlist(userId);
	}
	
	/*
	 * user will buy this item
	 * Server - check that item in db doesn't have eventId(==null) (open group or someone booked it)
	 * set isActive == false
	 * add this item to guest "I buy" list (you can get userId from Buyer)
	 */
	public void bookItemForUser(String wishlistItemId, String eventId,String userId) throws UserNotFoundException, UserException {
		WishlistManagement.bookItemForUser(wishlistItemId,eventId,userId);
	}
	
	/*
	 * user doesn't want to buy this item
	 * GUI - user can cancel only from "i'm buying " tab
	 * Server - change item status to active,Buyer = null, eventid =null and remove this item from user's 
	 * "I buy" list 
	 * check that in db "buyer" for this item has userId == userId(?)
	 */
	public void cancelBookItemForUser(String wishlistItemId, String userId) throws UserException {
		WishlistManagement.cancelBookItemForUser(wishlistItemId, userId);
		
	}
	
	/*
	 *  return wishlist for this user , for the event
	 *  server - all the items that belong to the user and their (eventId == eventId, or
	 *  eventId == null ) and isActive == true
	 */
	public ArrayList<WishlistItemNewData> getWishlistForEvent(String userId,String eventId) throws UserNotFoundException {
		return WishlistManagement.getWishlistForEvent(userId,eventId);
	}
	
	/*
	 * return all the items this user has booked
	 */
	public ArrayList<WishlistItemNewData> getBookedWishlistItems(String userId) throws UserNotFoundException, UserException {
		return WishlistManagement.getBookedWishlistItems(userId);
	}
	

	/*
	 * add participator
	 * Server - check if the item in db has the same eventId or eventId==null 
	 * (two people can join to the new group from different events)
	 * check that item is Active 
	 * check that user doesn't participate already in this group
	 * add this item to user's "I buy " items
	 */
	public void addParticipator(String wishlistItemId, String eventId,ParticipatorData participator) throws UserNotFoundException, UserException {
		WishlistManagement.addParticipator(wishlistItemId,eventId,participator);
		
	}
	/*
	 * delete participator from the group
	 * check if the item isActive (won't remove participator after the group has closed)
	 * if this participator is the only one - free this item(eventId == null)
	 * remove this item from user's "I buy " list
	 */
	public void deleteParticipator(String wishlistItemId, String userId) throws UserNotFoundException, UserException {
		WishlistManagement.deleteParticipator(wishlistItemId, userId);
		
	}
	/*
	 * remove this item from this user's "I buy " list , check if item isActive == false
	 * (won't remove item with open group)
	 */
//	public void deleteBookedWishlistItem(String userId, String wishlistItemId) throws UserNotFoundException {
//		WishlistManagement.deleteBookedWishlistItem(userId, wishlistItemId);
//		
//	}

	/*
	 * update participator (update money only)
	 * Server - check if the item is active (group is not closed yet) 
	 */
	public void updateParticipator(String wishlistItemId,ParticipatorData participator) throws UserException {
		WishlistManagement.updateParticipator(wishlistItemId,participator);
		
	}
	
	/*
	 * add new message to the chat 
	 */
	public void addChatMessageData(String itemId, ChatMessageData message) throws UserException{
		WishlistManagement.addChatMessageData(itemId, message);
	}
	/*
	 * the group will buy this item
	 * itemId - id of the item
	 * buyer - the information about the buyer
	 * server - check if current buyer == null
	 * remove buyer from participators list and  update Buyer for this item
	 * setActive = false
	 */
	public void bookItemForGroup(String itemId, String userId,String message,Double actualPrice) throws UserException, Exception {
		WishlistManagement.bookItemForGroup(itemId, userId, message,actualPrice);
	}
	/*
	 * only the buyer can cancel the reservation of the item
	 * check if buyer.userId == userId
	 * return buyer to participators list
	 * isActive = true
	 * buyer = null
	 */
	public void cancelBookItemForGroup(String itemId, String userId,String message) throws Exception {
		WishlistManagement.cancelBookItemForGroup(itemId, userId,message);
	}
	/*
	 * return item by id
	 */
	public WishlistItemNewData getWishlistItem(String itemId) throws UserNotFoundException, UserException {
		return WishlistManagement.getWishlistItem(itemId);
	}
	
	/*
	 * Three last items that this person bought for me
	 * 
	 */
	public ArrayList<WishlistItemPolaniData> getLastItemsForUser(String myUserId,
			String anotherUserId) throws UserNotFoundException {
		return WishlistManagement.getLastItemsForUser(myUserId, anotherUserId);
	}
	
	
	/*
	 * get chat messages for the item
	 */
	public ArrayList<ChatMessageData> getChatMessages(String itemId) throws UserNotFoundException, UserException {
		return WishlistManagement.getChatMessagesByItem(itemId);
	}

	
	
	
	protected String readContent(HttpServletRequest request)
	throws ServletException, IOException {
		String content = RPCServletUtils.readContentAsUtf8(request, false);
		content = URLDecoder.decode(content, "UTF-8");
		return content;
}

	


	

	

	


}
