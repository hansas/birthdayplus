package com.tau.birthdayplus.client;

import com.google.gwt.http.client.RequestCallback;

public class RequestProxy {
	public RequestProxy (){
		
	}
	public static native void makePostRequest(String url, String postdata, RequestCallback callback) /*-{
	var params = {};
		params[$wnd.gadgets.io.RequestParameters.METHOD] = $wnd.gadgets.io.MethodType.POST;
		params[$wnd.gadgets.io.RequestParameters.POST_DATA]= postdata;
		$wnd.gadgets.io.makeRequest(url, response, params); 

	function response(obj) {
			@com.tau.birthdayplus.client.RequestProxy::onSuccessInternal(Lcom/tau/birthdayplus/client/GadgetResponse;Lcom/google/gwt/http/client/RequestCallback;)(obj, callback);
	};
}-*/;

static void onSuccessInternal(final GadgetResponse response, RequestCallback callback) {
	try {
		callback.onResponseReceived(null, new FakeResponse(response));
	} catch (Exception e) {
		callback.onError(null, e);
	}		
}

}
