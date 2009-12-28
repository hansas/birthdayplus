package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.Services.RequestProxy;
import com.tau.birthdayplus.client.Services.WishlistService;
import com.tau.birthdayplus.client.Services.WishlistServiceAsync;

import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;

public class WishListFriendsDelegate {
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	public WishListFriendsGUI gui;
	public Birthdayplus entryPoint;
	
	void bookItemForUser(String wishlistItemId,String eventId, String userId){
		if(entryPoint.loadingImagePopup.isShowing())
			entryPoint.loadingImagePopup.hide();
		
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
		 if(entryPoint.loadingImagePopup.isShowing())
				entryPoint.loadingImagePopup.hide();
		 
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
		 if(entryPoint.loadingImagePopup.isShowing())
				entryPoint.loadingImagePopup.hide();
		 
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
	 
	 void getPolaniItems(String myUserId,String anotherUserId){
		 if(entryPoint.loadingImagePopup.isShowing())
				entryPoint.loadingImagePopup.hide();
		 
		 entryPoint.loadingImagePopup.center();
		 entryPoint.loadingImagePopup.show();
		 RequestBuilder requestBuilder = wishlistService.getLastItemsForUser(myUserId, anotherUserId, new AsyncCallback<ArrayList<WishlistItemPolaniData>>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventGetPolaniItemsFailed(caught);
				
			}

			public void onSuccess(ArrayList<WishlistItemPolaniData> result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventGetPolaniItemsSuccesfull(result);
				
			}
			 
		 });
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

		 
		 
	 }
	       

}
