package com.tau.birthdayplus.client.widgets.ItemDialogBox;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.Action;



public class ItemDialogBox {
	private static CwConstants constants = GWT.create(CwConstants.class);
	private ArrayList<ItemEventHandler>   handlers = new ArrayList<ItemEventHandler>()  ;
	private DialogBox box;
	private PopupPanel loadingImagePopup;
	private VerticalPanel itemDialogBoxVerticalPanel;
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
    private String linkText="";
    
    
    
    public ItemDialogBox(){
    	 box = new DialogBox(false,true);
    	
    	
    	itemDialogBoxVerticalPanel = new VerticalPanel();
    	box.add(itemDialogBoxVerticalPanel); 
    
	    errorMsgLabel = new Label();
	    itemDialogBoxVerticalPanel.add(errorMsgLabel);
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);	

	    buildForm();
	    
	 //   this.item = null;
	    
	    Image loadingImage=new Image( GWT.getModuleBaseURL() + "ajax-loader.gif");
	    loadingImage.setStyleName("loading image");
		

		
	    loadingImagePopup = new PopupPanel(false,true);
	   
	    loadingImagePopup.setAnimationEnabled(true);
	    
	    loadingImagePopup.setStyleName(constants.cwLoadingPopupPanelStyle());
	    loadingImagePopup.setWidget(loadingImage);
	    
