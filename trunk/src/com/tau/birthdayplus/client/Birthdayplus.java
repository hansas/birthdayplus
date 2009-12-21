package com.tau.birthdayplus.client;






import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;




import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.datepicker.client.DateBox;



import com.tau.birthdayplus.client.widgets.EventTabDelegate;
import com.tau.birthdayplus.client.widgets.EventTabGUI;
import com.tau.birthdayplus.client.widgets.IBuyDelegate;
import com.tau.birthdayplus.client.widgets.IBuyTabGUI;
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
	
	public TabPanel tab ;
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
	public IBuyTabGUI iBuyGUI;
	private IBuyDelegate iBuyDelegate;
	
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
//	 if (tab == null)
	//	 buildTab();
	    Image loadingImage=new Image( GWT.getModuleBaseURL() + "ajax-loader.gif");
	    loadingImage.setStyleName("loading image");
		

		
	    loadingImagePopup = new PopupPanel(false,true);
	   
	    loadingImagePopup.setAnimationEnabled(true);
	    
	    loadingImagePopup.setStyleName(constants.cwLoadingPopupPanelStyle());
	    loadingImagePopup.setWidget(loadingImage);
	    loadingImagePopup.center();
	    loadingImagePopup.show();
	    getSocialInfo();
     
 }
	/**
	 * This is the entry point method.
	 */
 private void buildTab(){
	        Window.enableScrolling(false);
	        Window.setMargin("0px");
	        
		    tab = new TabPanel();
		    RootPanel.get().add(tab);
		    
		  //  tab.setSize("100%", "400px");
		    tab.addStyleName("tabsPanel");
		//	tab.setAnimationEnabled(true);
			
			
			//initiate event tab
		    eventGui = new EventTabGUI(); 
		    eventDelegate = new EventTabDelegate();
		    eventGui.eventService = eventDelegate;
		    eventDelegate.eventGui = eventGui;
		    eventGui.entryPoint=this;
		    eventDelegate.entryPoint=this;
		    eventGui.init();
		   
		   
			
		    //initiate My wIwishlist tab
		    myWishlistGUI = new MyWishlistTabGUI();
		    myWishlistDelegate = new MyWishlistDelegate();
	        myWishlistGUI.wishlistService = myWishlistDelegate;
	        myWishlistDelegate.gui =myWishlistGUI;
	        myWishlistGUI.entryPoint=this;
	        myWishlistDelegate.entryPoint=this;
	        myWishlistGUI.init();
			
			
			
			//initiate I Buy tab
		    iBuyGUI = new com.tau.birthdayplus.client.widgets.IBuyTabGUI();
		    iBuyDelegate = new com.tau.birthdayplus.client.widgets.IBuyDelegate();
		    iBuyGUI.wishlistService =iBuyDelegate;
		    iBuyDelegate.gui =iBuyGUI;
		    iBuyGUI.entryPoint=this;
		    iBuyDelegate.entryPoint=this;
		    iBuyGUI.init();
	        
			//tab.add(iBuyGUI.wishlistVerticalPanel, "I buy");
		    
		    
		   
		    
		
		    
			
            
		
	}
	
	
	  
	  private void wireTabGUIEvents(){
		  
		  tab.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>(){

			public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
			
				if(event.getItem()== 0){
					if(eventGui.eventList == null){
						ArrayList<String> temp=new ArrayList<String>();
					    temp = getUserAndFriendsIds();
					    eventDelegate.getEvents(temp);
					}
				}else{
				     if(event.getItem()== 1){
				    	 if(myWishlistGUI.items == null)
				        	myWishlistDelegate.getMyWishlist(userId);
				     }
				     else{
				    	 if(iBuyGUI.itemsToBuy==null)
				    	   iBuyDelegate.getBookedWishlist(userId);
				     }
				}	
			}});
		  
		  
		  
	  
	  }
	  
	  
	  /*
		 * return social info about the user and his friends
		 */
		private  native void getSocialInfo()/*-{
		//  $wnd.alert("i'm getting social info");
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
		        that.@com.tau.birthdayplus.client.Birthdayplus::getProfile()();
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
		public void getProfile(){
		//	Window.alert(firstName); 
		//	Window.alert(lastName);
			RequestBuilder requestBuilder=profileService.getProfile(userId, new AsyncCallback<GuestData>(){

				public void onFailure(Throwable caught) {
					loadingImagePopup.hide();
			//		Window.alert("creating new profile" +caught);
					if (caught instanceof UserNotFoundException) {

						final FlowPanel main= new FlowPanel();
						RootPanel.get().add(main);
						main.setWidth("100%");
						
						Label title1 = new Label(firstName+", wellcome to Birthdayplus!");
						main.add(title1);
						title1.setWidth("100%");
						Label title2 = new Label("We just need you to confirm a few things before you start using Birthdayplus:");
						main.add(title2);
						title2.setWidth("100%");
						Label questionLabel= new Label("Sorry if we're being rude, but when were you born?");
						main.add(questionLabel);
						questionLabel.setWidth("100%");
						
						final ListBox days = new ListBox(false);
						main.add(days);
						
						
					    String[] listDayTypes = constants.cwListBoxDays();
					    for (int i = 0; i < listDayTypes.length; i++) {
					      days.addItem(listDayTypes[i]);
					    }
						
						final ListBox months= new ListBox(false);
						main.add(months);
						String[] listTypes = constants.cwListBoxMonths();
					    for (int i = 0; i < listTypes.length; i++) {
					      months.addItem(listTypes[i]);
					    }
					    final TextBox yearTextBox = new TextBox();
					    main.add(yearTextBox);
					    yearTextBox.setWidth("4em");
					    yearTextBox.setMaxLength(4);
					    
					 
						
						Label text = new Label("(your birth year, such as  1985)");
				       main.add(text);
				       
				      final  Label errorMessage= new Label();
				       main.add(errorMessage);
				       errorMessage.setVisible(false);
				       errorMessage.addStyleName("errorMessage");
				       
				       Anchor submit = new Anchor("all done, create my account!");
				       main.add(submit);
				       submit.addClickHandler(new ClickHandler(){

						public void onClick(ClickEvent event) {
							if(yearTextBox.getText().equals("")){
								errorMessage.setText(" please enter your birth year (you want people to give you birthday presents,right?)");
								errorMessage.setVisible(true);
							}else{	
								boolean valid = true;
								int day = days.getSelectedIndex()+1;
								int month = months.getSelectedIndex()+1;
								int year = 0;
								try{
								   year = Integer.parseInt(yearTextBox.getText());
								}catch( NumberFormatException ex){
									errorMessage.setText("please enter valid year");
									valid= false;
								}
								if(valid){
								    main.removeFromParent();
								    loadingImagePopup.center();
								    loadingImagePopup.show();
								    openWindow("http://testrpcplus.appspot.com/birthdayplus/login?openSocialId="+userId+"&firstName="+firstName+"&lastName="+lastName+"&day="+day+"&month="+month+"&year="+year);
								}
							}
						}
				       });
					}else{
						
					}
				}
				

				public void onSuccess(GuestData result) {
			//		Window.alert("get profile sucessful");
			//		Window.alert(result.getId());
					loadingImagePopup.hide();
				    user = result;
				    buildTab();
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
		
		
		/**
		 * open window for google login and wait until it closed
		 * @param destination
		 */
		private native void openWindow(String destination) /*-{
	    that = this;	
	   	var win = null;
		 win = $wnd.open(destination, "_blank", "width=800,height=500");
	     if (win) {
	       // Poll every 100ms to check if the window has been closed
	       timer = $wnd.setInterval(checkClosed, 100);
	      
	     }
	     
	    function checkClosed() {
	      if ((!win) || win.closed) {
	          win = null;
	          handleApproval();
	    }
	  }
	  
	  function handleApproval() {
	    if (timer) {
	      $wnd.clearInterval(timer);
	      timer = null;
	    }
	    if (win) {
	      win.close();
	      win = null;
	    }
	    that.@com.tau.birthdayplus.client.Birthdayplus::getProfile()();
	    return false;
	  }
	  


	}-*/;
		
	
	  

		

		
		
	  
}
