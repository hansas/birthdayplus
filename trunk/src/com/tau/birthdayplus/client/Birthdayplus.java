package com.tau.birthdayplus.client;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;


import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

import com.google.gwt.user.client.ui.HTMLTable.Cell;

import com.tau.birthdayplus.client.widgets.EventTabDelegate;
import com.tau.birthdayplus.client.widgets.EventTabGUI;
import com.tau.birthdayplus.client.widgets.MyWishlistDelegate;
import com.tau.birthdayplus.client.widgets.MyWishlistTabGUI;
import com.tau.birthdayplus.dto.client.EventData;
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
	public String userId="555";
	public HashMap<String,String> userFriends= new HashMap<String,String>();
	
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
	
	
	
	
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		    TabPanel tab = new TabPanel();
		
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
			
			
			//GuestData user= new GuestData(userId,"olga","vingurt",new Date());
		//	profileService.createProfile(user, new AsyncCallback<Void>(){
	    	//	public void onFailure(Throwable caught){
	    //			System.out.println(caught);
	    //		}
	    //		
		//		public void onSuccess(Void result) {
					ArrayList<String> temp=new ArrayList<String>();
					temp.add(userId);
					eventDelegate.getEvents(temp);
					//listen to the events in the tabs
	    			wireEventGUIEvents();
	    			wireMyWishlistGUIEvents();
	    			wireIBuyGUIEvents();
					
			//	}
				
	  //  	}//end of inner class
	   // 	);//end of method call)
			
			tab.selectTab(0);
		
		
		
		
		
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
	  
	  

		/*
		 * listen to the widgets of MyWishlist tab
		 */
		private void wireMyWishlistGUIEvents() {
			myWishlistGUI.wishTable.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	                 Cell cellForEvent = myWishlistGUI.wishTable.getCellForEvent(event);
	                 myWishlistGUI.gui_eventItemGridClicked(cellForEvent);                
	            }});
	        
			myWishlistGUI.addItemButton.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	myWishlistGUI.gui_eventAddItemButtonClicked();
	            }});

			myWishlistGUI.updateButton.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	myWishlistGUI.gui_eventUpdateButtonClicked();
	            }});
	        
			myWishlistGUI.addButton.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	myWishlistGUI.gui_eventAddButtonClicked();
	                
	            }});
	        
			myWishlistGUI.cancelButton.addClickHandler(new ClickHandler(){
	        	public void onClick(ClickEvent event){
	        		myWishlistGUI.gui_eventCancelButtonClicked();
	        	}
	        });
	  
		}
		
		/*
		 * listen to the widgets of I've booked this items tab
		 */
		private void wireIBuyGUIEvents(){
			iBuyGUI.wishTable.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					Cell cellForEvent=iBuyGUI.wishTable.getCellForEvent(event);
					iBuyGUI.gui_eventItemGridClicked(cellForEvent);
				}
			});
		}
		
		private void wireEventGUIEvents(){
			eventGui.eventTable.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					Cell cellForEvent = eventGui.eventTable.getCellForEvent(event);
					eventGui.gui_eventEventGridClicked(cellForEvent);
				}
			});
			eventGui.btnAddEvent.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	eventGui.gui_eventAddEventButtonClicked();
	            }});

			eventGui.updateButton.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	eventGui.gui_eventUpdateButtonClicked();
	            }});
	        
			eventGui.addButton.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	eventGui.gui_eventAddButtonClicked();
	                
	            }});
	        
			eventGui.cancelButton.addClickHandler(new ClickHandler(){
	        	public void onClick(ClickEvent event){
	        		eventGui.gui_eventCancelButtonClicked();
	        	}
	        });
		}
	  
}
