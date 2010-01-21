package com.tau.birthdayplus.client;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.Icons;
import com.tau.birthdayplus.client.widgets.ParticipatorList;
import com.tau.birthdayplus.client.widgets.StaticFunctions;
import com.tau.birthdayplus.client.widgets.TooltipHandler;
import com.tau.birthdayplus.client.widgets.MoneyDialogBox.MoneyDialogBox;
import com.tau.birthdayplus.client.widgets.MoneyDialogBox.MoneyEventHandler;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;



public class WishListFriendsGUI  {
	/*
	 * constants
	 */
	private static CwConstants constants = GWT.create(CwConstants.class);
	private static String helpMessage = "When there is enough money to buy this present someone (it can be you) should close this group and buy the present.<br/>We will send an email when someone closes this group. <br/>You can close the group in IBuy tab.";
	private static Icons icons = (Icons) GWT.create(Icons.class);
	private static final int BUY_LINK = 4;
    private static final int GROUP_BUY_LINK = 5;

	/*GUI Widgets*/
    private FlowPanelMenuTitle wishlistBoxPanel;
	//polani
    private MenuBar polaniTable ;
    private Command emptyCommand;
    private MenuItem polani ;
    //wish list table
	private HoverTable friendWishTable;
	private MoneyDialogBox moneyDialogBox;
	
	
	/*Data Model*/
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
			parent.mainPanel.add(wishlistBoxPanel);
			wishlistBoxPanel.addStyleName("mainPanel");
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
			moneyDialogBox = new MoneyDialogBox(helpMessage,parent.entryPoint.preferences.getCountry().getValue().getCurrency());	    
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
		
	
	
	private void loadMoneyDialog(WishlistItemNewData item){
		currentItem = item;
	    moneyDialogBox.show();

	}
	
	
	/*
	 * create flex table for friend's wishlist items
	 */
	private void buildFriendWishlistTable(){
		FlexTable headerFriendWishTable = new FlexTable();
		wishlistBoxPanel.add(headerFriendWishTable);
		headerFriendWishTable.addStyleName("TableHeader");
		headerFriendWishTable.setCellSpacing(0);
		 
	
		headerFriendWishTable.getColumnFormatter().setWidth(0, StaticFunctions.getPercentWidthPixels(35, 40) +"px");
		headerFriendWishTable.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(20, 40) +"px");
	
		
		headerFriendWishTable.setWidget(0, 0, new Label ("Item"));
		headerFriendWishTable.setWidget(0, 1, new Label("Priority"));
		headerFriendWishTable.setWidget(0, 2, new Label("Price"));
		headerFriendWishTable.getFlexCellFormatter().setColSpan(0, 2, 3);

		
		ScrollPanel scrollWishlistPanel = new ScrollPanel();
		wishlistBoxPanel.add(scrollWishlistPanel);
		scrollWishlistPanel.addStyleName("ShortScrollPanel");

	   
		friendWishTable = new HoverTable(0,6);
		scrollWishlistPanel.add(friendWishTable);
		friendWishTable.addStyleName("Table");
		friendWishTable.setCellSpacing(0);
		
