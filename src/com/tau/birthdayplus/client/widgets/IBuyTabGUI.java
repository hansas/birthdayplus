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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
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
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.RichTextToolbar.RichTextToolbar;
import com.tau.birthdayplus.dto.client.ChatMessageData;
import com.tau.birthdayplus.dto.client.ParticipatorData;

import com.tau.birthdayplus.dto.client.WishlistItemNewData;




public class IBuyTabGUI {
	//////////////////Constants///////////////////////////
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int BUY_LINK = 4;
	private static final int CANCEL_LINK = 3;
	private static final int UPDATE_LINK = 2;
	private static final int PRICE_LINK  = 1;
	
	

	//////////////////GUI Widgets////////////////////////
	private FlowPanel iBuyPanel;
	 private MenuBar menu ;
	
	private FlowPanel wishPanel;
	private  ScrollPanel iBuyScrollPanel;
	private  FlexTable iBuyTableHeader;
	private  HoverTable wishTable;
	private  RichTextArea emailTextArea;
	private  RichTextToolbar emailTextToolbar;
	private  Grid emailGrid;
	private  PopupPanel emailPanel;
	private  Anchor sendEmail;
	
    private FlowPanel mainChatPanel;
	private HorizontalPanel chatPanel;
//	private HorizontalPanel buttonPanel;
	
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
//	private Button closeChatButton;
	
	
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
		iBuyPanel.addStyleName("Panel");
		
		wishPanel= new FlowPanel();
		iBuyPanel.add(wishPanel);
		wishPanel.addStyleName("Panel");
	//	wishPanel.setSize("100%", "350px");
	
		buildWishlistTable();
		
		emailPanel = new PopupPanel(false,true); 
		emailTextArea = new RichTextArea();
	    emailTextArea.setSize("300px", "14em");
	    emailTextToolbar = new RichTextToolbar(emailTextArea);
	    emailTextToolbar.setWidth("300px");
	    sendEmail = new Anchor("send this message");
	    
	    

	    // Add the components to a panel
	    emailGrid = new Grid(4, 1);
	    emailGrid.setWidth("300px");
	    emailGrid.setText(0, 0, "We will send mail to the group on your behalf. Please enter the mail text below ");
	    emailGrid.setWidget(1, 0, emailTextToolbar);
	    emailGrid.setWidget(2, 0, emailTextArea);
	    emailGrid.setWidget(3, 0, sendEmail);
	    sendEmail.setWidth("100%");
	    emailPanel.add(emailGrid);
		
		mainChatPanel = new FlowPanel();
		iBuyPanel.add(mainChatPanel);
		mainChatPanel.setStyleName("Panel");
		mainChatPanel.setVisible(false);
		
		menu = new MenuBar();
		mainChatPanel.add(menu);
		menu.addStyleName("buttonPanel");
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		
		Command closeChatCommand = new Command(){
			public void execute() {
				gui_eventCloseChatButtonClicked();
		      }
		    };
		
		menu.addItem("<img src='http://birthdayplus.googlecode.com/svn/trunk/src/com/tau/birthdayplus/public/left_16.png' alt='Return to IBuy tab' title= 'Return' />",true,closeChatCommand);
		
	//	buttonPanel = new HorizontalPanel();
	//	mainChatPanel.add(buttonPanel);
	//	buttonPanel.setStyleName("buttonPanel");
		
	//	closeChatButton = new Button("return");
	//    buttonPanel.add(closeChatButton);
	    
		chatPanel = new HorizontalPanel();
		mainChatPanel.add(chatPanel);
		chatPanel.addStyleName("chatPanel");
	//	chatPanel.setSize("100%", "350px");
		
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
		
//	    closeChatButton.setSize("100%", "25px");
		leftSide = new FlowPanel();
		chatPanel.add(leftSide);
		chatPanel.setCellWidth(leftSide,"70%");
	//	leftSide.setSize("100%", "325px");
		leftSide.addStyleName("chatPanel");
		
		rightSide = new FlowPanel();
		chatPanel.add(rightSide);
		chatPanel.setCellWidth(rightSide, "30%");
	//	rightSide.setSize("100%", "325px");
		rightSide.addStyleName("chatPanel");
		
		
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
		iBuyTableHeader.addStyleName("TableHeader");
	//	iBuyTableHeader.setSize("100%", "25px");
		
		
		iBuyTableHeader.getColumnFormatter().setWidth(0, "150px");
	//	iBuyTableHeader.getColumnFormatter().setWidth(1, "70px");
	//	iBuyTableHeader.getColumnFormatter().setWidth(2, "50px");
		iBuyTableHeader.getColumnFormatter().setWidth(1, "90px");
				
