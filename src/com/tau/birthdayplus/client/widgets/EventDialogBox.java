package com.tau.birthdayplus.client.widgets;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.EventData;

public class EventDialogBox extends DialogBox{
	CwConstants constants = GWT.create(CwConstants.class);

	
	private VerticalPanel eventDialogBoxVerticalPanel;
	private FlexTable layout; 
	private  SuggestBox txtName;
	private DateBox txtDate;
	private CheckBox chkRecurrence;
    private Button boxButton;
    private Button cancelButton;
    private Label errorMsgLabel ;
    private EventData data;
    
    
    public EventDialogBox(){
    	super(false,true);
    	eventDialogBoxVerticalPanel = new VerticalPanel();
		add(eventDialogBoxVerticalPanel);
		
	    errorMsgLabel = new Label();
	    eventDialogBoxVerticalPanel.add(errorMsgLabel);
	    errorMsgLabel.setWidth("100%");
		errorMsgLabel.addStyleName("errorMessage");
		errorMsgLabel.setVisible(false);

		
		buildForm();
    }
    
    public void setButtonText(String text){
    	boxButton.setText(text);
    }
    
    public void show(EventData data){
    	this.data = data;
    	this.txtName.setText(data.getEventName());
		this.txtDate.setValue(data.getEventDate());
		this.chkRecurrence.setValue(data.getRecurrence());
		this.txtName.setFocus(true);
		super.show();
    }
    
   
    public EventData getInput(){
    	return this.data;
    }
    
   private void buildForm(){
		
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	    String[] words = constants.cwSuggestBoxEvents();
	    for (int i = 0; i < words.length; ++i) {
	      oracle.add(words[i]);
	    }
	    
	    txtName =new SuggestBox(oracle);
	
    	txtDate = new DateBox();
	    DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
	    txtDate.setFormat(new DateBox.DefaultFormat(fmt));
	
	    chkRecurrence=new CheckBox();
 	    boxButton=new Button();

        cancelButton = new Button(constants.cwDialogBoxCancel()); 
        layout = new FlexTable();
        eventDialogBoxVerticalPanel.add(layout); 
		
	    layout.setText(0,0,"Event Name");
	    layout.setWidget(0, 1, txtName);
	    
	    layout.setText(1, 0, "Date");
	    layout.setWidget(1, 1, txtDate);
	    
	    layout.setText(2, 0, "Annual");
	    layout.setWidget(2, 1, chkRecurrence);
	    
	 
        layout.setWidget(3, 1, boxButton);
        layout.setWidget(3, 2, cancelButton);
	    
        wireEvents();
		
	}
   
 



private boolean checkIfValidEvent(){
	
	if(txtName.getText().equals("")){
		errorMsgLabel.setText("Enter event name ");
		errorMsgLabel.setVisible(true);
		txtName.setFocus(true);
		return false;

	}
	if(txtDate.getValue()== null){
		errorMsgLabel.setText("Enter event date ");
		errorMsgLabel.setVisible(true);
		txtDate.setFocus(true);
		return false;
	}
    
    return true;
}

private void wireEvents(){
	this.boxButton.addClickHandler(new ClickHandler(){
        @SuppressWarnings("deprecation")
		public void onClick(ClickEvent event) {
        	if(checkIfValidEvent()){
        		data.setEventName(txtName.getText());
        		Date dateStart = txtDate.getValue();
                dateStart.setMinutes(0); // minute 0
                dateStart.setHours(0);   // hour 0
                dateStart.setSeconds(0); // second 0
        		data.setEventDate(dateStart);
        	    data.setRecurrence(chkRecurrence.getValue());
        	    errorMsgLabel.setVisible(false);
        		hide();
        	}
        }});
   
    
	this.cancelButton.addClickHandler(new ClickHandler(){
    	public void onClick(ClickEvent event){
    		data = null;
    		errorMsgLabel.setVisible(false);
    		hide();
    	}
    });
	
}

    
	



}
