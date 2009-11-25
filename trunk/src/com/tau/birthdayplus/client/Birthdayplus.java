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
import com.google.gwt.user.datepicker.client.DateBox;
import com.tau.birthdayplus.client.widgets.EventTab;
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
	    EventTab tabEvent = new EventTab(list); 
	    tab.add(tabEvent, "Events");
		
	    HTML wishlistText = new HTML("Wishlist");
		tab.add(wishlistText, "Wishlist");
		
		HTML buyListText = new HTML("Need to buy");
		tab.add(buyListText, "Need to buy");
		
		tab.selectTab(0);
		
		//Create the popup add event dialog box
		final DialogBox dialogBox = createAddEventDialogBox();

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
		
		

		// Create a handler for the tab
/*		class MyHandler implements SelectionHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
/*			public void onSelection(SelectionEvent event) {
				//sendNameToServer();
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
/*			private void sendNameToServer() {
				String textToServer = tab.getTabBar().getTitle();
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}
*/
		// Add a handler to send the name to the server
		//MyHandler handler = new MyHandler();
		//tab.addSelectionHandler(handler);
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
	  
	  /**
	   * Create the wishlist of specific contact.
	   * 
	   * @return the wishlist
	   */
	  private VerticalPanel createFriendWishlist(Person friend) {
	    VerticalPanel itemsPanel = new VerticalPanel();
	    itemsPanel.setSpacing(4);
	    String[] items = {"Korova", "Krokodil", "Hryak"};
	    for (int i = 0; i < items.length; i++) {
	      final String item = items[i];
	      final HTML contactLink = new HTML("<a href=\"javascript:undefined;\">"
	          + item + "</a>");
	      itemsPanel.add(contactLink);

	      // Open the contact info popup when the user clicks a contact
	      contactLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
		         Window.alert("Clicked");
	        }
	      });
	    }
	    return itemsPanel;
	  }
	  
	  private DecoratedStackPanel friendsEvents(List<Person> list){
		  DecoratedStackPanel dsPanel = new DecoratedStackPanel();
		  for (Person p : list){
			  String pName = getHeaderString(p.getName());
			  dsPanel.add(createFriendWishlist(p), pName, true);
		  }
		return dsPanel;  
	  }
	  
	  /**
	   * Create a form that contains undisclosed advanced options.
	   */
	  private DialogBox createAddEventDialogBox() {
		  final DialogBox dialogBox = new DialogBox();

		  dialogBox.setText("Add Event");
		  dialogBox.setAnimationEnabled(true);
	
		  HorizontalPanel buttonHPanel = new HorizontalPanel();
		  buttonHPanel.addStyleName("buttonHPanel");
		  buttonHPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		  // Add a handler to close the DialogBox
		  final Button btnOK = new Button(constants.cwDialogBoxOK(),
			        new ClickHandler() {
			          public void onClick(ClickEvent event) {
			            dialogBox.hide();
			          }
			        });
		  btnOK.getElement().setId("OkButton");
		  
		  final Button btnCancel = new Button(constants.cwDialogBoxCancel(),
			        new ClickHandler() {
			          public void onClick(ClickEvent event) {
			            dialogBox.hide();
			          }
			        });
		  btnCancel.getElement().setId("CancelButton");
		
		  if (LocaleInfo.getCurrentLocale().isRTL()) {
		      buttonHPanel.add(btnCancel);
		      buttonHPanel.add(btnOK);
		  } 
		  else {
			  buttonHPanel.add(btnOK);
		      buttonHPanel.add(btnCancel);
		  }

		  // Create a table to layout the form options
		  FlexTable layout = new FlexTable();
		  layout.setCellSpacing(6);
		  layout.setWidth("50%");

		  //Add some standard form options
		  layout.setHTML(1, 0, constants.cwDisclosurePanelFormName());
		  TextBox txtName = new TextBox();
		  layout.setWidget(1, 1, txtName);
		  layout.setHTML(2, 0, constants.cwEventLabel());
		  DateBox  txtBirthday = new DateBox();
		  txtBirthday.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		  layout.setWidget(2, 1, txtBirthday);
		  // Wrap the contents in a DecoratorPanel
		  DecoratorPanel decPanel = new DecoratorPanel();
		  decPanel.setWidget(layout);
		  VerticalPanel vPanel = new VerticalPanel();
		  vPanel.add(decPanel);
		  vPanel.add(buttonHPanel);
		  dialogBox.add(vPanel);

		  return dialogBox;
  }
}
