package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.WishlistItemData;



public class MyWishlistTabGUI {
    /*
     * constunts
     */
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int UPDATE_LINK = 4;
    private static final int DELETE_LINK = 5;

	/*GUI Widgets*/

	//VerticalPanel for the content of wishlist
	protected VerticalPanel wishlistVerticalPanel;
	// wishlist table
	protected TableWithHeader wishTable;
	// add new item button
	protected Button addItemButton;
	//box for adding new item
	
	
	protected DialogBox addItemBox;
	//table for the add form
	protected FlexTable formTable;
	//name of the item 
	protected TextBox itemField;
	//priority of the item
    protected ListBox priorityField;
    //link to the item
    protected TextBox linkField;
    //item's price
    protected TextBox priceField;
    //ok button in dialog box
    protected Button updateButton;
    protected Button addButton;
    //cancel button
    protected Button cancelButton;


	

	/*  
	 * Data Model
	 */
	private ArrayList<WishlistItemData> items;
    private WishlistItemData currentItem;
    protected MyWishlistDeligate wishlistService;
    protected String userId;
    
    
    

	/**
	 * This is the entry point method.
	 */
	public void init() {
		wishlistVerticalPanel=new VerticalPanel();
		buildWishlistTable();
		buildAddItemBox();
		
		addItemButton=new Button("Add item");
		
		
		wishlistVerticalPanel.add(wishTable);
		wishlistVerticalPanel.add(addItemButton);
		    
		 // Associate the Main panel with the HTML host page.
		    RootPanel.get("eventList").add(wishlistVerticalPanel);
		    
		  //  for (int i=0;i<5;i++){
		    //	fillRowFriendWishTable("dummy");
		    	
		   // }
		
	}

	
    /*
     * build add form
     */
    private void buildForm() {
        formTable = new FlexTable();
        
        formTable.setStyleName("cw-FlexTable");
   
        formTable.setText(0, 0, "Item name");
        formTable.setWidget(0, 1, itemField);

        formTable.setText(1, 0, "priority");
        formTable.setWidget(1, 1, priorityField);
        
        formTable.setText(2, 0, "link");
        formTable.setWidget(2, 1, linkField);
        
        formTable.setText(3, 0, "price");
        formTable.setWidget(3, 1, priceField);
        
        formTable.setWidget(4, 0, updateButton);
        formTable.setWidget(4, 1, addButton);
        formTable.setWidget(4, 2, cancelButton);
        
    }
    
    /*
     * build add item DialogBox
     */
	private void buildAddItemBox(){
	    	addItemBox=new DialogBox();
	        addItemBox.setText("Add new item ");
	        addItemBox.addStyleName("cw-DialogBox");
	        
	        itemField=new TextBox();
	        itemField.setStyleName("cw-TextBox");
	        
	    	priorityField=new ListBox(false);
	        String[] listTypes = constants.cwListBoxCategories();
	        for (int i = 0; i < listTypes.length; i++) {
	          priorityField.addItem(listTypes[i]);
	        }
;           priorityField.setStyleName("cw-ListBox");

	        linkField=new TextBox();
	        linkField.setStyleName("cw-TextBox");
	        
	        priceField=new TextBox();
	        priceField.setStyleName("cw-TextBox");
	        
	        updateButton=new Button("Update item");
	        updateButton.setStyleName("cw-Button");
	        
	        addButton=new Button("Add item");
	        addButton.setStyleName("cw-Button");
	        
		    cancelButton = new Button(constants.cwDialogBoxCancel());
		       
		    cancelButton.setStyleName("cw-Button");

		    buildForm();
		    
		    addItemBox.add(formTable); 
		    
		
	}
	
	
	
	/*
	 * create flex table for wishlist items
	 */
	private void buildWishlistTable(){
		//create table for whishlistitems
	    wishTable=new TableWithHeader();
	    wishTable.setStyleName("cw-TableWithHeader");
	    
	    FlexCellFormatter cellFormatter =  wishTable.getFlexCellFormatter();
	    wishTable.setWidth("32em");
	    wishTable.setCellSpacing(5);
	    wishTable.setCellPadding(3);
	    
	    
	    wishTable.setHeader(0,"Item");
	    wishTable.setHeader(1,"Priority");
	    wishTable.setHeader(2,"Price");
        
	    
	}
	
