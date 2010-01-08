package com.tau.birthdayplus.client;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.ListItem;
import com.tau.birthdayplus.client.widgets.MessagePanel;

import com.tau.birthdayplus.client.widgets.MoneyDialogBox;
import com.tau.birthdayplus.client.widgets.TooltipListener;
import com.tau.birthdayplus.client.widgets.UnorderedList;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;



public class WishListFriendsGUI  {
	private static  NumberFormat shortMoneyFormat = NumberFormat.getFormat("\u20AA#,##0.00");
	/*
	 * constants
	 */
	CwConstants constants = GWT.create(CwConstants.class);

	private static final int BUY_LINK = 4;
    private static final int GROUP_BUY_LINK = 5;

	/*GUI Widgets*/
    private FlowPanelMenuTitle wishlistBoxPanel;

	
	//polani
    private MenuBar polaniTable ;
    private Command emptyCommand;
    private MenuItem polani ;

	
	//friend's wishlist table
	private ScrollPanel scrollWishlistPanel;
	private FlexTable headerFriendWishTable;
	private HoverTable friendWishTable;

	
	
	private MoneyDialogBox moneyDialogBox;
	
	
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

			wishlistBoxPanel = new FlowPanelMenuTitle();
		//	wishlistBoxPanel = new FlowPanel();
			parent.mainPanel.add(wishlistBoxPanel);
			wishlistBoxPanel.addStyleName("mainPanel");
		//	wishlistBoxPanel.setSize("100%", "350px");
			wishlistBoxPanel.setVisible(false);
			
			
			Command closeFriendWishlistCommand = new Command(){
				public void execute() {
					gui_eventCloseButtonClicked();
			      }
			    };
			
			wishlistBoxPanel.addMenuItem("<img src='"+GWT.getModuleBaseURL()+"left_16.png"+"' alt='Return to IBuy tab' title= 'Return' />",true,closeFriendWishlistCommand);

			
			polaniTable = new MenuBar(true);
			emptyCommand = new Command(){
				public void execute() {
				}
			};
			polani = wishlistBoxPanel.addMenuItem("Polani",polaniTable);
			
