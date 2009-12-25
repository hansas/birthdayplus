package com.tau.birthdayplus.client;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.Services.EventService;
import com.tau.birthdayplus.client.Services.EventServiceAsync;
import com.tau.birthdayplus.client.Services.RequestProxy;


import com.tau.birthdayplus.dto.client.EventData;


public class EventTabDelegate {
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	public EventTabGUI eventGui;
	public Birthdayplus entryPoint;

	public void getEvents(final ArrayList<String> uIdlist) {
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=eventService.getEvents(uIdlist, new AsyncCallback<ArrayList<EventData>>(){
			public void onFailure(Throwable caught){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventGetEventsFailed(caught);
			}
			public void onSuccess(ArrayList<EventData> result){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventGetEventsSuccessful(result);
			}
		}//end of inner class
	);//end of method call
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());
	}
	
	void createEvent(final EventData event){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=eventService.createEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventCreateEventFailed(caught);
			}
		
			public void onSuccess(Void result){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventCreateEventSuccessful();
			}
		}//end of inner class
	);//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());
	}
	
	void updateEvent(final EventData event){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=eventService.updateEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventUpdateEventFailed(caught);
			}
			
			public void onSuccess(Void result){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventUpdateEventSuccessful();
			}
	}//end of inner class
	);//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	
	void deleteEvent(final EventData event){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		RequestBuilder requestBuilder=eventService.deleteEvent(event, new AsyncCallback<Void>(){
			public void onFailure(Throwable caught){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventDeleteEventFailed(caught);
			}
			   
			public void onSuccess(Void result){
				entryPoint.loadingImagePopup.hide();
				eventGui.service_eventDeleteEventSuccessful();
			}
	}//end of inner class
	);//end of method call
		
		RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());

	}
	

}
