package com.tau.birthdayplus.client;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.i18n.client.NumberFormat;


import com.google.gwt.user.client.Command;
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
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.TableWithHeader;
import com.tau.birthdayplus.client.widgets.TooltipListener;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;



public class WishListFriendsGUI  {
	/*
	 * constants
	 */
	CwConstants constants = GWT.create(CwConstants.class);
//	private static final int LINK = 0;
//	private static final int PRICE_LINK =2;
	private static final int BUY_LINK = 3;
    private static final int GROUP_BUY_LINK = 4;

	/*GUI Widgets*/
	

	//VerticalPanel for the content of wishlist box
	private FlowPanel wishlistBoxPanel;
	private Label title;
	private MenuBar menu ;
	
	//polani
	private PopupPanel polaniPanel;
	private FlexTable polaniTable;
	
	//friend's wishlist table
	private ScrollPanel scrollWishlistPanel;
	private FlexTable headerFriendWishTable;
	private HoverTable friendWishTable;

	//popup panel for participators
//	private PopupPanel participatorsPanel;
//	private TableWithHeader participatorsTable;
	
	//popup for money
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
    private EventData currentEvent;
    private ArrayList<WishlistItemPolaniData> polaniItems;
 
	

	
	 


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
			
			title = new Label();
			wishlistBoxPanel.add(title);
			title.addStyleName("Label");
			
			menu = new MenuBar();
			wishlistBoxPanel.add(menu);
			menu.addStyleName("buttonPanel");
			menu.setAutoOpen(true);
			menu.setAnimationEnabled(true);
			
			Command closeFriendWishlistCommand = new Command(){
				public void execute() {
					gui_eventCloseButtonClicked();
			      }
			    };
			
			menu.addItem("<img src='"+GWT.getModuleBaseURL()+"left_16.png"+"' alt='Return to IBuy tab' title= 'Return' />",true,closeFriendWishlistCommand);
			
			
			Command polaniCommand = new Command(){

				public void execute() {
					if(polaniItems == null){
						wishlistService.getPolaniItems(parent.entryPoint.userId, currentEvent.getUserId());
					}else
						showPolaniItems();	
				}
			};
			menu.addItem("Polani",polaniCommand);

			
	
			
			
			
			
			buildFriendWishlistTable();
			    
			
			
