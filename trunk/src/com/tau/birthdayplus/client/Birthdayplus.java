package com.tau.birthdayplus.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

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
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final TabPanel  tab = new TabPanel();
		List<Person> listFriends = new ArrayList<Person>();
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);

		tab.getElement().setId("tab");
		tab.setAnimationEnabled(true);
		
		RootPanel.get("tabBarContainer").add(vPanel);
		vPanel.add(tab);
		
		final Button btnAddEvent = new Button("Add Event"); 
		vPanel.add(btnAddEvent);
		
	    listFriends.add(new Person("1", "Eugene"));
	    listFriends.add(new Person("2", "Olga"));
	    listFriends.add(new Person("3", "Irena"));
	    listFriends.add(new Person("4", "Eugene 2"));
	    // Add the birthdays
	    DecoratedStackPanel stackPanel = friendsEvents(listFriends);
	    tab.add(stackPanel, "Events");
		
	    HTML wishlistText = new HTML("Wishlist");
		tab.add(wishlistText, "Wishlist");
		
		HTML buyListText = new HTML("Need to buy");
		tab.add(buyListText, "Need to buy");
		
		tab.selectTab(0);
		
		//Create the popup add event dialog box
		final DialogBox dialogBox = createAddEventDialogBox();
		
		btnAddEvent.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.show();
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
	  private VerticalPanel createFriendWishlist() {
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
			  dsPanel.add(createFriendWishlist(), pName, true);
		  }
		return dsPanel;  
	  }
	  
	  /**
	   * Create a form that contains undisclosed advanced options.
	   */
	  private DialogBox createAddEventDialogBox() {
		  final DialogBox dialogBox = new DialogBox();
		  final Button btnOK = new Button("Ok");
		  final Button btnCancel = new Button("Cancel");
		  
		  btnOK.getElement().setId("OkButton");
		  btnOK.getElement().setId("CancelButton");
		  dialogBox.setText("Add Event");
		  dialogBox.setAnimationEnabled(true);
		  dialogBox.center();
		  //final Label textToServerLabel = new Label();
		  //final HTML serverResponseLabel = new HTML();
		  HorizontalPanel buttonHPanel = new HorizontalPanel();
		  buttonHPanel.addStyleName("buttonHPanel");
		  
		  /*dialogVPanel.add(new HTML("<b>Sending text to the server:</b>"));
		  dialogVPanel.add(textToServerLabel);
		  dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		  dialogVPanel.add(serverResponseLabel);*/
		  buttonHPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		  buttonHPanel.add(btnOK);
		  buttonHPanel.add(btnCancel);

		  // Add a handler to close the DialogBox
		  btnOK.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  dialogBox.hide();
			  }
		  });
		  
		  // Create a table to layout the form options
		  FlexTable layout = new FlexTable();
		  layout.setCellSpacing(6);
		  layout.setWidth("75%");
		  FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		  //Add a title to the form
		  layout.setHTML(0, 0, constants.cwDisclosurePanelFormTitle());
		  cellFormatter.setColSpan(0, 0, 2);
		  cellFormatter.setHorizontalAlignment(0, 0,
				  HasHorizontalAlignment.ALIGN_CENTER);

		  //Add some standard form options
		  layout.setHTML(1, 0, constants.cwDisclosurePanelFormName());
		  layout.setWidget(1, 1, new TextBox());
		  layout.setHTML(2, 0, constants.cwDisclosurePanelFormDescription());
		  layout.setWidget(2, 1, new TextBox());

		  // Create some advanced options
		  HorizontalPanel genderPanel = new HorizontalPanel();
		  String[] genderOptions = constants.cwDisclosurePanelFormGenderOptions();
		  for (int i = 0; i < genderOptions.length; i++) {
			  genderPanel.add(new RadioButton("gender", genderOptions[i]));
		  }
		  Grid advancedOptions = new Grid(2, 2);
		  advancedOptions.setCellSpacing(6);
		  advancedOptions.setHTML(0, 0, constants.cwDisclosurePanelFormLocation());
		  advancedOptions.setWidget(0, 1, new TextBox());
		  advancedOptions.setHTML(1, 0, constants.cwDisclosurePanelFormGender());
		  advancedOptions.setWidget(1, 1, genderPanel);

		  //Add advanced options to form in a disclosure panel
		  DisclosurePanel advancedDisclosure = new DisclosurePanel(
				  constants.cwDisclosurePanelFormAdvancedCriteria());
		  advancedDisclosure.setAnimationEnabled(true);
		  advancedDisclosure.ensureDebugId("cwDisclosurePanel");
		  advancedDisclosure.setContent(advancedOptions);
		  layout.setWidget(3, 0, advancedDisclosure);
		  cellFormatter.setColSpan(3, 0, 2);

		  // Wrap the contents in a DecoratorPanel
		  DecoratorPanel decPanel = new DecoratorPanel();
		  decPanel.setWidget(layout);
		  dialogBox.add(decPanel);
		  //dialogBox.add(buttonHPanel);
		  return dialogBox;
  }
}
