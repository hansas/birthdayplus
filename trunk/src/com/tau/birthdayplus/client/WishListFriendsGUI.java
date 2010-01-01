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
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
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
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;


import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.MoneyDialogBox;

import com.tau.birthdayplus.client.widgets.TooltipListener;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;



public class WishListFriendsGUI  {
	private static  NumberFormat format = NumberFormat.getFormat("\u20AA#,##0.00");
	/*
	 * constants
	 */
	CwConstants constants = GWT.create(CwConstants.class);

	private static final int BUY_LINK = 3;
    private static final int GROUP_BUY_LINK = 4;

	/*GUI Widgets*/
    private FlowPanelMenuTitle wishlistBoxPanel;

	
	//polani
    private MenuItem polani ;
	private PopupPanel polaniPanel;
	private FlexTable polaniTable;
	
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
			wishlistBoxPanel.addStyleName("Panel");
		//	wishlistBoxPanel.setSize("100%", "350px");
			wishlistBoxPanel.setVisible(false);
			
			
			Command closeFriendWishlistCommand = new Command(){
				public void execute() {
					gui_eventCloseButtonClicked();
			      }
			    };
			
			wishlistBoxPanel.addMenuItem("<img src='"+GWT.getModuleBaseURL()+"left_16.png"+"' alt='Return to IBuy tab' title= 'Return' />",true,closeFriendWishlistCommand);

			
			Command polaniCommand = new Command(){

				public void execute() {
					if(polaniItems == null){
						wishlistService.getPolaniItems(parent.entryPoint.userId, currentEvent.getUserId());
					}else
						showPolaniItems();	
				}
			};
			
		   polani = wishlistBoxPanel.addMenuItem("Polani",polaniCommand);
			
			buildFriendWishlistTable();
			moneyDialogBox = new MoneyDialogBox();
			buildPolaniPanel();
			 
			 
			
			   
		}
		
		protected void showDialogBox(EventData event){
			  currentEvent = event;
		      wishlistBoxPanel.setTitle("wishlist for " + parent.entryPoint.userFriends.get(currentEvent.getUserId())+ "'s "+event.getEventName());
		      wishlistBoxPanel.setVisible(true);
		      this.wishlistService.getWishlist(event.getUserId() , event.getEventId());
			
		}
		
	
	
	private void loadMoneyDialog(WishlistItemNewData item,Widget widgetClicked){
		currentItem = item;
		moneyDialogBox.center();
	    moneyDialogBox.show();

	}
	
	
	
	
	private void buildPolaniPanel(){
		polaniPanel = new PopupPanel(true);
		polaniTable = new FlexTable();
		polaniPanel.add(polaniTable);
	}
		
	private void showPolaniItems(){
		if(polaniItems.isEmpty())
			return;
		
		polaniTable.clear();
		 int row = 0;
	        for(WishlistItemPolaniData item : polaniItems){
	        	polaniTable.setWidget(row, 0, new Label(item.getItemName()+" for "+item.getEventName(),false));
	        	row++;
	        }   
	     polaniPanel.showRelativeTo(polani);   
		
		
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

	   
		friendWishTable = new HoverTable(0,5);
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
       case BUY_LINK :      if(item.getIsActive())
    	                       wishlistService.bookItemForUser(item.getWishlistItemId(), currentEvent.getEventId(),parent.entryPoint.userId);
                            break;
                          
       case GROUP_BUY_LINK :if(item.getIsActive()&& !userInGroup(item))
    	                       loadMoneyDialog(item , widgetClicked);
                            break;
       }      
       
 
	 }
	
	protected void closeTab(){
		if(wishlistBoxPanel.isVisible()){
			wishlistBoxPanel.setVisible(false);
			friendWishTable.clear(true);
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
     		        		"<img   src="+"'"+item.getThumbnail()+"'"+"alt='"+item.getItemName()+"' height='90' width='90' style = 'border-style:inset;border-color:green;'>", 5000 ,"yourcssclass");
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
        	      friendWishTable.setWidget(row, 2,new Label(format.format(item.getPrice())) );
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
        	      Label price = new Label (format.format(sum) +" / "+format.format(item.getPrice()));
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
            	friendWishTable.getWidget(row, 0).addStyleName(constants.cwInactiveRowStyle());
            }
            
            row ++;
        }
	}

	public void service_eventGetWishlistFailed(Throwable caught) {
		this.parent.entryPoint.messages.setText(caught.getMessage());
	}
	
	public void service_eventBookItemForUserFailed(Throwable caught) {
		this.parent.entryPoint.messages.setText(caught.getMessage());		
	}

	public void service_eventBookItemForUserSuccesfull() {
		parent.entryPoint.iBuyGUI.makeDirtyIBuyItems();
		this.wishlistService.getWishlist(currentEvent.getUserId(), currentEvent.getEventId());
		
	}

	public void service_eventAddParticipatorFailed(Throwable caught) {
		this.parent.entryPoint.messages.setText(caught.getMessage());		
	}

	public void service_eventAddParticipatorSuccesfull() {
		parent.entryPoint.iBuyGUI.makeDirtyIBuyItems();
		this.wishlistService.getWishlist(currentEvent.getUserId(), currentEvent.getEventId());		
	}

	public void service_eventGetPolaniItemsFailed(Throwable caught) {
		this.parent.entryPoint.messages.setText(caught.getMessage());		
	}

	public void service_eventGetPolaniItemsSuccesfull(ArrayList<WishlistItemPolaniData> result) {
		polaniItems = result;
		showPolaniItems();	
		
	}
	
}


	    
		
		


