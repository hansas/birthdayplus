package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.ChatMessageData;
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
	

	//horizontal panel that holds everything
	private HorizontalPanel chatBoxHorizontalPanel;
	
	//left side
	private VerticalPanel chatVerticalPanel;
	//chat table
	private FlexTable chatTable;
	private HorizontalPanel chatHorizontalPanel;
	// Add a text area
    TextBox chatTextArea ;
    //add new message to the chat
	private Button addMessageButton;
	
	//right side
	private VerticalPanel participatorsVerticalPanel;
	private Label participatorsLabel;
	private TableWithHeader participatorsTable;
	private Button closeChatButton;
	
	
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
		iBuyPanel.addStyleName("iBuyPanel");
	
		buildWishlistTable();
	    buildChat();
		
		buildMoneyDialogBox();
	
		iBuyPanel.add(wishTable);
		iBuyPanel.add(chatBoxHorizontalPanel);
		
		    
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
	
	
	private void buildChat(){
		chatBoxHorizontalPanel = new HorizontalPanel();
        chatBoxHorizontalPanel.setStylePrimaryName("chatPanel");

		chatBoxHorizontalPanel.setVisible(false);
        buildChatLeftSide();
    	buildChatRightSide();
    	
    	chatBoxHorizontalPanel.add(chatVerticalPanel);
    	chatBoxHorizontalPanel.add(participatorsVerticalPanel);
    	
    	chatBoxHorizontalPanel.setCellWidth(chatVerticalPanel, "80%");
    	chatBoxHorizontalPanel.setCellWidth(participatorsVerticalPanel,"20%");
		
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
	
	
	
	private void buildChatLeftSide(){
		    chatVerticalPanel = new VerticalPanel();
	        chatVerticalPanel.setStylePrimaryName("chatLeftSide");
		    
	        chatTable = new FlexTable();
	        chatTable.setStylePrimaryName("chatTable");
	        
	        chatTable.getColumnFormatter().setWidth(0, "70%");
	        chatTable.getColumnFormatter().setWidth(1, "30%");
	        
	        chatHorizontalPanel = new HorizontalPanel();
	        
	        
			chatTextArea = new TextBox();
			chatTextArea.setStylePrimaryName("chatTextBox");
			
			addMessageButton = new Button("send");
			
			chatHorizontalPanel.add(chatTextArea);
			chatHorizontalPanel.add(addMessageButton);
			
			chatHorizontalPanel.setCellWidth(chatTextArea, "70%");
			chatHorizontalPanel.setCellHorizontalAlignment(addMessageButton, HasHorizontalAlignment.ALIGN_RIGHT);
			
			chatVerticalPanel.add(chatTable);
			chatVerticalPanel.add(chatHorizontalPanel);
			chatVerticalPanel.setCellVerticalAlignment(chatHorizontalPanel, HasVerticalAlignment.ALIGN_BOTTOM);
			
			chatVerticalPanel.setCellHeight(chatTable, "90%");
			chatVerticalPanel.setCellHeight(chatHorizontalPanel, "10%");
		
	}
	
	
	private void buildChatRightSide(){
		participatorsVerticalPanel = new VerticalPanel();
		participatorsVerticalPanel.setStylePrimaryName("chatRightSide");
		
		participatorsLabel = new Label("Participators");
		participatorsLabel.setStylePrimaryName("participatorsLabel");
		
		buildParticipatorsTable();
		
		closeChatButton = new Button("return");
		
		participatorsVerticalPanel.add(participatorsLabel);
		participatorsVerticalPanel.setCellHorizontalAlignment(participatorsLabel, HasHorizontalAlignment.ALIGN_CENTER);
		
		participatorsVerticalPanel.add(participatorsTable);
		
		
		participatorsVerticalPanel.add(closeChatButton);
		participatorsVerticalPanel.setCellHorizontalAlignment(closeChatButton,HasHorizontalAlignment.ALIGN_CENTER );
		participatorsVerticalPanel.setCellVerticalAlignment(closeChatButton, HasVerticalAlignment.ALIGN_BOTTOM);
		
		participatorsVerticalPanel.setCellHeight(participatorsLabel, "10%");
		participatorsVerticalPanel.setCellHeight(participatorsTable,"80%");
		participatorsVerticalPanel.setCellHeight(closeChatButton, "10%");
	}
	
	
	
	private void buildParticipatorsTable(){
		participatorsTable = new TableWithHeader();
		participatorsTable.setStylePrimaryName("participatorsTable");
		
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
	
	private void fillChatMessages(){
		this.chatTable.clear();
		FlexCellFormatter cellFormatter = chatTable.getFlexCellFormatter();
		RowFormatter rowFormatter = chatTable.getRowFormatter();
        int row = 0;
        
        for (ChatMessageData message : currentItem.getChatMessages()) {
            chatTable.setText(row, 0, message.getUserName()+" "+ "says :");
            rowFormatter.setStylePrimaryName(row, "chatLineDate");
            
            DateTimeFormat dateFormatter = 	DateTimeFormat.getMediumDateTimeFormat();
            chatTable.setText(row, 1, dateFormatter.format(message.getTimeStamp()));
            
            chatTable.setText(row+1, 0, message.getMesssage());
            cellFormatter.setColSpan(row+1, 0, 2);
            cellFormatter.setHorizontalAlignment(row+1, 0, HasHorizontalAlignment.ALIGN_LEFT);
            row+=2;
        }

	}
	
	
	private void loadChat(WishlistItemNewData item){
		if(!item.getParticipators().isEmpty()){
	    	currentItem = item;
	//	    participatorsBox.show();
	    	wishTable.setVisible(false);
	    	chatBoxHorizontalPanel.setVisible(true);
	    	
		    fillParticipatorsTable();
		    fillChatMessages();
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
	        case PRICE_LINK   : loadChat(item);
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
		
		public void gui_eventCloseChatButtonClicked(){
			chatBoxHorizontalPanel.setVisible(false);
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
			
			this.closeChatButton.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventCloseChatButtonClicked();
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
