package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLTable.Cell;




import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;



public class WishListFriendsGUI  {
	/*
	 * constants
	 */
	CwConstants constants = GWT.create(CwConstants.class);
//	private static final int LINK = 0;
	private static final int PRICE_LINK =2;
	private static final int BUY_LINK = 3;
    private static final int GROUP_BUY_LINK = 4;

	/*GUI Widgets*/
	

	//VerticalPanel for the content of wishlist box
	protected FlowPanel wishlistBoxPanel;
	protected Label title;
	private ScrollPanel scrollWishlistPanel;
	private FlexTable headerFriendWishTable;
	//friend's wishlist table
	protected FlexTable friendWishTable;
	//close button for closing wishlist box
	protected Button closeFriendWishlistBoxButton;
	//popup panel for participators
	protected PopupPanel participatorsPanel;
	//table for participators
	protected TableWithHeader participatorsTable;
	
	private DialogBox moneyDialogBox;
	private VerticalPanel moneyVerticalPanel;
	private Label errorMsgLabel ;
	private TextBox   enterSumTextBox;
	private HorizontalPanel moneyHorizontalPanel;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	
	
	
	
	
	
	
	/*  
	 * Data Model
	 */
	//list of friends items for the event
	private ArrayList<WishlistItemNewData> items;
    private WishlistItemNewData currentItem;
    protected WishListFriendsDelegate wishlistService;
    protected EventTabGUI parent;
 
	

	
	 


	 /**
		 * This is the entry point method.
		 */
		public void init() {
			items = new ArrayList<WishlistItemNewData>();

			wishlistBoxPanel = new FlowPanel();
			parent.mainPanel.add(wishlistBoxPanel);
			wishlistBoxPanel.addStyleName("Panel");
		//	wishlistBoxPanel.setSize("100%", "350px");
			wishlistBoxPanel.setVisible(false);
			
			closeFriendWishlistBoxButton = new Button("Back");
			wishlistBoxPanel.add(closeFriendWishlistBoxButton);
			closeFriendWishlistBoxButton.addStyleName("Button");
		//	closeFriendWishlistBoxButton.setSize("50px", "25px");
			
			title = new Label();
			wishlistBoxPanel.add(title);
			title.addStyleName("Label");
		//	title.setSize("100%", "25px");
			
			
			
			
			buildFriendWishlistTable();
			    
			
			
			buildMoneyDialogBox();
			buildParticipatorsPopupPanel();
			 
			 
			
			   
		}
		
	private void buildMoneyDialogBox(){
		moneyDialogBox = new DialogBox();
		moneyDialogBox.setText("Enter a sum : ");
	
		
		moneyVerticalPanel  = new VerticalPanel();
		moneyDialogBox.add(moneyVerticalPanel);
		
		errorMsgLabel = new Label();
		moneyVerticalPanel.add(errorMsgLabel);
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		
		
		
		enterSumTextBox = new TextBox();
		moneyVerticalPanel.add(enterSumTextBox);
		
		moneyHorizontalPanel = new HorizontalPanel();
		moneyVerticalPanel.add(moneyHorizontalPanel);
		moneyHorizontalPanel.setSpacing(20);
		
		okMoneyButton = new Button();
		moneyHorizontalPanel.add(okMoneyButton);
		okMoneyButton.setText("Participate");
		
		cancelMoneyButton = new Button();
		moneyHorizontalPanel.add(cancelMoneyButton);
		cancelMoneyButton.setText("Cancel");
		
		
	}
	
	private void loadMoneyDialog(WishlistItemNewData item,Widget widgetClicked){
		currentItem = item;
	//	moneyDialogBox.center();
	   // moneyDialogBox.show();
		moneyDialogBox.showRelativeTo(widgetClicked);
	    enterSumTextBox.setFocus(true);
	}
		
	private void buildParticipatorsPopupPanel(){	
	   participatorsPanel = new PopupPanel(true);
	  
	   
 	   participatorsTable = new TableWithHeader();
 	   participatorsPanel.add(participatorsTable);
 	   participatorsTable.setStyleName("tables");
 	   
 	   participatorsTable.setHeader(0, "Name");
 	   participatorsTable.setHeader(1, "sum");
 	   
 	   participatorsTable.getColumnFormatter().addStyleName(0, "tablesColumns");
 	   participatorsTable.getColumnFormatter().addStyleName(1, "tablesColumns");
 	   
 	  
 	   
	}
	
