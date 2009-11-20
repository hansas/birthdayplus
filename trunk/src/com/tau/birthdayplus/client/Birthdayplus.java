package com.tau.birthdayplus.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Birthdayplus implements EntryPoint {
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
		tab.getElement().setId("tab");
		
		RootPanel.get("tabBarContainer").add(tab);
		
	    DecoratedStackPanel stackPanel = new DecoratedStackPanel();

	    // Add the birthdays
	    String eugeneHeader = getHeaderString("Eugene");
	    stackPanel.add(createFriendWishlist(), eugeneHeader, true);
	    String olgaHeader = getHeaderString("Olga");
	    stackPanel.add(createFriendWishlist(), olgaHeader, true);

	    tab.add(stackPanel, "Events");
		
	    HTML wishlistText = new HTML("Wishlist");
		tab.add(wishlistText, "Wishlist");
		
		tab.selectTab(0);
		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		final Button closeButton = new Button("Close");
		closeButton.getElement().setId("closeButton");
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending text to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		// Create a handler for the tab
		class MyHandler implements SelectionHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onSelection(SelectionEvent event) {
				sendNameToServer();
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
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

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		tab.addSelectionHandler(handler);
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


}
