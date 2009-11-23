package com.tau.birthdayplus.server;

import java.util.ArrayList;

import com.tau.birthdayplus.client.GreetingService;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.logic.UserManagement;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
/*
	@Override
	public Guest createGuest(Guest guest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest updateGuest(Guest guest) {
		// TODO Auto-generated method stub
		return null;
	}


    */
	
	public Event createEvent(Event event, String uid) {
		// TODO Auto-generated method stub
		return null;
	}

 
	public WishlistItem createWishlistItem(WishlistItem wItem, String uid) {
		// TODO Auto-generated method stub
		return null;
	}
    /*
	@Override
	public Event deleteEvent(Event event, String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WishlistItem deleteWishlistItem(WishlistItem wItem, String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Event> getEventList(String uid) {
		// TODO Auto-generated method stub
		return null;
	}
    */
	public Guest loadGuest(String guestId) {
		return UserManagement.loadGuest(guestId);
	}
    /*
	@Override
	public Event updateEvent(Event event, String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WishlistItem updateWishlistItem(WishlistItem wItem, String uid) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