	private void showParticipatorsPanel(WishlistItemNewData item,Widget widgetClicked){
		currentItem = item;
        participatorsTable.clear();

        int left =  widgetClicked.getAbsoluteLeft() + 10;
        int top = widgetClicked.getAbsoluteTop() + 10;
        participatorsPanel.setPopupPosition(left, top);
        
        participatorsPanel.show();
     //   participatorsPanel.showRelativeTo(widgetClicked);
        
        int row = 0;
        for(ParticipatorData participator : item.getParticipators()){
        	participatorsTable.setWidget(row, 0, new Label(participator.getUserFirstName()+ " " +participator.getUserLastName()) );
        	participatorsTable.setWidget(row, 1, new Label(participator.getMoney().toString()));
        	participatorsTable.getRowFormatter().addStyleName(row, "tablesRows");
        	row++;
        }

		
	}
	
	
	

	
	/*
	 * create flex table for friend's wishlist items
	 */
	private void buildFriendWishlistTable(){
		headerFriendWishTable = new FlexTable();
		wishlistBoxPanel.add(headerFriendWishTable);
		headerFriendWishTable.addStyleName("TableHeader");
	//	headerFriendWishTable.setSize("100%", "25px");
		 
	
		
		headerFriendWishTable.getColumnFormatter().setWidth(0, "100px");
		headerFriendWishTable.getColumnFormatter().setWidth(1, "70px");
		headerFriendWishTable.getColumnFormatter().setWidth(2, "35px");
	
		
		headerFriendWishTable.setWidget(0, 0, new Label ("Item"));
		headerFriendWishTable.setWidget(0, 1, new Label("Priority"));
		headerFriendWishTable.setWidget(0, 2, new Label("Price"));
		headerFriendWishTable.getFlexCellFormatter().setColSpan(0, 2, 3);

		
		scrollWishlistPanel = new ScrollPanel();
		wishlistBoxPanel.add(scrollWishlistPanel);
		scrollWishlistPanel.addStyleName("ShortScrollPanel");
	//	scrollWishlistPanel.setSize("100%", "275px");

	   
		friendWishTable = new FlexTable();
		scrollWishlistPanel.add(friendWishTable);
	//	friendWishTable.setWidth("100%");
		friendWishTable.addStyleName("Table");
		
		friendWishTable.getColumnFormatter().setWidth(0, "100px");
		friendWishTable.getColumnFormatter().setWidth(1, "70px");
		
       
	}
	
    private boolean userInGroup(WishlistItemNewData item){
    	boolean userInGroup = false;
    
    	 for (ParticipatorData data : item.getParticipators()){
		      if(parent.entryPoint.userId.equals(data.getUserId()))
			     userInGroup = true;
    	 }
    	 return userInGroup;
    	
    }
	
	/*
	 * on click in the table
	 */
	 public void gui_eventItemGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
        WishlistItemNewData item = this.items.get(row);
        Widget widgetClicked = friendWishTable.getWidget(row, col);
         
