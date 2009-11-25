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

	public void printHello() {
	   System.out.println("hello from Event Service");
		
	}

	public void createEvent(EventData eventD) {
		EventManagement.createEvent(eventD);
	}
	/*
	 * delete the event 
	 * Client- give only to owner to delete event
	 */
	 public void deleteEvent(String uId, EventData eventD) {
		EventManagement.deleteEvent(uId,eventD);
		
	}
	/*
	 * get events by user id
	 * Server - events should be sorted by date
	 */
	public ArrayList<EventData> getEvents(ArrayList<String> UserIdList) {
		return EventManagement.getEvents(UserIdList);
	}

	/*
	 * update the event
	 * Client - give only to owner to update event
	 */
	public void updateEvent(String uId, EventData eventD) {
		EventManagement.updatEvent(uId,eventD);
		
	}

	

}
