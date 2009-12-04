package com.tau.birthdayplus.client;






import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;




import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.http.client.RequestBuilder;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;



import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.TabPanel;



import com.tau.birthdayplus.client.widgets.EventTabDelegate;
import com.tau.birthdayplus.client.widgets.EventTabGUI;
import com.tau.birthdayplus.client.widgets.MyWishlistDelegate;
import com.tau.birthdayplus.client.widgets.MyWishlistTabGUI;

import com.tau.birthdayplus.dto.client.GuestData;


@com.google.gwt.gadgets.client.Gadget.ModulePrefs(
		title = "Birthdayplus", 
		author = "", 
		author_email = "yalo_niv@yahoo.com"
)
public class Birthdayplus extends Gadget<UserPreferences> implements OpenSocial {
	CwConstants constants = GWT.create(CwConstants.class);
	
	// Create a remote service proxy to talk to the server-side Profile service.
	private final ProfileServiceAsync profileService = GWT.create(ProfileService.class); 
	
	/*
	 * user id and HashMap of <friend id, friend name> from open social
	 */
	public String userId;
	public String firstName;
	public String lastName;
	public HashMap<String,String> userFriends= new HashMap<String,String>();
	public GuestData user;
	
	private TabPanel tab;
	
	/*
	 *  Event tab
	 */
	private EventTabGUI eventGui;
	private EventTabDelegate eventDelegate;
	
	/*
	 * MyWishlistTab tab
	 */
	private MyWishlistTabGUI myWishlistGUI;
	private MyWishlistDelegate myWishlistDelegate;
	
	/*
	 * IBuy Tab
	 */
	private com.tau.birthdayplus.client.widgets.IBuyTabGUI iBuyGUI;
	private com.tau.birthdayplus.client.widgets.IBuyDelegate iBuyDelegate;
	
	static {
		disableStats();
	}

	private static native void disableStats() /*-{
		$wnd.$stats = null;
	}-*/;
	/*
	 * list of user and his friends id's
	 */
	public ArrayList<String> getUserAndFriendsIds(){
		Set<String> set = userFriends.keySet();
		ArrayList<String> temp=new ArrayList<String>();
		temp.add(userId);
		for(String key : set){
			temp.add(key);
		}
		return temp;
	}
	/*
	private native void makePostRequest(String url, String postdata, RequestCallback callback) /*-{
	var params = {};
		params[$wnd.gadgets.io.RequestParameters.METHOD] = $wnd.gadgets.io.MethodType.POST;
		params[$wnd.gadgets.io.RequestParameters.POST_DATA]= postdata;
		$wnd.gadgets.io.makeRequest(url, response, params); 

	function response(obj) {
			@com.tau.birthdayplus.client.Birthdayplus::onSuccessInternal(Lcom/tau/birthdayplus/client/GadgetResponse;Lcom/google/gwt/http/client/RequestCallback;)(obj, callback);
	};
}-*/;
/*
static void onSuccessInternal(final GadgetResponse response, RequestCallback callback) {
	try {
		callback.onResponseReceived(null, new FakeResponse(response));
	} catch (Exception e) {
		callback.onError(null, e);
	}		
}
	*/
/**
 * This is the entry point method.
 */
 protected void init(UserPreferences preferences) {
	 buildTab();
	 getSocialInfo();
     
 }
	/**
	 * This is the entry point method.
	 */
 private void buildTab(){
		    tab = new TabPanel();
		
			tab.getElement().setId("tab");
			tab.setAnimationEnabled(true);
		//	RootPanel.get("tabBarContainer").add(tab);
			RootPanel.get().add(tab);
			
			//initiate event tab
		    eventGui = new EventTabGUI(); 
		    eventDelegate = new EventTabDelegate();
		    eventGui.eventService = eventDelegate;
		    eventDelegate.eventGui = eventGui;
		    eventGui.entryPoint=this;
		    eventGui.init();
		    tab.add(eventGui.vPanel, "Events");
		   
			
		    //initiate My wIwishlist tab
		    myWishlistGUI = new MyWishlistTabGUI();
		    myWishlistDelegate = new MyWishlistDelegate();
	        myWishlistGUI.wishlistService = myWishlistDelegate;
	        myWishlistDelegate.gui =myWishlistGUI;
	        myWishlistGUI.entryPoint=this;
	        myWishlistGUI.init();
			tab.add(myWishlistGUI.wishlistVerticalPanel, "Wishlist");
			
			
			//initiate I Buy tab
		    iBuyGUI = new com.tau.birthdayplus.client.widgets.IBuyTabGUI();
		    iBuyDelegate = new com.tau.birthdayplus.client.widgets.IBuyDelegate();
		    iBuyGUI.wishlistService =iBuyDelegate;
		    iBuyDelegate.gui =iBuyGUI;
		    iBuyGUI.entryPoint=this;
		    iBuyGUI.init();
	        
			//tab.add(iBuyGUI.wishlistVerticalPanel, "I buy");
		    tab.add(iBuyGUI.iBuyPanel, "I buy");
			

		
	}
	
	
	  
