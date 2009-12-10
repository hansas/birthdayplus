package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;

import org.gwtwidgets.client.util.regex.Pattern;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;

import com.tau.birthdayplus.client.Actions;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class MyWishlistTabGUI {
    /*
     * constants
     */
	CwConstants constants = GWT.create(CwConstants.class);
//	private static final int LINK = 0;
	private static final int UPDATE_LINK = 3;
    private static final int DELETE_LINK = 4;

	/*GUI Widgets*/

	//VerticalPanel for the content of wishlist
	public VerticalPanel wishlistVerticalPanel;
	// wishlist table
	public TableWithHeader wishTable;
	// add new item button
	public Button addItemButton;
	//box for adding new item
	
	
	protected DialogBox addItemBox;
	//table for the add form
	VerticalPanel itemDialogBoxVerticalPanel;
	protected FlexTable formTable;
	//name of the item 
	protected TextBox itemField;
	//priority of the item
    private HorizontalPanel priorityPanel;
    private RadioButton highPriorityButton;
    private RadioButton lowPriorityButton;
	//   protected ListBox priorityField;
    //link to the item
    protected TextBox linkField;
    //item's price
    protected TextBox priceField;
    //ok button in dialog box
  //  public Button updateButton;
    private Button boxButton;
    //cancel button
    private Button cancelButton;
    
    private Label errorMsgLabel ;
    
    private Boolean addItem;


	
 
	/*  
	 * Data Model
	 */
	private ArrayList<WishlistItemData> items;
    private WishlistItemData currentItem;
    public MyWishlistDelegate wishlistService;
    public Birthdayplus entryPoint;
    private String linkText;
    
    

	/**
	 * This is the entry point method.
	 */
	public void init() {
		//it's the main panel for this tab
		wishlistVerticalPanel=new VerticalPanel();
		wishlistVerticalPanel.addStyleName("wishPanel");
		buildWishlistTable();
		buildAddItemBox();
		
		addItemButton=new Button("Add item");
		
		
		wishlistVerticalPanel.add(wishTable);
		wishlistVerticalPanel.add(addItemButton);
		    
		
		    
		   
		 //   ArrayList<WishlistItemData> data=new ArrayList<WishlistItemData>();
		//    for(int i=0;i<5;i++){
		 //   	data.add(new WishlistItemData(entryPoint.userId,"name"+i,i,"http://techblog.maydu.eu/?p=7",500));
		    	
		    	
		 //   }
		    
		 //   service_eventGetWishlistSuccesfull(data);
		
	}

	
    /*
     * build add form
     */
    private void buildForm() {
        formTable = new FlexTable();
        
     //   formTable.setStyleName(constants.cwTableStyle());
   
        formTable.setText(0, 0, "Item name");
        formTable.setWidget(0, 1, itemField);

        formTable.setText(1, 0, "priority");
        formTable.setWidget(1, 1, priorityPanel);
        
        formTable.setText(2, 0, "link");
        formTable.setWidget(2, 1, linkField);
        
        formTable.setText(3, 0, "price");
        formTable.setWidget(3, 1, priceField);
        
     //   formTable.setWidget(4, 0, updateButton);
        formTable.setWidget(4, 1, boxButton);
        formTable.setWidget(4, 2, cancelButton);
        
    }
    
    /*
     * build add item DialogBox
     */
	private void buildAddItemBox(){
	    	addItemBox=new DialogBox();
	    	
	    	itemDialogBoxVerticalPanel = new VerticalPanel();
	    
	        itemField=new TextBox();
	 
	        priorityPanel = new HorizontalPanel();
	        highPriorityButton = new RadioButton("priority", "High");
	        priorityPanel.add(highPriorityButton);
	        lowPriorityButton = new RadioButton("priority", "Low");
	        priorityPanel.add(lowPriorityButton);
	        
	        linkField=new TextBox();
	        
	        priceField=new TextBox();
	        
	        boxButton=new Button();
	   
		    cancelButton = new Button(constants.cwDialogBoxCancel());
		       
		    errorMsgLabel = new Label();
			errorMsgLabel.setStyleName("errorMessage");
			errorMsgLabel.setVisible(false);

			itemDialogBoxVerticalPanel.add(errorMsgLabel);

		    buildForm();
		    
		    itemDialogBoxVerticalPanel.add(formTable);
		    
		    addItemBox.add(itemDialogBoxVerticalPanel); 
		    
		
	}
	
	
	
	/*
	 * create flex table for wishlist items
	 */
	private void buildWishlistTable(){
		//create table for whishlistitems
	    wishTable=new TableWithHeader();
	    wishTable.setStyleName("tables");
	
	    wishTable.setHeader(0,"Item");
	    wishTable.setHeader(1,"Priority");
	    wishTable.setHeader(2,"Price");
        
		wishTable.getColumnFormatter().addStyleName(0, "tablesColumns");
		wishTable.getColumnFormatter().addStyleName(1, "tablesColumns");
		wishTable.getColumnFormatter().addStyleName(2, "tablesColumns");
	    
		
		wishTable.getColumnFormatter().addStyleName(3, "lastColumns");
		wishTable.getColumnFormatter().addStyleName(4, "lastColumns");
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
             this.addItemButton.setVisible(false);
             loadForm(item,Actions.UPDATE);
         } else if (col==DELETE_LINK) {
             this.wishlistService.deleteWishlistItem(item);
         }
      
    }
	 

	
	 /*
	  * show the popup box with filled fields
	  */
	 private void loadForm(WishlistItemData item,Actions action) {
		    addItemBox.center();
		    addItemBox.setText(action.toString()+" item");
		    boxButton.setText(action.toString());
   	        addItemBox.show();
   	        itemField.setFocus(true);
	        currentItem = item;
	        this.itemField.setText(item.getItemName());
	        this.linkField.setText(item.getLink());
	        this.priceField.setText(item.getPrice().toString());
	        this.highPriorityButton.setValue(true);
	    }


    /*
     * add new item
     */
    public void gui_eventAddButtonClicked() {
    	boolean valid = copyFieldDateToItem();
        if(valid){
            addItemButton.setVisible(true);
            errorMsgLabel.setVisible(false);
            addItemBox.hide();
            this.wishlistService.createWishlistItem(currentItem);
        }else
        	errorMsgLabel.setVisible(true);
    }
    
    /*
     * update item
     */
    public void gui_eventUpdateButtonClicked() {
    	boolean valid = copyFieldDateToItem();
        if(valid){
            addItemButton.setVisible(true);
            errorMsgLabel.setVisible(false);
            addItemBox.hide();
            this.wishlistService.updateWishlistItem(currentItem);
        }else
        	errorMsgLabel.setVisible(true);
    }
    /*
     * check if input is valid
     */
    private boolean copyFieldDateToItem(){
        if(itemField.getText().equals("")){
        	errorMsgLabel.setText("Enter item name ");
    		itemField.setFocus(true);
    		return false;
        }
    	currentItem.setItemName(itemField.getText());
    	
    	if(highPriorityButton.getValue())
        	currentItem.setPriority(5);
        else
        	currentItem.setPriority(1);
    	
        currentItem.setLink(linkField.getText());
        
        if(priceField.getText().equals("")){
        	errorMsgLabel.setText("Enter item price ");
    		priceField.setFocus(true);
    		return false;
        }
        try{
        	int price=Integer.parseInt(priceField.getText());
        	if(price < 1){
        		errorMsgLabel.setText("Enter valid price ");
        		priceField.setFocus(true);
        		return false;
        	}
        	 currentItem.setPrice(price);
        }catch(NumberFormatException ex){
        	errorMsgLabel.setText("Enter valid price ");
    		priceField.setFocus(true);
    		return false;
        }
        return true;
    }
    
    /*
     * on click on add item
     */
    public void gui_eventAddItemButtonClicked() {
        this.addItemButton.setVisible(false);
        this.addItem = true;
        loadForm(new WishlistItemData(entryPoint.userId),Actions.CREATE);
    }
    
    /*
     * on click on cancel button in dialog box
     */
    public void gui_eventCancelButtonClicked(){
    	addItemButton.setVisible(true);
    	errorMsgLabel.setVisible(false);
    	 addItemBox.hide();
    }

	/*
	 * wishlist returned from the server
	 */
	public void service_eventGetWishlistSuccesfull(ArrayList<WishlistItemData> result) {
	        this.items = result;
	        this.wishTable.clear();
	        
	        RowFormatter rf = wishTable.getRowFormatter();
           	        
	        int row = 0;
	        for (WishlistItemData item : result) {
	        	//link
	        	if (item.getLink().equals(""))
	        		wishTable.setWidget(row, 0,new Label(item.getItemName()));
	        	else
	        		wishTable.setWidget(row, 0,new Anchor(item.getItemName(),item.getLink(),"_blank"));
	        	//priority
	        	if(item.getPriority()== 5)
	        		wishTable.setWidget(row,1,new Label("high"));
	        	else	
	    	        wishTable.setWidget(row,1,new Label("low"));
	        	
	    	    wishTable.setWidget(row, 2,new Label(item.getPrice().toString()) );
	    	    
	    	    Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
			    updateImage.setTitle("update item");
			    Image deleteImage = new Image( GWT.getModuleBaseURL() + "trash_16.png");
			    deleteImage.setTitle("delete item");
			    
			
	    	    wishTable.setWidget(row, 3, updateImage);
	    	    wishTable.setWidget(row,4,deleteImage); 
	    	    if(item.getIsActive())
	    	    	rf.addStyleName(row,constants.cwActiveRowStyle());
	    	    else
	    	    	rf.addStyleName(row, constants.cwInactiveRowStyle());

	    	    
	    	    wishTable.getRowFormatter().addStyleName(row, "tablesRows");	    	    
	    	    row ++;
	        }
	    }
	
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
	//	showMessage("Unable to delete item");
	}
	
	
	public void wireMyWishlistGUIEvents() {
		this.wishTable.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                 Cell cellForEvent = wishTable.getCellForEvent(event);
                 gui_eventItemGridClicked(cellForEvent);                
            }});
        
		this.addItemButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui_eventAddItemButtonClicked();
            }});

		this.boxButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	if(addItem)
            		gui_eventAddButtonClicked();
            	else
            	    gui_eventUpdateButtonClicked();
            }});
        
	
        
		this.cancelButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        	    gui_eventCancelButtonClicked();
        	}
        });
		
		this.priceField.addKeyUpHandler(new KeyUpHandler(){
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_TAB) 
					gui_eventGetPriceByLink();
				}
		});
  
	}
	
	private void gui_eventGetPriceByLink(){
		entryPoint.loadingImagePopup.center();
		entryPoint.loadingImagePopup.show();
		if(linkField.getText().startsWith("http://www.zap.co.il/model.aspx?")){
			bringLink(linkField.getText());
		}else
			entryPoint.loadingImagePopup.hide();
		
	}
	public  native void bringLink(String url) /*-{
		thisTabGui = this;
	    var params = {};
	    params[$wnd.gadgets.io.RequestParameters.CONTENT_TYPE] = $wnd.gadgets.io.ContentType.TEXT; 
		$wnd.gadgets.io.makeRequest(url, response, params); 

	function response(obj) {
		    var responseText = obj.text; 
			thisTabGui.@com.tau.birthdayplus.client.widgets.MyWishlistTabGUI::linkText=obj.text;
			thisTabGui.@com.tau.birthdayplus.client.widgets.MyWishlistTabGUI::parse()();
	};
}-*/;
	
	
	
	private void parse(){
		Integer price = 0;
		Pattern pattern = new Pattern("[1-9],{0,1}(\\d)* ₪");
		String[] prices = pattern.match(linkText);
		if(prices.length != 0){
			String temp = prices[0].substring(0,prices[0].length()-2);
	    	try{
	    	    price = Integer.parseInt(temp);
	    	 }catch(NumberFormatException ex){
	    		 String[] splitString= temp.split(",");
	    		 temp = splitString[0]+splitString[1];
	    		 try{
	    			 price = Integer.parseInt(temp);
	    		 }catch(NumberFormatException exc){ 
	    			 
	    		 }
	    	 }
		}
	    this.priceField.setText(price.toString());	 
	    entryPoint.loadingImagePopup.hide();
			
		
	}
	
	
	
	
	

	  
	  
	  
	  
	  
	  
	
	  
	
	
	

	
	
}


	    
		
		


