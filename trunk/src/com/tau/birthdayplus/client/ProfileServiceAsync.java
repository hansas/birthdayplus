package com.tau.birthdayplus.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.GuestData;


/**
 * The async counterpart of <code>ProfileService</code>.
 */
public interface ProfileServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);

	void createProfile(GuestData guest, AsyncCallback<Void> callback);

	void updateProfile(GuestData profile, AsyncCallback<Void> callback);

	void getProfile(String userId,AsyncCallback<GuestData> callback);

}
