package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.Services.RequestProxy;
import com.tau.birthdayplus.client.Services.WishlistService;
import com.tau.birthdayplus.client.Services.WishlistServiceAsync;

import com.tau.birthdayplus.dto.client.WishlistItemData;


public class MyWishlistDelegate {
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	public MyWishlistTabGUI gui;
	public Birthdayplus entryPoint;

	 public void getMyWishlist(final String uIdlist) {
		 entryPoint.loadingImagePopup.center();
			entryPoint.loadingImagePopup.show();
		 RequestBuilder requestBuilder=wishlistService.getMyWishlist(uIdlist, new AsyncCallback<ArrayList<WishlistItemData>>(){

	    		public void onFailure(Throwable caught){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventGetWishlistFailed(caught);
	    		}
				public void onSuccess(ArrayList<WishlistItemData> result) {
					entryPoint.loadingImagePopup.hide();
					gui.service_eventGetWishlistSuccesfull(result);
					
				}
	    	}//end of inner class
	    	);//end of method call
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	    	}
	       
	    
	    
	    void createWishlistItem(final WishlistItemData item){
	    	 entryPoint.loadingImagePopup.center();
				entryPoint.loadingImagePopup.show();
	    	RequestBuilder requestBuilder=wishlistService.createWishlistItem(item, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventCreateWishlistItemFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventCreateWishlistItemSuccessful();
	    		}
	    	}//end of inner class
	    );//end of method call
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	    }
	    
	    void updateWishlistItem(final WishlistItemData item){
	    	 entryPoint.loadingImagePopup.center();
				entryPoint.loadingImagePopup.show();
	    	RequestBuilder requestBuilder=wishlistService.updateWishlistItem(item, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventUpdateWishlistItemFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventUpdateWishlistItemSuccessful();
	    		}
	    	}//end of inner class
	    	);//end of method call
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	    }


	   void deleteWishlistItem(final WishlistItemData item){
		   entryPoint.loadingImagePopup.center();
			entryPoint.loadingImagePopup.show();
		   RequestBuilder requestBuilder=wishlistService.deleteWishlistItem(item, new AsyncCallback<Void>(){
			   public void onFailure(Throwable caught){
				   entryPoint.loadingImagePopup.hide();
				   gui.service_deleteWishlistItemFailed(caught);
			   }
			   
			   public void onSuccess(Void result){
				   entryPoint.loadingImagePopup.hide();
				   gui.service_deleteWishlistItemSuccessful();
			   }
		   }//end of inner class
		   );//end of method call
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	   }
	   
	  

}
