package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


import com.google.gwt.user.client.ui.HTMLTable.Cell;




import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.RootPanel;


import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;



public class WishListFriendsGUI  {
	/*
	 * constants
	 */
	
	private static final int BUY_LINK = 4;
    private static final int GROUP_BUY_LINK = 5;

	/*GUI Widgets*/
	
	//Dialog Bog with friend's wishlist
	protected DialogBox friendWishlistBox;
	//VerticalPanel for the content of wishlist box
	protected VerticalPanel wishlistBoxVerticalPanel;
	//friend's wishlist table
	protected TableWithHeader friendWishTable;
	//close button for closing wishlist box
	protected Button closeFriendWishlistBox;
	
	//dialog ox with participators
	protected DialogBox participatorsBox;
	//horizontal panel for the content of participatorx box
	protected HorizontalPanel participatorsBoxHorizontalPanel;
	//vertical panel for the buttons and sum in participators box
	protected VerticalPanel participatorsBoxVerticalPanel;

	//participators table
	protected TableWithHeader participatorsTable;
	//the text ox for entering you sum in participators box
	protected TextBox sumBox;
	//close button for closing partisipators box
	protected Button closeParticipatorsBoxButton;
	//button for adding new participator
	protected Button addParticipatorButton;
	//button for adding your proposal
	protected Button updateParticipatorButton;

	
	/*  
	 * Data Model
	 */
	
	  ArrayList<WishlistItemData> wishlist;
	 
	 WishlistItemData currentItem;
	 ArrayList<ParticipatorData> participators;
	 


	
	
