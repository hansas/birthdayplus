package com.tau.birthdayplus.server;


import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.EventService;
import com.tau.birthdayplus.dto.client.EventData;


import com.tau.birthdayplus.logic.EventManagement;



/**
* The server side implementation of the RPC service.
*/
@SuppressWarnings("serial")
public class EventServiceImpl extends RemoteServiceServlet implements
EventService {
	/*
	 * creates new event for the user
	 */
	public void createEvent(EventData event) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * delete the event 
	 * Client- give only to owner to delete event
	 */
	public void deleteEvent(EventData event) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * get events by user id
	 * Server - events should be sorted by date
	 */
	public ArrayList<EventData> getEvents(ArrayList<String> idlist) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * function that checks that client can call to the remote service
	 */
	public void printHello() {
		// TODO Auto-generated method stub
		
	}
	/*
	 * update the event
	 * Client - give only to owner to update event
	 */
	public void updateEvent(EventData event) {
		// TODO Auto-generated method stub
		
	}

	

}
