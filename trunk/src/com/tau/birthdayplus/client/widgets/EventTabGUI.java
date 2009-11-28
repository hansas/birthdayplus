package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tau.birthdayplus.client.Actions;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.EventData;


public class EventTabGUI {
	 /*
     * constants
     */
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int EVENT_LINK = 1;
	private static final int UPDATE_LINK = 3;
    private static final int DELETE_LINK = 4;
    
    /*GUI Widgets*/
    //vertical panel for the content of event list
	public VerticalPanel vPanel;
	//table for events
	public TableWithHeader eventTable;
	//add new event button
	public Button btnAddEvent;
	
	//add event box
	public DialogBox eventDialogBox;
	//table for the form
	private FlexTable layout; 
	//event name field
	private TextBox txtName;
	//event date field
	private DateBox txtDate;
	//recurrence field
	private CheckBox chkRecurrence;
	//update button
	public Button updateButton;
	//add button
    public Button addButton;
    //cancel button
    public Button cancelButton;
    
    private WishListFriendsGUI wishlistFriendGUI;
    private WishListFriendsDelegate wishlistFriendService;
    
    //Data Model\\
	public EventTabDelegate eventService;
//	private WishListFriendsGUI wishlistFriendGui;
	private ArrayList<EventData> eventList;
	protected EventData currentEvent;
	public Birthdayplus entryPoint;
	
	
	

	
	public void init(){
		eventList = new ArrayList<EventData>(); 
		
		//create DialogBox for user's friend wishlist
		wishlistFriendGUI = new WishListFriendsGUI();
	    wishlistFriendService = new WishListFriendsDelegate();
	    
	    wishlistFriendGUI.wishlistService = wishlistFriendService;
	    wishlistFriendService.gui = wishlistFriendGUI;
	    
	    wishlistFriendGUI.parent = this;
	    wishlistFriendGUI.init();
	    
		vPanel = new VerticalPanel();
		buildEventTable();
		buildEventDialogBox();
	
		
		btnAddEvent = new Button("Add Event");
		placeWidgets();
		
		wishlistFriendGUI.wireWishlistFriendGUIEvents();
		
	}
	
	/*
	* create flex table for wishlist items
	*/
	private void buildEventTable(){
		eventTable=new TableWithHeader();
		eventTable.setStyleName(constants.cwTableStyle());
		
		eventTable.setHeader(0, "Name" );
		eventTable.setHeader(1, "Event");
		eventTable.setHeader(2, "Due");   
	}
	
	private void placeWidgets() {
		vPanel.add(eventTable);
		vPanel.add(btnAddEvent);
	}
	
	
	 

	/**
	* Create a form that contains undisclosed advanced options.
	*/
	private void buildEventDialogBox() {
		eventDialogBox = new DialogBox();
		eventDialogBox.setStyleName(constants.cwDialogBoxStyle());
		
		txtName = new TextBox();
		txtName.setStyleName(constants.cwTextBoxStyle());
		
		txtDate = new DateBox();
		txtDate.setStyleName(constants.cwDateBoxStyle());
		txtDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		
		chkRecurrence=new CheckBox();
		chkRecurrence.setStyleName(constants.cwCheckBoxStyle());
		
		updateButton=new Button("Update item");
        updateButton.setStyleName(constants.cwButtonStyle());
        
        addButton=new Button("Add item");
        addButton.setStyleName(constants.cwButtonStyle());
        
	    cancelButton = new Button(constants.cwDialogBoxCancel()); 
	    cancelButton.setStyleName(constants.cwButtonStyle());

		buildForm();
		
		eventDialogBox.add(layout);
		
	}
	
	
	
	private void buildForm(){
		layout = new FlexTable();
		layout.setStyleName(constants.cwTableStyle());
	//	layout.setCellSpacing(6);
		
	    layout.setText(0,0,"Event Name");
	    layout.setWidget(0, 1, txtName);
	    
	    layout.setText(1, 0, "Date");
	    layout.setWidget(1, 1, txtDate);
	    
	    layout.setText(2, 0, "Recurrence");
	    layout.setWidget(2, 1, chkRecurrence);
	    
	    layout.setWidget(3, 0, updateButton);
        layout.setWidget(3, 1, addButton);
        layout.setWidget(3, 2, cancelButton);
	    
	    
	    
		
		/* Wrap the contents in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(decPanel);
		vPanel.add(buttonHPanel);
		eventDialogBox.add(vPanel);
		
		return eventDialogBox;
		*/
	}
	
	/*
	 * list of user and his friends id's
	 */
	private ArrayList<String> getUserAndFriendsIds(){
		Set<String> set = entryPoint.userFriends.keySet();
		ArrayList<String> temp=new ArrayList<String>();
		temp.add(entryPoint.userId);
		for(String key : set){
			temp.add(key);
		}
		return temp;
	}
	
	 /*
     * show the form
     */

    private void loadForm(EventData eventData,Actions action) {
		eventDialogBox.center();
		eventDialogBox.setText(action.toString()+ "Event");
		eventDialogBox.show();
		this.currentEvent = eventData;
		this.txtName.setText(this.currentEvent.getEventName());
		this.txtDate.setValue(currentEvent.getEventDate());
		this.chkRecurrence.setValue(this.currentEvent.getRecurrence());
	}
    