	  /**
	   * Fill row in friend's wishlist table,by given wishlistItemData
	   *
	  private void fillRowFriendWishTable(String item) {
	    int numRows = wishTable.getRowCount();
	    wishTable.setWidget(numRows, 0,new Hyperlink("Item"+numRows,"Items"+numRows));
	    wishTable.setWidget(numRows,1,new Label("Priority"));
	    wishTable.setWidget(numRows, 2,new Label("price") );
	    wishTable.setWidget(numRows, 3, new Hyperlink("update", null));
	    wishTable.setWidget(numRows,4,new Hyperlink("delete", null));
	  }
	  */
	
	
	 public void gui_eventItemGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
         WishlistItemData item = this.items.get(row);
         
        
         if (col==UPDATE_LINK) {
             this.addButton.setVisible(false);
             this.updateButton.setVisible(true);
             this.addItemButton.setVisible(false);
             loadForm(item);
         } else if (col==DELETE_LINK) {
             this.wishlistService.deleteWishlistItem(item.getWishlistItemId());
         }
    }
	 
	 /*
	  * show the popup box with filled fields
	  */
	 private void loadForm(WishlistItemData item) {
		    addItemBox.center();
   	        addItemBox.show();
	        currentItem = item;
	        this.itemField.setText(item.getItemName());
	        this.linkField.setText(item.getLink());
	        this.priceField.setText(item.getPrice().toString());
	    }



    public void gui_eventAddButtonClicked() {
    	boolean valid=true;
        try{    
        copyFieldDateToItem();
        }catch(NumberFormatException ex){
     //   	showMessage("The price should be a number");
        }
        if(valid){
            addButton.setVisible(true);
            addItemBox.hide();
            this.wishlistService.createWishlistItem(currentItem);
        }
    }

    public void gui_eventUpdateButtonClicked() {
    	boolean valid=true;
        try{
        copyFieldDateToItem();
        }catch(NumberFormatException ex){
        	valid=false;
     //   	showMessage("The price should be a number");
        }
        if(valid){
            addButton.setVisible(true);
            addItemBox.hide();
            this.wishlistService.updateWishlistItem(currentItem);
        }
    }
    /*
     * check if input is valid
     */
    private void copyFieldDateToItem(){
    	int price;
    	currentItem.setItemName(itemField.getText());
        currentItem.setLink(linkField.getText());
        currentItem.setPriority(Integer.parseInt(constants.cwListBoxCategories()[priorityField.getSelectedIndex()]));
        try{
        	price=Integer.parseInt(priceField.getText());
        }catch(NumberFormatException ex){
        	 throw ex;
        }
        currentItem.setPrice(price);

    }

    public void gui_eventAddItemButtonClicked() {
        this.addItemButton.setVisible(false);
        this.updateButton.setVisible(false);
        this.addButton.setVisible(true);
        loadForm(new WishlistItemData());
    }
    
    public void gui_eventCancelButtonClicked(){
    	 addItemBox.hide();
    }

	  
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemData> result) {
	        this.items = result;
	        this.wishTable.clear();
	        
	        int row = wishTable.getRowCount();
	        for (WishlistItemData item : result) {
	        	wishTable.setWidget(row, 0,new Hyperlink(item.getItemName(),item.getLink()));
	    	    wishTable.setWidget(row,1,new Label(item.getPriority().toString()));
	    	    wishTable.setWidget(row, 2,new Label(item.getPrice().toString()) );
	    	    wishTable.setWidget(row, 3, new Hyperlink("update", null));
	    	    wishTable.setWidget(row,4,new Hyperlink("delete", null)); 
	            row ++;
	        }
	    }
	
	public void service_eventCreateWishlistItemSuccessful(){
	//	showMessage("Item was successfully created");
        this.wishlistService.getWishlist(userId);

		
	}
	
	public void service_eventUpdateWishlistItemSuccessful(){
		//showMessage("Item was successfully updated");
        this.wishlistService.getWishlist(userId);
		
	}
	
	public void service_deleteWishlistItemSuccessful(){
	//	showMessage("Item was successfully deleteded");
        this.wishlistService.getWishlist(userId);
	}
	
	public void service_eventGetWishlistFailed(Throwable caught){
		// showMessage("Unable to get  wishlist");

	}
	
	public void service_eventCreateWishlistItemFailed(Throwable caught){
	//	showMessage("Unable to create item");
	}
	
	public void service_eventUpdateWishlistItemFailed(Throwable caught){
		//showMessage("Unable to update item");
	}
	
	public void service_deleteWishlistItemFailed(Throwable caught){
	//	showMessage("Unable to delete item");
	}
	
	
	
	

	  
	  
	  
	  
	  
	  
	
	  
	
	
	

	
	
}


	    
		
		


