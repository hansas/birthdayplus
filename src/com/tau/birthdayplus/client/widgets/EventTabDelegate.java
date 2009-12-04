package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.EventService;
import com.tau.birthdayplus.client.EventServiceAsync;
import com.tau.birthdayplus.client.RequestProxy;

import com.tau.birthdayplus.dto.client.EventData;


public class EventTabDelegate {
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	public EventTabGUI eventGui;

	public void getEvents(final ArrayList<String> uIdlist) {
		RequestBuilder requestBuilder=eventService.getEvents(uIdlist, new AsyncCallback<ArrayList<EventData>>(){
			public void onFailure(Throwable caught){
				Window.alert("get events failded "+caught);
				eventGui.service_eventGetEventsFailed(caught);
			}
			public void onSuccess(ArrayList<EventData> result){
				eventGui.service_eventGetEventsSuccessful(result);
			}
		}//end of inner class
	);//end of method call
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());
	}
	
	void createEvent(final EventData event){
		RequestBuilder requestBuilder=eventService.createEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				Window.alert("create event failded "+caught);
				eventGui.service_eventCreateEventFailed(caught);
			}
		
			public void onSuccess(Void result){
				eventGui.service_eventCreateEventSuccessful();
			}
		}//end of inner class
	);//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());
	}
	
	void updateEvent(final EventData event){
		RequestBuilder requestBuilder=eventService.updateEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventUpdateEventFailed(caught);
			}
			
			public void onSuccess(Void result){
				eventGui.service_eventUpdateEventSuccessful();
			}
	}//end of inner class
	);//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	
	void deleteEvent(final EventData event){
		RequestBuilder requestBuilder=eventService.deleteEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				eventGui.service_eventDeleteEventFailed(caught);
			}
			   
			public void onSuccess(Void result){
				eventGui.service_eventDeleteEventSuccessful();
			}
	}//end of inner class
	);//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	

}
