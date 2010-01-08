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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;

import com.google.gwt.user.client.ui.ScrollPanel;

import com.google.gwt.user.client.ui.TextBox;


import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;

import com.tau.birthdayplus.client.widgets.MessagePanel;
import com.tau.birthdayplus.client.widgets.MoneyDialogBox;
import com.tau.birthdayplus.client.widgets.TooltipListener;
import com.tau.birthdayplus.client.widgets.UnorderedList;
import com.tau.birthdayplus.client.widgets.RichTextToolbar.RichTextToolbar;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.ParticipatorData;

import com.tau.birthdayplus.dto.client.WishlistItemNewData;




public class IBuyTabGUI {
//////////////////Constants///////////////////////////
	private static  NumberFormat shortMoneyFormat = NumberFormat.getFormat("\u20AA#,##0");
	private static 	DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("EEE, dd MMM , yyyy");
	private static final int CHAT_LINK = 6;
	private static final int BUY_LINK = 5;
	private static final int CANCEL_LINK = 4;
	private static final int UPDATE_LINK = 3;

	//////////////////GUI Widgets////////////////////////
	private FlowPanel iBuyPanel;	
	
	private  MessagePanel messages; 
    //items
	private FlowPanelMenuTitle wishPanel;
	private  ScrollPanel iBuyScrollPanel;
	private  FlexTable iBuyTableHeader;
	private  HoverTable wishTable;
	//text area for message to group
	private  RichTextArea emailTextArea;
	private  RichTextToolbar emailTextToolbar;
	private  FlexTable emailGrid;
	private  DialogBox emailPanel;
	private  Anchor sendEmail;
	private  Anchor cancelEmail;
	private  TextBox actualPrice;
	
	private FlowPanelMenuTitle mainChatPanel;
	
	private ScrollPanel chatScrollPanel;
	private FlexTable chatTable;
	private HorizontalPanel messagePanel;
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
	private boolean closeGroup;
	
	
	/**
	 * build the Tab
	 */
	public void init() {
		iBuyPanel = new FlowPanel();
		entryPoint.tab.add(iBuyPanel, "I buy");
	//	iBuyPanel.setSize("100%", "350px");
		iBuyPanel.addStyleName("mainPanel");
		
		wishPanel= new FlowPanelMenuTitle();
		iBuyPanel.add(wishPanel);
		wishPanel.addStyleName("Panel");
	
		buildWishlistTable();
		
		emailPanel = new DialogBox(false,true); 
		emailTextArea = new RichTextArea();
	    emailTextArea.setSize("290px", "9em");
	    emailTextToolbar = new RichTextToolbar(emailTextArea);
	    emailTextToolbar.setWidth("290px");
	    sendEmail = new Anchor("send this message");
	    cancelEmail = new Anchor("cancel");
	    actualPrice = new TextBox();
	    actualPrice.setTabIndex(0);
	    emailTextArea.setTabIndex(1);
	    
	    actualPrice.setVisibleLength(20);
	    actualPrice.setMaxLength(9);
	    
	    
	    // Add the components to a panel
	    emailGrid = new FlexTable();
	    emailPanel.add(emailGrid);
	    emailGrid.addStyleName("emailGrid");
	    emailGrid.setWidth("290px");
	    emailGrid.setCellSpacing(0);
	    
	    emailGrid.setHTML(0, 0, "<FONT color=red >You are responsible for buying this present.</FONT><br /> Please enter the actual price for the item and a short message for the group.We will send an email to the group.<br /><FONT color=red >You can reopen this group by clicking on the reopen button in IBuy tab.</FONT >");
	    emailGrid.getFlexCellFormatter().setColSpan(0, 0, 2);
	    emailGrid.setHTML(1, 0, "<STRONG> Actual price in "+'\u20AA'+ ":</STRONG>");
	    emailGrid.setWidget(1, 1,actualPrice);
	    emailGrid.setWidget(2, 0, emailTextToolbar);
	    emailGrid.getFlexCellFormatter().setColSpan(2, 0, 2);
	    emailGrid.setWidget(3, 0, emailTextArea);
	    emailGrid.getFlexCellFormatter().setColSpan(3, 0, 2);
	    emailGrid.setWidget(4, 0, sendEmail);
	    emailGrid.setWidget(4, 1, cancelEmail);
	    
	    emailGrid.getRowFormatter().addStyleName(1, "emailActualPrice");
	    emailGrid.getRowFormatter().addStyleName(3, "emailTextArea");
	    emailGrid.getFlexCellFormatter().setHorizontalAlignment(4, 1,HasHorizontalAlignment.ALIGN_RIGHT );

	    
	   
		
		mainChatPanel = new FlowPanelMenuTitle();
		iBuyPanel.add(mainChatPanel);
		mainChatPanel.setStyleName("Panel");
		mainChatPanel.addStyleName("chatPanel");
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
		
	    moneyDialogBox = new MoneyDialogBox("Update the amount you're willing to spend on this present");
	    messages = new MessagePanel(25000,false);
	 
	
		    
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
		iBuyTableHeader = new FlexTable();
		wishPanel.add(iBuyTableHeader);
		iBuyTableHeader.addStyleName("TableHeader");
		iBuyTableHeader.setCellSpacing(0);
	//	iBuyTableHeader.setSize("100%", "25px");
		
		
		iBuyTableHeader.getColumnFormatter().setWidth(0, "150px");
				
		iBuyTableHeader.setText(0, 0, "What");
		iBuyTableHeader.setText(0, 1, "Price");
		iBuyTableHeader.getFlexCellFormatter().setColSpan(0, 1, 4);
		
		
		iBuyScrollPanel = new ScrollPanel();
		wishPanel.add(iBuyScrollPanel);
		iBuyScrollPanel.addStyleName("ShortScrollPanel");
	//	iBuyScrollPanel.setSize("100%", "300px");
	
		wishTable = new HoverTable(0,7);
		iBuyScrollPanel.add(wishTable);
		wishTable.addStyleName("Table");
		wishTable.setCellSpacing(0);
	//	wishTable.setWidth("100%");
		
		wishTable.getColumnFormatter().setWidth(0, "150px");
		wishTable.getColumnFormatter().setWidth(1, "80px");
		wishTable.getColumnFormatter().setWidth(UPDATE_LINK, "20px");
		wishTable.getColumnFormatter().setWidth(CANCEL_LINK, "20px");
		wishTable.getColumnFormatter().setWidth(BUY_LINK, "20px");
		wishTable.getColumnFormatter().setWidth(CHAT_LINK, "20px");


//		wishTable.getColumnFormatter().setWidth(2, "50px");
	    
	 
	}
	
	
	
