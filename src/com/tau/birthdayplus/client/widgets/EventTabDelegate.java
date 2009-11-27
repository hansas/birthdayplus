package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.EventService;
import com.tau.birthdayplus.client.EventServiceAsync;
import com.tau.birthdayplus.dto.client.EventData;

public class EventTabDelegate {
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	public EventTabGUI eventGui;
	
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
}
