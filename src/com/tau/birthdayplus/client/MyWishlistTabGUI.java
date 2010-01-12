package com.tau.birthdayplus.client;


import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.ItemDialogBox;
import com.tau.birthdayplus.client.widgets.MessagePanel;
import com.tau.birthdayplus.client.widgets.StaticFunctions;

import com.tau.birthdayplus.client.widgets.TooltipListener;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class MyWishlistTabGUI {
	// CONSTANTS 
	private static CwConstants constants = GWT.create(CwConstants.class);
	private static  NumberFormat shortMoneyFormat = NumberFormat.getFormat("\u20AA#,##0");
	private static final int UPDATE_LINK = 4;
    private static final int DELETE_LINK = 5;

	/*GUI Widgets*/
    private FlowPanelMenuTitle wishlistPanel;
	private FlexTable wishTableHeader;
	private HoverTable wishTable;
	private ScrollPanel wishlistScrollPanel;
	private ItemDialogBox addItemBox;
    private Boolean addItem;
    
    
    private enum Actions {
    	CREATE("Add"), 
    	REMOVE("Delete"), 
    	UPDATE("Update");
    	
    	private String description;
    	
    	private Actions(String description){
    		this.description = description;
    	}
    	
    	public String toString(){
    		return description;
    	}
    }

 
	/*  
	 * Data Model
	 */
	private  ArrayList<WishlistItemData> items = null;
    private WishlistItemData currentItem;
    public MyWishlistDelegate wishlistService;
    public Birthdayplus entryPoint;

    
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
		wishTableHeader = new FlexTable();
		wishlistPanel.add(wishTableHeader);
		wishTableHeader.addStyleName("TableHeader");
		wishTableHeader.setCellSpacing(0);
	//	wishTableHeader.setSize("100%", "25px");
		
		wishTableHeader.getColumnFormatter().setWidth(0,StaticFunctions.getPercentWidthPixels(35, 40) +"px");
		wishTableHeader.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(20, 40)+"px");
	//	wishTableHeader.getColumnFormatter().setWidth(0, "100px");
	//	wishTableHeader.getColumnFormatter().setWidth(1, "55px");
	
				
		wishTableHeader.setWidget(0, 0, new Label("Item"));
		wishTableHeader.setWidget(0,1, new Label("Priority"));
		wishTableHeader.setWidget(0, 2, new Label("Price"));
		wishTableHeader.getFlexCellFormatter().setColSpan(0, 2, 3);
		
		wishlistScrollPanel = new ScrollPanel();
		wishlistPanel.add(wishlistScrollPanel);
		wishlistScrollPanel.addStyleName("ShortScrollPanel");
	//	wishlistScrollPanel.setSize("100%", "300px");
		//create table for whishlistitems
		
		wishTable = new HoverTable(0,6);
		wishlistScrollPanel.add(wishTable);
		wishTable.addStyleName("Table");
		wishTable.setCellSpacing(0);
	//	wishTable.setWidth("100%");
		
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
         
       
         if (col==UPDATE_LINK) {
        	 this.addItem = false;
             loadForm(item,Actions.UPDATE);
         } else if (col==DELETE_LINK) {
             this.wishlistService.deleteWishlistItem(item);
         }
    }
	 

	
	 /*
	  * show the popup box with filled fields
	  */
	 private void loadForm(WishlistItemData item,Actions action) {
		    currentItem = item; 
		    addItemBox.setText(action.toString()+" Item");
		    addItemBox.setButtonText(action.toString()+ " item");
		    addItemBox.center();
   	        addItemBox.show(item);
	    }


    /*
     * on click on add item
     */
    public void gui_eventAddItemButtonClicked() {
        this.addItem = true;
        loadForm(new WishlistItemData(entryPoint.userId),Actions.CREATE);
    }
    
   
	/*
	 * wishlist returned from the server
	 */
	@SuppressWarnings("deprecation")
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemData> result) {
	        this.items = result;
	        this.wishTable.clear(true);
	        wishTable.resizeRows(result.size());
	        int countInactive = 0;
	        
           	        
	        int row = 0;
	        for (WishlistItemData item : result) {
	        	//link
	        	if ((item.getLink()== null) ||(item.getLink().equals("")))
	        		wishTable.setWidget(row, 0,new Label(item.getItemName()));
	        	else{
	        		if((item.getThumbnail() == null) || (item.getThumbnail().equals(""))  )
	        	    	wishTable.setWidget(row, 0,new Anchor(item.getItemName(),item.getLink(),"_blank"));
	        		else{
	        		//	Anchor anchor =new Anchor(item.getItemName(),item.getLink(),"_blank");
	        		
	        			wishTable.setWidget(row,0,StaticFunctions.getAnchorWithThumbnail(item.getLink(), item.getItemName(), item.getThumbnail()));
	        		//	TooltipListener listener  = new TooltipListener(
	     		   ///     		"<img   src="+"'"+item.getThumbnail()+"'"+"alt='"+item.getItemName()+"' height='90' width='90' style = 'background-color:  #f7d8a9; padding: 3px; border: 1px solid #6f3d29;'>", 5000 ,true);
	        		//	anchor.addMouseListener( listener);
	        		}
	        	    	
	        	}
	        	//priority
	        	if(item.getPriority())
	        		wishTable.setText(row,1,"high");
	        	else	
	    	        wishTable.setText(row,1,"low");
	        	        
	    	    wishTable.setText(row, 2,shortMoneyFormat.format(item.getPrice()));
	    	    
	    	    Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
			    updateImage.setTitle("update item");
			    Image deleteImage = new Image( GWT.getModuleBaseURL() + "trash_16.png");
			    deleteImage.setTitle("delete item");
			    
	    	    wishTable.setWidget(row, UPDATE_LINK, updateImage);
	    	    wishTable.setWidget(row,DELETE_LINK,deleteImage); 
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
                 gui_eventItemGridClicked(cellForEvent);                
            }});
		
		this.addItemBox.addCloseHandler(new CloseHandler<PopupPanel>(){

			public void onClose(CloseEvent<PopupPanel> event) {
				if(event.isAutoClosed())
					return;
				WishlistItemData data = addItemBox.getInput();
				if(data == null)
					return;
				if(addItem)
					wishlistService.createWishlistItem(data);
				else
					wishlistService.updateWishlistItem(data);
			}
		
		});
        
	}
	
	

}


	    
		
		


