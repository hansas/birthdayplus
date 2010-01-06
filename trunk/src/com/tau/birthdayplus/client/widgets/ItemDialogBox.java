package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import java.util.List;



import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class ItemDialogBox extends DialogBox{
	private PopupPanel loadingImagePopup;
	private static CwConstants constants = GWT.create(CwConstants.class);
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
    
    private WishlistItemData item;
    private String linkText;
    
    
    
    public ItemDialogBox(){
    	super(false,true);
    	
    	itemDialogBoxVerticalPanel = new VerticalPanel();
    	add(itemDialogBoxVerticalPanel); 
    
	    errorMsgLabel = new Label();
	    itemDialogBoxVerticalPanel.add(errorMsgLabel);
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);	

	    buildForm();
	    
	    this.item = null;
	    
	    Image loadingImage=new Image( GWT.getModuleBaseURL() + "ajax-loader.gif");
	    loadingImage.setStyleName("loading image");
		

		
	    loadingImagePopup = new PopupPanel(false,true);
	   
	    loadingImagePopup.setAnimationEnabled(true);
	    
	    loadingImagePopup.setStyleName(constants.cwLoadingPopupPanelStyle());
	    loadingImagePopup.setWidget(loadingImage);
	    
	    wireEvents();
    }
    
    public void setButtonText(String text){
    	this.boxButton.setText(text);
    }
    
    public void show(WishlistItemData item){
    	this.item = item;
    	this.itemField.setText(item.getItemName());
        this.linkField.setText(item.getLink());
        this.priceField.setText(item.getPrice().toString());
        this.highPriorityButton.setValue(true);
        if(item.getThumbnail()!=null)
           this.thumbnailField.setText(item.getThumbnail());
        this.itemField.setFocus(true);
        super.show();
    }
    
    public WishlistItemData getInput(){
    	return this.item;
    }
    
    
    private void buildForm() {
        formTable = new FlexTable();
        itemDialogBoxVerticalPanel.add(formTable);
        
        itemField=new TextBox();
   	 
        priorityPanel = new HorizontalPanel();
        highPriorityButton = new RadioButton("priority", "High");
        priorityPanel.add(highPriorityButton);
        lowPriorityButton = new RadioButton("priority", "Low");
        priorityPanel.add(lowPriorityButton);
        
        linkField=new TextBox();
        
        priceField=new TextBox();
        
        thumbnailField=new TextBox();
        
        boxButton=new Button("Ok");
   
	    cancelButton = new Button(constants.cwDialogBoxCancel());
        
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
        boxButton.setWidth("85%");
        formTable.setWidget(4, 2, cancelButton);
        
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
    
    private void copyFields(){
    	item.setItemName(itemField.getText());
		if(highPriorityButton.getValue())
        	item.setPriority(true);
        else
        	item.setPriority(false);
    	
        item.setLink(linkField.getText());
        item.setPrice(Double.parseDouble(priceField.getText()));	
        item.setThumbnail(thumbnailField.getText());	
    }
    
    
    private void wireEvents(){
    	this.boxButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	if(checkIfValid()){
                    copyFields();
                    errorMsgLabel.setVisible(false);
    				hide();
            	}
            }});
        
	
        
		this.cancelButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        		item = null;
        		errorMsgLabel.setVisible(false);
				hide();
        	}
        });
		
		this.linkField.addChangeHandler(new ChangeHandler(){

			public void onChange(ChangeEvent event) {
				gui_eventGetPriceByLink();
				
			}
			
		});
		this.priceField.addKeyUpHandler(new KeyUpHandler(){
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_TAB) 
					gui_eventGetPriceByLink();
				else
					if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
						if(checkIfValid()){
							copyFields();
		                    errorMsgLabel.setVisible(false);
		    				hide();
						}
					}
					
				}
		});
		
	
    }
    
    
    private void gui_eventGetPriceByLink(){
		loadingImagePopup.center();
	    loadingImagePopup.show();
		
		bringLink(linkField.getText());
		
		
	}
	public  native void bringLink(String url) /*-{
		thisTabGui = this;
	    var params = {};
	    params[$wnd.gadgets.io.RequestParameters.CONTENT_TYPE] = $wnd.gadgets.io.ContentType.TEXT; 
		$wnd.gadgets.io.makeRequest(url, response, params); 

	function response(obj) {
		    var responseText = obj.text; 
			thisTabGui.@com.tau.birthdayplus.client.widgets.ItemDialogBox::linkText=obj.text;
			thisTabGui.@com.tau.birthdayplus.client.widgets.ItemDialogBox::parse()();
	};
}-*/;
	
	
	
	private void parse(){
		Double price = 0.0;
		String[] prices = match(linkText,"([1-9]\\d*)(?:,(\\d+))?(?: ?₪)");
		
		if(prices.length != 0){
			price = Double.parseDouble(prices[1]+prices[2]);
		}else{
			prices = match(linkText,"(?:₪ ?)([1-9]\\d*)(?:,(\\d+))?()");
			if(prices.length != 0)
				price = Double.parseDouble(prices[1]+prices[2]);
			
		}
	    this.priceField.setText(price.toString());
	    String[] thumbnail = match(linkText,"(?:href=\")([^\"]+\\.gif|jpg|jpeg|png|mng)(\")");
	   
	    if(thumbnail.length!= 0)
	    	this.thumbnailField.setText(thumbnail[1]);
	    else
	    	this.thumbnailField.setText("");
	    
	    loadingImagePopup.hide();
			
		
	}
	
	
	private native static  void _match(String text, List matches,String pattern)/*-{
	   var regExp = new RegExp(pattern);
	   var result = text.match(regExp);
	   if (result == null) return;
	   for (var i=0;i<result.length;i++)
	   matches.@java.util.ArrayList::add(Ljava/lang/Object;)(result[i]);
	   }-*/;
	
	public static String[] match(String text,String pattern) {
	    List matches = new ArrayList();
	    _match(text, matches,pattern);
	    String arr[] = new String[matches.size()];
	    for (int i = 0; i < matches.size(); i++)
	      arr[i] = matches.get(i).toString();
	    return arr;
	  }

}
