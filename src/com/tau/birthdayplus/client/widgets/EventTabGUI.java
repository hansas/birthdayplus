package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tau.birthdayplus.client.Actions;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.EventData;

public class EventTabGUI extends Composite {
	/* Constants. */
	private static final String EVENT_TAB_ROOT_PANEL = "vEventTabPanel";
	
	protected VerticalPanel vPanel;
	private CwConstants constants;
	protected TableWithHeader eventTable;
	public Button btnAddEvent; 
	public DialogBox eventDialogBox;
	private TextBox txtName;
	private DateBox txtDate;
	private CheckBox chkRecurrence;
	public EventTabDeligate eventService;
	
	private List<EventData> eventList;
	private EventData currentEvent;
	public EventTabGUI(List<EventData> eventList){
		this.eventList = eventList;
		buildEventTab();
		placeWidgets();		 
		initWidget(vPanel);
	}
	
	private void buildEventTab(){
		vPanel = new VerticalPanel();
		btnAddEvent = new Button("Add Event");
		
		vPanel.getElement().setId(EVENT_TAB_ROOT_PANEL);
		buildEventTable();
	}
	/*
	* create flex table for wishlist items
	*/
	private void buildEventTable(){
		eventTable=new TableWithHeader();
		eventTable.setStyleName(constants.cwTableStyle());
		FlexCellFormatter cellFormatter =  eventTable.getFlexCellFormatter();
		eventTable.setCellSpacing(5);
		  
		eventTable.setHeader(0, constants.cwEventLabel());
		eventTable.setHeader(1, constants.cwEventDue());   
	}
	
	private void placeWidgets() {
		RootPanel.get(EVENT_TAB_ROOT_PANEL).add(eventTable);
		RootPanel.get(EVENT_TAB_ROOT_PANEL).add(btnAddEvent);
	}
	
	/**
	* Create a form that contains undisclosed advanced options.
	*/
	private DialogBox buildEventDialogBox(Actions action) {
		eventDialogBox = new DialogBox();
		
		eventDialogBox.setText(action + " Event");
		eventDialogBox.setAnimationEnabled(true);
		
		HorizontalPanel buttonHPanel = new HorizontalPanel();
		buttonHPanel.addStyleName("buttonHPanel");
		buttonHPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		// Add a handler to close the DialogBox
		final Button btnOK = new Button(constants.cwDialogBoxOK());
		btnOK.getElement().setId("OkButton");
		  
		final Button btnCancel = new Button(constants.cwDialogBoxCancel());
		btnCancel.getElement().setId("CancelButton");
		
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			buttonHPanel.add(btnCancel);
			buttonHPanel.add(btnOK);
		} 
		else {
			buttonHPanel.add(btnOK);
			buttonHPanel.add(btnCancel);
		}
		
		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		
		//Add some standard form options
		layout.setHTML(1, 0, constants.cwDisclosurePanelFormName());
		txtName = new TextBox();
		layout.setWidget(1, 1, txtName);
		layout.setHTML(2, 0, constants.cwEventLabel());
		txtDate = new DateBox();
		txtDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		layout.setWidget(2, 1, txtDate);
		
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(decPanel);
		vPanel.add(buttonHPanel);
		eventDialogBox.add(vPanel);
		
		return eventDialogBox;
	}
	
	/*
     * on click on add item
     */
    public void gui_eventAddEventButtonClicked() {
        this.btnAddEvent.setVisible(false);
        
        loadForm(new EventData("", "", new Date(), false));
    }
    
    private void loadForm(EventData eventData) {
    	eventDialogBox = buildEventDialogBox(Actions.CREATE);
		eventDialogBox.center();
		eventDialogBox.show();
		this.currentEvent = eventData;
		this.txtName.setText(this.currentEvent.getEventName());
		this.txtDate.setValue(this.currentEvent.getEventDate());
		this.chkRecurrence.setValue(this.currentEvent.getRecurrence());
	}
	public void gui_eventOkEventButtonClicked() {
    	this.eventDialogBox.hide();
    }
	public void service_eventGetEventsSuccess(ArrayList<EventData> result) {
		// TODO Auto-generated method stub
		
	}
	public void service_eventGetEventsFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}
	public void service_eventCreateEventSuccess() {
		// TODO Auto-generated method stub
		
	}
	public void service_eventCreateEventFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}
	public void service_eventUpdateEventSuccess() {
		// TODO Auto-generated method stub
		
	}
	public void service_eventUpdateEventFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}
	public void service_eventDeleteEventSuccess() {
		// TODO Auto-generated method stub
		
	}
	public void service_eventDeleteEventFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

}