       switch(col){
    	                  
       case PRICE_LINK :    if(!item.getParticipators().isEmpty())
  		                        showParticipatorsPanel(item , widgetClicked);
                            break;
                          
       case BUY_LINK :      if(item.getIsActive())
    	                       wishlistService.bookItemForUser(item.getWishlistItemId(), parent.currentEvent.getEventId(),parent.entryPoint.userId);
                            break;
                          
       case GROUP_BUY_LINK :if(item.getIsActive()&& !userInGroup(item))
    	                       loadMoneyDialog(item , widgetClicked);
                            break;
       }      
       
 
	 }
	
	
	
	public void gui_eventCloseButtonClicked() {
	    wishlistBoxPanel.setVisible(false);
	    parent.eventPanel.setVisible(true);
		friendWishTable.clear();
        
    }
	
	public void gui_eventParticipatorsTableClicked(){
		participatorsPanel.hide();
	}
	

	
	public void gui_eventOkMoneyButtonClicked(){
		//if empty
		if(enterSumTextBox.equals("")){
			errorMsgLabel.setText("Enter the sum ");
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return;
		}
		//full , try to parse
		Integer sum = 0;
		try{
		   sum = Integer.parseInt(enterSumTextBox.getText());
		}catch(NumberFormatException ex){
			errorMsgLabel.setText("Enter valid sum ");
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return;
		}
		if (sum > 0){
			errorMsgLabel.setVisible(false);
			moneyDialogBox.hide();
			enterSumTextBox.setText("");
		    ParticipatorData data = new ParticipatorData(parent.entryPoint.userId,parent.entryPoint.user.getFirstName(),parent.entryPoint.user.getLastName(),sum);
            this.wishlistService.addParticipator(currentItem.getWishlistItemId(), parent.currentEvent.getEventId(), data);
		}else{
			errorMsgLabel.setText("Enter valid sum ");
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
		}
	}
	
	public void gui_eventCancelMoneyButtonClicked(){
		errorMsgLabel.setVisible(false);
		moneyDialogBox.hide();
		enterSumTextBox.setText("");
	}
	
	public void wireWishlistFriendGUIEvents() {
		this. closeFriendWishlistBoxButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        	    gui_eventCloseButtonClicked();
        	}
        });
		
		this.friendWishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	
                 Cell cellForEvent = friendWishTable.getCellForEvent(event);
            
                 if(cellForEvent!=null)
                    gui_eventItemGridClicked(cellForEvent);                
            }});
		
		
		
		this.participatorsTable.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				gui_eventParticipatorsTableClicked();
				
			}
		});
		
		this.okMoneyButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				gui_eventOkMoneyButtonClicked();
			}
		});
		
		this.cancelMoneyButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				gui_eventCancelMoneyButtonClicked();
			}
		});
		
		this.enterSumTextBox.addKeyUpHandler(new KeyUpHandler(){
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
					gui_eventOkMoneyButtonClicked();
				}
		});
	}
	
	/*
	 * friend's wishlist
	 */
	public void service_eventGetWishlistSuccesfull(
			ArrayList<WishlistItemNewData> result) {
	//	
		this.items = result;
        this.friendWishTable.clear();
        
        int row = 0;
        for (WishlistItemNewData item : result) {
        	//link
        	if (item.getLink().equals(""))
        		friendWishTable.setWidget(row, 0,new Label(item.getItemName()));
        	else
        		friendWishTable.setWidget(row, 0,new Anchor(item.getItemName(),item.getLink(),"_blank"));
        	//priority
        	if(item.getPriority())
        	   friendWishTable.setWidget(row,1,new Label("high"));
        	else
        		friendWishTable.setWidget(row,1,new Label("low"));
        	
            if(item.getIsActive()){	
        	   if(item.getParticipators().isEmpty()){
        	      friendWishTable.setWidget(row, 2,new Label(item.getPrice().toString()) );
        	      
        	      Image buyImage = new Image( GWT.getModuleBaseURL() + "present_16.png");
			      buyImage.setTitle("I'll buy");
        	      friendWishTable.setWidget(row, 3, buyImage);
        	      
        	      Image groupImage = new Image( GWT.getModuleBaseURL() + "group_24.png");
			      groupImage.setTitle("Start a group");
			      groupImage.setPixelSize(16, 16);
    	          friendWishTable.setWidget(row ,4, groupImage);
            	}else{
        	      Integer sum = 0;
        	      Boolean userInGroup = false;
        	      for (ParticipatorData data : item.getParticipators()){
        		      if(parent.entryPoint.userId.equals(data.getUserId()))
        			     userInGroup = true;
        		      sum += data.getMoney(); 
        	      }
        	      Label price = new Label (sum +"/"+item.getPrice().toString());
          	      friendWishTable.setWidget(row, 2,price );

        	   
        	    //  friendWishTable.setWidget(row, 2,new Hyperlink(sum +"/"+item.getPrice().toString(),null) );
        	      if(!userInGroup){
        		     Image groupImage = new Image( GWT.getModuleBaseURL() + "group_24.png");
        		     
    			     groupImage.setTitle("Join the group");
    			     groupImage.setPixelSize(16, 16);
        	         friendWishTable.setWidget(row, 4, groupImage);
        	      }
            	}
            }else {
            	friendWishTable.setText(row, 2,item.getPrice().toString() );
            	friendWishTable.getRowFormatter().addStyleName( row,constants.cwInactiveRowStyle());
            }
        //	friendWishTable.getRowFormatter().addStyleName(row, "tablesRows");
            row ++;
        }
		
	}

	public void service_eventGetWishlistFailed(Throwable caught) {
		// showMessage("Unable to get  wishlist");
		
	}
	public void service_eventBookItemForUserFailed(Throwable caught) {
		//show Message("You can't book this item");
		
	}

	public void service_eventBookItemForUserSuccesfull() {
		//showMessage("Now you can see this item in "I buy " tab");
		parent.entryPoint.iBuyGUI.itemsToBuy = null;
		this.wishlistService.getWishlist(parent.currentEvent.getUserId(), parent.currentEvent.getEventId());
		
	}

	public void service_eventAddParticipatorFailed(Throwable caught) {
		Window.alert("add participator " +caught);
		
	}

	public void service_eventAddParticipatorSuccesfull() {
		parent.entryPoint.iBuyGUI.itemsToBuy = null;
		this.wishlistService.getWishlist(parent.currentEvent.getUserId(), parent.currentEvent.getEventId());
		
	}

	

	
	  
	  


	
	
}


	    
		
		


