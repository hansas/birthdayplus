package com.tau.birthdayplus.client.Services;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.client.EventData;

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
	void createEvent(EventData event) throws UserNotFoundException, UserException;
	/*
	 * update the event
	 * Client - give only to owner to update event
	 */
	void updateEvent( EventData event) throws UserException;
	/*
	 * delete the event 
	 * Client- give only to owner to delete event
	 */
	void deleteEvent( EventData event) throws UserException, Exception;
	/*
	 * get events by user id
	 * Server - events should be sorted by date
	 */
	ArrayList<EventData> getEvents(ArrayList<String> uIdlist) throws UserException;

}
