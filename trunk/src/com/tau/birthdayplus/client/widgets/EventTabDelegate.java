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
	
	public EventTabGUI eventGui;
	public WishListFriendsGUI wishlistFriendGUI;
	public void getEvents(final ArrayList<String> uIdlist) {
		eventService.getEvents(uIdlist, new AsyncCallback<ArrayList<EventData>>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventGetEventsFailed(caught);
			}
			public void onSuccess(ArrayList<EventData> result){
				eventGui.service_eventGetEventsSuccessful(result);
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
				eventGui.service_eventCreateEventSuccessful();
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
				eventGui.service_eventUpdateEventSuccessful();
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
				eventGui.service_eventDeleteEventSuccessful();
			}
	}//end of inner class
	);//end of method call
	}
	

}
