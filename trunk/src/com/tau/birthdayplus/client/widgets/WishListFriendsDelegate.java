package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.client.WishlistServiceAsync;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class WishListFriendsDelegate {
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	public WishListFriendsGUI gui;
	
	void bookItemForUser(String wishlistItemId,String eventId, String userId){
		   wishlistService.bookItemForUser(wishlistItemId, eventId, userId, new AsyncCallback<Void>(){
			   public void onFailure(Throwable caught){
				   gui.service_eventBookItemForUserFailed(caught);
			   }
			   
			   public void onSuccess(Void result){
				   gui.service_eventBookItemForUserSuccesfull();
			   }
		   }//end of inner class
		   );//end of method call
	   }   
	
	
	
	 void getWishlist(final String userId, final String eventId) {
		
	    	wishlistService.getWishlistForEvent(userId, eventId,  new AsyncCallback<ArrayList<WishlistItemData>>(){

	    		public void onFailure(Throwable caught){
	    			gui.service_eventGetWishlistFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<WishlistItemData> result){
	    			gui.service_eventGetWishlistSuccesfull(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
	    	}
	 
	 void addParticipator(String wishlistItemId , String eventId, ParticipatorData participator){
		 wishlistService.addParticipator(wishlistItemId, eventId, participator, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				gui.service_eventAddParticipatorFailed(caught);
			}

			public void onSuccess(Void result) {
				gui.service_eventAddParticipatorSuccesfull();
				
			} 
		 }
		 );
	 }
	       

}
