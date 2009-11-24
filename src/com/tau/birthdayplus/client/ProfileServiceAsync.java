package com.tau.birthdayplus.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.Guest;
/**
 * The async counterpart of <code>ProfileService</code>.
 */
public interface ProfileServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);

	void createProfile(Guest guset, AsyncCallback<Void> callback);

	void updateProfile(Guest profile, AsyncCallback<Void> callback);

	void getProfile(AsyncCallback<Guest> callback);

}
