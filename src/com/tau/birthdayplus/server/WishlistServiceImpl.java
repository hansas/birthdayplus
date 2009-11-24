package com.tau.birthdayplus.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WishlistServiceImpl extends RemoteServiceServlet implements
WishlistService  {

	public void printHello() {
		System.out.println("Hello from the WishlistService");
		
	}
	/*
	 * add participator
	 * Server - check if the item is active and the user isn't already participate
	 */
	public void createParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * creates new item for the user
	 */
	public void createWishlistItem(WishlistItemData item) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * delete  participator from the list
	 * 
	 */
	public void deleteParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * delete the item 
	 * GUI - user can delete only his wishlist items
	 */
	public void deleteWishlistItem(String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * return all the items this user booked
	 */
	public ArrayList<WishlistItemData> getBookedWishlistItems(String usetId) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * return user's wishlist
	 */
	public ArrayList<WishlistItemData> getWishlist(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * user don't want to buy it 
	 * GUI - user can cancel only from "i'm buying " tab
	 */
	public void setActive(String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * user wants to buy this item
	 * Server - check that item is active
	 */
	public void setInactive(String userId, String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * update participator (update money only)
	 * Server - check if exists
	 */
	public void updatePartcipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * update the item, don't update isActive
	 * GUI-user can update only his wishlist
	 */
	public void updateWishlistItem(WishlistItemData item) {
		// TODO Auto-generated method stub
		
	}

}
