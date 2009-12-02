package com.tau.birthdayplus.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mbeans.UserMBean;

import com.google.appengine.api.datastore.KeyFactory;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.DALWrapper;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
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
	
	public static WishlistItemData itemToItemData(WishlistItem item,String userId){
		return new WishlistItemData(KeyFactory.keyToString(item.getKey()),userId,item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
	}
	
	public static ArrayList<WishlistItemData> getWishlist(String userId) {
		DALWrapper wrapper = new DALWrapper();
		try{
			List<WishlistItem> itemList = wrapper.getWishlist(userId);
			return getWishlistItemData(itemList,userId);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static ArrayList<WishlistItemNewData> getWishlistItemNewData(List<WishlistItem> itemList,
			Guest guest,DALWrapper wrapper){
		ArrayList<WishlistItemNewData> itemDataList = new ArrayList<WishlistItemNewData>();
		for (WishlistItem item : itemList){
			itemDataList.add(itemToItemNewData(item,guest,wrapper));
		}
		return itemDataList;
	}
	
	public static WishlistItemNewData itemToItemNewData(WishlistItem item,Guest guest,DALWrapper wrapper){
		if (item.getEventKey()==null){
			return new WishlistItemNewData(KeyFactory.keyToString(item.getKey()),guest.getId(),
			guest.getFirstName(),"","",
			item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
		}
		else{
			Event event = wrapper.getEventByKey(item.getEventKey());
			WishlistItemNewData newItemData = new WishlistItemNewData(KeyFactory.keyToString(item.getKey()),guest.getId(),
					guest.getFirstName(),KeyFactory.keyToString(item.getEventKey()),event.getEventName(),item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
			newItemData.setParticipators(getParticipatorDataList(item.getParticipators(),wrapper));
			return newItemData;
		}
	}
	
	public static ArrayList<WishlistItemNewData> getWishlistForEvent(String userId,String eventId){
		DALWrapper wrapper = new DALWrapper();
		try{
			List<WishlistItem> itemList = wrapper.getWishlistForEvent(userId,eventId);
			Guest guest = wrapper.getGuestById(userId);
			return getWishlistItemNewData(itemList,guest,wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static ArrayList<WishlistItemNewData> getBookedWishlistItems(String userId){
		DALWrapper wrapper = new DALWrapper();
		try{
			Guest guest = wrapper.getGuestById(userId);
			List<WishlistItem> bookedItemList = guest.getIBuyItems();
			return getWishlistItemNewData(bookedItemList,guest,wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void bookItemForUser(String wishlistItemId, String eventId,String userId) {
		BusinessObjectDAL.bookItemForUser(wishlistItemId,eventId,userId);
	}
	
	public static ArrayList<ParticipatorData> getParticipatorDataList(ArrayList<Participator> participators,
			DALWrapper wrapper){
		ArrayList<ParticipatorData> participatorsD = new ArrayList<ParticipatorData>();
		for (Participator p: participators){
			participatorsD.add(participatorToParticipatorData(p,wrapper));
		}
		return participatorsD;
	}
	
	public static ParticipatorData participatorToParticipatorData(Participator participator,DALWrapper wrapper){
		Guest guest = wrapper.getGuestById(participator.getUserId());
		return new ParticipatorData(participator.getUserId(),guest.getFirstName(),guest.getLastName(),participator.getMoney());
	}
	
	public static void addParticipator(String wishlistItemId, String eventId,ParticipatorData participator) {
		BusinessObjectDAL.addParticipator(wishlistItemId,eventId,participator);
	}
	
	public static ArrayList<WishlistItemData> getParicipationWishlist(String userId){
		Guest g = UserManagement.loadGuest(userId);
		List<WishlistItem> itemList = BusinessObjectDAL.getParticipationWishlist(g);
		for (WishlistItem wi: itemList){
			System.out.println(wi.getItemName());
		}
		for (WishlistItem wi: g.getWishlistItems()){
			System.out.println(wi.getItemName());
			for (Participator p: wi.getParticipators()){
				System.out.print("P1");
			}
		}
		return null;
	}

}
