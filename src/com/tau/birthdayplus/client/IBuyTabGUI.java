package com.tau.birthdayplus.client;



import java.util.ArrayList;
import java.util.Date;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.Icons;
import com.tau.birthdayplus.client.widgets.MoneyDialogBox;
import com.tau.birthdayplus.client.widgets.ParticipatorList;
import com.tau.birthdayplus.client.widgets.StaticFunctions;
import com.tau.birthdayplus.client.widgets.TooltipHandler;

import com.tau.birthdayplus.client.widgets.EmailDialogBox.EmailDialogBox;
import com.tau.birthdayplus.client.widgets.EmailDialogBox.GroupEventHandler;
import com.tau.birthdayplus.client.widgets.EmailDialogBox.EmailDialogBox.Status;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;




public class IBuyTabGUI {
//////////////////Constants///////////////////////////
    private static String helpMessage = "Update the amount you're willing to spend on this present";
	private static 	DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("EEE, dd MMM , yyyy");
	private static Icons icons = (Icons) GWT.create(Icons.class);
	
	private static final int CHAT_LINK = 6;
	private static final int BUY_LINK = 5;
	private static final int CANCEL_LINK = 4;
	private static final int UPDATE_LINK = 3;

	//////////////////GUI Widgets////////////////////////
	private FlowPanel iBuyPanel;	
	
    //items
	private FlowPanelMenuTitle wishPanel;
	private  HoverTable wishTable;
	private EmailDialogBox emailDialogBox;
	
	private FlowPanelMenuTitle mainChatPanel;
	private ScrollPanel chatScrollPanel;
	private FlexTable chatTable;
    private TextBox chatTextArea ;
	private Button addMessageButton;
	
	
	private MenuBar participators;
	private Command emptyCommand;
	private MoneyDialogBox moneyDialogBox;
	
	
		
	//////////////////Model///////////////////////////////
	private ArrayList<WishlistItemNewData> itemsToBuy = null;
	private WishlistItemNewData currentItem;
	public IBuyDelegate wishlistService;
	public Birthdayplus entryPoint;
	private Status groupStatus ;

	
	
	/**
	 * build the Tab
	 */
	public void init() {
		iBuyPanel = new FlowPanel();
		entryPoint.tab.add(iBuyPanel, "I buy");
		iBuyPanel.addStyleName("mainPanel");
		
		wishPanel= new FlowPanelMenuTitle();
		iBuyPanel.add(wishPanel);
		wishPanel.addStyleName("Panel");
	
		buildWishlistTable();
		
		emailDialogBox = new EmailDialogBox(entryPoint.preferences.getCountry().getValue().getCurrency());
		
		mainChatPanel = new FlowPanelMenuTitle();
		iBuyPanel.add(mainChatPanel);
		mainChatPanel.setStyleName("Panel");
		
		mainChatPanel.setVisible(false);
		
		
		Command closeChatCommand = new Command(){
			public void execute() {
				gui_eventCloseChatButtonClicked();
		      }
		    };
		
		    mainChatPanel.addMenuItem("<img src='http://birthdayplus.googlecode.com/svn/trunk/src/com/tau/birthdayplus/public/left_16.png' alt='Return to IBuy tab' title= 'Return' />",true,closeChatCommand);
	
	    participators= new MenuBar(true);
	    emptyCommand = new Command(){
			public void execute() {	
			}	
	    };
	    
	    mainChatPanel.addMenuItem("Participators", participators);
	
		
	    buildChat();
		
	    moneyDialogBox = new MoneyDialogBox(helpMessage,entryPoint.preferences.getCountry().getValue().getCurrency());
	   
	 
	
		    
	}
	
	protected void showIBuyTab(){
		closeChat();
		if(itemsToBuy == null)
			this.wishlistService.getBookedWishlist(entryPoint.userId);
    	wishPanel.setVisible(true);
	}
	
