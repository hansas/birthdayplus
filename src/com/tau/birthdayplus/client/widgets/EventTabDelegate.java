package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.EventService;
import com.tau.birthdayplus.client.EventServiceAsync;
import com.tau.birthdayplus.client.WishlistService;
import com.tau.birthdayplus.client.WishlistServiceAsync;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class EventTabDelegate {
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class);
	public EventTabGUI eventGui;
	public WishListFriendsGUI wishlistFriendGUI;
	void getEvents(final ArrayList<String> uIdlist) {
		eventService.getEvents(uIdlist, new AsyncCallback<ArrayList<EventData>>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventGetEventsFailed(caught);
			}
			public void onSuccess(ArrayList<EventData> result){
				eventGui.service_eventGetEventsSuccess(result);
			}
		}//end of inner class
	);//end of method call
	}
	
	void createEvent(final EventData event){
		eventService.createEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventCreateEventFailed(caught);
			}
		
			public void onSuccess(Void result){
				eventGui.service_eventCreateEventSuccess();
			}
		}//end of inner class
	);//end of method call
	}
	
	void updateEvent(final EventData event){
		eventService.updateEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventUpdateEventFailed(caught);
			}
			
			public void onSuccess(Void result){
				eventGui.service_eventUpdateEventSuccess();
			}
	}//end of inner class
	);//end of method call
	}
	
	void deleteEvent(final EventData event){
		eventService.deleteEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventDeleteEventFailed(caught);
			}
			   
			public void onSuccess(Void result){
				eventGui.service_eventDeleteEventSuccess();
			}
	}//end of inner class
	);//end of method call
	}
	
	void getWishlist(String uId){
		wishlistService.getWishlist(uId, new AsyncCallback<ArrayList<WishlistItemData>>(){
			public void onFailure(Throwable caught) {
				eventGui.service_eventGetWishlistFailed(caught);
				
			}

			public void onSuccess(ArrayList<WishlistItemData> result) {
				eventGui.service_eventGetWishlistSuccess();	
			}
			
		}//end of inner class
	);//end of method call
	}
}
