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

import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.ParticipatorData;

import com.tau.birthdayplus.dto.client.WishlistItemNewData;

public class IBuyDelegate {
	 // Create a remote service proxy to talk to the server-side Profile service.
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class);
	public IBuyTabGUI gui;
	public Birthdayplus entryPoint;
	
	
	void cancelBookItemForUser(String wishlistItemId, String userId){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=wishlistService.cancelBookItemForUser(wishlistItemId, userId,new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.sevice_eventCancelBookItemForUserFailed(caught);				
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventCancelBookItemForUserSuccesfull();	
			}
		});		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	
	void deleteParticipator(String wishlistItemId, String userId){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=wishlistService.deleteParticipator(wishlistItemId, userId, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventDeleteParticipatorFailed(caught);
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventDeleteParticipatorSuccesfull();
			}
			
		});
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	
	void updateParticipator(String wishlistItemId,ParticipatorData participator){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=wishlistService.updateParticipator(wishlistItemId, participator, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventUpdateParticipatorFailed(caught);
				
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_eventUpdateParticipatorSuccesfull();
				
			}	
		});
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	 
	 
	public void getBookedWishlist(final String userId) {
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		
		RequestBuilder requestBuilder=wishlistService.getBookedWishlistItems(userId, new AsyncCallback<ArrayList<WishlistItemNewData>>(){

	    		public void onFailure(Throwable caught){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_getBookedWishlistFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<WishlistItemNewData> result){
	    			entryPoint.loadingImagePopup.hide();
	    			gui.service_getBookedWishlistSuccesfull(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	    	}
	
	public void addChatMessage(String itemId,ChatMessageData message){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder = wishlistService.addChatMessageData(itemId, message, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_addChatMessageFailed(caught);
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_addChatMessageSuccesfull();
				
			}
			
		});
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

		
	}
	
	public void getWishlistItem(String itemId){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder = wishlistService.getWishlistItem(itemId, new AsyncCallback<WishlistItemNewData>(){

			public void onFailure(Throwable caught) {
				
				entryPoint.loadingImagePopup.hide();
				gui.service_getWishlistItemFailed(caught);
				
			}

			public void onSuccess(WishlistItemNewData result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_getWishlistItemSuccesfull(result);
				
			}
			
		});
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());
		
	}
	
	public void cancelBookItemForGroup(String itemId,String userId,String message){
		if(entryPoint.loadingImagePopup.isShowing())
			entryPoint.loadingImagePopup.hide();
		
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder = wishlistService.cancelBookItemForGroup(itemId, userId,message, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_cancelBookItemForGroupFailed(caught);
				
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_cancelBookItemForGroupSuccesfull();
				
			}
		
		});
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

		
	}
	
	public void bookItemForGroup(String itemId,String userId,String message,Double actualPrice){
		if(entryPoint.loadingImagePopup.isShowing())
			entryPoint.loadingImagePopup.hide();
		
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder = wishlistService.bookItemForGroup(itemId, userId,message,actualPrice, new AsyncCallback<Void>(){

			public void onFailure(Throwable caught) {
				entryPoint.loadingImagePopup.hide();
				gui.service_bookItemForGroupFailed(caught);
				
			}

			public void onSuccess(Void result) {
				entryPoint.loadingImagePopup.hide();
				gui.service_bookItemForGroupSuccesfull();
			}
		});
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

		
	}
	

	
	


}
