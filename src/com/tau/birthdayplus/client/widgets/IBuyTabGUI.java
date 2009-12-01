package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;




public class IBuyTabGUI {
	//////////////////Constants///////////////////////////
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int DELETE_LINK = 6;
	private static final int CANCEL_LINK = 5;
	private static final int UPDATE_LINK = 4;
	private static final int NAME_LINK   =1;
	

	//////////////////GUI Widgets////////////////////////
	//VerticalPanel for the content of wishlist
	public VerticalPanel wishlistVerticalPanel;
	// wishlist table
	public TableWithHeader wishTable;
	
	//dialog for participators
	private DialogBox participatorsBox;
	//vertical panel that holds everything
	private VerticalPanel participatorsBoxVerticalPanel;
	//participators table
	private TableWithHeader participatorsTable;
	
	//main panel for the chat
	private VerticalPanel chatVerticalPanel;
	//chat table
	private TableWithHeader chatTable;
	// Add a text area
    TextArea chatTextArea ;
    //add new message to the chat
	private Button addMessageButton;
	
	
	//close dialog box
	private Button closePartisipatorsBoxButton;
	
	
	private DialogBox moneyDialogBox;
	private VerticalPanel moneyVerticalPanel;
	private TextBox   enterSumTextBox;
	private HorizontalPanel moneyHorizontalPanel;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	
	
	//////////////////Model///////////////////////////////
	ArrayList<WishlistItemNewData> itemsToBuy;
	WishlistItemNewData currentItem;
	public IBuyDelegate wishlistService;
	public Birthdayplus entryPoint;
	

	

	
	/**
	 * build the Tab
	 */
	public void init() {
		wishlistVerticalPanel=new VerticalPanel();
		wishlistVerticalPanel.setTitle("Booked Items ");
		buildWishlistTable();
		buildParticipatorsBox();
		wishlistVerticalPanel.add(wishTable);
		
		buildMoneyDialogBox();
		    
		 ArrayList<WishlistItemNewData> data=new ArrayList<WishlistItemNewData>();
		    for(int i=0;i<5;i++){
		    	data.add(new WishlistItemNewData("","","Drug","","prazdnik"+i,"podarok"+i, i, "", 500,false));
		    }
		    
		service_getBookedWishlistSuccesfull(data);
	}
	
	
	private void buildMoneyDialogBox(){
		moneyDialogBox = new DialogBox();
		moneyDialogBox.setStyleName(constants.cwDialogBoxStyle());
		
		moneyVerticalPanel  = new VerticalPanel();
		enterSumTextBox = new TextBox();
		enterSumTextBox.setStyleName(constants.cwTextBoxStyle());
		enterSumTextBox.setText("Enter the sum :");
		
		moneyHorizontalPanel = new HorizontalPanel();
		okMoneyButton = new Button();
		okMoneyButton.setStyleName(constants.cwButtonStyle());
		okMoneyButton.setText("ok");
		
		cancelMoneyButton = new Button();
		cancelMoneyButton.setStyleName(constants.cwButtonStyle());
		cancelMoneyButton.setText("cancel");
		
		moneyHorizontalPanel.add(okMoneyButton);
		moneyHorizontalPanel.add(cancelMoneyButton);
		
		moneyVerticalPanel.add(enterSumTextBox);
		moneyVerticalPanel.add(moneyHorizontalPanel);
		
		moneyDialogBox.add(moneyVerticalPanel);
		
	}
	
	private void loadMoneyDialog(WishlistItemNewData item){
		moneyDialogBox.center();
		currentItem = item;
		
	    moneyDialogBox.show();
	}
	
	
	/*
	 * create flex table for wishlist items
	 */
	private void buildWishlistTable(){
		//create table for whishlistitems
	    wishTable=new TableWithHeader();
	    wishTable.setStyleName(constants.cwTableStyle());
	    
	    //header
	    wishTable.setHeader(0,"Event");
	    wishTable.setHeader(1,"Item");
	    wishTable.setHeader(2,"Priority");
	    wishTable.setHeader(3,"Price");
	}
	
	private void buildParticipatorsTable(){
		participatorsTable = new TableWithHeader();
		participatorsTable.setStyleName(constants.cwTableStyle());
		
		//header
		participatorsTable.setHeader(0, "Name");
		participatorsTable.setHeader(1, "Sum");
	}
	
	private void fillParticipatorsTable(){
		this.participatorsTable.clear();
		
		int row = 0;
		
		for (ParticipatorData user : currentItem.getParticipators()){
			participatorsTable.setWidget(row, 0, new Label( user.getUserFirstName() + " " + user.getUserLastName()) );
			participatorsTable.setWidget(row, 1, new Label(user.getMoney().toString()));
			row++;
		}
		
	}
	
	private void buildChatTable(){
		chatTable = new TableWithHeader();
		chatTable.setStyleName(constants.cwTableStyle());
		
		//header
		chatTable.setHeader(0, "From");
		chatTable.setHeader(1, "Time");
		chatTable.setHeader(2,"Message");
	}
	
	private void buildChat(){
        chatVerticalPanel = new VerticalPanel();
		
		chatTextArea = new TextArea();
		chatTextArea.setStyleName(constants.cwTextAreaStyle());
		
		chatTextArea.setVisibleLines(3);
		
		addMessageButton = new Button("send message");
		addMessageButton.setStyleName(constants.cwButtonStyle());
		
		
		buildChatTable();
		
		chatVerticalPanel.add(chatTable);
		chatVerticalPanel.setCellHorizontalAlignment(chatTable,HasHorizontalAlignment.ALIGN_CENTER);
		
		chatVerticalPanel.add(chatTextArea);
		chatVerticalPanel.setCellHorizontalAlignment(chatTextArea, HasHorizontalAlignment.ALIGN_CENTER);
		
		chatVerticalPanel.add(addMessageButton);
		chatVerticalPanel.setCellHorizontalAlignment(addMessageButton,HasHorizontalAlignment.ALIGN_RIGHT );
	}
		
	
		
	
	
