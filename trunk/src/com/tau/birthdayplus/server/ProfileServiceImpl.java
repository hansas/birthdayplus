package com.tau.birthdayplus.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.tau.birthdayplus.client.ProfileService;
import com.tau.birthdayplus.dto.client.GuestData;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")

public class ProfileServiceImpl extends RemoteServiceServlet implements
ProfileService {

	public void printHello() {
		System.out.println("Hello from ProfileService");
		
	}

	/*
	 * create new profile
	 */
	public void createProfile(GuestData guset) {
		// TODO Auto-generated method stub
		
	}
	/*
     * get user's profile
     */
	public GuestData getProfile(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * update profile
	 */
	public void updateProfile(GuestData profile) {
		// TODO Auto-generated method stub
		
	}

}
