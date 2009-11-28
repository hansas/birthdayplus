package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.client.WishlistServiceAsync;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class MyWishlistDelegate {
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	public MyWishlistTabGUI gui;
	 void getWishlist(final String uIdlist) {
	    	wishlistService.getWishlist(uIdlist, new AsyncCallback<ArrayList<WishlistItemData>>(){

	    		public void onFailure(Throwable caught){
	    			gui.service_eventGetWishlistFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<WishlistItemData> result){
	    			gui.service_eventGetWishlistSuccesfull(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
	    	}
	       
	    
	    
	    void createWishlistItem(final WishlistItemData item){
	    	wishlistService.createWishlistItem(item, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_eventCreateWishlistItemFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			gui.service_eventCreateWishlistItemSuccessful();
	    		}
	    	}//end of inner class
	    );//end of method call
	    }
	    
	    void updateWishlistItem(final WishlistItemData item){
	    	wishlistService.updateWishlistItem(item, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_eventUpdateWishlistItemFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			gui.service_eventUpdateWishlistItemSuccessful();
	    		}
	    	}//end of inner class
	    	);//end of method call
	    }

	   void deleteWishlistItem(final WishlistItemData item){
		   wishlistService.deleteWishlistItem(item, new AsyncCallback<Void>(){
			   public void onFailure(Throwable caught){
				   gui.service_deleteWishlistItemFailed(caught);
			   }
			   
			   public void onSuccess(Void result){
				   gui.service_deleteWishlistItemSuccessful();
			   }
		   }//end of inner class
		   );//end of method call
	   }    

}
