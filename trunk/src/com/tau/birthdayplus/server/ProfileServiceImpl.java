package com.tau.birthdayplus.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RPCServletUtils;
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

	public void createProfile(GuestData guestData) {
		
		UserManagement.createProfile(guestData);
		
	}

	public GuestData getProfile(String userId) {
		return UserManagement.loadGuestData(userId);
	}

	public void updateProfile(GuestData profile) {
		// TODO Auto-generated method stub
		
	}
	
	protected String readContent(HttpServletRequest request)
	throws ServletException, IOException {
return RPCServletUtils.readContentAsUtf8(request, false);
}


}
