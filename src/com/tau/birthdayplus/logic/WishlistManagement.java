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
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.WishlistItemData;

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
	
	public static ArrayList<WishlistItemData> getParicipationWishlist(String userId){
		Guest g = UserManagement.loadGuest(userId);
		return null;
	}

}
