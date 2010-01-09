package com.tau.birthdayplus.server;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.client.Services.ProfileService;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
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

//	public void createProfile(Guest guestData) {
		
	//	UserManagement.createProfile(guestData);
		
	//}

	public GuestData getProfile(String userId) throws UserNotFoundException {
		return UserManagement.loadGuestDataCached(userId);
	}

	public void updateProfile(GuestData profile) throws UserNotFoundException, UserException {
		UserManagement.updateProfile(profile);
	}
	
	
	
	protected String readContent(HttpServletRequest request)
	throws ServletException, IOException {
		String content = RPCServletUtils.readContentAsUtf8(request, false);
		content = URLDecoder.decode(content, "UTF-8");
		return content;
}


}
