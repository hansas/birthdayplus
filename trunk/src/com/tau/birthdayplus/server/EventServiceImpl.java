package com.tau.birthdayplus.server;


import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.EventService;
import com.tau.birthdayplus.dto.EventData;

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

	public void createEvent( EventData event) {
		// TODO Auto-generated method stub
		
	}

	public void deleteEvent(String id, EventData event) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<EventData> getEvents(ArrayList<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateEvent(String id, EventData event) {
		EventManagement.updatEvent(id, event);
		
	}

}
