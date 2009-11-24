package com.tau.birthdayplus.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.EventData;



/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("event")
public interface EventService extends RemoteService{
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello();
	/*
	 * creates new event for the user
	 */
	void createEvent(EventData event);
	/*
	 * update the event
	 * check if the user is the event owner
	 */
	void updateEvent(String uId,EventData event);
	/*
	 * delete the event 
	 * check if the user is the event owner
	 */
	void deleteEvent(String uId,EventData event);
	/*
	 * get events by user id
	 */
	ArrayList<EventData> getEvents(ArrayList<String> uIdlist);

}