			buildFriendWishlistTable();
			moneyDialogBox = new MoneyDialogBox("When there is enough money to buy this present someone (it can be you) should close this group and buy the present.<br/>We will send an email when someone closes this group. <br/>You can close the group in IBuy tab.");
			
			
			
			 
			
			   
		}
		
		protected void showDialogBox(EventData event){
			  currentEvent = event;
			  String friendName = parent.entryPoint.userFriends.get(currentEvent.getUserId());
		      wishlistBoxPanel.setTitle("wishlist for " +friendName+ "'s "+event.getEventName());
		      polani.setText(friendName+" bought you recently");
		      polani.setTitle("Items that "+friendName+" bought you recently");
		      wishlistBoxPanel.setVisible(true);
		      this.wishlistService.getWishlist(event.getUserId() , event.getEventId());

			
		}
		
	
	
	private void loadMoneyDialog(WishlistItemNewData item,Widget widgetClicked){
		currentItem = item;
		moneyDialogBox.center();
	    moneyDialogBox.show();

	}
	
	
	private void showPolaniItems(ArrayList<WishlistItemPolaniData> result){
		polaniTable.clearItems();
		if(polaniItems.isEmpty())
			polaniTable.addItem( parent.entryPoint.userFriends.get(currentEvent.getUserId())+ " hasn't bought you anything yet", emptyCommand);
		else{
	        for(WishlistItemPolaniData item : result){
	        	polaniTable.addItem(item.getItemName()+" for "+item.getEventName(), emptyCommand);
	        }
		}		
	}
	
	
	

	
	/*
	 * create flex table for friend's wishlist items
	 */
	private void buildFriendWishlistTable(){
		headerFriendWishTable = new FlexTable();
		wishlistBoxPanel.add(headerFriendWishTable);
		headerFriendWishTable.addStyleName("TableHeader");
		headerFriendWishTable.setCellSpacing(0);
	//	headerFriendWishTable.setSize("100%", "25px");
		 
	
		
		headerFriendWishTable.getColumnFormatter().setWidth(0, "100px");
		headerFriendWishTable.getColumnFormatter().setWidth(1, "55px");
	//	headerFriendWishTable.getColumnFormatter().setWidth(2, "80px");
	
		
		headerFriendWishTable.setWidget(0, 0, new Label ("Item"));
		headerFriendWishTable.setWidget(0, 1, new Label("Priority"));
		headerFriendWishTable.setWidget(0, 2, new Label("Price"));
		headerFriendWishTable.getFlexCellFormatter().setColSpan(0, 2, 3);

		
		scrollWishlistPanel = new ScrollPanel();
		wishlistBoxPanel.add(scrollWishlistPanel);
		scrollWishlistPanel.addStyleName("ShortScrollPanel");
	//	scrollWishlistPanel.setSize("100%", "275px");

	   
		friendWishTable = new HoverTable(0,6);
		scrollWishlistPanel.add(friendWishTable);
	//	friendWishTable.setWidth("100%");
		friendWishTable.addStyleName("Table");
		friendWishTable.setCellSpacing(0);
		
		friendWishTable.getColumnFormatter().setWidth(0, "100px");
		friendWishTable.getColumnFormatter().setWidth(1, "55px");
		friendWishTable.getColumnFormatter().setWidth(2, "80px");
		friendWishTable.getColumnFormatter().setWidth(BUY_LINK, "20px");
		friendWishTable.getColumnFormatter().setWidth(GROUP_BUY_LINK, "20px");
		
		
		
       
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
       case BUY_LINK :      if(item.getIsActive()&& item.getParticipators().isEmpty())
    	                       wishlistService.bookItemForUser(item.getWishlistItemId(), currentEvent.getEventId(),parent.entryPoint.userId);
                            break;
                          
       case GROUP_BUY_LINK :if(item.getIsActive()&& !userInGroup(item)){
    	                       if(item.getParticipators().isEmpty())
    	                    	   moneyDialogBox.setText("Start a group", "Participate");
    	                       else
    	                    	   moneyDialogBox.setText("Join to the group", "Participate");
    	                       loadMoneyDialog(item , widgetClicked);
                            }
                            break;
       }      
       
 
	 }
	
	protected void closeTab(){
		if(wishlistBoxPanel.isVisible()){
			wishlistBoxPanel.setVisible(false);
			friendWishTable.clear(true);
			friendWishTable.resizeRows(0);
			polaniItems = null;
		}
	}
	
	public void gui_eventCloseButtonClicked() {
		parent.showEventTab();
        
    }
	

	public void wireWishlistFriendGUIEvents() {

		this.friendWishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	
                 Cell cellForEvent = friendWishTable.getCellForEvent(event);
            
                 if(cellForEvent!=null)
                    gui_eventItemGridClicked(cellForEvent);                
            }});
		
		this.moneyDialogBox.addCloseHandler(new CloseHandler<PopupPanel>(){
			public void onClose(CloseEvent<PopupPanel> event) {
				if(event.isAutoClosed())
					return;
				else{
					Integer sum = moneyDialogBox.getInput();
					if(sum == null)
						return;
					else {
						 ParticipatorData data = new ParticipatorData(parent.entryPoint.userId,parent.entryPoint.user.getFirstName(),parent.entryPoint.user.getLastName(),sum);
				         wishlistService.addParticipator(currentItem.getWishlistItemId(), currentEvent.getEventId(), data);
					}	
				}
			}
		});
	}
	
	
	/*
	 * friend's wishlist
	 */
	@SuppressWarnings("deprecation")
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemNewData> result) {
		//go to get polani items
		this.wishlistService.getPolaniItems(parent.entryPoint.userId, currentEvent.getUserId());
		this.items = result;
        friendWishTable.clear(true);
        friendWishTable.resizeRows(result.size());
        
        int row = 0;
        for (WishlistItemNewData item : result) {
        	//link
        	if ((item.getLink() == null) || (item.getLink().equals(""))  )
        		friendWishTable.setWidget(row, 0,new Label(item.getItemName()));
        	else {
        		if((item.getThumbnail() == null)||(item.getThumbnail().equals(""))  )
            		friendWishTable.setWidget(row, 0,new Anchor(item.getItemName(),item.getLink(),"_blank"));
        		else{
        			Anchor anchor =new Anchor(item.getItemName(),item.getLink(),"_blank");
        			friendWishTable.setWidget(row,0,anchor);
        			TooltipListener listener  = new TooltipListener(
     		        		"<img   src="+"'"+item.getThumbnail()+"'"+"alt='"+item.getItemName()+"' height='90' width='90' style = 'background-color: #f7d8a9; padding: 3px; border: 1px solid #6f3d29;'>", 5000 ,true);
        			anchor.addMouseListener( listener);
        		}
        	}
        	//priority
        	if(item.getPriority())
        	   friendWishTable.setText(row,1,"high");
        	else
        		friendWishTable.setText(row,1,"low");
        	
            if(item.getIsActive()){	
        	   if(item.getParticipators().isEmpty()){
        	      friendWishTable.setWidget(row, 2,new Label(shortMoneyFormat.format(item.getPrice())) );
        	      Image buyImage = new Image( GWT.getModuleBaseURL() + "present_16.png");
			      buyImage.setTitle("I'll buy");
        	      friendWishTable.setWidget(row, BUY_LINK , buyImage);
        	      
        	      Image groupImage = new Image( GWT.getModuleBaseURL() + "group_24.png");
			      groupImage.setTitle("Start a group");
			      groupImage.setPixelSize(16, 16);
    	          friendWishTable.setWidget(row ,GROUP_BUY_LINK, groupImage);
            	}else{
        	      Integer sum = 0;
        	      Boolean userInGroup = false;
	    			 String html =" <div style='background-color:#FFFFCC;border:1px solid #FFCC35;'><p style ='color:#224499;font-weight:bold;'>Participators are :<p><UL style='list-style-type: square;padding:1px 10px 1px 10px !important;margin:0px !important; list-style-position:inside;'>";
        	      
        	      for (ParticipatorData data : item.getParticipators()){
        	    	  sum += data.getMoney();
        		      if(parent.entryPoint.userId.equals(data.getUserId())){
        			     userInGroup = true;
		        		 html+="<LI style='color:#224499'>"+data.getUserFirstName()+" "+data.getUserLastName()+" - "+shortMoneyFormat.format(data.getMoney());
        		      }else
        		         html+="<LI>"+data.getUserFirstName()+" "+data.getUserLastName()+" - "+shortMoneyFormat.format(data.getMoney());
        	      }
	    	      html+="</UL></div>";
        	      Label price = new Label (shortMoneyFormat.format(sum) +" / "+shortMoneyFormat.format(item.getPrice()));
          	      friendWishTable.setWidget(row, 2,price );
          	      
          	      TooltipListener listener  = new TooltipListener(html, 10000 ,false);
          	      price.addMouseListener(listener);

        	   
        	    //  friendWishTable.setWidget(row, 2,new Hyperlink(sum +"/"+item.getPrice().toString(),null) );
        	      if(!userInGroup){
        		     Image groupImage = new Image( GWT.getModuleBaseURL() + "group_24.png");
        		     
    			     groupImage.setTitle("Join the group");
    			     groupImage.setPixelSize(16, 16);
        	         friendWishTable.setWidget(row, GROUP_BUY_LINK, groupImage);
        	      }
            	}
            }else {
            	friendWishTable.setWidget(row, 2,new Label(shortMoneyFormat.format(item.getPrice())));
            	friendWishTable.getWidget(row, 0).addStyleName(constants.cwInactiveRowStyle());
            }
            
            row ++;
        }
	}

	public void service_eventGetWishlistFailed(Throwable caught) {
		friendWishTable.clear(true);
        friendWishTable.resizeRows(0);
		parent.entryPoint.messages.setText("GetWishlistFailed"+caught.getMessage());
	}
	
	public void service_eventBookItemForUserFailed(Throwable caught) {
		parent.entryPoint.messages.setText("BookItemForUserFailed"+caught.getMessage());		
	}

	public void service_eventBookItemForUserSuccesfull() {
		parent.entryPoint.iBuyGUI.makeDirtyIBuyItems();
		parent.entryPoint.tab.selectTab(2);
		parent.entryPoint.messages.setText("In IBuy tab you can cancel the reservation for the item.");	
		
	}

	public void service_eventAddParticipatorFailed(Throwable caught) {
		parent.entryPoint.messages.setText("AddParticipatorFailed"+caught.getMessage());		
	}

	public void service_eventAddParticipatorSuccesfull() {
		parent.entryPoint.iBuyGUI.makeDirtyIBuyItems();
		parent.entryPoint.tab.selectTab(2);
		parent.entryPoint.messages.setText("In IBuy tab you can see the progress and chat with your group.");	
	}

	public void service_eventGetPolaniItemsFailed(Throwable caught) {
		parent.entryPoint.messages.setText("GetPolaniItemsFailed"+caught.getMessage());		
	}

	public void service_eventGetPolaniItemsSuccesfull(ArrayList<WishlistItemPolaniData> result) {
		polaniItems = result;
		showPolaniItems(result);	
		
	}
	
}


	    
		
		


