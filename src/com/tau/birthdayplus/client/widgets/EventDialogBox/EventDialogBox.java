package com.tau.birthdayplus.client.widgets.EventDialogBox;





import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.tau.birthdayplus.client.widgets.Action;



public class EventDialogBox {
	CwConstants constants = GWT.create(CwConstants.class);
	private ArrayList<GadgetEventHandler>   handlers = new ArrayList<GadgetEventHandler>()  ;

	private DialogBox box;
	private VerticalPanel eventDialogBoxVerticalPanel;
	private FlexTable layout; 
	private  SuggestBox txtName;
	private DateBox txtDate;
	private CheckBox chkRecurrence;
    private Button boxButton;
    private Button cancelButton;
    private Label errorMsgLabel ;
 
    private Action action;
    
    
    public EventDialogBox(){
    	box = new DialogBox(false,true);
    	eventDialogBoxVerticalPanel = new VerticalPanel();
		box.add(eventDialogBoxVerticalPanel);
		
	    errorMsgLabel = new Label();
	    eventDialogBoxVerticalPanel.add(errorMsgLabel);
	    errorMsgLabel.setWidth("100%");
		errorMsgLabel.addStyleName("errorMessage");
		errorMsgLabel.setVisible(false);

		
		buildForm();
    }
    
   
    
    public void show(String eventName,Date eventDate, Boolean recurrence,Action action){
    	
    	this.action = action;
    	
    	box.setText(action.toString()+ " Event");
		boxButton.setText(action.toString());
		
    	this.txtName.setText(eventName);
		this.txtDate.setValue(eventDate);
		this.chkRecurrence.setValue(recurrence);
		
		box.center();
		box.show();
		
		this.txtName.setFocus(true);
    }
    
    public void show(Action action){
    	show("",null,false,action);
    }
    
    
    public void addGagdetEventEvent(GadgetEventHandler handler){
		handlers.add(handler);
	}
	
	public void removeGadgetEvent(GadgetEventHandler handler){
		handlers.remove(handler);
	}
	
	private void onCreateGadgetEvent(String eventName,Date eventDate,boolean recurrence){
		for(Iterator<GadgetEventHandler> it = handlers.iterator(); it.hasNext();)
        {
            GadgetEventHandler handler = it.next();
            handler.onCreateGadgetEvent(eventName,eventDate,recurrence);
        }
	}
	
	private void onUpdateGadgetEvent(String eventName,Date eventDate,boolean recurrence){
		for(Iterator<GadgetEventHandler> it = handlers.iterator(); it.hasNext();)
        {
            GadgetEventHandler handler = it.next();
            handler.onUpdateGadgetEvent(eventName,eventDate,recurrence);
        }
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
        boxButton.setWidth("50px");
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


private void sendEvent(String eventName,Date eventDate,Boolean recurrence){
	switch(action){
	case CREATE: onCreateGadgetEvent(eventName,eventDate,recurrence);
	             break;
	case UPDATE: onUpdateGadgetEvent(eventName,eventDate,recurrence);
	             break;
	}
	
}

private void wireEvents(){
	this.boxButton.addClickHandler(new ClickHandler(){
        
		public void onClick(ClickEvent event) {
        	if(checkIfValidEvent()){
        		String eventName = txtName.getText();
        		Date dateStart = txtDate.getValue();
        	    Boolean recurrence = chkRecurrence.getValue();
        	    errorMsgLabel.setVisible(false);
        		box.hide();
        		sendEvent(eventName,dateStart,recurrence);
        	}
        }});
   
    
	this.cancelButton.addClickHandler(new ClickHandler(){
    	public void onClick(ClickEvent event){
    		errorMsgLabel.setVisible(false);
    		box.hide();
    	}
    });
	
}

    
	



}
