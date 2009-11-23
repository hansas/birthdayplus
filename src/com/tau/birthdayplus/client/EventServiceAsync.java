package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.domain.Event;

/**
 * The async counterpart of <code>EventService</code>.
 */

public interface EventServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);
	/*
	 * creates new event for the given user
	 */
	void createEvent(String uId, Event event, AsyncCallback<Void> callback);
	/*
	 * update the event
	 * check if the user is the event owner
	 */
	void updateEvent(String uId, Event event, AsyncCallback<Void> callback);
	/*
	 * delete the event 
	 * check if the user is the event owner
	 */
	void deleteEvent(String uId, Event event, AsyncCallback<Void> callback);
	/*
	 * get events by user id
	 */
	void getEvents(String uId, AsyncCallback<ArrayList<Event>> callback);


}