	private void buildChat(){
		chatScrollPanel = new ScrollPanel();
	//	leftSide.add(chatScrollPanel);
		mainChatPanel.add(chatScrollPanel);
		chatScrollPanel.addStyleName("ShortScrollPanel");
	//	chatScrollPanel.setSize("100%", "300px");
		
	    chatTable = new FlexTable();
        chatScrollPanel.add(chatTable);
        chatTable.addStyleName("Table");
        chatTable.setCellSpacing(0);
        
        
        messagePanel = new HorizontalPanel();
        mainChatPanel.add(messagePanel);
        messagePanel.addStyleName("messagePanel");
	        
	    chatTextArea = new TextBox();
	    messagePanel.add(chatTextArea);
	    messagePanel.setCellWidth(chatTextArea, "80%");
	    chatTextArea.setMaxLength(25);
	    chatTextArea.setWidth("100%");
	    chatTextArea.addStyleName("chatTextArea");
			
	    addMessageButton = new Button("Send");
	    messagePanel.add(addMessageButton);
	    messagePanel.setCellWidth(addMessageButton,"20%");
	    addMessageButton.setWidth("100%");
		
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
	
	
	
	private void fillChatMessages(){
		removeAllRows(chatTable);
		FlexCellFormatter cellFormatter = chatTable.getFlexCellFormatter();
		RowFormatter rowFormatter = chatTable.getRowFormatter();
        int row = 0;
        
        for (ChatMessageData message : currentItem.getChatMessages()) {
            chatTable.setText(row, 0, message.getUserName()+" "+ "says:");
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
	    	currentItem = item;
	    	mainChatPanel.setTitle("Group chat for buying a "+item.getItemName()+" for "+item.getUserName());
	    	wishPanel.setVisible(false);
	    	mainChatPanel.setVisible(true);
	    	
		    fillParticipatorsTable();
		    fillChatMessages();
		
	}
	
	
	private void showTextArea(WishlistItemNewData item){
			currentItem = item;
			
			emailGrid.clearCell(0, 0);
			
			if(closeGroup){
				emailPanel.setText("Buy a present for the group");
			    emailGrid.setHTML(0, 0, "<FONT color=red >You are responsible for buying this present.</FONT><br /> Please enter the actual price for the item and a short message for the group.We will send an email to the group.<br /><FONT color=red >You can reopen this group by clicking on the reopen button in IBuy tab.</FONT >");
			    emailGrid.getRowFormatter().setVisible(1, true);
				actualPrice.setFocus(true);
			}else{
				emailPanel.setText("Reopen this group");
			    emailGrid.setHTML(0, 0," Please enter a short message for the group about reopening .We will send an email to the group.");
			    emailGrid.getRowFormatter().setVisible(1, false);
			    emailTextArea.setFocus(true);
	    	    
			}
		
			emailPanel.center();
	        emailPanel.show();
	    	
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
	                            	   closeGroup = false;
	                            	   showTextArea(item);
	                               }
	                               }
	                            }
	                            break;
	                            
