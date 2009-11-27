package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.EventData;






/**
 * The async counterpart of <code>EventService</code>.
 */

public interface EventServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);
	
	/*
	 * update the event
	 * 
	 */
	void updateEvent(  EventData event, AsyncCallback<Void> callback);
	/*
	 * delete the event 
	 * 
	 */
	void deleteEvent( EventData event, AsyncCallback<Void> callback);
	/*
	 * get events by user id
	 */
	
	void getEvents(ArrayList<String> uIdlist,
			AsyncCallback<ArrayList<EventData>> callback);
	
	void createEvent(EventData event, AsyncCallback<Void> callback);


}
