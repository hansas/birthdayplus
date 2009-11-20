package com.example.friends.client;



import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;





/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@ModulePrefs(title = "Simple", author = "yournamehere", author_email = "yournamehere@gmail.com")

public class Friends extends Gadget<UserPreferences> implements OpenSocial{

	@Override
	protected void init(UserPreferences preferences) {
		getName();
	  //  Window.alert("Hello World!");
		
		
	}
	
	private  native void getName()/*-{
		  var req = $wnd.opensocial.newDataRequest();
		  req.add(req.newFetchPersonRequest('VIEWER'), 'viewer'); 
		  req.send(this.@com.example.friends.client.Friends::onLoadFriends(Lcom/google/gwt/core/client/JavaScriptObject;));
		
	}-*/;
	
	private  native void onLoadFriends(JavaScriptObject data)/*-{
	
		if(!data.hadError()){
        	var viewer = data.get('viewer').getData();
	        $wnd.alert(viewer.getDisplayName());
		}else{
			$wnd.alert("noop");
		}
	
	
	}-*/;

	public void initializeFeature(OpenSocialFeature feature) {
		// TODO Auto-generated method stub
		
	}
}