	    wireEvents();
    }
    
   
    public void show(String itemName,String link,Double price,String thumbnail,Action action){
    	if((itemName!=null) && (itemName.length()>0) )    		
           this.itemField.setText(itemName);
        
    	if((link!=null) && (link.length()>0))
           this.linkField.setText(link);
    	
        this.priceField.setText(price.toString());
        
        this.highPriorityButton.setValue(true);
        
        if((thumbnail!=null) && (thumbnail.length()>0)){
        	this.thumbnailField.setText(thumbnail);
        	showThumbnail();
        }
        else
        {
           formTable.getRowFormatter().setVisible(4, false);
           this.thumbnailField.setText("");
        }
        
    	box.setText(action.toString()+" Item");
    	boxButton.setText(action.toString()+" item");
    	
    	
        box.center();
        box.show();
        
        this.itemField.setFocus(true);
    }
    
    
    public void show(Action action){
    	show(null,null,0.0,null,action);
    }
    
    
    private void showThumbnail(){
    	Image thumbImage = new Image(this.thumbnailField.getText());
    	formTable.setWidget(4, 0, thumbImage);
    	thumbImage.setSize("60px", "60px");
    	formTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
    	thumbImage.setStyleName("thumbnail");
        
        rightImage.setValue(true);
        formTable.getRowFormatter().setVisible(4, true);
    	
    }
    
    private void buildForm() {
        formTable = new FlexTable();
        itemDialogBoxVerticalPanel.add(formTable);
       // formTable.setCellSpacing(20);
        formTable.setCellPadding(3);
        
        itemField=new TextBox();
        itemField.setMaxLength(25);
        itemField.setVisibleLength(25);
   	 
        priorityPanel = new HorizontalPanel();
        highPriorityButton = new RadioButton("priority", "High");
        priorityPanel.add(highPriorityButton);
        lowPriorityButton = new RadioButton("priority", "Low");
        priorityPanel.add(lowPriorityButton);
        
        linkField=new TextBox();
        linkField.setVisibleLength(25);
        
        priceField=new TextBox();
        priceField.setMaxLength(10);
        priceField.setVisibleLength(25);
        
        thumbnailField=new TextBox();
        
        rightImage = new CheckBox("This is the image of the item");
        
        boxButton=new Button("Ok");
   
	    cancelButton = new Button(constants.cwDialogBoxCancel());
        
     //   formTable.setStyleName(constants.cwTableStyle());
   
        formTable.setText(0, 0, "Item name");
        formTable.setWidget(0, 1, itemField);
        formTable.getFlexCellFormatter().setColSpan(0, 1, 2);

        formTable.setText(1, 0, "priority");
        formTable.setWidget(1, 1, priorityPanel);
        formTable.getFlexCellFormatter().setColSpan(1, 1, 2);
        
        
        formTable.setText(2, 0, "link");
        formTable.setWidget(2, 1, linkField);
        formTable.getFlexCellFormatter().setColSpan(2, 1, 2);
        
        formTable.setText(3, 0, "price");
        formTable.setWidget(3, 1, priceField);
        formTable.getFlexCellFormatter().setColSpan(3, 1, 2);
        
        formTable.setWidget(4, 1, rightImage);
        formTable.getFlexCellFormatter().setColSpan(4, 1, 2);
        
        
     //   formTable.setWidget(4, 0, updateButton);
        formTable.setWidget(5, 0, boxButton);
        boxButton.setWidth("100px");
    //    formTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
        formTable.setWidget(5, 2, cancelButton);
        formTable.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_RIGHT);

       
    }
    
    
    public void addItemEvent(ItemEventHandler handler){
		handlers.add(handler);
	}
	
	public void removeItemEvent(ItemEventHandler handler){
		handlers.remove(handler);
	}
	
	
	private void onSaveItem(String itemName,Boolean priority,String link,Double price,String thumbnail){
		for(Iterator<ItemEventHandler> it = handlers.iterator(); it.hasNext();)
        {
            ItemEventHandler handler = it.next();
            handler.onSaveItem(itemName,priority,link,price,thumbnail);
        }
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
    
  
    
    
   
    
    
    private void cleanDialogBox(){
    	itemField.setText("");
    	linkField.setText("");
    	priceField.setText("");
    	thumbnailField.setText("");
    	errorMsgLabel.setVisible(false);
    	linkText = "";
    	
    }
    
    
    private void wireEvents(){
    	this.boxButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	if(checkIfValid()){
                    String itemName = itemField.getText();
                    Boolean priority = highPriorityButton.getValue() ? true : false;
                    String link = linkField.getText();
                    Double price = Double.parseDouble(priceField.getText());
                    
                    String thumbnail = "";
                    if(!thumbnailField.getText().equals("")){
        	        	if(rightImage.getValue())
        	               thumbnail = thumbnailField.getText();	
        	        }
                    
    				box.hide();
    				onSaveItem(itemName,priority,link,price,thumbnail);
    				cleanDialogBox();
            	}
            }});
        
	
        
		this.cancelButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        		box.hide();
        		cleanDialogBox();
        	}
        });
		
		
		
		this.linkField.addChangeHandler(new ChangeHandler(){

			public void onChange(ChangeEvent event) {
				gui_eventGetPriceByLink();
				
			}
			
		});
		this.priceField.addKeyUpHandler(new KeyUpHandler(){
			public void onKeyUp(KeyUpEvent event) {
					if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
						boxButton.click();
					}
					
				}
		});
		
	
    }
    
    
    private void gui_eventGetPriceByLink(){
		loadingImagePopup.center();
	    loadingImagePopup.show();
		
	    if(!linkField.getText().equals("")){
		   bringLink(linkField.getText());
	    }
	    else
	    	loadingImagePopup.hide();
		
		
	}
	public  native void bringLink(String url) /*-{
		thisTabGui = this;
	    var params = {};
	    params[$wnd.gadgets.io.RequestParameters.CONTENT_TYPE] = $wnd.gadgets.io.ContentType.TEXT; 
		$wnd.gadgets.io.makeRequest(url, response, params); 

	function response(obj) {
		    if (obj.text === undefined) {
                
            }else
		    	thisTabGui.@com.tau.birthdayplus.client.widgets.ItemDialogBox.ItemDialogBox::linkText=obj.text;
			
			thisTabGui.@com.tau.birthdayplus.client.widgets.ItemDialogBox.ItemDialogBox::parse()();
	};
}-*/;
	
	
	
	private void parse(){
		String price = match(linkText,"([1-9]\\d*)(?:,(\\d+))?(?: ?₪)");
	
		if(price.equals(""))
			price = match(linkText,"(?:₪ ?)([1-9]\\d*)(?:,(\\d+))?()");
	
	    this.priceField.setText(price);
	    
	    String thumbnail = match(linkText,"(?:href=\")([^\"]+\\.gif|jpg|jpeg|png|mng)(?:\")");
	    
	    if((thumbnail!=null)&& (thumbnail.length()>0)){
	    	this.thumbnailField.setText(thumbnail);
        	showThumbnail();
        }
        else
        {
           formTable.getRowFormatter().setVisible(4, false);
           this.thumbnailField.setText("");
        }
	    
	    loadingImagePopup.hide();
			
		
	}
	
	private native static  String match(String text,String pattern)/*-{
	   var regExp = new RegExp(pattern);
	   var result = text.match(regExp);
	   if (result == null) return "";
	   var temp = "";
	   for (var i=1;i<result.length;i++){
	    	if ((!(result[i]=== undefined))&&(/\S+/.test(result[i]))){
	   	       temp+=result[i];
	    	}
	   }
	   return temp;
	   }-*/;
	
	
}
