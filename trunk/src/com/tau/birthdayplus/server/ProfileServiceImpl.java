package com.tau.birthdayplus.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.GreetingService;
import com.tau.birthdayplus.client.ProfileService;
import com.tau.birthdayplus.logic.UserManagement;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")

public class ProfileServiceImpl extends RemoteServiceServlet implements
ProfileService {

	public void printHello() {
		UserManagement.loadMyGuest();
		System.out.println("Hello from ProfileService");
		
	}

}
