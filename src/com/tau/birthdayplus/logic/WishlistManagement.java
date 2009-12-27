package com.tau.birthdayplus.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.Cache;

import org.apache.catalina.mbeans.UserMBean;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.tau.birthdayplus.Email.GroupEmail;
import com.tau.birthdayplus.Email.ParticipatorEmail;
import com.tau.birthdayplus.Email.SendEmail;
import com.tau.birthdayplus.cache.Caching;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.DALWrapper;
import com.tau.birthdayplus.domain.ChatMessage;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;

public class WishlistManagement {
	private static final Logger log = Logger.getLogger(WishlistManagement.class.getName());
	
	public static void createWishlistItem(WishlistItemData item) throws UserNotFoundException {
		DALWrapper wrapper = new DALWrapper();
		try{
			BusinessObjectDAL.createWishlistItem(item, wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void createWishlistItem(WishlistItemData item,String googleId) throws UserNotFoundException{
		DALWrapper wrapper = new DALWrapper();
		try{
			Guest guest = wrapper.loadGuestByGoogleId(googleId);
			wrapper.newCreateWishlistItem(item, guest);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void removeWishlistItemForEventFromCache(WishlistItem i){
		if ((i!=null)&&(i.getEventKey()!=null)){
			Cache cache = Caching.getWishlistForEventCache();
			String key = Caching.generateWishlistForEventKey(i.getEventKey());
			cache.remove(key);
		}
	}
		
	public static void updateWishlistItem(WishlistItemData item) {
		try{
			WishlistItem i = BusinessObjectDAL.updateWishlistItem(item);
			removeWishlistItemForEventFromCache(i);
		}catch(Exception ex){
			throw new RuntimeException("updateWishlistItem failed", ex);
		}	
	}
	
	public static void deleteWishlistItem(WishlistItemData item) throws UserException {
		WishlistItem i = BusinessObjectDAL.deleteWishlistItem(item);
		removeWishlistItemForEventFromCache(i);
	}
	
	public static ArrayList<WishlistItemData> getWishlistItemData(List<WishlistItem> itemList,String userId){
		ArrayList<WishlistItemData> itemDataList = new ArrayList<WishlistItemData>();
		for (WishlistItem item : itemList){
			itemDataList.add(itemToItemData(item, userId));
		}
		return itemDataList;
	}
	
	public static WishlistItemNewData getWishlistItem(String itemId) throws UserNotFoundException {
		DALWrapper wrapper = new DALWrapper();
		try{
			WishlistItem item = wrapper.getWishlistItem(itemId);
			Guest guest = wrapper.getGuestByKey(item.getKey().getParent());
			return itemToItemNewData(item,guest,wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static WishlistItemData itemToItemData(WishlistItem item,String userId){
		return new WishlistItemData(KeyFactory.keyToString(item.getKey()),userId,item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive(),item.getThumbnail());
	}
	
	public static ArrayList<WishlistItemData> getWishlist(String userId) throws UserNotFoundException {
		DALWrapper wrapper = new DALWrapper();
		try{
			List<WishlistItem> itemList = wrapper.getWishlist(userId);
			return getWishlistItemData(itemList,userId);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static ArrayList<WishlistItemNewData> getBookedWishlistItemNewData(List<WishlistItem> itemList,
			DALWrapper wrapper) throws UserNotFoundException{
		ArrayList<WishlistItemNewData> itemDataList = new ArrayList<WishlistItemNewData>();
		for (WishlistItem item : itemList){
			Guest guest = wrapper.getGuestByKey(item.getKey().getParent());
			itemDataList.add(itemToItemNewData(item,guest,wrapper));
		}
		return itemDataList;
	}
	
	public static ArrayList<WishlistItemNewData> getWishlistItemNewDataForEvent(List<WishlistItem> itemList,Guest guest,
			DALWrapper wrapper) throws UserNotFoundException{
		ArrayList<WishlistItemNewData> itemDataList = new ArrayList<WishlistItemNewData>();
		for (WishlistItem item : itemList){
			itemDataList.add(itemToItemNewData(item,guest,wrapper));
		}
		return itemDataList;
	}
	
	public static WishlistItemNewData itemToItemNewData(WishlistItem item,Guest guest,DALWrapper wrapper) throws UserNotFoundException{
		if (item.getEventKey()==null){
			return new WishlistItemNewData(KeyFactory.keyToString(item.getKey()),guest.getId(),
			guest.getFirstName(),"","",null,
			item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive(),item.getThumbnail());
		}
		else{
			Event event = wrapper.getEventByKey(item.getEventKey());
			WishlistItemNewData newItemData = new WishlistItemNewData(KeyFactory.keyToString(item.getKey()),guest.getId(),
					guest.getFirstName(),KeyFactory.keyToString(item.getEventKey()),event.getEventName(),event.getEventDate(),item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive(),item.getThumbnail());
			ArrayList<Participator> participators = item.getParticipators();
			newItemData.setParticipators(getParticipatorDataList(participators,wrapper));
			ParticipatorData pData=null;
			if (item.getBuyerKey()!=null){
				Guest g = wrapper.getGuestByKey(item.getBuyerKey());
				pData = new ParticipatorData(g.getId(), g.getFirstName(), g.getLastName(), 0);
				for (Participator p:participators){
					if (p.getId().equals(KeyFactory.keyToString(item.getBuyerKey()))){
						pData = participatorToParticipatorData(p,wrapper);
						break;
					}
				}
			}
			newItemData.setBuyer(pData);
			newItemData.setChatMessages(getChatMessageDataList(item.getMessages(),wrapper));
			return newItemData;
		}
	}
	
	public static ArrayList<WishlistItemNewData> getWishlistForEvent(String userId,String eventId) throws UserNotFoundException{
		Cache cache = Caching.getWishlistForEventCache();
		String key = Caching.generateWishlistForEventId(eventId);
		ArrayList<WishlistItemNewData> result = (ArrayList<WishlistItemNewData>)cache.get(key);
		if (result==null){
			DALWrapper wrapper = new DALWrapper();
			try{
				List<WishlistItem> itemList = wrapper.getWishlistForEvent(userId,eventId);
				Guest guest = wrapper.getGuestById(userId);
				result = getWishlistItemNewDataForEvent(itemList,guest,wrapper);
				cache.put(key, result);
			}
			finally{
				wrapper.close();
			}
		}
		return result;
	}
	
//	public static ArrayList<WishlistItemNewData> getBookedWishlistItems(String userId) throws UserNotFoundException{
//		DALWrapper wrapper = new DALWrapper();
//		try{
//			Guest guest = wrapper.getGuestById(userId);
//			List<Key> bookedItemList = guest.getIBuyItems();
//			List<WishlistItem> items = wrapper.getWishlistItemById(bookedItemList);
//			return getBookedWishlistItemNewData(items,wrapper);
//		}
//		finally{
//			wrapper.close();
//		}
//	}
	
	public static ArrayList<WishlistItemNewData> getBookedWishlistItems(String userId)
	throws UserNotFoundException{
		Cache cache = Caching.getBookedWishlistItemsCache();
		String key = Caching.generateBookedWishlistItemsId(userId);
		ArrayList<WishlistItemNewData> result = (ArrayList<WishlistItemNewData>)cache.get(key);
		if (result==null){
			DALWrapper wrapper = new DALWrapper();
			try{
				List<WishlistItem> items = wrapper.getBookedWishlistItems2(userId);
				result = getBookedWishlistItemNewData(items,wrapper);
				cache.put(key, result);
			}
			finally{
				wrapper.close();
			}
		}
		return result;
	}
	
	public static ArrayList<WishlistItemPolaniData> getLastItemsForUser(String myUserId,
			String anotherUserId) throws UserNotFoundException {
		DALWrapper wrapper = new DALWrapper();
		try{
			return wrapper.getLastItemsForUser(myUserId, anotherUserId);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static ArrayList<WishlistItemPolaniData> getWishlistitemPolaniDataList(List<WishlistItem> itemList,String anotherUserId,DALWrapper wrapper){
		ArrayList<WishlistItemPolaniData> itemsPolani = new ArrayList<WishlistItemPolaniData>();
		for (WishlistItem item : itemList){
			Event e = wrapper.getEventByKey(item.getEventKey());
			List<Participator> participators = item.getParticipators();
			Integer participation = 0;
			for (Participator p : participators){
				if (p.getId().equals(anotherUserId)){
					participation = p.getMoney();
				}
			}
			WishlistItemPolaniData itemPolani = new WishlistItemPolaniData(KeyFactory.keyToString(item.getKey()),item.getItemName(), item.getPrice(), participation, e.getEventName());
			itemsPolani.add(itemPolani);
		}
		return itemsPolani;
	}
	
	public static void bookItemForUser(String wishlistItemId, String eventId,String userId) throws UserNotFoundException, UserException {
		BusinessObjectDAL.bookItemForUser(wishlistItemId,eventId,userId);
//		DALWrapper wrapper = new DALWrapper();
//		try{
//			WishlistItem i = wrapper.getWishlistItem(wishlistItemId);
//			Guest g = wrapper.getGuestByKey(i.getKey().getParent());
//			String anotherUserId = g.getId();
//			log.info("user Id: "+userId);
//			log.info("anotherUserId: "+anotherUserId);
//			List<WishlistItemPolaniData> items = getLastItemsForUser(userId, anotherUserId);
//			for (WishlistItemPolaniData item : items){
//				log.info(item.getItemName());
//			}
//		}
//		finally{
//			wrapper.close();
//		}
	}
	
	public static void cancelBookItemForUser(String wishlistItemId, String userId) {
		BusinessObjectDAL.cancelBookItemForUser(wishlistItemId, userId);
	}
	
	public static void bookItemForGroup(String itemId, String userId,String message) throws UserException, Exception{
		DALWrapper wrapper = new DALWrapper();
		try{
			wrapper.bookItemForGroup(itemId, userId);
			WishlistItem item = wrapper.getWishlistItem(itemId);
			ArrayList<Participator> participators = item.getParticipators();
			ArrayList<ParticipatorEmail> participatorsE = getParticipatorEmailList(participators,wrapper);
			wrapper.sendEmailToGroup(itemId, userId, message, participatorsE);
		}
		finally{
			wrapper.close();
		}
		
		
	}
	
	public static void cancelBookItemForGroup(String itemId, String userId) throws UserNotFoundException, UserException{
		BusinessObjectDAL.cancelBookItemForGroup(itemId, userId);
	}
	
	public static ArrayList<ParticipatorData> getParticipatorDataList(ArrayList<Participator> participators,
			DALWrapper wrapper) throws UserNotFoundException{
		ArrayList<ParticipatorData> participatorsD = new ArrayList<ParticipatorData>();
		for (Participator p: participators){
			participatorsD.add(participatorToParticipatorData(p,wrapper));
		}
		return participatorsD;
	}
	
	public static ArrayList<ParticipatorEmail> getParticipatorEmailList(ArrayList<Participator> participators,
			DALWrapper wrapper) throws UserNotFoundException{
		ArrayList<ParticipatorEmail> participatorsE = new ArrayList<ParticipatorEmail>();
		for (Participator p: participators){
			participatorsE.add(participatorToParticipatorEmail(p,wrapper));
		}
		return participatorsE;
	}
	
	public static ParticipatorEmail participatorToParticipatorEmail(Participator participator,DALWrapper wrapper) throws UserNotFoundException{
		Guest guest = wrapper.getGuestById(participator.getId());
		return new ParticipatorEmail(participator.getId(),guest.getFirstName(),guest.getLastName(),participator.getMoney(),guest.getEmail());
	}
	
	public static ArrayList<ChatMessageData> getChatMessageDataList(ArrayList<ChatMessage> messages,DALWrapper wrapper) throws UserNotFoundException{
		ArrayList<ChatMessageData> messagesD = new ArrayList<ChatMessageData>();
		for (ChatMessage m:messages){
			messagesD.add(chatMessageToChatMessageData(m,wrapper));
		}
		return messagesD;
	}
	
	public static ChatMessageData chatMessageToChatMessageData(ChatMessage message,DALWrapper wrapper) throws UserNotFoundException{
		Guest guest = wrapper.getGuestById(message.getId());
		return new ChatMessageData(guest.getId(),guest.getFirstName(),message.getTimeStamp(),message.getMessage());
	}
	
	
//	public static void deleteBookedWishlistItem(String userId, String wishlistItemId) throws UserNotFoundException{
//		BusinessObjectDAL.deleteBookedWishlistItem(userId,wishlistItemId);
//	}
	
	public static ParticipatorData participatorToParticipatorData(Participator participator,DALWrapper wrapper) throws UserNotFoundException{
		Guest guest = wrapper.getGuestById(participator.getId());
		return new ParticipatorData(participator.getId(),guest.getFirstName(),guest.getLastName(),participator.getMoney());
	}
	
	public static void addParticipator(String wishlistItemId, String eventId,ParticipatorData participator) throws UserNotFoundException {
		BusinessObjectDAL.addParticipator(wishlistItemId,eventId,participator);
	}
	
	public static void updateParticipator(String wishlistItemId,ParticipatorData participator){
		BusinessObjectDAL.updateParticipator(wishlistItemId,participator);
	}
	
	public static void deleteParticipator(String wishlistItemId, String userId) throws UserNotFoundException {
		BusinessObjectDAL.deleteParticipator(wishlistItemId, userId);
	}
	
	
	
	public static void addChatMessageData(String itemId, ChatMessageData messageData){
		ChatMessage message = new ChatMessage(messageData.getUserId(),messageData.getTimeStamp(),messageData.getMesssage());
		BusinessObjectDAL.addChatMessageData(itemId, message);
	}
	
//	public static ArrayList<WishlistItemData> getParicipationWishlist(String userId){
//		Guest g = UserManagement.loadGuest(userId);
//		List<WishlistItem> itemList = BusinessObjectDAL.getParticipationWishlist(g);
//		for (WishlistItem wi: itemList){
//			System.out.println(wi.getItemName());
//		}
//		for (WishlistItem wi: g.getWishlistItems()){
//			System.out.println(wi.getItemName());
//			for (Participator p: wi.getParticipators()){
//				System.out.print("P1");
//			}
//		}
//		return null;
//	}

	
	
}
