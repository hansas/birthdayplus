package com.tau.birthdayplus.client;






import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;




import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.gadgets.client.DynamicHeightFeature;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.NeedsDynamicHeight;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.i18n.client.DateTimeFormat;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;



import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.datepicker.client.DateBox;



import com.tau.birthdayplus.client.widgets.EventTabDelegate;
import com.tau.birthdayplus.client.widgets.EventTabGUI;
import com.tau.birthdayplus.client.widgets.MyWishlistDelegate;
import com.tau.birthdayplus.client.widgets.MyWishlistTabGUI;

import com.tau.birthdayplus.dto.client.GuestData;


@com.google.gwt.gadgets.client.Gadget.ModulePrefs(
		title = "Birthday+", 
		author = "Irena Letuchaya,Eugene Rozenberg,Jenia Zilber,Olga Vingurt", 
		author_email = "yalo_niv@yahoo.com",
		height = 400
)
public class Birthdayplus extends Gadget<UserPreferences> implements OpenSocial  {
	CwConstants constants = GWT.create(CwConstants.class);
	//public DynamicHeightFeature feature;
	
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
	
	private TabPanel tab ;
	public  PopupPanel loadingImagePopup ;

	
	
	
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

/**
 * This is the entry point method.
 */
 protected void init(UserPreferences preferences) {
	 if (tab == null)
		 buildTab();
	 getSocialInfo();
     
 }
	/**
	 * This is the entry point method.
	 */
 private void buildTab(){
	        
		    tab = new TabPanel();
		    tab.addStyleName("tabsPanel");
		    
	//	    tab.setStyleName(constants.cwTabStyle());
		
			tab.getElement().setId("tab");
			tab.setAnimationEnabled(true);
		//	RootPanel.get("tabBarContainer").add(tab);
			
		//	RootPanel.get().add(tab);
			
			
			
			//initiate event tab
		    eventGui = new EventTabGUI(); 
		    eventDelegate = new EventTabDelegate();
		    eventGui.eventService = eventDelegate;
		    eventDelegate.eventGui = eventGui;
		    eventGui.entryPoint=this;
		    eventDelegate.entryPoint=this;
		    eventGui.init();
		    tab.add(eventGui.vPanel, "Events");
		   
			
		    //initiate My wIwishlist tab
		    myWishlistGUI = new MyWishlistTabGUI();
		    myWishlistDelegate = new MyWishlistDelegate();
	        myWishlistGUI.wishlistService = myWishlistDelegate;
	        myWishlistDelegate.gui =myWishlistGUI;
	        myWishlistGUI.entryPoint=this;
	        myWishlistDelegate.entryPoint=this;
	        myWishlistGUI.init();
			tab.add(myWishlistGUI.wishlistVerticalPanel, "Wishlist");
			
			
			//initiate I Buy tab
		    iBuyGUI = new com.tau.birthdayplus.client.widgets.IBuyTabGUI();
		    iBuyDelegate = new com.tau.birthdayplus.client.widgets.IBuyDelegate();
		    iBuyGUI.wishlistService =iBuyDelegate;
		    iBuyDelegate.gui =iBuyGUI;
		    iBuyGUI.entryPoint=this;
		    iBuyDelegate.entryPoint=this;
		    iBuyGUI.init();
	        
			//tab.add(iBuyGUI.wishlistVerticalPanel, "I buy");
		    tab.add(iBuyGUI.iBuyPanel, "I buy");
		    
		   
		    Image loadingImage=new Image( GWT.getModuleBaseURL() + "ajax-loader.gif");
		    loadingImage.setStyleName("loading image");
			
	
			
		    loadingImagePopup = new PopupPanel(false,true);
		    loadingImagePopup.setAnimationEnabled(true);
		    
		    loadingImagePopup.setStyleName(constants.cwLoadingPopupPanelStyle());
		    loadingImagePopup.setWidget(loadingImage);
		    
		 // Remove the loading message
        //    DOM.setInnerHTML(RootPanel.get("Loading-Message").getElement(), "");

		    // Get the Application Container div from the DOM
		 //    RootPanel.get().add(tab);
		    
		    RootPanel.get().add(tab);
		 //   feature.getContentDiv().add(tab);
		    
			
            
		
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
			//		Window.alert("creating new profile" +caught);
					if (caught instanceof UserNotFoundException) {

					
					final DialogBox dateDialogBox = new DialogBox();
		//			dateDialogBox.setStyleName(constants.cwDialogBoxStyle());
					dateDialogBox.setText("Enter your birthday :");
					
					final VerticalPanel vPanel =new VerticalPanel();
		//			vPanel.setStyleName(constants.cwVerticalPanelStyle());
					
					final DateBox dateBox = new DateBox();
		//			dateBox.setStyleName(constants.cwDialogBoxStyle());
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
					dateBox.setFormat(new DateBox.DefaultFormat(fmt));

					final Button okButton = new Button();
		//			okButton.setStyleName(constants.cwButtonStyle());
					okButton.setText("ok");
					
					vPanel.add(dateBox);
					vPanel.add(okButton);
					
					vPanel.setCellHorizontalAlignment(okButton,HasHorizontalAlignment.ALIGN_CENTER);
					
					dateDialogBox.add(vPanel);
					
					
					dateDialogBox.center();
					dateDialogBox.show();
					
					
					okButton.addClickHandler(new ClickHandler(){
				        public void onClick(ClickEvent event) {
				        	dateDialogBox.hide();
					        GuestData user= new GuestData(userId,firstName,lastName,dateBox.getValue());
					        RequestBuilder requestBuilder1=profileService.createProfile(user, new AsyncCallback<Void>(){
			                	public void onFailure(Throwable caught){
			    		    	Window.alert("failed to create profile " +caught);
			    	     	}
			    		
					        	public void onSuccess(Void result) {
					    	//	Window.alert("create profile sucessful");
					    		//listen to the events in the tabs
						    	eventGui.wireEventGUIEvents();
			    		    	myWishlistGUI.wireMyWishlistGUIEvents();
			    	    		iBuyGUI.wireIBuyGUIEvents();
			    		    	wireTabGUIEvents();
			    			
			    	    		tab.selectTab(0);	
					        	}
					        });
					        RequestProxy.makePostRequest(requestBuilder1.getUrl(), requestBuilder1.getRequestData(), requestBuilder1.getCallback());
					
				        }});
				}else{
					//do something
				}
				}

				public void onSuccess(GuestData result) {
		//			Window.alert("get profile sucessful");
				    user = result;
			//	    Window.alert(user.toString());
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
