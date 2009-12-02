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
			Guest guest,Event event){
		ArrayList<WishlistItemNewData> itemDataList = new ArrayList<WishlistItemNewData>();
		for (WishlistItem item : itemList){
			itemDataList.add(itemToItemNewData(item,guest,event));
		}
		return itemDataList;
	}
	
	public static WishlistItemNewData itemToItemNewData(WishlistItem item,Guest guest,Event event){
		return new WishlistItemNewData(KeyFactory.keyToString(item.getKey()),guest.getId(),
				guest.getFirstName(),KeyFactory.keyToString(event.getKey()),event.getEventName(),
			item.getItemName(),item.getPriority(),item.getLink(),item.getPrice(),item.getIsActive());
	}
	
	public static ArrayList<WishlistItemNewData> getWishlistForEvent(String userId,String eventId){
		DALWrapper wrapper = new DALWrapper();
		try{
			List<WishlistItem> itemList = wrapper.getWishlistForEvent(userId,eventId);
			Guest guest = wrapper.getGuestById(userId);
			Event event = wrapper.getEventById(eventId); 
			return getWishlistItemNewData(itemList,guest,event);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void bookItemForUser(String wishlistItemId, String eventId,String userId) {
		BusinessObjectDAL.bookItemForUser(wishlistItemId,eventId,userId);
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
