package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


import com.google.gwt.user.client.Window;
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
import com.tau.birthdayplus.client.Actions;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;



public class WishListFriendsGUI  {
	/*
	 * constants
	 */
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int LINK = 0;
	private static final int BUY_LINK = 3;
    private static final int GROUP_BUY_LINK = 4;

	/*GUI Widgets*/
	
	//Dialog Bog with friend's wishlist
	protected DialogBox friendWishlistBox;
	//VerticalPanel for the content of wishlist box
	protected VerticalPanel wishlistBoxVerticalPanel;
	//friend's wishlist table
	protected TableWithHeader friendWishTable;
	//close button for closing wishlist box
	protected Button closeFriendWishlistBox;
	
	
	
	/*  
	 * Data Model
	 */
	private ArrayList<WishlistItemData> items;
    private WishlistItemData currentItem;
    protected WishListFriendsDelegate wishlistService;
    protected EventTabGUI parent;
 
	
	/*
	//dialog Box with participators
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

	
	 


	 /**
		 * This is the entry point method.
		 */
		public void init() {
			 buildFriendWishlistBox();
			 
			 items = new ArrayList<WishlistItemData>();
			
			
			 ArrayList<WishlistItemData> data=new ArrayList<WishlistItemData>();
			    for(int i=0;i<5;i++){
			    	data.add(new WishlistItemData("555","name"+i,i,"http://techblog.maydu.eu/?p=7",500));
			    	
			    }
			    
			//    service_eventGetWishlistSuccesfull(data);
			    
			    
		}
	
	private void buildFriendWishlistBox(){
	    	friendWishlistBox=new DialogBox();
	    	friendWishlistBox.setStyleName(constants.cwDialogBoxStyle());
            
		    
		    closeFriendWishlistBox = new Button("Close");
		    closeFriendWishlistBox.setStyleName("cw-Button");
		    
		   // create panel to layout the content
		    wishlistBoxVerticalPanel = new VerticalPanel();
		    
		    
		    buildFriendWishlistTable();
		    
		    wishlistBoxVerticalPanel.add(friendWishTable);
		    wishlistBoxVerticalPanel.setCellHorizontalAlignment(friendWishTable,
	     	        HasHorizontalAlignment.ALIGN_CENTER);


		    wishlistBoxVerticalPanel.add(closeFriendWishlistBox);
		  
		    wishlistBoxVerticalPanel.setCellHorizontalAlignment(closeFriendWishlistBox,
		          HasHorizontalAlignment.ALIGN_RIGHT);
		    
		    friendWishlistBox.add(wishlistBoxVerticalPanel);

	}
	
	/*
	 * create flex table for friend's wishlist items
	 */
	private void buildFriendWishlistTable(){
	    friendWishTable=new TableWithHeader();
	    friendWishTable.addStyleName(constants.cwTableStyle());    
	    
	    friendWishTable.setHeader(0,"Item");
	    friendWishTable.setHeader(1,"Priority");
	    friendWishTable.setHeader(2,"Price");
       
	}
	
	/*
	 * on click in the table
	 */
	 public void gui_eventItemGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
        WishlistItemData item = this.items.get(row);
         
       
         if (col== LINK) {
        	 if(item.getLink()!=null)
        		 Window.open(item.getLink(), "_blank", null);
         } else if (col==BUY_LINK) {
             wishlistService.setInactiveWishlistitem(parent.entryPoint.userId, item.getWishlistItemId());
         }else if(col==GROUP_BUY_LINK){
        	 
         }
    }
	
	/*
	 * friend's wishlist
	 */
	public void service_eventGetWishlistSuccesfull(
			ArrayList<WishlistItemData> result) {
	//	friendWishlistBox.setText(parent.entryPoint.userFriends.get(parent.currentEvent.getUserId()));
		friendWishlistBox.center();
		friendWishlistBox.show();
		this.items = result;
        this.friendWishTable.clear();
        
        int row = 0;
        for (WishlistItemData item : result) {
        	if (item.getLink()== null)
        		friendWishTable.setWidget(row, 0,new Label(item.getItemName()));
        	else
        		friendWishTable.setWidget(row, 0,new Hyperlink(item.getItemName(),null));
        	friendWishTable.setWidget(row,1,new Label(item.getPriority().toString()));
        	friendWishTable.setWidget(row, 2,new Label(item.getPrice().toString()) );
        	friendWishTable.setWidget(row, 3, new Hyperlink("I'm buing", null));
    	    friendWishTable.setWidget(row ,4,new Hyperlink("Join the group", null));
            row ++;
        }
		
	}
	
	public void service_setInactiveWishlistitemSuccessful() {
		//showMessage("the item was successfully booked");
		this.wishlistService.getWishlist(parent.currentEvent.getUserId());
		
	}
	
	public void service_setInactiveWishlistitemFailed(Throwable caught) {
       //showMessage("Unable to get  wishlist");
		
	}

	

	public void service_eventGetWishlistFailed(Throwable caught) {
		// showMessage("Unable to get  wishlist");
		
	}
	
	public void gui_eventCloseButtonClicked() {
		friendWishlistBox.hide();
        
    }
	
	public void wireWishlistFriendGUIEvents() {
		this. closeFriendWishlistBox.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        	    gui_eventCloseButtonClicked();
        	}
        });
		
		this.friendWishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                 Cell cellForEvent = friendWishTable.getCellForEvent(event);
                 gui_eventItemGridClicked(cellForEvent);                
            }});
	}


	
	
	
	/*
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
	*/
	
	
	
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

	
	  
	  
	  /*
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
	  */
	  
	 /* 
	  private void LoadParticipatorTable(){
		  participatorsBox.center();
		  participatorsBox.show();
		  participators=currentItem.getParticipators();
		  
		  
		  
		  
	  }
	  
	  
	  
	  
	   * filling the row in participator table
	   * Participator - ParticipatorData
	   * 
	   
	  private void fillRowParticipatorsTable(String participator){
		  int numRows = participatorsTable.getRowCount();
		  participatorsTable.setWidget(numRows,0,new Label("name "+numRows));
		  participatorsTable.setWidget(numRows, 1, new Label("sum "+numRows));
		  
	  }
	  */
	
	
	
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


	    
		
		


