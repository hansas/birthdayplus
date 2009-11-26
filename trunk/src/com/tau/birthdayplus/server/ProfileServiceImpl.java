package com.tau.birthdayplus.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.tau.birthdayplus.client.ProfileService;
import com.tau.birthdayplus.dto.client.GuestData;
import com.tau.birthdayplus.logic.UserManagement;



/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")

public class ProfileServiceImpl extends RemoteServiceServlet implements
ProfileService {

	public void printHello() {
		System.out.println("Hello from ProfileService");
		
	}

	@Override
	public void createProfile(GuestData guestData) {
		UserManagement.createProfile(guestData);
		
	}

	@Override
	public GuestData getProfile(String userId) {
		return UserManagement.loadGuestData(userId);
	}

	@Override
	public void updateProfile(GuestData profile) {
		// TODO Auto-generated method stub
		
	}


}
