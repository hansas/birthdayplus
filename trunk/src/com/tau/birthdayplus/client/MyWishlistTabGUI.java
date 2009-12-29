package com.tau.birthdayplus.client;


import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.ItemDialogBox;
import com.tau.birthdayplus.client.widgets.TooltipListener;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class MyWishlistTabGUI {
	// CONSTANTS 
	private static CwConstants constants = GWT.create(CwConstants.class);
	private static  NumberFormat shortMoneyFormat = NumberFormat.getFormat("\u20AA#,##0");
	private static final int UPDATE_LINK = 3;
    private static final int DELETE_LINK = 4;

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

    
    

	/**
	 * This is the entry point method.
	 */
	public void init() {
		wishlistPanel = new FlowPanelMenuTitle();
		entryPoint.tab.add(wishlistPanel, "My Wishlist");
		wishlistPanel.addStyleName("Panel");
	//	wishlistPanel.setSize("100%", "350px");
		
		
		Command addItemCommand = new Command(){
			public void execute() {
				gui_eventAddItemButtonClicked();
		      }
		    };
		
		wishlistPanel.addMenuItem("Add Item",addItemCommand).setTitle("Add new item to your wishlist");
		
		wishlistPanel.addMenuItem("<a href=\"javascript:(function(){function%20I(u){var%20t=u.split('.'),e=t[t.length-1].toLowerCase();return%20{gif:1,jpg:1,jpeg:1,png:1,mng:1}[e]}var%20q,h,i;for(i=0;q=document.links[i];++i){h=q.href;if(h&&I(h)){break;}}void(open('http://testrpcplus.appspot.com/birthdayplus/addWishlistItem?autoclose=yes&link='+encodeURIComponent(location.href)+'&wish='+encodeURIComponent(document.title)+'&thumbnail='+encodeURIComponent(h),'Birthday+','scrollbars=yes,menubars=no,toolbars=no,resizable=yes,width=800,height=500'))})()\">Add to Wishlist</a>",true,new Command(){
		 public void execute() {
			}		
		}).setTitle("Just drag this button to the bookmarks toolbar in your web browser.");
	   
		
		buildWishlistTable();
	//	buildAddItemBox();
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
	//	wishTableHeader.setSize("100%", "25px");
		
		wishTableHeader.getColumnFormatter().setWidth(0, "100px");
		wishTableHeader.getColumnFormatter().setWidth(1, "50px");
	
				
		wishTableHeader.setWidget(0, 0, new Label("Item"));
		wishTableHeader.setWidget(0,1, new Label("Priority"));
		wishTableHeader.setWidget(0, 2, new Label("Price"));
		wishTableHeader.getFlexCellFormatter().setColSpan(0, 2, 3);
		
		wishlistScrollPanel = new ScrollPanel();
		wishlistPanel.add(wishlistScrollPanel);
		wishlistScrollPanel.addStyleName("ShortScrollPanel");
	//	wishlistScrollPanel.setSize("100%", "300px");
		//create table for whishlistitems
		
		wishTable = new HoverTable(0,5);
		wishlistScrollPanel.add(wishTable);
		wishTable.addStyleName("Table");
	//	wishTable.setWidth("100%");
		
		wishTable.getColumnFormatter().setWidth(0, "100px");
		wishTable.getColumnFormatter().setWidth(1, "50px");
		wishTable.getColumnFormatter().setWidth(2, "80px");

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
           //  this.addItemButton.setVisible(false);
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
		    addItemBox.setText(action.toString()+" item");
		    addItemBox.setText(action.toString());
		    addItemBox.center();
   	        addItemBox.show(item);
	    }


    /*
     * on click on add item
     */
    public void gui_eventAddItemButtonClicked() {
       // this.addItemButton.setVisible(false);
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
	        		if((item.getThumbnail().equals("")) || (item.getThumbnail() == null))
	        	    	wishTable.setWidget(row, 0,new Anchor(item.getItemName(),item.getLink(),"_blank"));
	        		else{
	        			Anchor anchor =new Anchor(item.getItemName(),item.getLink(),"_blank");
	        			wishTable.setWidget(row,0,anchor);
	        			TooltipListener listener  = new TooltipListener(
	     		        		"<img   src="+"'"+item.getThumbnail()+"'"+"alt='"+item.getItemName()+"' height='90' width='90' style = 'border-style:inset;border-color:green;'>", 5000 ,"yourcssclass");
	        			anchor.addMouseListener( listener);
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
			    
	    	    wishTable.setWidget(row, 3, updateImage);
	    	    wishTable.setWidget(row,4,deleteImage); 
	    	    if(!item.getIsActive())
	    	    {
	    	        wishTable.getWidget(row, 0).addStyleName(constants.cwInactiveRowStyle());
	    	    	countInactive+=1;
	    	    }
	    	    
	    	    row ++;
	        }
	        
	        wishlistPanel.setTitle("Your friends will give you "+countInactive+" presents soon");
	    }
	
	
	 private  native void showMessage(String message)/*-{
	 	miniMessage.createDismissibleMessage(message);
	 	
	 }-*/;
	
	
	
	public void service_eventCreateWishlistItemSuccessful(){
	//	showMessage("Item was successfully created");
        this.wishlistService.getMyWishlist(entryPoint.userId);

		
	}
	
	public void service_eventUpdateWishlistItemSuccessful(){
		//showMessage("Item was successfully updated");
        this.wishlistService.getMyWishlist(entryPoint.userId);
		
	}
	
	public void service_deleteWishlistItemSuccessful(){
	//	showMessage("Item was successfully deleteded");
       this.wishlistService.getMyWishlist(entryPoint.userId);
	}
	
	public void service_eventGetWishlistFailed(Throwable caught){
	//	 showMessage("Unable to get  wishlist");

	}
	
	public void service_eventCreateWishlistItemFailed(Throwable caught){
	//	showMessage("Unable to create item");
	}
	
	public void service_eventUpdateWishlistItemFailed(Throwable caught){
		//showMessage("Unable to update item");
	}
	
	public void service_deleteWishlistItemFailed(Throwable caught){
	//	Window.alert(caught.getMessage());
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


	    
		
		


