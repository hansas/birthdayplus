package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.client.WishlistServiceAsync;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class IBuyDelegate {
	 // Create a remote service proxy to talk to the server-side Profile service.
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class);
	public IBuyTabGUI gui;
	
	
	void cancelBookItemForUser(String wishlistItemId, String userId){
		wishlistService.cancelBookItemForUser(wishlistItemId, userId,new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				gui.sevice_eventCancelBookItemForUserFailed(caught);				
			}

			public void onSuccess(Void result) {
				gui.service_eventCancelBookItemForUserSuccesfull();	
			}
		});		
	}
	
	void deleteParticipator(String wishlistItemId, String userId){
		wishlistService.deleteParticipator(wishlistItemId, userId, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				gui.service_eventDeleteParticipatorFailed(caught);
			}

			public void onSuccess(Void result) {
				gui.service_eventDeleteParticipatorSuccesfull();
			}
			
		});
	}
	
	void deleteItemFromTab(String userId,String wishlistItemId){
		wishlistService.deleteBookedWishlistItem(userId, wishlistItemId,new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				gui.service_eventDeleteItemFromTabFailed(caught);
			}

			public void onSuccess(Void result) {
				gui.service_eventDeleteItemFromTabSuccesfull();				
			}
		});
	}
	
	 
	 
	 void getBookedWishlist(final String userId) {
	    	wishlistService.getBookedWishlistItems(userId, new AsyncCallback<ArrayList<WishlistItemData>>(){

	    		public void onFailure(Throwable caught){
	    			gui.service_getBookedWishlistFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<WishlistItemData> result){
	    			gui.service_getBookedWishlistSuccesfull(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
	    	}


}