		friendWishTable.getColumnFormatter().setWidth(0, StaticFunctions.getPercentWidthPixels(35, 40) +"px");
		friendWishTable.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(20, 40) +"px");
		friendWishTable.getColumnFormatter().setWidth(2,  StaticFunctions.getPercentWidthPixels(30, 40) +"px");
		friendWishTable.getColumnFormatter().setWidth(BUY_LINK, "20px");
		friendWishTable.getColumnFormatter().setWidth(GROUP_BUY_LINK, "20px");
		
		
		
       
	}
	
    private boolean userInGroup(WishlistItemNewData item){
    	boolean userInGroup = false;
    
    	 for (ParticipatorData data : item.getParticipators()){
		      if(parent.entryPoint.userId.equals(data.getUserId())){
			     userInGroup = true;
			     break;
		      }
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
         
       switch(col){   	                                       
       case BUY_LINK :      if(item.getIsActive()&& item.getParticipators().isEmpty())
    	                       wishlistService.bookItemForUser(item.getWishlistItemId(), currentEvent.getEventId(),parent.entryPoint.userId);
                            break;
                          
       case GROUP_BUY_LINK :if(item.getIsActive()&& !userInGroup(item)){
    	                       if(item.getParticipators().isEmpty())
    	                    	   moneyDialogBox.setText("Start a group", "Participate");
    	                       else
    	                    	   moneyDialogBox.setText("Join to the group", "Participate");
    	                       loadMoneyDialog(item );
                            }
                            break;
       }      
       
 
	 }
	
	protected void closeTab(){
		if(wishlistBoxPanel.isVisible()){
			wishlistBoxPanel.setVisible(false);
			friendWishTable.clear(true);
			friendWishTable.resizeRows(0);
			items = null;
			
			polaniTable.clearItems();
			polaniItems = null;
			parent.entryPoint.messages.hide();
			
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
		
		this.moneyDialogBox.addMoneyEvent(new MoneyEventHandler(){
			public void onMoneySave(int amount) {
				 ParticipatorData data = new ParticipatorData(parent.entryPoint.userId,parent.entryPoint.user.getFirstName(),parent.entryPoint.user.getLastName(),amount);
		         wishlistService.addParticipator(currentItem.getWishlistItemId(), currentEvent.getEventId(), data);	
			}
			
		});
		/*
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
		*/
	}
	
	
	/*
	 * friend's wishlist
	 */
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemNewData> result) {
		//go to get polani items
		this.wishlistService.getPolaniItems(parent.entryPoint.userId, currentEvent.getUserId());
		this.items = result;
        friendWishTable.clear(true);
        friendWishTable.resizeRows(result.size());
        
        int row = 0;
        for (WishlistItemNewData item : result) {
        	//item
			friendWishTable.setWidget(row,0,StaticFunctions.getAnchorWithThumbnail(item.getLink(), item.getItemName(), item.getThumbnail()));
        	//priority
        	friendWishTable.setText(row,1,item.getPriority() ? "hight" : "low");
        	
            if(item.getIsActive()){	
        	   if(item.getParticipators().isEmpty()){
        		  //price
        	      friendWishTable.setWidget(row, 2,new Label(parent.entryPoint.shortMoneyFormat.format(item.getPrice())) );
        	      //icons
        	      friendWishTable.setWidget(row, BUY_LINK ,StaticFunctions.createIcon(icons.presentIcon(),"I'll buy"));
    	          friendWishTable.setWidget(row ,GROUP_BUY_LINK, StaticFunctions.createIcon(icons.groupIcon(),"Start a group"));
            	}else{
        	      Boolean userInGroup = false;
        	      ParticipatorList list = StaticFunctions.getParticipatorsList(item.getParticipators(), item.getEventDate(), parent.entryPoint.userId,parent.entryPoint.shortMoneyFormat);
        	      if(list.getUserPart() > 0)
        	    	 userInGroup = true;
        	      
        	      //price
        	      Label price = new Label (parent.entryPoint.shortMoneyFormat.format(list.getTotalAmount()) +" / "+parent.entryPoint.shortMoneyFormat.format(item.getPrice()));
          	      friendWishTable.setWidget(row, 2,price );
          	      TooltipHandler handler  = new TooltipHandler(list.getHtmlList(), 10000 ,false);
          	      price.addMouseOverHandler(handler);
          	      price.addMouseOutHandler(handler);
                  //icons
        	      if(!userInGroup)
        	         friendWishTable.setWidget(row, GROUP_BUY_LINK, StaticFunctions.createIcon(icons.groupIcon(), "Join the group")); 
            	}
            }else {
            	friendWishTable.setWidget(row, 2,new Label(parent.entryPoint.shortMoneyFormat.format(item.getPrice())));
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
		polaniTable.clearItems();
		if(polaniItems.isEmpty())
			polaniTable.addItem( parent.entryPoint.userFriends.get(currentEvent.getUserId())+ " hasn't bought you anything yet", emptyCommand);
		else{
	        for(WishlistItemPolaniData item : result){
	        	polaniTable.addItem(item.getItemName()+" for "+item.getEventName(), emptyCommand);
	        }
		}	
		
	}
	
}


	    
		
		