    /*
     * check if input is valid
     */
    private void copyFieldDateToEvent(){
    	currentEvent.setEventName(txtName.getText());
        currentEvent.setEventDate(txtDate.getValue());
        currentEvent.setRecurrence(chkRecurrence.getValue());

    }
    
    private long daysBetween(Date d1, Date d2) {
    	if(d1== null || d2 == null)
    		return 0;
		final long ONE_HOUR = 60 * 60 * 1000L;
	    return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	    
	}
	
	
	/*
	 * on click in the table
	 */
	 public void gui_eventEventGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
        EventData event = this.eventList.get(row);
         
       
         if (col==UPDATE_LINK) {
             this.addButton.setVisible(false);
             this.updateButton.setVisible(true);
             this.btnAddEvent.setVisible(false);
             loadForm(event,Actions.UPDATE);
         } else if (col==DELETE_LINK) {
        //	 item.setUserId(entryPoint.userId);
             this.eventService.deleteEvent(event);
         }else if(col==EVENT_LINK){
        	 this.wishlistFriendService.getWishlist(event.getUserId());
         }
    }
	 
	 
	    /*
	     * add new event
	     */
	    public void gui_eventAddButtonClicked() {
	    	boolean valid=true;
	        try{    
	        copyFieldDateToEvent();
	        }catch(NumberFormatException ex){
	     //   	showMessage("The price should be a number");
	        	valid=false;
	        }
	        if(valid){
	            btnAddEvent.setVisible(true);
	            eventDialogBox.hide();
	            this.eventService.createEvent(currentEvent);
	        }
	    }
	    
	    /*
	     * update event
	     */
	    public void gui_eventUpdateButtonClicked() {
	    	boolean valid=true;
	        try{
	        copyFieldDateToEvent();
	        }catch(NumberFormatException ex){
	        	valid=false;
	     //   	showMessage("The price should be a number");
	        }
	        if(valid){
	            btnAddEvent.setVisible(true);
	            eventDialogBox.hide();
	            this.eventService.updateEvent(currentEvent);
	        }
	    }
	 
	/*
     * on click on add item
     */
    public void gui_eventAddEventButtonClicked() {
        this.btnAddEvent.setVisible(false);
        this.updateButton.setVisible(false);
        this.addButton.setVisible(true);
        loadForm(new EventData(entryPoint.userId),Actions.CREATE);
    }
    
   
    
    
    /*
     * on click on cancel button in dialog box
     */
    public void gui_eventCancelButtonClicked(){
    	btnAddEvent.setVisible(true);
    	eventDialogBox.hide();
    }
	
    
    
   public void service_eventGetEventsSuccessful(ArrayList<EventData> result) {
	   String due;
		this.eventList = result;
		this.eventTable.clear();
		
		int row = 0;
	
		for (EventData event : eventList) {
			if(event.getUserId().equals(entryPoint.userId))
				eventTable.setWidget(row, 0, new Label("My"));
			else
			    eventTable.setWidget(row, 0, new Label(entryPoint.userFriends.get(event.getUserId())));
			eventTable.setWidget(row, 1, new Hyperlink(event.getEventName(),null));
			due = String.valueOf(daysBetween(new Date(), event.getEventDate()));
			Label lblEventDate = new Label(due);
			if(event.getEventDate()!= null)
    			lblEventDate.setTitle(event.getEventDate().toString());
			eventTable.setWidget(row, 2, lblEventDate);
			if(event.getUserId().equals( entryPoint.userId)){
				eventTable.setWidget(row, 3, new Hyperlink("update", null));
	    	    eventTable.setWidget(row,4,new Hyperlink("delete", null)); 
			}
			row++;
		}	
	}
	
	
	public void service_eventCreateEventSuccessful() {
          // showMessage("Event was successfully created");
	        this.eventService.getEvents(this.getUserAndFriendsIds());
		
		
	}

	public void service_eventUpdateEventSuccessful() {
		//showMessage("Event was successfully updated");
		 this.eventService.getEvents(this.getUserAndFriendsIds());
		
		
	}

	public void service_eventDeleteEventSuccessful() {
//		showMessage("Event was successfully deleteded");
	 this.eventService.getEvents(this.getUserAndFriendsIds());
		
		
	}

	public void service_eventGetWishlistSuccessful() {
		
		
	}
	
	
	public void service_eventGetEventsFailed(Throwable caught) {
		 System.out.println("Unable to get event list");
		
		
	}
	
	public void service_eventCreateEventFailed(Throwable caught) {
		 System.out.println("Unable to create new event");
		
	}

	public void service_eventUpdateEventFailed(Throwable caught) {
		 System.out.println("Unable to update event");
		
	}

	public void service_eventDeleteEventFailed(Throwable caught) {
		 System.out.println("Unable to delete  event");
	}
	


	public void service_eventGetWishlistFailed(Throwable caught) {
		 System.out.println("Unable to get  your frind's wishlist");	
		
	}
	
	public void wireEventGUIEvents(){
		this.eventTable.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Cell cellForEvent = eventTable.getCellForEvent(event);
				gui_eventEventGridClicked(cellForEvent);
			}
		});
		this.btnAddEvent.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui_eventAddEventButtonClicked();
            }});

		this.updateButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui_eventUpdateButtonClicked();
            }});
        
		this.addButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui_eventAddButtonClicked();
                
            }});
        
		this.cancelButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        		gui_eventCancelButtonClicked();
        	}
        });
	}

	

	

	
	
}