	        case BUY_LINK     : if(item.getIsActive()){
	        	                   closeGroup = true;
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
	        wishTable.resizeRows(result.size());
	        Double countMoney = 0.0;
	        Date today = new Date();

	        int row = 0;
	        
	        for (WishlistItemNewData item : result) {
	        	String what = item.getItemName()+" for "+ item.getUserName()+"'s "+item.getEventName();
	        
	        
	        	if ((item.getLink()==null) || (item.getLink().equals("")) ){
	        		Label link = new Label(what);
	        		link.setTitle(dateFormatter.format(item.getEventDate()));
	        		wishTable.setWidget(row, 0,link);
	        	}
	        	else{
	        		Anchor link = new Anchor(what,item.getLink(),"_blank");
	        		link.setTitle(dateFormatter.format(item.getEventDate()));
	        		wishTable.setWidget(row, 0,link);
	        	}
	        
	    	    //it's only me
	    	    if(item.getParticipators().isEmpty()){
	    	    	wishTable.setWidget(row, 1,new Label(shortMoneyFormat.format(item.getPrice()))) ;
	    	    	if((item.getEventDate().getYear()==today.getYear()) && (item.getEventDate().getMonth() == today.getMonth()))
	    	    		countMoney+=item.getPrice();
	    	    	Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
				    cancelImage.setTitle("cancel reservation");
		    	    wishTable.setWidget(row, CANCEL_LINK, cancelImage); 
		
	    	    }else{
	    	    	Integer sum = 0;
	    			 String html =" <div style='background-color:#FFFFCC;border:1px solid #FFCC35;'><p style ='color:#224499;font-weight:bold;'>Participators are :<p><UL style='list-style-type: square;padding:1px 10px 1px 10px !important;margin:0px !important; list-style-position:inside;'>";
	    	    	for(ParticipatorData user : item.getParticipators()){
	    	    		sum+=user.getMoney();
	    	    		if(user.getUserId().equals(entryPoint.userId)){
	    	    			if((item.getEventDate().getYear()==today.getYear()) && (item.getEventDate().getMonth() == today.getMonth()))
	    	    	    		countMoney+=user.getMoney();
		        		    html+="<LI style='color:#224499'>"+user.getUserFirstName()+" "+user.getUserLastName()+" - "+shortMoneyFormat.format(user.getMoney());

	    	    		}else
	        		        html+="<LI>"+user.getUserFirstName()+" "+user.getUserLastName()+" - "+shortMoneyFormat.format(user.getMoney());

	    	    	}
	    	    	html+="</UL></div>";
	    	    	Label priceLabel = new Label(shortMoneyFormat.format(sum)+" / "+shortMoneyFormat.format(item.getPrice()));
	    	        wishTable.setWidget(row, 1,priceLabel );
	    	        
	    	        TooltipListener listener  = new TooltipListener(html, 10000 ,false);
	          	    priceLabel.addMouseListener(listener);
	          	    
	    	        if(item.getIsActive()){
	    	        	Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
	    			    updateImage.setTitle("update my sum");
	    			    Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
	    			    cancelImage.setTitle("leave this group");
	    			    Image buyImage = new Image( GWT.getModuleBaseURL() + "present_16.png");
	    			    buyImage.setTitle("There is enough money to buy the present. \n I'll buy a present on behalf of the group.");
	    	        	wishTable.setWidget(row, UPDATE_LINK, updateImage);
	    	        	wishTable.setWidget(row, CANCEL_LINK, cancelImage);
	    	        	wishTable.setWidget(row, BUY_LINK, buyImage);
	    	        }else{
	    	        	if(item.getBuyer().getUserId().equals(entryPoint.userId)){
	    	        	   Image cancelImage = new Image( GWT.getModuleBaseURL() + "reload16.png");
	    			       cancelImage.setTitle("reopen this group");
	    			       wishTable.setWidget(row, CANCEL_LINK, cancelImage);
	    	        	}
	    	        	
	    	        }
	    	        
	    	        Image chatImage = new Image(GWT.getModuleBaseURL()+"chat-icon.png");
	    	        chatImage.setTitle("chat");
	    	        wishTable.setWidget(row, CHAT_LINK, chatImage);

	        }   
	    	    row ++;
	        }
	        wishPanel.setTitle("In this month you're going to spend "+shortMoneyFormat.format(countMoney));
			    
		}
		