	private void buildFriendWishlistBox(){
	    	friendWishlistBox=new DialogBox();
	    	friendWishlistBox.setText("Friend's Wishlist");

		    // Add a close button at the bottom of the dialog
		    Button closeFriendWishlistBox = new Button("close",
		        new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  friendWishlistBox.hide();
		          }
		        });
		    
		    closeFriendWishlistBox.setStyleName("cw-Button");
		    
		   // create panel to layou the content
		    wishlistBoxVerticalPanel = new VerticalPanel();
		    wishlistBoxVerticalPanel.setSpacing(4);
		    
		    buildFriendWishlistTable();
	//	    wishlistScroll=new ScrollPanel(friendWishTable);
		    
		//    wishlistBoxVerticalPanel.add(headerFriendWishTable);
	     	
		  //  wishlistBoxVerticalPanel.add(wishlistScroll);
		    wishlistBoxVerticalPanel.add(friendWishTable);
		    wishlistBoxVerticalPanel.setCellHorizontalAlignment(friendWishTable,
	     	        HasHorizontalAlignment.ALIGN_CENTER);


		    wishlistBoxVerticalPanel.add(closeFriendWishlistBox);
		  
		    wishlistBoxVerticalPanel.setCellHorizontalAlignment(closeFriendWishlistBox,
		          HasHorizontalAlignment.ALIGN_RIGHT);
		    
		    friendWishlistBox.add(wishlistBoxVerticalPanel);
		    
		    
		    
		
	}
	
	private void buildParticipationBox(){
		participatorsBox=new DialogBox();
		participatorsBox.setText("Participators");
		//main panel
		participatorsBoxHorizontalPanel=new HorizontalPanel();
		participatorsBoxHorizontalPanel.setSpacing(4);
		//panel for the text box and buttons
		participatorsBoxVerticalPanel=new VerticalPanel();
		participatorsBoxVerticalPanel.setSpacing(4);
		//text box for the sum
		sumBox=new TextBox();
		//close button
		closeParticipatorsBoxButton=new Button("close",
		        new ClickHandler() {
	          public void onClick(ClickEvent event) {
	            participatorsBox.hide();
	          }
	        });
		//add and update buttons
		addParticipatorButton=new Button("add");
		updateParticipatorButton=new Button("update");
		
		//build scrollable table for participators
		buildParticipatorsTable();
		
		
		//prepare vertical panel with text box and buttons
		participatorsBoxVerticalPanel.add(sumBox);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(sumBox, HasHorizontalAlignment.ALIGN_CENTER);
		
		participatorsBoxVerticalPanel.add(addParticipatorButton);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(addParticipatorButton, HasHorizontalAlignment.ALIGN_CENTER);
		
		participatorsBoxVerticalPanel.add(updateParticipatorButton);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(updateParticipatorButton,HasHorizontalAlignment.ALIGN_CENTER);
		
		
		participatorsBoxVerticalPanel.add(closeParticipatorsBoxButton);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(closeParticipatorsBoxButton, HasHorizontalAlignment.ALIGN_RIGHT);

		
	    //prepare horizontal panel with scroll and vertical panel
		
		participatorsBoxHorizontalPanel.add(participatorsTable);
		participatorsBoxHorizontalPanel.setCellVerticalAlignment(participatorsTable,
     	        HasVerticalAlignment.ALIGN_TOP);
		
		participatorsBoxHorizontalPanel.add(participatorsBoxVerticalPanel);
		participatorsBoxHorizontalPanel.setCellVerticalAlignment(participatorsBoxVerticalPanel,
     	        HasVerticalAlignment.ALIGN_MIDDLE);
		
		participatorsBox.add(participatorsBoxHorizontalPanel);
		
	}
	
	/*
	 * create flex table for friend's wishlist items
	 */
	private void buildFriendWishlistTable(){
		//create table for whishlistitems
	    friendWishTable=new TableWithHeader();
	    FlexCellFormatter cellFormatter =  friendWishTable.getFlexCellFormatter();
	    friendWishTable.addStyleName("cw-FlexTable");
	    friendWishTable.setWidth("32em");
	    friendWishTable.setCellSpacing(5);
	    friendWishTable.setCellPadding(3);
	    
	    
	    friendWishTable.setHeader(0,"Item");
	    friendWishTable.setHeader(1,"Priority");
	    friendWishTable.setHeader(2,"Price");
        /*
	    friendWishTable.setText(0, 0, "Item");
	    friendWishTable.setText(0, 1, "Priority");
	    friendWishTable.setText(0, 2, "Price");
	    
	    */
	    
	    friendWishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                Cell cellForEvent = friendWishTable.getCellForEvent(event);
                participatorsBox.center();
                participatorsBox.show();
            }
	    });
	}
	
	  /**
	   * Fill row in friend's wishlist table,by given wishlistItemData
	   */
	  private void fillRowFriendWishTable(String item) {
	    int numRows = friendWishTable.getRowCount();
	    friendWishTable.setWidget(numRows, 0,new Hyperlink("Item"+numRows,"Items"+numRows));
	    friendWishTable.setWidget(numRows,1,new Label("Priority"));
	    friendWishTable.setWidget(numRows, 2,new Label("price") );
	    friendWishTable.setWidget(numRows, 3, new Hyperlink("I'm buing", null));
	    friendWishTable.setWidget(numRows,4,new Hyperlink("Join the group", null));
	  }
	  
	  
	  private void buildParticipatorsTable(){
		  participatorsTable=new TableWithHeader();
		  FlexCellFormatter cellFormatter = participatorsTable.getFlexCellFormatter();
		  participatorsTable.addStyleName("cw-FlexTable");
		  participatorsTable.setWidth("32em");
		  participatorsTable.setCellSpacing(5);
		  participatorsTable.setCellPadding(3);
		    
		  participatorsTable.setWidget(0,0,new Label("Name"));
		  participatorsTable.setWidget(0, 1, new Label("Sum"));	
	
	  }
	  
	 /* 
	  private void LoadParticipatorTable(){
		  participatorsBox.center();
		  participatorsBox.show();
		  participators=currentItem.getParticipators();
		  
		  
		  
		  
	  }
	  
	  */
	  
	  /*
	   * filling the row in participator table
	   * Participator - ParticipatorData
	   * 
	   */
	  private void fillRowParticipatorsTable(String participator){
		  int numRows = participatorsTable.getRowCount();
		  participatorsTable.setWidget(numRows,0,new Label("name "+numRows));
		  participatorsTable.setWidget(numRows, 1, new Label("sum "+numRows));
		  
	  }
	  
	
	/**
	 * This is the entry point method.
	 */
	public void init() {
		 buildParticipationBox();
		 buildFriendWishlistBox();
		   
		    // Create a button to show the dialog Box
		    Button openButton = new Button("openFriendsWishlist",
		        new ClickHandler() {
		          public void onClick(ClickEvent sender) {
		        	  friendWishlistBox.center();
		        	  friendWishlistBox.show();
		          }
		        });
		    
		 // Associate the Main panel with the HTML host page.
		//    RootPanel.get("eventList").add(openButton);
		    
		    for (int i=0;i<5;i++){
		    	fillRowFriendWishTable("dummy");
		    	
		    }
		    
		//   friendWishTable.clear();
		    

		   

		  
		

		
		
		
	}
	
	/*
	 * 
	 
	 private void wireGUIEvents() {
		 friendWishTable.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	                 Cell cellForEvent = friendWishTable.getCellForEvent(event);
	                 gui_eventFriendWishTableClicked(cellForEvent);                
	            }});

	
	
	 }
	 
	 public void gui_eventFriendWishTableClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
         currentItem = wishlist.get(row);
         
        
         if (col==BUY_LINK) {
        	 bplusService.setInactive(userId,item.getWishlistItemId());
             
         } else if (col==BUY_GROUP) {
        	 
        	
         }
    }
   ...
    private void loadForm(Contact contact) {
        this.formGrid.setVisible(true);
        currentContact = contact;
        this.emailField.setText(contact.getEmail());
        this.phoneField.setText(contact.getPhone());
        this.nameField.setText(contact.getName());
    }



	*/

	
	
}


	    
		
		


