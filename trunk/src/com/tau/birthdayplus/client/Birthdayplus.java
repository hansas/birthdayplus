package com.tau.birthdayplus.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tau.birthdayplus.client.widgets.EventTabDeligate;
import com.tau.birthdayplus.client.widgets.EventTabGUI;
import com.tau.birthdayplus.client.widgets.MyWishlistDeligate;
import com.tau.birthdayplus.client.widgets.MyWishlistTabGUI;
import com.tau.birthdayplus.dto.client.EventData;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Birthdayplus implements EntryPoint {
	CwConstants constants = GWT.create(CwConstants.class);
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	/**
	 * Create a remote service proxy to talk to the server-side Event service.
	 */
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	
	/**
	 * Create a remote service proxy to talk to the server-side Profile service.
	 */
	private final ProfileServiceAsync profileService = GWT.create(ProfileService.class); 
	
	/**
	 * Create a remote service proxy to talk to the server-side Profile service.
	 */
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	
	/*
	 *  Event tab
	 */
	private EventTabGUI eventGui;
	private EventTabDeligate eventDeligate;
	
	/*
	 * for second tab
	 */
	private MyWishlistTabGUI gui;
	private MyWishlistDeligate delegate;
	
	/*
	 * listen to the widgets of second tab
	 */
	private void wireGUIEvents() {
        gui.wishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                 Cell cellForEvent = gui.wishTable.getCellForEvent(event);
                 gui.gui_eventItemGridClicked(cellForEvent);                
            }});
        
        gui.addItemButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui.gui_eventAddItemButtonClicked();
            }});

        gui.updateButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                gui.gui_eventUpdateButtonClicked();
            }});
        
        gui.addButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui.gui_eventAddButtonClicked();
                
            }});
        
        gui.cancelButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        		gui.gui_eventCancelButtonClicked();
        	}
        });
        
       /* eventGui.btnAddEvent.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        		eventGui.gui_eventAddEventButtonClicked();
        	}
        });
        
*/
    }


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final TabPanel  tab = new TabPanel();
		List<Person> listFriends = new ArrayList<Person>();

		tab.getElement().setId("tab");
		tab.setAnimationEnabled(true);
		
		RootPanel.get("tabBarContainer").add(tab);
		
		
	    listFriends.add(new Person("1", "Eugene"));
	    listFriends.add(new Person("2", "Olga"));
	    listFriends.add(new Person("3", "Irena"));
	    listFriends.add(new Person("4", "Eugene 2"));
	    
	    /**
	     *  Add events tab
	     */
	    List<EventData> list = new ArrayList<EventData>(); 
	    EventData event = new EventData("1","smert", new Date(2000), false);
	    list.add(event);
	    
	    //eventGui = new EventTabGUI(list); 
	    //eventDeligate = new EventTabDeligate();
	    //eventGui.eventService = eventDeligate;
	    //tab.add(eventGui, "Events");
		
	    /*
	     * add MyWishlist to the second tab
	     */
	    gui = new MyWishlistTabGUI();
        delegate = new MyWishlistDeligate();
        gui.wishlistService = delegate;
        delegate.gui = gui;
        gui.init();
        
		tab.add(gui.wishlistVerticalPanel, "Wishlist");
		
		//listen to the events in second tab
		wireGUIEvents();
		
		HTML buyListText = new HTML("Need to buy");
		tab.add(buyListText, "Need to buy");
		
		tab.selectTab(0);

		//check that can communicate with remote services 
		eventService.printHello(
				new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						
					}

					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
					}
				});
		
		profileService.printHello(new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
		});
		
		wishlistService.printHello(new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
		});
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
	  
	  
}
