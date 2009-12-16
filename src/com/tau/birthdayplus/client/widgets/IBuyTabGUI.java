package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;
import java.util.Date;

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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
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
	private FlowPanel iBuyPanel;
	
	private FlowPanel wishPanel;
	private  ScrollPanel iBuyScrollPanel;
	private  FlexTable iBuyTableHeader;
	private  FlexTable wishTable;
	

	private HorizontalPanel chatPanel;
	
	private FlowPanel leftSide;
	private Label chatLabel;
	private ScrollPanel chatScrollPanel;
	private FlexTable chatTable;
    private TextBox chatTextArea ;
	private Button addMessageButton;
	
	private FlowPanel rightSide;
	private Label participatorsLabel;
	private ScrollPanel participatorsScrollPanel;
	private FlexTable participatorsTableHeader;
	private FlexTable participatorsTable;
	private Button closeChatButton;
	
	
	private DialogBox moneyDialogBox;
	private VerticalPanel moneyVerticalPanel;
	private Label errorMsgLabel ;
	private TextBox   enterSumTextBox;
	private HorizontalPanel moneyHorizontalPanel;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	
	
	//////////////////Model///////////////////////////////
	public ArrayList<WishlistItemNewData> itemsToBuy=null;
	WishlistItemNewData currentItem;
	public IBuyDelegate wishlistService;
	public Birthdayplus entryPoint;
	

	

	
	/**
	 * build the Tab
	 */
	public void init() {
		iBuyPanel = new FlowPanel();
		entryPoint.tab.add(iBuyPanel, "I buy");
	//	iBuyPanel.setSize("100%", "350px");
		iBuyPanel.addStyleName("iBuyPanel");
		
		wishPanel= new FlowPanel();
		iBuyPanel.add(wishPanel);
		wishPanel.addStyleName("iBuyWishlistPanel");
	//	wishPanel.setSize("100%", "350px");
	
		buildWishlistTable();
		
		chatPanel = new HorizontalPanel();
		iBuyPanel.add(chatPanel);
		chatPanel.addStyleName("chatPanel");
	//	chatPanel.setSize("100%", "350px");
		chatPanel.setVisible(false);
		
	    buildChat();
		
		buildMoneyDialogBox();
	
	
		
		    
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
		leftSide = new FlowPanel();
		chatPanel.add(leftSide);
		chatPanel.setCellWidth(leftSide,"70%");
	//	leftSide.setSize("100%", "350px");
		leftSide.addStyleName("chatLeftSidePanel");
		
		rightSide = new FlowPanel();
		chatPanel.add(rightSide);
		chatPanel.setCellWidth(rightSide, "30%");
	//	rightSide.setSize("100%", "350px");
		rightSide.addStyleName("chatRightSidePanel");
		
		
		buildChatLeftSide();
    	buildChatRightSide();
    	
    //	chatBoxHorizontalPanel.add(chatVerticalPanel);
    //	chatBoxHorizontalPanel.add(participatorsVerticalPanel);
    	
    //	chatBoxHorizontalPanel.setCellWidth(chatVerticalPanel, "80%");
    //	chatBoxHorizontalPanel.setCellWidth(participatorsVerticalPanel,"20%");
		
	}
	
	
	
	
	/*
	 * create flex table for wishlist items
	 */
	private void buildWishlistTable(){
		iBuyTableHeader = new FlexTable();
		wishPanel.add(iBuyTableHeader);
		iBuyTableHeader.addStyleName("iBuyTableHeader");
	//	iBuyTableHeader.setSize("100%", "25px");
		
		
		iBuyTableHeader.getColumnFormatter().setWidth(0, "80px");
		iBuyTableHeader.getColumnFormatter().setWidth(1, "70px");
		iBuyTableHeader.getColumnFormatter().setWidth(2, "50px");
		iBuyTableHeader.getColumnFormatter().setWidth(3, "40px");
				
		iBuyTableHeader.setText(0, 0, "Event");
		iBuyTableHeader.setText(0,1, "Item");
		iBuyTableHeader.setText(0, 2, "Priority");
		iBuyTableHeader.setText(0, 3, "Price");
		iBuyTableHeader.getFlexCellFormatter().setColSpan(0, 3, 4);
		
		
		iBuyScrollPanel = new ScrollPanel();
		wishPanel.add(iBuyScrollPanel);
		iBuyScrollPanel.addStyleName("iBuyScrollPanel");
	//	iBuyScrollPanel.setSize("100%", "300px");
	
		wishTable = new FlexTable();
		iBuyScrollPanel.add(wishTable);
		wishTable.addStyleName("iBuyWishTable");
	//	wishTable.setWidth("100%");
		
		wishTable.getColumnFormatter().setWidth(0, "80px");
		wishTable.getColumnFormatter().setWidth(1, "70px");
		wishTable.getColumnFormatter().setWidth(2, "50px");
	    
	   // wishTable.getColumnFormatter().addStyleName(0, "tablesColumns");
	//	wishTable.getColumnFormatter().addStyleName(1, "tablesColumns");
	//	wishTable.getColumnFormatter().addStyleName(2, "tablesColumns");
	//	wishTable.getColumnFormatter().addStyleName(3, "tablesColumns");
		
	//	wishTable.getColumnFormatter().addStyleName(4, "lastColumns");
	//	wishTable.getColumnFormatter().addStyleName(5, "lastColumns");
	//	wishTable.getColumnFormatter().addStyleName(6, "lastColumns");
	}
	
	
	
	private void buildChatLeftSide(){
		chatLabel = new Label();
		leftSide.add(chatLabel);
		chatLabel.addStyleName("chatLabel");
	//	chatLabel.setSize("100%", "25px");
		chatScrollPanel = new ScrollPanel();
		leftSide.add(chatScrollPanel);
		chatScrollPanel.addStyleName("chatScrollPanel");
	//	chatScrollPanel.setSize("100%", "300px");
		
		    
	    chatTable = new FlexTable();
        chatScrollPanel.add(chatTable);
        chatTable.addStyleName("chatTable");
     //   chatTable.setWidth("100%");
      //  chatTable.getColumnFormatter().setWidth(0, "50%");
      //  chatTable.getColumnFormatter().setWidth(1, "50%");
	        
	    chatTextArea = new TextBox();
	    leftSide.add(chatTextArea);
	    chatTextArea.addStyleName("chatTextArea");
	   // chatTextArea.setSize("70%", "25px");
	    chatTextArea.setMaxLength(25);
			
	    addMessageButton = new Button("send");
	    leftSide.add(addMessageButton);
	    addMessageButton.addStyleName("sendButton");
	  //  addMessageButton.setSize("25%", "25px");
		
	}
	
	
	private void buildChatRightSide(){
		participatorsLabel = new Label("Participators");
		rightSide.add(participatorsLabel);
		participatorsLabel.addStyleName("participatorsLabel");
//		participatorsLabel.setSize("100%", "25px");
		
		buildParticipatorsTable();
		
		closeChatButton = new Button("return");
	    rightSide.add(closeChatButton);
	    closeChatButton.addStyleName("closeChatButton");
//	    closeChatButton.setSize("100%", "25px");
	}
	
	
	
	private void buildParticipatorsTable(){
		participatorsTableHeader = new FlexTable();
		rightSide.add(participatorsTableHeader);
		participatorsTableHeader.addStyleName("chatParticipatorsTableHeader");
	//	participatorsTableHeader.setSize("100%", "25px");
		
		participatorsTableHeader.getColumnFormatter().setWidth(0, "50px");		
		participatorsTableHeader.setText(0, 0, "Name");
		participatorsTableHeader.setText(0,1, "Sum");
		
		
		participatorsScrollPanel = new ScrollPanel();
		rightSide.add(participatorsScrollPanel);
		participatorsScrollPanel.setStyleName("participatorsScrollPanel");
	//	participatorsScrollPanel.setSize("100%", "275px");
		
		
		participatorsTable = new FlexTable();
		participatorsScrollPanel.add(participatorsTable);
		participatorsTable.addStyleName("chatParticipatorsTable");
	//	participatorsTable.setWidth("100%");
		participatorsTable.getColumnFormatter().setWidth(0, "50px");
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
	
	    	wishPanel.setVisible(false);
	    	chatPanel.setVisible(true);
	    	
		    fillParticipatorsTable();
		//    Window.alert("loading chat, number of messages is : "+currentItem.getChatMessages().size());
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
	                            {  if(item.getIsActive())
	                                 this.wishlistService.deleteParticipator(item.getWishlistItemId(), entryPoint.userId);
	                               else
	                               { if(item.getBuyer().getUserId().equals(entryPoint.userId))
	                            	   this.wishlistService.cancelBookItemForGroup(item.getWishlistItemId(), entryPoint.userId);
	                               }
	                            }
	                            break;
	        case BUY_LINK     : //ASK ABOUT CONTACTS AND SHORT MESSAGE
	        	                if(item.getIsActive())
	        	                   this.wishlistService.bookItemForGroup(item.getWishlistItemId(), entryPoint.userId,"",false);
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
	        	if(item.getPriority() )
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
	    	        }else{
	    	        	if(item.getBuyer().getUserId().equals(entryPoint.userId)){
	    	        	   Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
	    			       cancelImage.setTitle("reopen this group");
	    			       wishTable.setWidget(row, 5, cancelImage);
	    	        	}
	    	        	
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
			chatPanel.setVisible(false);
	    	wishPanel.setVisible(true);
			
		}
		
		private void gui_eventAddMessageButtonClicked(){
			if(chatTextArea.equals(""))
				return;
			ChatMessageData message = new ChatMessageData(entryPoint.userId,entryPoint.firstName+" "+entryPoint.lastName,chatTextArea.getText());
			this.wishlistService.addChatMessage(currentItem.getWishlistItemId(),message);
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
			
			this.addMessageButton.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventAddMessageButtonClicked();
				}
				
			});
			
			this.chatTextArea.addKeyUpHandler(new KeyUpHandler(){

				public void onKeyUp(KeyUpEvent event) {
					if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
					   gui_eventAddMessageButtonClicked();
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




		public void service_addChatMessageFailed(Throwable caught) {
			
			
		}




		public void service_addChatMessageSuccesfull() {
			this.chatTextArea.setText("");
			this.wishlistService.getWishlistItem(currentItem.getWishlistItemId());
			
		}




		public void service_getWishlistItemFailed(Throwable caught) {
			
			
		}




		public void service_getWishlistItemSuccesfull(WishlistItemNewData result) {
			this.loadChat(result);
			
		}




		public void service_cancelBookItemForGroupFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}




		public void service_cancelBookItemForGroupSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
		}




		public void service_bookItemForGroupFailed(Throwable caught) {
			// TODO Auto-generated method stub
			
		}




		public void service_bookItemForGroupSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}

	  
}
