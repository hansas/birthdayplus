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


	/*
	
	public Guest loadGuest(String guestId) {
		return UserManagement.loadGuest(guestId);
	}
    */
	
}
