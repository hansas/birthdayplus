package com.tau.birthdayplus.client;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;


import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.TabPanel;



import com.tau.birthdayplus.client.widgets.EventTabDelegate;
import com.tau.birthdayplus.client.widgets.EventTabGUI;
import com.tau.birthdayplus.client.widgets.MyWishlistDelegate;
import com.tau.birthdayplus.client.widgets.MyWishlistTabGUI;

import com.tau.birthdayplus.dto.client.GuestData;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Birthdayplus implements EntryPoint {
	CwConstants constants = GWT.create(CwConstants.class);
	
	// Create a remote service proxy to talk to the server-side Profile service.
	private final ProfileServiceAsync profileService = GWT.create(ProfileService.class); 
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	/*
	 * user id and HashMap of <friend id, friend name> from open social
	 */
	public String userId="765";
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
	public void onModuleLoad() {
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
	        
			tab.add(iBuyGUI.wishlistVerticalPanel, "I buy");
			
			userFriends.put("123456789", "misha dezl");
			userFriends.put("987654321", "olga vingurt");
			
			profileService.getProfile(userId, new AsyncCallback<GuestData>(){

				public void onFailure(Throwable caught) {
					GuestData user= new GuestData(userId,"ya","yaya",new Date(4,6,85));
					profileService.createProfile(user, new AsyncCallback<Void>(){
			    	public void onFailure(Throwable caught){
			    			System.out.println(caught);
			    		}
			    		
						public void onSuccess(Void result) {
							
							//listen to the events in the tabs
							eventGui.wireEventGUIEvents();
			    			myWishlistGUI.wireMyWishlistGUIEvents();
			    			iBuyGUI.wireIBuyGUIEvents();
			    			wireTabGUIEvents();
			    			
			    			tab.selectTab(0);
							
							
						}
						
			    	}//end of inner class
			    	);//end of method call)
					
					
				}

				public void onSuccess(GuestData result) {
			    
				user = result;
			    	eventGui.wireEventGUIEvents();
			    	
	    			myWishlistGUI.wireMyWishlistGUIEvents();
	    			iBuyGUI.wireIBuyGUIEvents();
	    			wireTabGUIEvents();
	    			
	    			tab.selectTab(0);
					
				}
				
			});
			
			
			
		//	wireTabGUIEvents();
		
		
		
		
		
	}
	
	/**
	   * Get a string representation of the header that includes an image and some
	   * text.
	   * 
	   * @param text the header text
	   * @param image the {@link AbstractImagePrototype} to add next to the header
	   * @return the header as a string
	   */
	  private String getHeaderString(String text) {
	    // Add the image and text to a horizontal panel
	    HorizontalPanel hPanel = new HorizontalPanel();
	    hPanel.setSpacing(0);
	    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    HTML headerText = new HTML(text);
	    headerText.setStyleName("cw-StackPanelHeader");
	    hPanel.add(headerText);

	    // Return the HTML string for the panel
	    return hPanel.getElement().getString();
	  }
	  
	  private void wireTabGUIEvents(){
		  tab.addSelectionHandler(new SelectionHandler<Integer>(){

			public void onSelection(SelectionEvent<Integer> event) {
				if(event.getSelectedItem()== 0){
					ArrayList<String> temp=new ArrayList<String>();
					temp = getUserAndFriendsIds();
					eventDelegate.getEvents(temp);
				};
				if(event.getSelectedItem()== 1)
					myWishlistDelegate.getMyWishlist(userId);
				
					
					
				
			}});
		  
	  
	  }
	  

		
		
	  
}
