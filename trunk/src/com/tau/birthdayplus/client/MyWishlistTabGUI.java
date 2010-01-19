package com.tau.birthdayplus.client;


import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.Action;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.Icons;
import com.tau.birthdayplus.client.widgets.StaticFunctions;

import com.tau.birthdayplus.client.widgets.ItemDialogBox.ItemDialogBox;
import com.tau.birthdayplus.client.widgets.ItemDialogBox.ItemEventHandler;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class MyWishlistTabGUI {
	// CONSTANTS 
	private static CwConstants constants = GWT.create(CwConstants.class);
	private static Icons icons = (Icons) GWT.create(Icons.class);
	private static final int UPDATE_LINK = 4;
    private static final int DELETE_LINK = 5;

	/*GUI Widgets*/
    private FlowPanelMenuTitle wishlistPanel;
	//private FlexTable wishTableHeader;
	private HoverTable wishTable;
	//private ScrollPanel wishlistScrollPanel;
	private ItemDialogBox addItemBox;
 
    
 
	/*  
	 * Data Model
	 */
	private  ArrayList<WishlistItemData> items = null;
    private WishlistItemData currentItem;
    protected MyWishlistDelegate wishlistService;
    protected Birthdayplus entryPoint;
    private Action currentAction = Action.NONE;
    
    public static native String getUserAgent() /*-{
    var ua = navigator.userAgent.toLowerCase();

    if (ua.indexOf("opera") != -1) {
            return "opera";
    }
    if (ua.indexOf("webkit") != -1) {
            return "safari";
    }
    if (ua.indexOf("msie") != -1) {
            return "ie";
    }
    if (ua.indexOf("gecko") != -1) {
            return "gecko";
    }
    return "unknown";
}-*/; 

	/**
	 * This is the entry point method.
	 */
	public void init() {
		wishlistPanel = new FlowPanelMenuTitle();
		entryPoint.tab.add(wishlistPanel, "My Wishlist");
		wishlistPanel.addStyleName("mainPanel");
		
		Command addItemCommand = new Command(){
			public void execute() {
				gui_eventAddItemButtonClicked();
		      }
		    };
		
		wishlistPanel.addMenuItem("Add Item",addItemCommand).setTitle("Add new item to your wishlist");
		
		if(getUserAgent().equals("ie")){
			wishlistPanel.addMenuItem("<a onclick=\"alert('Just add this link to Favorites in your web browser.'); return false;\" href=\"javascript:(function(){var%20s=document.createElement('script');s.setAttribute('src','http://birthdayplus.googlecode.com/svn/trunk/AddItem.js');s.setAttribute('type','text/javascript');document.getElementsByTagName('head')[0].appendChild(s);})()\">Add to Wish List</a>",true,new Command(){	
				public void execute() {
					}		
				}).setTitle("Add this link to Favorites and add items to the gadget from any site by clicking on this link from the site.");
		}else{
		
    		wishlistPanel.addMenuItem("<a onclick=\"alert('Just drag this button to the bookmarks toolbar in your web browser.'); return false;\" href=\"javascript:(function(){function%20I(u){var%20t=u.split('.'),e=t[t.length-1].toLowerCase();return%20{gif:1,jpg:1,jpeg:1,png:1,mng:1}[e]}var%20q,h,i;for(i=0;q=document.links[i];++i){h=q.href;if(h&&I(h)){break;}else h=null;}void(open('http://birthdayplus.appspot.com/birthdayplus/addWishlistItem?autoclose=yes&link='+encodeURIComponent(location.href)+'&wish='+encodeURIComponent(document.title)+'&thumbnail='+encodeURIComponent(h),'Birthdayplus','scrollbars=yes,menubars=no,toolbars=no,resizable=yes,width=800,height=500'))})()\"><img  border=\"0\" align=\"absmiddle\" name=\"Add to Wish List\" alt=\"Add to Wish List\" src=\"http://birthdayplus.googlecode.com/svn/trunk/addwishlist.png\"/></a>",true,new Command(){
	    	public void execute() {
	    	}		
    		}).setTitle("Add this button to the bookmarks and add items to the gadget from any site by clicking on this button from the site.");
		}
		buildWishlistTable();
		addItemBox = new ItemDialogBox();   
		
	
	
		
	}
	
	protected void showMyWishlistTab(){
		if (items == null)
		       this.wishlistService.getMyWishlist(entryPoint.userId);
		
	}


	/*
	 * create flex table for wishlist items
	 */
	private void buildWishlistTable(){
		FlexTable wishTableHeader = new FlexTable();
		wishlistPanel.add(wishTableHeader);
		wishTableHeader.addStyleName("TableHeader");
		wishTableHeader.setCellSpacing(0);
	
		
		wishTableHeader.getColumnFormatter().setWidth(0,StaticFunctions.getPercentWidthPixels(35, 40) +"px");
		wishTableHeader.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(20, 40)+"px");
	
	
				
		wishTableHeader.setWidget(0, 0, new Label("Item"));
		wishTableHeader.setWidget(0,1, new Label("Priority"));
		wishTableHeader.setWidget(0, 2, new Label("Price"));
		wishTableHeader.getFlexCellFormatter().setColSpan(0, 2, 3);
		
		ScrollPanel wishlistScrollPanel = new ScrollPanel();
		wishlistPanel.add(wishlistScrollPanel);
		wishlistScrollPanel.addStyleName("ShortScrollPanel");
	
		
		wishTable = new HoverTable(0,6);
		wishlistScrollPanel.add(wishTable);
		wishTable.addStyleName("Table");
		wishTable.setCellSpacing(0);
	
		
		wishTable.getColumnFormatter().setWidth(0, StaticFunctions.getPercentWidthPixels(35, 40) +"px");
		wishTable.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(20, 40)+"px");
		wishTable.getColumnFormatter().setWidth(2, StaticFunctions.getPercentWidthPixels(30, 40)+"px");
		wishTable.getColumnFormatter().setWidth(4,"20px");
		wishTable.getColumnFormatter().setWidth(5,"20px");
		

	}
	
	
	/*
	 * on click in the table
	 */
	 public void gui_eventItemGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
        WishlistItemData item = this.items.get(row);
        
        switch(col){
        case UPDATE_LINK : currentAction = Action.UPDATE;
        	               loadForm(item,currentAction);
                           break;
        case DELETE_LINK : this.wishlistService.deleteWishlistItem(item);
                           break;
        }
    }
	 

	
	 /*
	  * show the popup box with filled fields
	  */
	 private void loadForm(WishlistItemData item,Action action) {
		    currentItem = item; 
		    switch(action){
		    case CREATE: addItemBox.show(action);
		                 break;
		    case UPDATE: addItemBox.show(item.getItemName(),item.getLink(),item.getPrice(),item.getThumbnail(),action);
		                 break;
		    }
	    }


    /*
     * on click on add item
     */
    public void gui_eventAddItemButtonClicked() {
    	this.currentAction = Action.CREATE;
        loadForm(new WishlistItemData(entryPoint.userId),currentAction);
    }
    
    
    
   
	/*
	 * wishlist returned from the server
	 */
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemData> result) {
	        this.items = result;
	        this.wishTable.clear(true);
	        wishTable.resizeRows(result.size());
	        int countInactive = 0;
	        
           	        
	        int row = 0;
	        for (WishlistItemData item : result) {
	        	//link 
	        	Widget itemName = StaticFunctions.getAnchorWithThumbnail(item.getLink(),item.getItemName(), item.getThumbnail());
	        	wishTable.setWidget(row, 0, itemName);
	        	//priority
	        	String priority = item.getPriority() ? "hight" : "low" ;
	        	wishTable.setText(row, 1, priority);
	        	//price
	    	    wishTable.setText(row, 2,entryPoint.shortMoneyFormat.format(item.getPrice()));
	    	    //icons
	    	    wishTable.setWidget(row, UPDATE_LINK, StaticFunctions.createIcon(icons.updateIcon(),"update item"));
	    	    wishTable.setWidget(row,DELETE_LINK,StaticFunctions.createIcon(icons.deleteIcon(),"delete item"));
	    	    
	    	    if(!item.getIsActive())
	    	    {
	    	        wishTable.getWidget(row, 0).addStyleName(constants.cwInactiveRowStyle());
	    	    	countInactive+=1;
	    	    }
	    	    
	    	    row ++;
	        }
	        
	        wishlistPanel.setTitle("Your friends will give you "+countInactive+" presents soon");
	    }
	
	
	
	
	public void service_eventCreateWishlistItemSuccessful(){
        this.wishlistService.getMyWishlist(entryPoint.userId);

		
	}
	
	public void service_eventUpdateWishlistItemSuccessful(){
        this.wishlistService.getMyWishlist(entryPoint.userId);
		
	}
	
	public void service_deleteWishlistItemSuccessful(){
       this.wishlistService.getMyWishlist(entryPoint.userId);
	}
	
	public void service_eventGetWishlistFailed(Throwable caught){
        wishlistPanel.setTitle("");
		this.wishTable.clear(true);
        wishTable.resizeRows(0);
	    entryPoint.messages.setText("GetWishlistFailed"+caught.getMessage());

	}
	
	public void service_eventCreateWishlistItemFailed(Throwable caught){
	   entryPoint.messages.setText("CreateWishlistItemFailed"+caught.getMessage());
	}
	
	public void service_eventUpdateWishlistItemFailed(Throwable caught){
		entryPoint.messages.setText("UpdateWishlistItemFailed"+caught.getMessage());
	}
	
	public void service_deleteWishlistItemFailed(Throwable caught){
	    entryPoint.messages.setText("WishlistItemFailed"+caught.getMessage());
	}
	
	
	public void wireMyWishlistGUIEvents() {
		this.wishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                 Cell cellForEvent = wishTable.getCellForEvent(event);
                 if(cellForEvent!=null)
                    gui_eventItemGridClicked(cellForEvent);                
            }});
		
		this.addItemBox.addItemEvent(new ItemEventHandler(){
			public void onSaveItem(String itemName, Boolean priority,
					String link, Double price, String thumbnail) {
				currentItem.setItemName(itemName);
				currentItem.setPriority(priority);
				currentItem.setLink(link);
				currentItem.setPrice(price);
				currentItem.setThumbnail(thumbnail);
				
				switch (currentAction){
				case CREATE : wishlistService.createWishlistItem(currentItem);
				              break;
				case UPDATE : wishlistService.updateWishlistItem(currentItem);
				              break;
				}
			}
			
		});
		
		
        
	}
	
	

}


	    
		
		


