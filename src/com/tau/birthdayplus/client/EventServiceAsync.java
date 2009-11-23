package com.tau.birthdayplus.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>EventService</code>.
 */

public interface EventServiceAsync {
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello(AsyncCallback<Void> callback);

}
