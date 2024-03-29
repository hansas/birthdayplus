package com.tau.birthdayplus.addItem;




import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;


import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.tau.birthdayplus.dto.client.WishlistItemData;

public class AddItem implements EntryPoint{
	private final AddItemServiceAsync wishlistService = GWT.create(AddItemService.class);
	
 	private PopupPanel loadingImagePopup;
	
	private VerticalPanel mainPanel;
	
	private FlexTable formTable; 
	private TextBox itemField;
    private HorizontalPanel priorityPanel;
    private RadioButton highPriorityButton;
    private RadioButton lowPriorityButton;
    private TextBox linkField;
    private TextBox priceField;
    private TextBox thumbnailField;
    private Button boxButton;
    private Button cancelButton;
    private Label errorMsgLabel ;
    private CheckBox rightImage;
    
    
 
    
  


	public void onModuleLoad() {
	
		 Window.enableScrolling(false);
	        Window.setMargin("0px");
		String link = decode(Window.Location.getParameter("link")); 
		String wish = decode(Window.Location.getParameter("wish"));
		String thumbnail = decode(Window.Location.getParameter("thumbnail"));
		String price = Window.Location.getParameter("price");
		
		 
		
	//	DecoratorPanel	 decPanel = new DecoratorPanel();
	//	RootPanel.get().add(decPanel);
	//	decPanel.addStyleName("mainPanel");
		
	    mainPanel = new VerticalPanel();
	    RootPanel.get().add(mainPanel);
	    mainPanel.addStyleName("mainPanel");
    //	decPanel.setWidget(mainPanel);
    	mainPanel.setSize("100%", "100%");
    	
  //  	mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    	

    
    	
    	Image logo = new Image(GWT.getModuleBaseURL()+"Birthday+.gif");
		mainPanel.add(logo);
//		mainPanel.setCellHorizontalAlignment(logo,HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setCellHeight(logo, "20%");
	
    
	    errorMsgLabel = new Label();
	    mainPanel.add(errorMsgLabel);
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		
		
		
		if(thumbnail.equals("null"))
			thumbnail="";
	    buildForm(link,wish,thumbnail,price);
	    
	    
	    Image loadingImage=new Image( GWT.getModuleBaseURL() + "ajax-loader.gif");
	    loadingImage.addStyleName("loadingImage");
		

		
	    loadingImagePopup = new PopupPanel(false,true);
	    loadingImagePopup.setAnimationEnabled(true);
	    loadingImagePopup.setWidget(loadingImage);
	    loadingImagePopup.addStyleName("loading");
	    
	 //   resize(430,450);
	    
	    wireEvents();
		
	}
	
	private  native String decode(String data)/*-{
		return decodeURIComponent(data);
	}-*/;
	

	
	
