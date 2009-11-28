package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.client.WishlistServiceAsync;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class WishListFriendsDelegate {
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	public WishListFriendsGUI gui;
	
	void setInactiveWishlistitem(final String userId, final String wishlistItemId){
		   wishlistService.setInactive(userId, wishlistItemId, new AsyncCallback<Void>(){
			   public void onFailure(Throwable caught){
				   gui.service_setInactiveWishlistitemFailed(caught);
			   }
			   
			   public void onSuccess(Void result){
				   gui.service_setInactiveWishlistitemSuccessful();
			   }
		   }//end of inner class
		   );//end of method call
	   }       
	
	 void getWishlist(final String userId) {
	    	wishlistService.getWishlist(userId, new AsyncCallback<ArrayList<WishlistItemData>>(){

	    		public void onFailure(Throwable caught){
	    			gui.service_eventGetWishlistFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<WishlistItemData> result){
	    			gui.service_eventGetWishlistSuccesfull(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
	    	}
	       

}
