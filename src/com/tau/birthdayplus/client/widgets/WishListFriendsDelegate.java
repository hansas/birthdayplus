package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.RequestProxy;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.client.WishlistServiceAsync;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;

public class WishListFriendsDelegate {
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	public WishListFriendsGUI gui;
	public Birthdayplus entryPoint;
	
	void bookItemForUser(String wishlistItemId,String eventId, String userId){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder = wishlistService.bookItemForUser(wishlistItemId, eventId, userId, new AsyncCallback<Void>(){
			   public void onFailure(Throwable caught){
				   entryPoint.loadingImagePopup.hide();
				   gui.service_eventBookItemForUserFailed(caught);
			   }
			   
			   public void onSuccess(Void result){
				   entryPoint.loadingImagePopup.hide();
				   gui.service_eventBookItemForUserSuccesfull();
			   }
		   }//end of inner class
		   );//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	   }   
	
	
	
	 void getWishlist(final String userId, final String eventId) {
		 entryPoint.loadingImagePopup.center();
			entryPoint.loadingImagePopup.show();
		
		 RequestBuilder requestBuilder = wishlistService.getWishlistForEvent(userId, eventId,  new AsyncCallback<ArrayList<WishlistItemNewData>>(){

	    		public void onFailure(Throwable caught){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventGetWishlistFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<WishlistItemNewData> result){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_eventGetWishlistSuccesfull(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
		 
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	    	}
	 
	 void addParticipator(String wishlistItemId , String eventId, ParticipatorData participator){
		 entryPoint.loadingImagePopup.center();
			entryPoint.loadingImagePopup.show();
		 RequestBuilder requestBuilder = wishlistService.addParticipator(wishlistItemId, eventId, participator, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventAddParticipatorFailed(caught);
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventAddParticipatorSuccesfull();
				
			} 
		 }
		 );
		 
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	 }
	       

}