	  private void buildForm(String link,String wish,String thumbnail,String price) {
		  formTable = new FlexTable();
	        mainPanel.add(formTable);
	        mainPanel.setCellHeight(formTable, "75%");
	        formTable.addStyleName("table");
	        formTable.setCellPadding(10);
	        formTable.setCellSpacing(0);
	      
	        
	  
	      
	        
	        itemField=new TextBox();
	        itemField.setMaxLength(22);
	        itemField.setVisibleLength(30);
	   	 
	        priorityPanel = new HorizontalPanel();
	        highPriorityButton = new RadioButton("priority", "High");
	        priorityPanel.add(highPriorityButton);
	        lowPriorityButton = new RadioButton("priority", "Low");
	        priorityPanel.add(lowPriorityButton);
	        
	        linkField=new TextBox();
	        linkField.setVisibleLength(30);
	        
	        priceField=new TextBox();
	        priceField.setMaxLength(10);
	        priceField.setVisibleLength(30);
	        
	        thumbnailField=new TextBox();
	        
	        boxButton=new Button("Save");
	   
		    cancelButton = new Button("Close ");
	        

	   
	        formTable.setText(0, 0, "Item name");
	        formTable.setWidget(0, 1, itemField);
	        formTable.getFlexCellFormatter().setColSpan(0, 1, 2);
	        if(wish.length()> 22)
	        	wish = wish.substring(0, 21);
	        this.itemField.setText(wish);
	       

	        

	        formTable.setText(1, 0, "Priority");
	        formTable.setWidget(1, 1, priorityPanel);
	        formTable.getFlexCellFormatter().setColSpan(1, 1, 2);
	        highPriorityButton.setValue(true);
	        
	        
	        formTable.setText(2, 0, "Link");
	        formTable.setWidget(2, 1, linkField);
	        formTable.getFlexCellFormatter().setColSpan(2, 1, 2);
	        this.linkField.setText(link);

	        
	        formTable.setText(3, 0, "Price  ");
	        formTable.setWidget(3, 1, priceField);
	        formTable.getFlexCellFormatter().setColSpan(3, 1, 2);
	        this.priceField.setText(price);

	        
	        this.thumbnailField.setText(thumbnail);
	        rightImage = new CheckBox("This is the image of the item");
	        rightImage.setValue(true);
	        
	        if(!thumbnail.equals("")){
	        	Image thumbImage = new Image(thumbnail);
	        	formTable.setWidget(4, 0, thumbImage);
	        	thumbImage.setSize("120px", "120px");
	        	formTable.getCellFormatter().setStyleName(4,0,"image");
	        	
		        formTable.setWidget(4, 1, rightImage);
		        formTable.getFlexCellFormatter().setColSpan(4, 1, 2);
		        formTable.getRowFormatter().addStyleName(4, "row");
	        }
	        
	        
	     //   formTable.setWidget(4, 0, updateButton);
	        formTable.setWidget(5, 1, boxButton);
	        formTable.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
	        formTable.setWidget(5, 2, cancelButton);
	        formTable.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_RIGHT);
	        
	    }
	    
	    
	    private boolean checkIfValid(){
	        if(itemField.getText().equals("")){
	        	errorMsgLabel.setText("Enter item name ");
	        	errorMsgLabel.setVisible(true);
	    		itemField.setFocus(true);
	    		return false;
	        }
	        
	        if(priceField.getText().equals("")){
	        	errorMsgLabel.setText("Enter item price ");
	        	errorMsgLabel.setVisible(true);
	    		priceField.setFocus(true);
	    		return false;
	        }
	        try{
	        	double price=Double.parseDouble(priceField.getText());
	        	if(price < 1){
	        		errorMsgLabel.setText("Enter valid price ");
	        		errorMsgLabel.setVisible(true);
	        		priceField.setFocus(true);
	        		return false;
	        	}
	        }catch(NumberFormatException ex){
	        	errorMsgLabel.setText("Enter valid price ");
	        	errorMsgLabel.setVisible(true);
	    		priceField.setFocus(true);
	    		return false;
	        }
	        return true;
	    }
	    
	    private WishlistItemData copyFields(){
	    	WishlistItemData item = new WishlistItemData();
	    	item.setItemName(itemField.getText());
			if(highPriorityButton.getValue())
	        	item.setPriority(true);
	        else
	        	item.setPriority(false);
	    	
	        item.setLink(linkField.getText());
	        item.setPrice(Double.parseDouble(priceField.getText()));	
	        if(!this.thumbnailField.getText().equals("")){
	        	if(rightImage.getValue())
	               item.setThumbnail(thumbnailField.getText());	
	        }
	        return item;
	    }
	    
	    
	    private void wireEvents(){
	    	this.boxButton.addClickHandler(new ClickHandler(){
	            public void onClick(ClickEvent event) {
	            	if(checkIfValid()){
	                    WishlistItemData data = copyFields();
	              //      loadingImagePopup.center();
	            		loadingImagePopup.show();
	                    wishlistService.createWishlistItemSite(data,  new AsyncCallback<Void>(){

							public void onFailure(Throwable caught) {
								mainPanel.remove(formTable);
								loadingImagePopup.hide();
								mainPanel.add(new HTML("<div id='pnl_AfterAction'><table width='100%'><tr><td  class='ablack' ><b><br />"+caught.getMessage()+"<br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>Click here to close the window</u></a></b></td></tr></table></div></div>"));
								
							}

							public void onSuccess(Void result) {
								mainPanel.remove(formTable);
								loadingImagePopup.hide();
								mainPanel.add(new HTML("<div id='pnl_AfterAction'><table width='100%'><tr><td  class='ablack' ><b><br />The item was successfully added <br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>Click here to close the window</u></a></b></td></tr></table></div></div>"));
								
							}
	                    	
	                    });
	                    
	            	}
	            }});
	        
		
	        
			this.cancelButton.addClickHandler(new ClickHandler(){
	        	public void onClick(ClickEvent event){
	        		closeWindow();
	        		
					
	        	}
	        });
			
			
						
		
			
		
	    }
	    
	    private  native void closeWindow()/*-{
	        $wnd.close();
	    }-*/;
	    
	    private  native String resize(int width, int height)/*-{
		 $wnd.resizeTo(width,height);
	}-*/;
		
		
		
	
	}