			buildMoneyDialogBox();
	//		buildParticipatorsPopupPanel();
			buildPolaniPanel();
			 
			 
			
			   
		}
		
		protected void showDialogBox(EventData event){
			  currentEvent = event;
		      title.setText("wishlist for " + parent.entryPoint.userFriends.get(currentEvent.getUserId())+ "'s "+event.getEventName());
		      wishlistBoxPanel.setVisible(true);
		      this.wishlistService.getWishlist(event.getUserId() , event.getEventId());
			
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
	
	/*
	private void buildParticipatorsPopupPanel(){	
	   participatorsPanel = new PopupPanel(true);
	  
	   
 	   participatorsTable = new TableWithHeader();
 	   participatorsPanel.add(participatorsTable);
 	   participatorsTable.setStyleName("tables");
 	   
 	   participatorsTable.setHeader(0, "Name");
 	   participatorsTable.setHeader(1, "sum");
 	   
 	//   participatorsTable.getColumnFormatter().addStyleName(0, "tablesColumns");
 	//   participatorsTable.getColumnFormatter().addStyleName(1, "tablesColumns");
 	   
 	  
 	   
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
	*/
	
	
	private void buildPolaniPanel(){
		polaniPanel = new PopupPanel(true);
		polaniTable = new FlexTable();
		polaniPanel.add(polaniTable);
	}
		
	private void showPolaniItems(){
		
		polaniTable.clear();
		 int row = 0;
	        for(WishlistItemPolaniData item : polaniItems){
	        	polaniTable.setWidget(row, 0, new Label(item.getItemName()+" for "+item.getEventName(),false));
	        	row++;
	        }   
	     polaniPanel.showRelativeTo(menu);   
		
		
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
		headerFriendWishTable.getColumnFormatter().setWidth(1, "50px");
	//	headerFriendWishTable.getColumnFormatter().setWidth(2, "80px");
	
		
		headerFriendWishTable.setWidget(0, 0, new Label ("Item"));
		headerFriendWishTable.setWidget(0, 1, new Label("Priority"));
		headerFriendWishTable.setWidget(0, 2, new Label("Price"));
		headerFriendWishTable.getFlexCellFormatter().setColSpan(0, 2, 3);

		
		scrollWishlistPanel = new ScrollPanel();
		wishlistBoxPanel.add(scrollWishlistPanel);
		scrollWishlistPanel.addStyleName("ShortScrollPanel");
	//	scrollWishlistPanel.setSize("100%", "275px");

	   
		friendWishTable = new HoverTable();
		scrollWishlistPanel.add(friendWishTable);
	//	friendWishTable.setWidth("100%");
		friendWishTable.addStyleName("Table");
		
		friendWishTable.getColumnFormatter().setWidth(0, "100px");
		friendWishTable.getColumnFormatter().setWidth(1, "50px");
		friendWishTable.getColumnFormatter().setWidth(2, "80px");
		
       
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
    	                  
  //     case PRICE_LINK :    if(!item.getParticipators().isEmpty())
  		              //          showParticipatorsPanel(item , widgetClicked);
                    //        break;
                          
       case BUY_LINK :      if(item.getIsActive())
    	                       wishlistService.bookItemForUser(item.getWishlistItemId(), currentEvent.getEventId(),parent.entryPoint.userId);
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
		polaniItems = null;
        
    }
	
//	public void gui_eventParticipatorsTableClicked(){
//		participatorsPanel.hide();
//	}
	

	
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
            this.wishlistService.addParticipator(currentItem.getWishlistItemId(), currentEvent.getEventId(), data);
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
	//	this. closeFriendWishlistBoxButton.addClickHandler(new ClickHandler(){
     //   	public void onClick(ClickEvent event){
      //  	    gui_eventCloseButtonClicked();
      //  	}
     //   });
		
		this.friendWishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	
                 Cell cellForEvent = friendWishTable.getCellForEvent(event);
            
                 if(cellForEvent!=null)
                    gui_eventItemGridClicked(cellForEvent);                
            }});
		
		
		
	//	this.participatorsTable.addClickHandler(new ClickHandler(){
	//		public void onClick(ClickEvent event){
	//			gui_eventParticipatorsTableClicked();
				
	//		}
	//	});
		
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
	@SuppressWarnings("deprecation")
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemNewData> result) {
		this.items = result;
        this.friendWishTable.clear();
        NumberFormat format = NumberFormat.getFormat("\u20AA#,##0.00");
        
        int row = 0;
        for (WishlistItemNewData item : result) {
        	//link
        	if ((item.getLink().equals("")) || (item.getLink() == null))
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
        	      friendWishTable.setWidget(row, 2,new Label(format.format(item.getPrice()),false) );
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
	    	      String html =" <div style='background-color:#FFFFFF;border-style:inset;border-color:green;'><p style ='color:green'>Participators are :<p><UL style='list-style-type: square;'>";
        	      
        	      for (ParticipatorData data : item.getParticipators()){
        		      if(parent.entryPoint.userId.equals(data.getUserId()))
        			     userInGroup = true;
        		      sum += data.getMoney();
        		      html+="<LI>"+data.getUserFirstName()+" "+data.getUserLastName()+" - "+format.format(data.getMoney());
        	      }
	    	      html+="</UL></div>";
        	      Label price = new Label (format.format(sum) +"/"+format.format(item.getPrice()));
          	      friendWishTable.setWidget(row, 2,price );
          	      
          	      TooltipListener listener  = new TooltipListener(html, 5000 ,"yourcssclass");
          	      price.addMouseListener(listener);

        	   
        	    //  friendWishTable.setWidget(row, 2,new Hyperlink(sum +"/"+item.getPrice().toString(),null) );
        	      if(!userInGroup){
        		     Image groupImage = new Image( GWT.getModuleBaseURL() + "group_24.png");
        		     
    			     groupImage.setTitle("Join the group");
    			     groupImage.setPixelSize(16, 16);
        	         friendWishTable.setWidget(row, 4, groupImage);
        	      }
            	}
            }else {
            	friendWishTable.setWidget(row, 2,new Label(format.format(item.getPrice())));
            	friendWishTable.getCellFormatter().addStyleName( row,0,constants.cwInactiveRowStyle());
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
		this.wishlistService.getWishlist(currentEvent.getUserId(), currentEvent.getEventId());
		
	}

	public void service_eventAddParticipatorFailed(Throwable caught) {
		Window.alert("add participator " +caught);
		
	}

	public void service_eventAddParticipatorSuccesfull() {
		parent.entryPoint.iBuyGUI.itemsToBuy = null;
		this.wishlistService.getWishlist(currentEvent.getUserId(), currentEvent.getEventId());
		
	}

	public void service_eventGetPolaniItemsFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void service_eventGetPolaniItemsSuccesfull(ArrayList<WishlistItemPolaniData> result) {
		polaniItems = result;
		showPolaniItems();	
		
	}

	

	
	  
	  


	
	
}


	    
		
		


