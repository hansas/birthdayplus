package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.WishlistItemData;




public class IBuyTabGUI {
	//////////////////Constants///////////////////////////
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int CANCEL_LINK = 4;
	private static final int NAME_LINK   =1;
	

	//////////////////GUI Widgets////////////////////////
	//VerticalPanel for the content of wishlist
	public VerticalPanel wishlistVerticalPanel;
	// wishlist table
	public TableWithHeader wishTable;
	
	
	//////////////////Model///////////////////////////////
	ArrayList<WishlistItemData> itemsToBuy;
	WishlistItemData currentItem;
	public IBuyDelegate wishlistService;
	public Birthdayplus entryPoint;
	

	

	
	/**
	 * build the Tab
	 */
	public void init() {
		wishlistVerticalPanel=new VerticalPanel();
		wishlistVerticalPanel.setTitle("Booked Items ");
		buildWishlistTable();
		wishlistVerticalPanel.add(wishTable);
		    
		 ArrayList<WishlistItemData> data=new ArrayList<WishlistItemData>();
		    for(int i=0;i<5;i++){
		    	data.add(new WishlistItemData(entryPoint.userId,"name"+i,i,"http://techblog.maydu.eu/?p=7",500));
		    }
		    
		service_getBookedWishlistSuccesfull(data);
	}
	
	
	
	/*
	 * create flex table for wishlist items
	 */
	private void buildWishlistTable(){
		//create table for whishlistitems
	    wishTable=new TableWithHeader();
	    wishTable.setStyleName(constants.cwTableStyle());
	    
	    //header
	    wishTable.setHeader(0,"For whom");
	    wishTable.setHeader(1,"Item");
	    wishTable.setHeader(2,"Priority");
	    wishTable.setHeader(3,"Price");
	}
	
	  
	  
	  
	  public void gui_eventItemGridClicked(Cell cellClicked) {
	         int row = cellClicked.getRowIndex();
	         int col = cellClicked.getCellIndex();
	        
	         WishlistItemData item = this.itemsToBuy.get(row);
	         
	        
	         if (col==NAME_LINK) {
	        	 if(item.getLink()!=null)
	        		 Window.open(item.getLink(), "_blank", null);
	         } else if (col==CANCEL_LINK) {
	             this.wishlistService.setActiveWishlistitem(item.getWishlistItemId());
	         }
	    }


		public void service_getBookedWishlistSuccesfull(
				ArrayList<WishlistItemData> result) {
			this.itemsToBuy = result;
	        this.wishTable.clear();
	        
	        int row = wishTable.getRowCount();
	        
	        for (WishlistItemData item : result) {
	        	wishTable.setWidget(row,0,new Label("Name of the person"));
	        	if (item.getLink()== null)
	        		wishTable.setWidget(row, 1,new Label(item.getItemName()));
	        	else
	        		wishTable.setWidget(row, 1,new Hyperlink(item.getItemName(),null));
	    	    wishTable.setWidget(row,2,new Label(item.getPriority().toString()));
	    	    wishTable.setWidget(row, 3,new Label(item.getPrice().toString()) );
	    	    wishTable.setWidget(row, 4, new Hyperlink("cancel", null)); 
	            row ++;
	        }
			    
		}
		
		public void service_setActiveWishlistitemSuccessful() {
	//		showMessage("Item was successfully canceled");
	        this.wishlistService.getBookedWishlist(entryPoint.userId);

			
		}
	  
	  public void service_setActiveWishlistitemFailed(Throwable caught) {
			//showMessage("Unable to cancel this item");

			
		}

		
		
		public void service_getBookedWishlistFailed(Throwable caught) {
			//status.setText("Unable to get booked item list");

			
		}

	  
}