		iBuyTableHeader.setText(0, 0, "What");
	//	iBuyTableHeader.setText(0,1, "Item");
	//	iBuyTableHeader.setText(0, 2, "Priority");
		iBuyTableHeader.setText(0, 1, "Price");
		iBuyTableHeader.getFlexCellFormatter().setColSpan(0, 1, 4);
		
		
		iBuyScrollPanel = new ScrollPanel();
		wishPanel.add(iBuyScrollPanel);
		iBuyScrollPanel.addStyleName("ScrollPanel");
	//	iBuyScrollPanel.setSize("100%", "300px");
	
		wishTable = new HoverTable();
		iBuyScrollPanel.add(wishTable);
		wishTable.addStyleName("Table");
	//	wishTable.setWidth("100%");
		
		wishTable.getColumnFormatter().setWidth(0, "150px");
	//	wishTable.getColumnFormatter().setWidth(1, "70px");
//		wishTable.getColumnFormatter().setWidth(2, "50px");
	    
	   // wishTable.getColumnFormatter().addStyleName(0, "tablesColumns");
	//	wishTable.getColumnFormatter().addStyleName(1, "tablesColumns");
	//	wishTable.getColumnFormatter().addStyleName(2, "tablesColumns");
	//	wishTable.getColumnFormatter().addStyleName(3, "tablesColumns");
		
