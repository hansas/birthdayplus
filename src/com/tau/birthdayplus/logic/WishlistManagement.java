package com.tau.birthdayplus.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mbeans.UserMBean;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.client.UserNotFoundException;
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

public class WishlistManagement {
	
	public static void createWishlistItem(WishlistItemData item) {
		try{
			BusinessObjectDAL.createWishlistItem(item);
		}catch(Exception ex){
			throw new RuntimeException("createWishlistItem failed", ex);
		}
	}
	
		
	public static void updateWishlistItem(WishlistItemData item) {
		try{
			BusinessObjectDAL.updateWishlistItem(item);
		}catch(Exception ex){
			throw new RuntimeException("updateWishlistItem failed", ex);
		}	
	}
	
	public static void deleteWishlistItem(WishlistItemData item) {
		try{
			BusinessObjectDAL.deleteWishlistItem(item);
		}catch(Exception ex){
			throw new RuntimeException("deleteWishlistItem failed", ex);
		}		
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
		return new WishlistItemData(KeyFactory.keyToString(item.getKey()),userId,item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
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
			guest.getFirstName(),"","",
			item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
		}
		else{
			Event event = wrapper.getEventByKey(item.getEventKey());
			WishlistItemNewData newItemData = new WishlistItemNewData(KeyFactory.keyToString(item.getKey()),guest.getId(),
					guest.getFirstName(),KeyFactory.keyToString(item.getEventKey()),event.getEventName(),item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
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
			return newItemData;
		}
	}
	
	public static ArrayList<WishlistItemNewData> getWishlistForEvent(String userId,String eventId) throws UserNotFoundException{
		DALWrapper wrapper = new DALWrapper();
		try{
			List<WishlistItem> itemList = wrapper.getWishlistForEvent(userId,eventId);
			Guest guest = wrapper.getGuestById(userId);
			return getWishlistItemNewDataForEvent(itemList,guest,wrapper);
		}
		finally{
			wrapper.close();
		}
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
		DALWrapper wrapper = new DALWrapper();
		try{
			List<WishlistItem> items = wrapper.getBookedWishlistItems2(userId);
			return getBookedWishlistItemNewData(items,wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void bookItemForUser(String wishlistItemId, String eventId,String userId) throws UserNotFoundException {
		BusinessObjectDAL.bookItemForUser(wishlistItemId,eventId,userId);
	}
	
	public static void cancelBookItemForUser(String wishlistItemId, String userId) {
		BusinessObjectDAL.cancelBookItemForUser(wishlistItemId, userId);
	}
	
	public static void bookItemForGroup(String itemId, ParticipatorData buyer) throws UserNotFoundException{
		BusinessObjectDAL.bookItemForGroup(itemId, buyer);
	}
	
	public static void cancelBookItemForGroup(String itemId, String userId) throws UserNotFoundException{
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