	private void buildParticipatorsBox(){
		participatorsBox = new DialogBox();
		participatorsBox.addStyleName(constants.cwDialogBoxStyle());
		
		participatorsBoxVerticalPanel = new VerticalPanel();
		
		closePartisipatorsBoxButton = new Button("close");
		closePartisipatorsBoxButton.setStyleName(constants.cwButtonStyle());
		
		buildParticipatorsTable();
		buildChat();
		
		participatorsBoxVerticalPanel.add(participatorsTable);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(participatorsTable,  HasHorizontalAlignment.ALIGN_CENTER);
		
		participatorsBoxVerticalPanel.add(chatVerticalPanel);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(chatVerticalPanel, HasHorizontalAlignment.ALIGN_CENTER );
		
		participatorsBoxVerticalPanel.add(closePartisipatorsBoxButton);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(closePartisipatorsBoxButton , HasHorizontalAlignment.ALIGN_RIGHT);
		
		participatorsBox.add(participatorsBoxVerticalPanel);

		
	}
		
	
		
		
		
	
	
	  
	  
	  
	  public void gui_eventItemGridClicked(Cell cellClicked) {
	         int row = cellClicked.getRowIndex();
	         int col = cellClicked.getCellIndex();
	        
	         WishlistItemNewData item = this.itemsToBuy.get(row);
	         
	        
	        switch(col){
	        case NAME_LINK    : if(!item.getLink().equals(""))
       		                       Window.open(item.getLink(), "_blank", null);
	                            break;
	        case UPDATE_LINK  : loadMoneyDialog(item);    
	                            break;
	        case CANCEL_LINK  : if(item.getParticipators().isEmpty())
	        	                   this.wishlistService.cancelBookItemForUser(item.getWishlistItemId(), entryPoint.userId);
	                            else
	                               this.wishlistService.deleteParticipator(item.getWishlistItemId(), entryPoint.userId);
	                            break;
	        case DELETE_LINK  : this.wishlistService.deleteItemFromTab(entryPoint.userId, item.getWishlistItemId());
	                            break;
	        }
	        
	  }
	    

      
		public void service_getBookedWishlistSuccesfull(
				ArrayList<WishlistItemNewData> result) {
			this.itemsToBuy = result;
	        this.wishTable.clear();
	        
	        RowFormatter rf = wishTable.getRowFormatter();
	        
	        int row = 0;
	        
	        for (WishlistItemNewData item : result) {
	        	wishTable.setWidget(row,0,new Label(item.getUserName()+"'s "+item.getEventName()));
	        	if (item.getLink()== null)
	        		wishTable.setWidget(row, 1,new Label(item.getItemName()));
	        	else
	        		wishTable.setWidget(row, 1,new Hyperlink(item.getItemName(),null));
	    	    wishTable.setWidget(row,2,new Label(item.getPriority().toString()));
	    	    //it's only me
	    	    if(item.getParticipators().isEmpty()){
	    	    	wishTable.setWidget(row, 3,new Label(item.getPrice().toString()) );
		    	    wishTable.setWidget(row, 5, new Hyperlink("cancel", null)); 
		    	    wishTable.setWidget(row, 6, new Hyperlink("delete" , null));
	    	    }else{
	    	    	Integer sum = 0;
	    	    	for(ParticipatorData user : item.getParticipators()){
	    	    		sum+=user.getMoney();
	    	    	}
	    	        wishTable.setWidget(row, 3,new Hyperlink(sum+ " / " + item.getPrice().toString(), null) );
	    	        if(item.getIsActive()){
	    	        	wishTable.setWidget(row, 4, new Hyperlink("update", null));
	    	        	wishTable.setWidget(row, 5, new Hyperlink("leave this group",null));
	    	        }else{
	    	        	wishTable.setWidget(row, 6, new Hyperlink("delete",null));
	    	        }
	    	        wishTable.setWidget(row, 4, new Hyperlink("update", null));
	    	        wishTable.setWidget(row, 5, new Hyperlink("leave this group",null));
	           
	        }
	    	    
	    	    row ++;
	        }
			    
		}
		
		public void service_setActiveWishlistitemSuccessful() {
	//		showMessage("Item was successfully canceled");
	        this.wishlistService.getBookedWishlist(entryPoint.userId);

			
		}
	  
	  public void service_setActiveWishlistitemFailed(Throwable caught) {
			//showMessage("Unable to cancel this item");

			
		}

		
		
		public void service_getBookedWishlistFailed(Throwable caught) {
			//status.setText("Unable to get booked item list");

			
		}
		
		
		public void wireIBuyGUIEvents(){
			this.wishTable.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					Cell cellForEvent=wishTable.getCellForEvent(event);
					gui_eventItemGridClicked(cellForEvent);
				}
			});
		}


		public void sevice_eventCancelBookItemForUserFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}


		public void service_eventCancelBookItemForUserSuccesfull() {
			// TODO Auto-generated method stub
			
		}


		public void service_eventDeleteParticipatorFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}


		public void service_eventDeleteParticipatorSuccesfull() {
			// TODO Auto-generated method stub
			
		}


		public void service_eventDeleteItemFromTabFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}


		public void service_eventDeleteItemFromTabSuccesfull() {
			// TODO Auto-generated method stub
			
		}

	  
}