	//	wishTable.getColumnFormatter().addStyleName(4, "lastColumns");
	//	wishTable.getColumnFormatter().addStyleName(5, "lastColumns");
	//	wishTable.getColumnFormatter().addStyleName(6, "lastColumns");
	}
	
	
	
	private void buildChatLeftSide(){
		chatLabel = new Label("chat");
		leftSide.add(chatLabel);
		chatLabel.addStyleName("Label");
	//	chatLabel.setSize("100%", "25px");
		chatScrollPanel = new ScrollPanel();
		leftSide.add(chatScrollPanel);
		chatScrollPanel.addStyleName("ShortScrollPanel");
	//	chatScrollPanel.setSize("100%", "300px");
		
		    
	    chatTable = new FlexTable();
        chatScrollPanel.add(chatTable);
        chatTable.addStyleName("Table");
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
		participatorsLabel.addStyleName("Label");
//		participatorsLabel.setSize("100%", "25px");
		
		buildParticipatorsTable();
		
		
	}
	
	
	
	private void buildParticipatorsTable(){
		participatorsTableHeader = new FlexTable();
		rightSide.add(participatorsTableHeader);
		participatorsTableHeader.addStyleName("TableHeader");
	//	participatorsTableHeader.setSize("100%", "25px");
		
		participatorsTableHeader.getColumnFormatter().setWidth(0, "50px");		
		participatorsTableHeader.setText(0, 0, "Name");
		participatorsTableHeader.setText(0,1, "Sum");
		
		
		participatorsScrollPanel = new ScrollPanel();
		rightSide.add(participatorsScrollPanel);
		participatorsScrollPanel.setStyleName("ShortScrollPanel");
	//	participatorsScrollPanel.setSize("100%", "275px");
		
		
		participatorsTable = new FlexTable();
		participatorsScrollPanel.add(participatorsTable);
		participatorsTable.addStyleName("Table");
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
            chatTable.setWidget(row, 0, new Label(message.getUserName()+" "+ "says :"));
            rowFormatter.setStylePrimaryName(row, "chatLineDate");
            
            DateTimeFormat dateFormatter = 	DateTimeFormat.getMediumDateTimeFormat();
            chatTable.setWidget(row, 1, new Label(dateFormatter.format(message.getTimeStamp())));
            
            chatTable.setWidget(row+1, 0, new Label(message.getMesssage()));
            cellFormatter.setColSpan(row+1, 0, 2);
            cellFormatter.setHorizontalAlignment(row+1, 0, HasHorizontalAlignment.ALIGN_LEFT);
            row+=2;
        }

	}
	
	
	private void loadChat(WishlistItemNewData item){
		
		if(!item.getParticipators().isEmpty()){
	    	currentItem = item;
	
	    	wishPanel.setVisible(false);
	    	mainChatPanel.setVisible(true);
	    	
		    fillParticipatorsTable();
		//    Window.alert("loading chat, number of messages is : "+currentItem.getChatMessages().size());
		    fillChatMessages();
		}
	}
	
	
	private void showTextArea(WishlistItemNewData item){
		if(item.getIsActive() && !item.getParticipators().isEmpty()){
			currentItem = item;
	    	emailPanel.center();
		    emailPanel.show();
	    	emailTextArea.setFocus(true);
		}
		
	}
		
	
		
		
		
	
	
	  
	  
	  
	  public void gui_eventItemGridClicked(Cell cellClicked) {
	         int row = cellClicked.getRowIndex();
	         int col = cellClicked.getCellIndex();
	        
	         WishlistItemNewData item = this.itemsToBuy.get(row);
	         
	        
	        switch(col){
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
	        case BUY_LINK     : showTextArea(item);
                                break;
	        }
	        
	  }
	    

      
		public void service_getBookedWishlistSuccesfull(
				ArrayList<WishlistItemNewData> result) {
			this.itemsToBuy = result;
	        this.wishTable.clear();
	        
	       
	        
	        int row = 0;
	        
	        for (WishlistItemNewData item : result) {
	        	String what = item.getItemName()+" for "+ item.getUserName()+"'s "+item.getEventName();
	       // 	wishTable.setWidget(row,0,new Label(item.getUserName()+"'s "+item.getEventName()));
	        	//link
	        	if (item.getLink().equals(""))
	        		wishTable.setWidget(row, 0,new Label(what));
	        	else
	        		wishTable.setWidget(row, 0,new Anchor(what,item.getLink(),"_blank"));
	        	//priority
	        	//if(item.getPriority() )
	        	///	wishTable.setWidget(row,2,new Label("high"));
	        	//else
	    	  //     wishTable.setWidget(row,2,new Label("low"));
	    	    //it's only me
	    	    if(item.getParticipators().isEmpty()){
	    	    	wishTable.setWidget(row, 1,new Label(item.getPrice().toString()) );
	    	    	Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
				    cancelImage.setTitle("cancel reservation");
		    	    wishTable.setWidget(row, 3, cancelImage); 
		
	    	    }else{
	    	    	Integer sum = 0;
	    	    	for(ParticipatorData user : item.getParticipators()){
	    	    		sum+=user.getMoney();
	    	    	}
	    	    	Hyperlink chatLink = new Hyperlink(sum+ " / " + item.getPrice().toString(), null);
	    	    	chatLink.setTitle("click to enter the chat");
	    	        wishTable.setWidget(row, 1,chatLink );
	    	        if(item.getIsActive()){
	    	        	Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
	    			    updateImage.setTitle("update ");
	    			    Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
	    			    cancelImage.setTitle("leave this group");
	    			    Image buyImage = new Image( GWT.getModuleBaseURL() + "present_16.png");
	    			    buyImage.setTitle("We'll buy");
	    	        	wishTable.setWidget(row, 2, updateImage);
	    	        	wishTable.setWidget(row, 3, cancelImage);
	    	        	wishTable.setWidget(row, 4, buyImage);
	    	        }else{
	    	        	if(item.getBuyer().getUserId().equals(entryPoint.userId)){
	    	        	   Image cancelImage = new Image( GWT.getModuleBaseURL() + "delete_16.png");
	    			       cancelImage.setTitle("reopen this group");
	    			       wishTable.setWidget(row, 3, cancelImage);
	    	        	}
	    	        	
	    	        }
	    	        
	    
	           
	        }
	    	    
	  //  	    wishTable.getRowFormatter().addStyleName(row, "tablesRows");

	    	    
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
			mainChatPanel.setVisible(false);
	    	wishPanel.setVisible(true);
			
		}
		
		private void gui_eventAddMessageButtonClicked(){
			if(chatTextArea.getText().equals(""))
				return;
			ChatMessageData message = new ChatMessageData(entryPoint.userId,entryPoint.firstName+" "+entryPoint.lastName,chatTextArea.getText());
			this.wishlistService.addChatMessage(currentItem.getWishlistItemId(),message);
		}
		
		
		private void gui_eventSendEmailButtomClicked(){
			if(emailTextArea.getText().equals(""))
				return;
			emailPanel.hide();
		//	this.wishlistService.sendEmail(emailTextArea.getHTML());
			this.wishlistService.bookItemForGroup(currentItem.getWishlistItemId(), entryPoint.userId,emailTextArea.getHTML(),false);
			emailTextArea.setText("");
			
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
			
		//	this.closeChatButton.addClickHandler(new ClickHandler(){

		//		public void onClick(ClickEvent event) {
		//			gui_eventCloseChatButtonClicked();
		//		}
		//	});
			
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
			
			this.sendEmail.addClickHandler(new ClickHandler(){

				public void onClick(ClickEvent event) {
					gui_eventSendEmailButtomClicked();
					
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
