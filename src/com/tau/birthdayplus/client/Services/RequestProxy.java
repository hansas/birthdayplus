package com.tau.birthdayplus.client.Services;

import com.google.gwt.http.client.RequestCallback;

public class RequestProxy {
	public RequestProxy (){
		
	}
	public static native void makePostRequest(String url, String postdata, RequestCallback callback) /*-{
	    var params = {};
		params[$wnd.gadgets.io.RequestParameters.METHOD] = $wnd.gadgets.io.MethodType.POST;
		params[$wnd.gadgets.io.RequestParameters.POST_DATA]= postdata;
		params[$wnd.gadgets.io.RequestParameters.HEADERS] = { 'Content-Type' : 'text/x-gwt-rpc; charset=UTF-8'};
		
	//	$wnd.gadgets.io.makeRequest(url, response, params); 
        makeCachedRequest(url, response, params, 0);
    //gadgets.io.RequestParameters.REFRESH_INTERVAL = number of seconds to cache    
        
	function response(obj) {
			@com.tau.birthdayplus.client.Services.RequestProxy::onSuccessInternal(Lcom/tau/birthdayplus/client/Services/GadgetResponse;Lcom/google/gwt/http/client/RequestCallback;)(obj, callback);
	};
	
	function makeCachedRequest(url, callback, params, refreshInterval) {
       var ts = new Date().getTime();
       var sep = "?";
       if (refreshInterval && refreshInterval > 0) {
           ts = Math.floor(ts / (refreshInterval * 1000));
        }
       if (url.indexOf("?") > -1) {
           sep = "&";
        }
       url = [ url, sep, "nocache=", ts ].join("");
       $wnd.gadgets.io.makeRequest(url, callback, params);
}
}-*/;

static void onSuccessInternal(final GadgetResponse response, RequestCallback callback) {
	try {
		callback.onResponseReceived(null, new FakeResponse(response));
	} catch (Exception e) {
		callback.onError(null, e);
	}		
}

}
