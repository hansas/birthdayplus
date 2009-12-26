package com.tau.birthdayplus.server;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.tau.birthdayplus.client.Services.EventService;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
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

	public void createEvent(EventData eventD) throws UserNotFoundException {
		EventManagement.createEvent(eventD);
	}
	/*
	 * delete the event 
	 * Client- give only to owner to delete event
	 */
	 public void deleteEvent(EventData eventD) throws UserException {
		EventManagement.deleteEvent(eventD);
		
	}
	/*
	 * get events by user id
	 * Server - events should be sorted by date
	 */
	public ArrayList<EventData> getEvents(ArrayList<String> UserIdList) {
		if(UserIdList==null)
			throw new RuntimeException("empty key list");
		return EventManagement.getEvents(UserIdList);
	}

	/*
	 * update the event
	 * Client - give only to owner to update event
	 */
	public void updateEvent(EventData eventD) {
		EventManagement.updateEvent(eventD);
		
	}
	
	protected String readContent(HttpServletRequest request)
	throws ServletException, IOException {
		
return RPCServletUtils.readContentAsUtf8(request, false);
}

	

}
