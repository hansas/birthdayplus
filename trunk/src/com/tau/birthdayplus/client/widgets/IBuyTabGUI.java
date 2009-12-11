package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.ParticipatorData;

import com.tau.birthdayplus.dto.client.WishlistItemNewData;




public class IBuyTabGUI {
	//////////////////Constants///////////////////////////
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int BUY_LINK = 6;
	private static final int CANCEL_LINK = 5;
	private static final int UPDATE_LINK = 4;
	private static final int PRICE_LINK  = 3;
	private static final int NAME_LINK   =1;
	

	//////////////////GUI Widgets////////////////////////
	public AbsolutePanel iBuyPanel;
	//VerticalPanel for the content of wishlist
//	public VerticalPanel wishlistVerticalPanel;
	// wishlist table
	public TableWithHeader wishTable;
	
	//dialog for participators
//	private DialogBox participatorsBox;
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
	private Label errorMsgLabel ;
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
		iBuyPanel = new AbsolutePanel();
		iBuyPanel.setStyleName("iBuyPanel");
	
		buildWishlistTable();
		buildParticipatorsBox();
		buildMoneyDialogBox();
	
		iBuyPanel.add(wishTable);
		iBuyPanel.add(participatorsBoxVerticalPanel);
		
		    
	}
	
	
	private void buildMoneyDialogBox(){
		moneyDialogBox = new DialogBox();
		moneyDialogBox.setText("Enter a sum ");
		
	
		
		moneyVerticalPanel  = new VerticalPanel();
		
		enterSumTextBox = new TextBox();
		
		
		moneyHorizontalPanel = new HorizontalPanel();
		moneyHorizontalPanel.setSpacing(20);
		
		okMoneyButton = new Button();
		okMoneyButton.setText("ok");
		
		cancelMoneyButton = new Button();
		cancelMoneyButton.setText("cancel");
		
		moneyHorizontalPanel.add(okMoneyButton);
		moneyHorizontalPanel.add(cancelMoneyButton);
		
		errorMsgLabel = new Label();
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		
		moneyVerticalPanel.add(errorMsgLabel);
		moneyVerticalPanel.add(enterSumTextBox);
		moneyVerticalPanel.add(moneyHorizontalPanel);
		
		moneyDialogBox.add(moneyVerticalPanel);
		
		
	}
	
	private void loadMoneyDialog(WishlistItemNewData item){
		currentItem = item;
		moneyDialogBox.center();
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
	    
	    wishTable.getColumnFormatter().addStyleName(0, "tablesColumns");
		wishTable.getColumnFormatter().addStyleName(1, "tablesColumns");
		wishTable.getColumnFormatter().addStyleName(2, "tablesColumns");
		wishTable.getColumnFormatter().addStyleName(3, "tablesColumns");
		
		wishTable.getColumnFormatter().addStyleName(4, "lastColumns");
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
			participatorsTable.getRowFormatter().addStyleName(row, "tablesRows");
			row++;
		}
		
	}
	
	private void buildChatTable(){
		chatTable = new TableWithHeader();
		chatTable.setStyleName("tables");
		
		//header
		chatTable.setHeader(0, "From");
		chatTable.setHeader(1, "Time");
		chatTable.setHeader(2,"Message");
		
		
		
		
	}
	
	private void buildChat(){
        chatVerticalPanel = new VerticalPanel();
		
		chatTextArea = new TextArea();
	//	chatTextArea.setStyleName(constants.cwTextAreaStyle());
		
		chatTextArea.setVisibleLines(3);
		
		addMessageButton = new Button("send");
	//	addMessageButton.setStyleName(constants.cwButtonStyle());
		
		
		buildChatTable();
		
		chatVerticalPanel.add(chatTable);
		chatVerticalPanel.setCellHorizontalAlignment(chatTable,HasHorizontalAlignment.ALIGN_CENTER);
		
		chatVerticalPanel.add(chatTextArea);
		chatVerticalPanel.setCellHorizontalAlignment(chatTextArea, HasHorizontalAlignment.ALIGN_CENTER);
		
		chatVerticalPanel.add(addMessageButton);
		chatVerticalPanel.setCellHorizontalAlignment(addMessageButton,HasHorizontalAlignment.ALIGN_RIGHT );
	}
		
	
		
	
	
	private void buildParticipatorsBox(){
		participatorsBoxVerticalPanel = new VerticalPanel();
		participatorsBoxVerticalPanel.setVisible(false);
		
		closePartisipatorsBoxButton = new Button("return");
		
		buildParticipatorsTable();
		buildChat();
		
		participatorsBoxVerticalPanel.add(participatorsTable);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(participatorsTable,  HasHorizontalAlignment.ALIGN_CENTER);
		
		participatorsBoxVerticalPanel.add(chatVerticalPanel);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(chatVerticalPanel, HasHorizontalAlignment.ALIGN_CENTER );
		
		participatorsBoxVerticalPanel.add(closePartisipatorsBoxButton);
		participatorsBoxVerticalPanel.setCellHorizontalAlignment(closePartisipatorsBoxButton , HasHorizontalAlignment.ALIGN_RIGHT);
		
	//	participatorsBox.add(participatorsBoxVerticalPanel);

		
	}
	
	private void loadParticipatorsBox(WishlistItemNewData item){
		if(!item.getParticipators().isEmpty()){
	    	currentItem = item;
	//	    participatorsBox.show();
	    	wishTable.setVisible(false);
	    	participatorsBoxVerticalPanel.setVisible(true);
	    	
		    fillParticipatorsTable();
		   // fillChatMessages();
		}
	}
		
	
		
		
		
	
	
	  
	  
	  
	  public void gui_eventItemGridClicked(Cell cellClicked) {
	         int row = cellClicked.getRowIndex();
	         int col = cellClicked.getCellIndex();
	        
	         WishlistItemNewData item = this.itemsToBuy.get(row);
	         
	        
	        switch(col){
	        case NAME_LINK    : if(!item.getLink().equals(""))
       		                       Window.open(item.getLink(), "_blank", null);
	                            break;
	        case PRICE_LINK   : loadParticipatorsBox(item);
	                            break;
	        case UPDATE_LINK  : loadMoneyDialog(item);    
	                            break;
	        case CANCEL_LINK  : if(item.getParticipators().isEmpty())
	        	                   this.wishlistService.cancelBookItemForUser(item.getWishlistItemId(), entryPoint.userId);
	                            else
	                               this.wishlistService.deleteParticipator(item.getWishlistItemId(), entryPoint.userId);
	                            break;
	        case BUY_LINK     : //ASK ABOUT CONTACTS AND SHORT MESSAGE
                                break;
	        }
	        
	  }
	    

      
		public void service_getBookedWishlistSuccesfull(
				ArrayList<WishlistItemNewData> result) {
			this.itemsToBuy = result;
	        this.wishTable.clear();
	        
	       
	        
	        int row = 0;
	        
	        for (WishlistItemNewData item : result) {
	        	wishTable.setWidget(row,0,new Label(item.getUserName()+"'s "+item.getEventName()));
	        	//link
	        	if (item.getLink().equals(""))
	        		wishTable.setWidget(row, 1,new Label(item.getItemName()));
	        	else
	        		wishTable.setWidget(row, 1,new Hyperlink(item.getItemName(),null));
	        	//priority
	        	if(item.getPriority() == 5)
	        		wishTable.setWidget(row,2,new Label("high"));
	        	else
	    	        wishTable.setWidget(row,2,new Label("low"));
	    	    //it's only me
	    	    if(item.getParticipators().isEmpty()){
	    	    	wishTable.setWidget(row, 3,new Label(item.getPrice().toString()) );
	    	    	Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
				    cancelImage.setTitle("cancel reservation");
		    	    wishTable.setWidget(row, 5, cancelImage); 
		
	    	    }else{
	    	    	Integer sum = 0;
	    	    	for(ParticipatorData user : item.getParticipators()){
	    	    		sum+=user.getMoney();
	    	    	}
	    	        wishTable.setWidget(row, 3,new Hyperlink(sum+ " / " + item.getPrice().toString(), null) );
	    	        if(item.getIsActive()){
	    	        	Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
	    			    updateImage.setTitle("update ");
	    			    Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
	    			    cancelImage.setTitle("leave this group");
	    			    Image buyImage = new Image( GWT.getModuleBaseURL() + "present_16.png");
	    			    buyImage.setTitle("We'll buy");
	    	        	wishTable.setWidget(row, 4, updateImage);
	    	        	wishTable.setWidget(row, 5, cancelImage);
	    	        	wishTable.setWidget(row, 6, buyImage);
	    	        }
	    
	           
	        }
	    	    
	    	    wishTable.getRowFormatter().addStyleName(row, "tablesRows");

	    	    
	    	    row ++;
	        }
			    
		}
		
		
		public void gui_eventOkMoneyButtonClicked(){
			//is empty
			if(enterSumTextBox.equals("")){
				errorMsgLabel.setText("Enter the sum ");
	    		enterSumTextBox.setFocus(true);
	    		errorMsgLabel.setVisible(true);
	    		return;
			}
			//parse
			Integer sum = 0;
			try{
			   sum = Integer.parseInt(enterSumTextBox.getText());
			}catch(NumberFormatException ex){
				errorMsgLabel.setText("Enter valid sum ");
		        enterSumTextBox.setFocus(true);
		        errorMsgLabel.setVisible(true);
		    	return;
			}
			//valid sum
			if (sum > 0){
				moneyDialogBox.hide();
				enterSumTextBox.setText("");
				errorMsgLabel.setVisible(false);
			    ParticipatorData data = new ParticipatorData(entryPoint.userId,entryPoint.user.getFirstName(),entryPoint.user.getLastName(),sum);
			    this.wishlistService.updateParticipator(currentItem.getWishlistItemId(), data);
			}else{
				errorMsgLabel.setText("Enter valid sum ");
		        enterSumTextBox.setFocus(true);
		        errorMsgLabel.setVisible(true);
			}
		}
		
		public void gui_eventCancelMoneyButtonClicked(){
			moneyDialogBox.hide();
			enterSumTextBox.setText("");
			errorMsgLabel.setVisible(false);
		}
		
		public void gui_eventClosePartisipatorsBoxButtonClicked(){
	    	participatorsBoxVerticalPanel.setVisible(false);
	    	wishTable.setVisible(true);
			
		}
		
		
	

		
		
		
		public void wireIBuyGUIEvents(){
			this.wishTable.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					Cell cellForEvent=wishTable.getCellForEvent(event);
					gui_eventItemGridClicked(cellForEvent);
				}
			});
			
			this.okMoneyButton.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventOkMoneyButtonClicked();
				}
			});
			
			this.cancelMoneyButton.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					 gui_eventCancelMoneyButtonClicked();
				}
			});
			
			this.closePartisipatorsBoxButton.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventClosePartisipatorsBoxButtonClicked();
				}
			});
			
			this.enterSumTextBox.addKeyUpHandler(new KeyUpHandler(){
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
						gui_eventOkMoneyButtonClicked();
					}
			});
		}


		public void sevice_eventCancelBookItemForUserFailed(Throwable caught) {
			System.out.println(caught);
			
		}


		public void service_eventCancelBookItemForUserSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}


		public void service_eventDeleteParticipatorFailed(Throwable caught) {
			System.out.println(caught);
			
		}


		public void service_eventDeleteParticipatorSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}


		public void service_eventDeleteItemFromTabFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}


		public void service_eventDeleteItemFromTabSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}


		public void service_eventUpdateParticipatorFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}


		public void service_eventUpdateParticipatorSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}


		public void service_getBookedWishlistFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

	  
}