		private void closeChat(){
			if(mainChatPanel.isVisible()){
				this.itemsToBuy = null;
				this.wishTable.clear(true);
			    wishTable.resizeRows(0);
				mainChatPanel.setVisible(false);
			}
		}
		
		private void gui_eventCloseChatButtonClicked(){
			showIBuyTab();
		}
		
		
		
		
		private void gui_eventAddMessageButtonClicked(){
			if(chatTextArea.getText().equals("")){
				chatTextArea.setText("please, type a message");
				chatTextArea.setFocus(true);
				return;
			}
			
			ChatMessageData message = new ChatMessageData(entryPoint.userId,entryPoint.firstName+" "+entryPoint.lastName,chatTextArea.getText());
			this.wishlistService.addChatMessage(currentItem.getWishlistItemId(),message);
		}
		
		
		private void gui_eventSendEmailButtomClicked(){
			Double price = 0.0;
			if(emailTextArea.getText().equals("")){
				emailTextArea.setHTML("<FONT color=red>Please, enter the message for the group</FONT>");
				return;
			}
			if(closeGroup){
				try{
				    price = Double.parseDouble(actualPrice.getText());
				}catch(NumberFormatException e){
					actualPrice.setText("enter a valid price");
					actualPrice.setFocus(true);
					return;
				}
			}
			emailPanel.hide();
			if(closeGroup)
			   this.wishlistService.bookItemForGroup(currentItem.getWishlistItemId(), entryPoint.userId,emailTextArea.getHTML(),price);
			else
         	   this.wishlistService.cancelBookItemForGroup(currentItem.getWishlistItemId(), entryPoint.userId,emailTextArea.getHTML());
			actualPrice.setText("");
			emailTextArea.setText("");
			
		}
		
		private void gui_eventCanelEmailButtonClicked(){
			actualPrice.setText("");
			emailTextArea.setText("");
		    emailPanel.hide();
		}
		
		
	

		
		
		
		protected void wireIBuyGUIEvents(){
			this.wishTable.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					Cell cellForEvent=wishTable.getCellForEvent(event);
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
			
			this.sendEmail.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventSendEmailButtomClicked();
					
				}
				
			});
			
			this.cancelEmail.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventCanelEmailButtonClicked();
					
				}
				
			});
		}


		public void sevice_eventCancelBookItemForUserFailed(Throwable caught) {
			messages.setText("CancelBookItemForUserFailed"+caught.getMessage());
		}
		
		public void service_eventCancelBookItemForUserSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);	
		}

		public void service_eventDeleteParticipatorFailed(Throwable caught) {
			messages.setText("DeleteParticipatorFailed"+caught.getMessage());
		}

		public void service_eventDeleteParticipatorSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);			
		}

		public void service_eventDeleteItemFromTabFailed(Throwable caught) {
			messages.setText("DeleteItemFromTabFailed"+caught.getMessage());
		}

		public void service_eventDeleteItemFromTabSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);			
		}

		public void service_eventUpdateParticipatorFailed(Throwable caught) {
			messages.setText("UpdateParticipatorFailed"+caught.getMessage());
		}

		public void service_eventUpdateParticipatorSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);	
		}

		public void service_getBookedWishlistFailed(Throwable caught) {
			messages.setText("BookedWishlistFailed"+caught.getMessage());			
		}

		public void service_addChatMessageFailed(Throwable caught) {
			messages.setText("ChatMessageFailed"+caught.getMessage());
		}

		public void service_addChatMessageSuccesfull() {
			this.chatTextArea.setText("");
			this.wishlistService.getWishlistItem(currentItem.getWishlistItemId());
		}

		public void service_getWishlistItemFailed(Throwable caught) {
		    messages.setText("WishlistItemFailed"+caught.getMessage());
		}

		public void service_getWishlistItemSuccesfull(WishlistItemNewData result) {
			this.loadChat(result);			
		}

		public void service_cancelBookItemForGroupFailed(Throwable caught) {
			messages.setText("BookItemForGroupFailed"+caught.getMessage());
		}

		public void service_cancelBookItemForGroupSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
		}

		public void service_bookItemForGroupFailed(Throwable caught) {
			messages.setText("ItemForGroupFailed"+caught.getMessage());
		}

		public void service_bookItemForGroupSuccesfull() {
			this.wishlistService.getBookedWishlist(entryPoint.userId);
			
		}

}
