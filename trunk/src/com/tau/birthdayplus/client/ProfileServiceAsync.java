package com.tau.birthdayplus.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * The async counterpart of <code>ProfileService</code>.
 */
public interface ProfileServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);

}