	protected void makeDirtyIBuyItems(){
		this.itemsToBuy = null;
		this.wishTable.clear(true);
	    wishTable.resizeRows(0);
		
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
		FlexTable iBuyTableHeader = new FlexTable();
		wishPanel.add(iBuyTableHeader);
		iBuyTableHeader.addStyleName("TableHeader");
		iBuyTableHeader.setCellSpacing(0);
		
		iBuyTableHeader.getColumnFormatter().setWidth(0, StaticFunctions.getPercentWidthPixels(60, 80) +"px");
				
		iBuyTableHeader.setText(0, 0, "What");
		iBuyTableHeader.setText(0, 1, "Price");
		iBuyTableHeader.getFlexCellFormatter().setColSpan(0, 1, 4);
		
		
		ScrollPanel iBuyScrollPanel = new ScrollPanel();
		wishPanel.add(iBuyScrollPanel);
		iBuyScrollPanel.addStyleName("ShortScrollPanel");
		
	
		wishTable = new HoverTable(0,7);
		iBuyScrollPanel.add(wishTable);
		wishTable.addStyleName("Table");
		wishTable.setCellSpacing(0);
		
		wishTable.getColumnFormatter().setWidth(0,  StaticFunctions.getPercentWidthPixels(60, 80) +"px");
		wishTable.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(30, 80) +"px");
		wishTable.getColumnFormatter().setWidth(UPDATE_LINK, "20px");
		wishTable.getColumnFormatter().setWidth(CANCEL_LINK, "20px");
		wishTable.getColumnFormatter().setWidth(BUY_LINK, "20px");
		wishTable.getColumnFormatter().setWidth(CHAT_LINK, "20px");
	    
	 
	}
	
	
	
	private void buildChat(){
		chatScrollPanel = new ScrollPanel();
		mainChatPanel.add(chatScrollPanel);
		chatScrollPanel.addStyleName("ShortScrollPanel");
		chatScrollPanel.addStyleName("chatPanel");
		
	    chatTable = new FlexTable();
        chatScrollPanel.add(chatTable);
        chatTable.addStyleName("Table");
        chatTable.setCellSpacing(0);
        
        
        HorizontalPanel messagePanel = new HorizontalPanel();
        mainChatPanel.add(messagePanel);
        messagePanel.addStyleName("messagePanel");
	        
	    chatTextArea = new TextBox();
	    messagePanel.add(chatTextArea);
	    messagePanel.setCellWidth(chatTextArea, "80%");
	    chatTextArea.setMaxLength(60);
	    chatTextArea.addStyleName("chatTextArea");
			
	    addMessageButton = new Button("Send");
	    messagePanel.add(addMessageButton);
	    messagePanel.setCellWidth(addMessageButton,"20%");
	    addMessageButton.addStyleName("sendButton");
		
	}
	
	
	
	public static void removeAllRows(FlexTable table) {
	    int numRows = table.getRowCount();
	    for (int i = 0; i < numRows; i++) {
	      table.removeRow(0);
	    }
	  }

	private void fillParticipatorsTable(){
		participators.clearItems();
		
		for (ParticipatorData user : currentItem.getParticipators()){
			participators.addItem(user.getUserFirstName()+" "+user.getUserLastName(),emptyCommand);

		}
		
	}
	
	
	
	private void fillChatMessages(ArrayList<ChatMessageData> result){
		removeAllRows(chatTable);
		FlexCellFormatter cellFormatter = chatTable.getFlexCellFormatter();
		RowFormatter rowFormatter = chatTable.getRowFormatter();
        int row = 0;
        
        for (ChatMessageData message : result) {
            chatTable.setText(row, 0, message.getUserName()+" "+ "says:");
            rowFormatter.setStylePrimaryName(row, "chatLineDate");
            
            DateTimeFormat dateFormatter = 	DateTimeFormat.getMediumDateTimeFormat();
            chatTable.setText(row, 1, dateFormatter.format(message.getTimeStamp()));
            
            chatTable.setText(row+1, 0, message.getMesssage());
            cellFormatter.setColSpan(row+1, 0, 2);
            cellFormatter.setHorizontalAlignment(row+1, 0, HasHorizontalAlignment.ALIGN_LEFT);
            row+=2;
        }
        
        chatScrollPanel.scrollToBottom();
        

	}
	
	
	private void loadChat(WishlistItemNewData item){
	    	currentItem = item;
	    	mainChatPanel.setTitle("Group chat for buying a "+item.getItemName()+" for "+item.getUserName());
	    	entryPoint.messages.hide();
	    	wishPanel.setVisible(false);
	    	mainChatPanel.setVisible(true);
	    	
		    fillParticipatorsTable();
		    this.wishlistService.getChatMessages(item.getWishlistItemId());
		
	}
	
	
	private void showTextArea(WishlistItemNewData item){
			currentItem = item;
			emailDialogBox.show(groupStatus, item.getParticipators());
			
	}
		 
	  
	  public void gui_eventItemGridClicked(Cell cellClicked) {
	         int row = cellClicked.getRowIndex();
	         int col = cellClicked.getCellIndex();
	        
	         WishlistItemNewData item = this.itemsToBuy.get(row);
	           
	        
	        switch(col){
	        case UPDATE_LINK  : if(item.getIsActive()){
	        	                   moneyDialogBox.setText("Update the amount", "Update");
	        	                   loadMoneyDialog(item);
	                            }
	                            break;
	                            
	        case CANCEL_LINK  : if(item.getParticipators().isEmpty())
	        	                   this.wishlistService.cancelBookItemForUser(item.getWishlistItemId(), entryPoint.userId);
	                            else 
	                            {  if(item.getIsActive())
	                                 this.wishlistService.deleteParticipator(item.getWishlistItemId(), entryPoint.userId);
	                               else
	                               { if(item.getBuyer().getUserId().equals(entryPoint.userId)){
	                            	   groupStatus = Status.REOPEN_GROUP;
	                            	   showTextArea(item);
	                               }
	                               }
	                            }
	                            break;
	                            
	        case BUY_LINK     : if(item.getIsActive()){
	        	                   groupStatus = Status.CLOSE_GROUP;
	        	                   showTextArea(item);
	                             }
                                break;
                                
	        case CHAT_LINK     : if(!item.getParticipators().isEmpty())
	        	                    loadChat(item);
	                            break;
	                              
	        }
	        
	  }
	    

      
		@SuppressWarnings("deprecation")
		public void service_getBookedWishlistSuccesfull(ArrayList<WishlistItemNewData> result) {
			this.itemsToBuy = result;
	        this.wishTable.clear(true);
	        this.wishTable.resizeRows(result.size());
	        
	        Double countMoney = 0.0;
	        Date today = new Date();
	        int row = 0;
	        
	        for (WishlistItemNewData item : result) {
	        	String what = item.getItemName()+" for "+ item.getUserName()+"'s "+item.getEventName();
	            Boolean isCurrentMonthEvent = (item.getEventDate().getYear() == today.getYear()) && (item.getEventDate().getMonth() == today.getMonth());

	    
	            Widget itemName = StaticFunctions.getAnchorWithThumbnail(item.getLink(), what, item.getThumbnail());
	            itemName.setTitle(dateFormatter.format(item.getEventDate()));
        		wishTable.setWidget(row, 0,itemName);
        		
	    	        //it's only me
	    	    if(item.getParticipators().isEmpty())
	    	    {
	    	    	if(isCurrentMonthEvent)
	    	    		countMoney+=item.getPrice();
	    	    	
	    	    	//price
	    	    	wishTable.setWidget(row, 1,new Label(entryPoint.shortMoneyFormat.format(item.getPrice()))) ;
	    	    	//icon
		    	    wishTable.setWidget(row, CANCEL_LINK, StaticFunctions.createIcon(icons.cancelIcon(), "cancel reservation")); 
		
	    	    }else
	    	    	//group present 
	    	    {
	    	    	ParticipatorList list = StaticFunctions.getParticipatorsList(item.getParticipators(), item.getEventDate(), entryPoint.userId,entryPoint.shortMoneyFormat);
	    	    	if(isCurrentMonthEvent)
	    	    	   countMoney+=list.getUserPart();
	    	    	
	    	    	//price
	    	    	Label priceLabel = new Label(entryPoint.shortMoneyFormat.format(list.getTotalAmount())+" / "+entryPoint.shortMoneyFormat.format(item.getPrice()));
	    	        wishTable.setWidget(row, 1,priceLabel );     
	    	        //participators
	    	        TooltipHandler handler  = new TooltipHandler(list.getHtmlList(), 10000 ,false);
	          	    priceLabel.addMouseOverHandler(handler);
	          	    priceLabel.addMouseOutHandler(handler);
	          	    
	          	    //icons
	    	        if(item.getIsActive()){
	    	        	wishTable.setWidget(row, UPDATE_LINK, StaticFunctions.createIcon(icons.updateIcon(), "update my amount"));
	    	        	wishTable.setWidget(row, CANCEL_LINK, StaticFunctions.createIcon(icons.cancelIcon(), "leave this group"));
	    	        	wishTable.setWidget(row, BUY_LINK, StaticFunctions.createIcon(icons.presentIcon(), "There is enough money to buy the present. \n I'll buy a present on behalf of the group."));
	    	        }else{
	    	        	if(item.getBuyer().getUserId().equals(entryPoint.userId))
	    			       wishTable.setWidget(row, CANCEL_LINK, StaticFunctions.createIcon(icons.reloadIcon(), "reopen this group"));
	    	        }
	    	        wishTable.setWidget(row, CHAT_LINK, StaticFunctions.createIcon(icons.chatIcon(), "chat"));

	    	    }
	    	    row ++;
	        }
	        wishPanel.setTitle("In this month you're going to spend "+entryPoint.shortMoneyFormat.format(countMoney));
			    
		}
		
		private void closeChat(){
			if(mainChatPanel.isVisible()){
				mainChatPanel.setVisible(false);
				removeAllRows(chatTable);
			}
		}
		
		private void gui_eventCloseChatButtonClicked(){
			showIBuyTab();
		}
		
		
		
		
		private void gui_eventAddMessageButtonClicked(){
			if(chatTextArea.getText().equals("")){
				chatTextArea.setText("please, type a message");
				chatTextArea.setSelectionRange(0,22 );
				chatTextArea.setFocus(true);
				return;
			}
			
			if(chatTextArea.getText().equals("please, type a message")){
                chatTextArea.setFocus(true);
				return;
			}			
			ChatMessageData message = new ChatMessageData(entryPoint.userId,entryPoint.firstName+" "+entryPoint.lastName,chatTextArea.getText());
			this.wishlistService.addChatMessage(currentItem.getWishlistItemId(),message);
		}
		
		
		protected void wireIBuyGUIEvents(){
			this.wishTable.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					Cell cellForEvent=wishTable.getCellForEvent(event);
					if(cellForEvent == null)
						return;
					gui_eventItemGridClicked(cellForEvent);
				}
			});
			
			
			this.moneyDialogBox.addCloseHandler(new CloseHandler<PopupPanel>(){

				public void onClose(CloseEvent<PopupPanel> event) {
					if(event.isAutoClosed())
						return;
					Integer sum = moneyDialogBox.getInput();
					if (sum == null)
						return;
					else
					{
						  ParticipatorData data = new ParticipatorData(entryPoint.userId,entryPoint.user.getFirstName(),entryPoint.user.getLastName(),sum);
						  wishlistService.updateParticipator(currentItem.getWishlistItemId(), data);
						
					}
					
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
			
			
			this.emailDialogBox.addGroupEvent(new GroupEventHandler(){

				public void onCloseGroup(Double actualPrice, String htmlMessage) {
					   wishlistService.bookItemForGroup(currentItem.getWishlistItemId(), entryPoint.userId,htmlMessage,actualPrice);
				}

				public void onReopenGroup(String htmlMessage) {
		         	   wishlistService.cancelBookItemForGroup(currentItem.getWishlistItemId(), entryPoint.userId,htmlMessage);
				}
			});
		}


		public void sevice_eventCancelBookItemForUserFailed(Throwable caught) {
			entryPoint.messages.setText("CancelBookItemForUserFailed"+caught.getMessage());
		}
		
		public void service_eventCancelBookItemForUserSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);	
		}

		public void service_eventDeleteParticipatorFailed(Throwable caught) {
			entryPoint.messages.setText("DeleteParticipatorFailed"+caught.getMessage());
		}

		public void service_eventDeleteParticipatorSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);			
		}

		public void service_eventDeleteItemFromTabFailed(Throwable caught) {
			entryPoint.messages.setText("DeleteItemFromTabFailed"+caught.getMessage());
		}

		public void service_eventDeleteItemFromTabSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);			
		}

		public void service_eventUpdateParticipatorFailed(Throwable caught) {
			entryPoint.messages.setText("UpdateParticipatorFailed"+caught.getMessage());
		}

		public void service_eventUpdateParticipatorSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);	
		}

		public void service_getBookedWishlistFailed(Throwable caught) {
			this.wishTable.clear(true);
		    wishTable.resizeRows(0);
			entryPoint.messages.setText("BookedWishlistFailed"+caught.getMessage());			
		}

		public void service_addChatMessageFailed(Throwable caught) {
			entryPoint.messages.setText("ChatMessageFailed"+caught.getMessage());
		}

		public void service_addChatMessageSuccesfull() {
			this.chatTextArea.setText("");
			this.wishlistService.getChatMessages(currentItem.getWishlistItemId());
		}


		public void service_cancelBookItemForGroupFailed(Throwable caught) {
			entryPoint.messages.setText("BookItemForGroupFailed"+caught.getMessage());
		}

		public void service_cancelBookItemForGroupSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
		}

		public void service_bookItemForGroupFailed(Throwable caught) {
			entryPoint.messages.setText("ItemForGroupFailed"+caught.getMessage());
		}

		public void service_bookItemForGroupSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}

		public void service_getChatMessagesFailed(Throwable caught) {
			removeAllRows(chatTable);
			entryPoint.messages.setText("getChatMessagesFailed"+caught.getMessage());
			
		}

		public void service_getChatMessagesSuccesfull(ArrayList<ChatMessageData> result) {
			fillChatMessages(result);
		}

}