	  private void wireTabGUIEvents(){
		  tab.addSelectionHandler(new SelectionHandler<Integer>(){

			public void onSelection(SelectionEvent<Integer> event) {
				if(event.getSelectedItem()== 0){
					ArrayList<String> temp=new ArrayList<String>();
					temp = getUserAndFriendsIds();
					eventDelegate.getEvents(temp);
				}else{
				     if(event.getSelectedItem()== 1)
				    	myWishlistDelegate.getMyWishlist(userId);
				     else
				    	 iBuyDelegate.getBookedWishlist(userId);
				
				}
					
					
				
			}});
		  
	  
	  }
	  
	  
	  /*
		 * return social info about the user and his friends
		 */
		private  native void getSocialInfo()/*-{
		//	  $wnd.alert("i'm getting social info");
			  //global variable that points to this,important in javascript
			  that=this;
			  var req = $wnd.opensocial.newDataRequest();
			  req.add(req.newFetchPersonRequest('VIEWER'), 'viewer'); 
			  var viewerFriends = $wnd.opensocial.newIdSpec({"userId" : "VIEWER", "groupId" : "FRIENDS"});
			  var opt_params = {};
			  opt_params[$wnd.opensocial.DataRequest.PeopleRequestFields.MAX]=100;
			  req.add(req.newFetchPeopleRequest(viewerFriends, opt_params), 'viewerFriends');
			
			  req.send(this.@com.tau.birthdayplus.client.Birthdayplus::onLoadInfo(Lcom/google/gwt/core/client/JavaScriptObject;));
			
		}-*/;
		
		


		/*
		 * when the open social information is ready call this function
		 */
		private  native void onLoadInfo(JavaScriptObject data)/*-{
		//	$wnd.alert("i'm loading info");
			var user = data.get('viewer');
		
			//check permissions
			if(!user.hadError()){
				var viewer = data.get('viewer').getData();
				var viewerFriends = data.get('viewerFriends').getData();
				//get user id
				that.@com.tau.birthdayplus.client.Birthdayplus::userId=viewer.getId();
				var nameArray = viewer.getDisplayName().split(" ");
				that.@com.tau.birthdayplus.client.Birthdayplus::firstName=nameArray[0];
			//	$wnd.alert(nameArray[0]);
				that.@com.tau.birthdayplus.client.Birthdayplus::lastName=nameArray[1];
			//	$wnd.alert(nameArray[1]);
				
	            //get user's friends
				viewerFriends.each(function(person){
		        	if(person.getId()){
		        		that.@com.tau.birthdayplus.client.Birthdayplus::userFriends.@java.util.HashMap::put(Ljava/lang/Object;Ljava/lang/Object;)(person.getId(),person.getDisplayName());
		        	}
		        });
		        that.@com.tau.birthdayplus.client.Birthdayplus::print()();
			}else{
				if (user.getErrorCode() == 'forbidden' ||user.getErrorCode() == '403') {
	                  // user needs to approve social data access
	                  $wnd.document.write("Please adjust the gadgets settings to use social features");
	            }else{
	                  $wnd.document.write("An unexpected error occurred.");
				}			
			}
			
		}-*/;
		
   /*
    * for open social
    */
		public void initializeFeature(OpenSocialFeature feature) {
			
			
		}
		
		
		/*
		 * function that is called after retrieving social information about the user
		 */
		public void print(){
		//	Window.alert(firstName);
		//	Window.alert(lastName);
			RequestBuilder requestBuilder=profileService.getProfile(userId, new AsyncCallback<GuestData>(){

				public void onFailure(Throwable caught) {
					Window.alert("creating new profile" +caught);
					GuestData user= new GuestData(userId,firstName,lastName,new Date());
					RequestBuilder requestBuilder1=profileService.createProfile(user, new AsyncCallback<Void>(){
			    	public void onFailure(Throwable caught){
			    			Window.alert("failed to create profile " +caught);
			    		}
			    		
						public void onSuccess(Void result) {
							Window.alert("create profile sucessful");
							//listen to the events in the tabs
							eventGui.wireEventGUIEvents();
			    			myWishlistGUI.wireMyWishlistGUIEvents();
			    			iBuyGUI.wireIBuyGUIEvents();
			    			wireTabGUIEvents();
			    			
			    			tab.selectTab(0);
							
							
						}
						
			    	}//end of inner class
			    	);//end of method call)
					
					RequestProxy.makePostRequest(requestBuilder1.getUrl(), requestBuilder1.getRequestData(), requestBuilder1.getCallback());
					
					
				}

				public void onSuccess(GuestData result) {
					Window.alert("get profile sucessful");
				    user = result;
				    Window.alert(user.toString());
			    	eventGui.wireEventGUIEvents();
			    	
	    			myWishlistGUI.wireMyWishlistGUIEvents();
	    			iBuyGUI.wireIBuyGUIEvents();
	    			wireTabGUIEvents();
	    			
	    			tab.selectTab(0);
					
				}
				
			});
			
			
			RequestProxy.makePostRequest(requestBuilder.getUrl(), requestBuilder.getRequestData(), requestBuilder.getCallback());
			
			
			
		//	wireTabGUIEvents();
			
		}
	  

		

		
		
	  
}
