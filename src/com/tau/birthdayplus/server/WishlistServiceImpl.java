package com.tau.birthdayplus.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.UserNotFoundException;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.PMF;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.BuyerData;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.logic.EventManagement;
import com.tau.birthdayplus.logic.UserManagement;
import com.tau.birthdayplus.logic.WishlistManagement;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WishlistServiceImpl extends RemoteServiceServlet implements
WishlistService  {
   

	/*
	 * creates new item for the user
	 */
	public void createWishlistItem(WishlistItemData item) throws UserNotFoundException {
		WishlistManagement.createWishlistItem(item);
//		GuestData guestData = new GuestData("irinal2","Ira","Let",new Date(17,9,85));
//		GuestData friendData = new GuestData("moshel2","Moshe","Levi",new Date(12,01,85));
//		UserManagement.createProfile(guestData);
//		UserManagement.createProfile(friendData);
//		EventData eventData = new EventData("","irinal2","New Year",new Date(31,12,9), false);
//		EventManagement.createEvent(eventData);
//		EventData friendEventData = new EventData("","moshel2","Hanuka",new Date(20,12,9), false);
//		EventManagement.createEvent(friendEventData);
//		WishlistItemData itemData = new WishlistItemData("","irinal2","Car",4,"No link",500000,true);
//		WishlistItemData friendItemData = new WishlistItemData("","moshel2","TV",5,"No link",4000,true);
//		WishlistManagement.createWishlistItem(itemData);
//		WishlistManagement.createWishlistItem(friendItemData);
//		List<WishlistItemData> itemDataList = WishlistManagement.getWishlist("irinal2");
//		for (WishlistItemData itemD : itemDataList){
//			System.out.println(itemD.getItemName());
//			System.out.println(itemD.getWishlistItemId());
//		}
//		Guest g = UserManagement.loadGuest("irinal2");
//		ArrayList<WishlistItemNewData> newItems = WishlistManagement.getWishlistForEvent("irinal2", eventData.getEventId());
//		for (WishlistItemNewData itemD : newItems){
//			System.out.println(itemD.getUserName());
//		}
//		List<WishlistItem> items = g.getWishlistItems();
//		int size = items.size();
//		ParticipatorData p = new ParticipatorData("moshel2","Moshe","Levi",30000);
//		WishlistItem i = items.get(0);
//		WishlistManagement.addParticipator(KeyFactory.keyToString(i.getKey()),eventData.getEventId(), p);
//		newItems = WishlistManagement.getWishlistForEvent("irinal2", eventData.getEventId());
//		for (WishlistItemNewData itemD : newItems){
//			if (!itemD.getParticipators().isEmpty()){
//				System.out.print("{");
//				for (ParticipatorData par: itemD.getParticipators()){
//				System.out.print(par.getUserFirstName()+",");
//				}
//				System.out.println("}");
//			}
//		}
//		WishlistManagement.bookItemForUser(friendItemData.getWishlistItemId(), friendEventData.getEventId(), "irinal2");
//		newItems = WishlistManagement.getBookedWishlistItems("irinal2");
//		for (WishlistItemNewData itemD : newItems){
//			System.out.println(itemD.getEventName());
//		}
//		WishlistManagement.deleteParticipator(KeyFactory.keyToString(i.getKey()),"moshel2");
//		WishlistManagement.cancelBookItemForUser(friendItemData.getWishlistItemId(), "irinal2");
		
//		List<WishlistItemData> items2 = WishlistManagement.getParicipationWishlist("123");
//		for (WishlistItemData wi: items2){
//			System.out.print(wi.getItemName());
//		}
//		
//
//		itemData.setItemName("Iphone");
//		WishlistManagement.updateWishlistItem(itemData);
//		itemDataList = WishlistManagement.getWishlist("123");
//		
//		for (WishlistItemData itemD : itemDataList){
//			System.out.println(itemD.getItemName());
//			System.out.println(itemD.getWishlistItemId());
//			WishlistManagement.deleteWishlistItem(itemD);
//		}
//		itemDataList = WishlistManagement.getWishlist("123");
//		if (itemDataList.isEmpty()){
//			System.out.println("there is no events");
//		}
		
//		System.out.println("creating new item : "+item);
//		GuestData guestData = new GuestData("123","Ira","Let",new Date(17,9,85));
//		UserManagement.createProfile(guestData);
//		GuestData savedGuest = UserManagement.loadGuestData("123"); 
//		EventData eventData = new EventData("","123","Test1",new Date(1,1,1), false);
//		EventManagement.createEvent(eventData);
//		eventData = new EventData(eventData.getEventId(),"123","Test2", new Date(1,2,3),false);
//		EventManagement.updateEvent(eventData);
//		savedGuest = new GuestData("123", "Ira", "Letuchaya", new Date(17,9,85));
//		UserManagement.updateProfile(savedGuest);
//		Guest g = UserManagement.loadGuest(savedGuest.getId());
//		System.out.println(g.getLastName());
//		List<Event> events = g.getEvents();
//		for (Event e: events){
//			System.out.print(e.getEventName());
//		}
//		ArrayList<String> users = new ArrayList<String>();
//		users.add("123");
//		ArrayList<EventData> eventsData = new ArrayList<EventData>();
//		eventsData = EventManagement.getEvents(users);
//		for (EventData eD: eventsData){
//			System.out.println(eD.getEventName());
//			System.out.println(eD.getUserId());
//		}
//		eventsData = EventManagement.getEvents(users);
//		if (eventsData.isEmpty()){
//			System.out.println("there is no events");
//		}
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
	public void deleteWishlistItem(WishlistItemData item) {
		System.out.println("deleting item : "+item);
		WishlistManagement.deleteWishlistItem(item);
	}
	/*
	 * return user's wishlist
	 */
	public ArrayList<WishlistItemData> getMyWishlist(String userId) throws UserNotFoundException {
		return WishlistManagement.getWishlist(userId);
	}
	
	/*
	 * user will buy this item
	 * Server - check that item in db doesn't have eventId(==null) (open group or someone booked it)
	 * set isActive == false
	 * add this item to guest "I buy" list (you can get userId from Buyer)
	 */
	public void bookItemForUser(String wishlistItemId, String eventId,String userId) throws UserNotFoundException {
		WishlistManagement.bookItemForUser(wishlistItemId,eventId,userId);
	}
	
	/*
	 * user doesn't want to buy this item
	 * GUI - user can cancel only from "i'm buying " tab
	 * Server - change item status to active,Buyer = null, eventid =null and remove this item from user's 
	 * "I buy" list 
	 * check that in db "buyer" for this item has userId == userId(?)
	 */
	public void cancelBookItemForUser(String wishlistItemId, String userId) {
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
	public ArrayList<WishlistItemNewData> getBookedWishlistItems(String userId) throws UserNotFoundException {
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
	public void addParticipator(String wishlistItemId, String eventId,ParticipatorData participator) throws UserNotFoundException {
		WishlistManagement.addParticipator(wishlistItemId,eventId,participator);
		
	}
	/*
	 * delete participator from the group
	 * check if the item isActive (won't remove participator after the group has closed)
	 * if this participator is the only one - free this item(eventId == null)
	 * remove this item from user's "I buy " list
	 */
	public void deleteParticipator(String wishlistItemId, String userId) throws UserNotFoundException {
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
	public void updateParticipator(String wishlistItemId,ParticipatorData participator) {
		WishlistManagement.updateParticipator(wishlistItemId,participator);
		
	}
	
	/*
	 * add new message to the chat 
	 */
	public void addChatMessageData(String itemId, ChatMessageData message){
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
	public void bookItemForGroup(String itemId, BuyerData buyer) throws UserNotFoundException {
		WishlistManagement.bookItemForGroup(itemId, buyer);
	}
	/*
	 * only the buyer can cancel the reservation of the item
	 * check if buyer.userId == userId
	 * return buyer to participators list
	 * isActive = true
	 * buyer = null
	 */
	public void cancelBookItemForGroup(String itemId, String userId) throws UserNotFoundException {
		WishlistManagement.cancelBookItemForGroup(itemId, userId);
	}
	/*
	 * return item by id
	 */
	public WishlistItemNewData getWishlistItem(String itemId) throws UserNotFoundException {
		return WishlistManagement.getWishlistItem(itemId);
	}
	
	
	
	protected String readContent(HttpServletRequest request)
	throws ServletException, IOException {
return RPCServletUtils.readContentAsUtf8(request, false);
}

	

	


}
