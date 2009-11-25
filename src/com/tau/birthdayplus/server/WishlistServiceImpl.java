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

	@Override
	public void createParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createWishlistItem(WishlistItemData item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteParticipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWishlistItem(String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<WishlistItemData> getBookedWishlistItems(String usetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<WishlistItemData> getWishlist(ArrayList<String> uIdlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActive(String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInactive(String userId, String wishlistItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePartcipator(String wishlistItemId,
			ParticipatorData participator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWishlistItem(WishlistItemData item) {
		// TODO Auto-generated method stub
		
	}

	

}
