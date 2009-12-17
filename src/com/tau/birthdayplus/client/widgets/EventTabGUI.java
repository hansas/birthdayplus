package com.tau.birthdayplus.client.widgets;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
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
    protected FlowPanel mainPanel;
    //vertical panel for the content of event list
    private HorizontalPanel buttonPanel;
	protected FlowPanel eventPanel;
	//table for events
	private ScrollPanel eventScrollPanel;
	private FlexTable eventTableHeader;
	private HoverTable eventTable;
	//add new event button
	private Button btnAddEvent;
	private Button googleButton;
	private ArrayList<RadioButton> radioButtonList;
	
	//add event box
	private DialogBox eventDialogBox;
	
	VerticalPanel eventDialogBoxVerticalPanel;
	//table for the form
	private FlexTable layout; 
	//event name field
	private  SuggestBox txtName;
	//event date field
	private DateBox txtDate;
	//recurrence field
	private CheckBox chkRecurrence;
	//update button
//	public Button updateButton;
	//add button
    private Button boxButton;
    //cancel button
    private Button cancelButton;
    
    private Boolean addEvent;
    
    private  WishListFriendsGUI wishlistFriendGUI;
    private WishListFriendsDelegate wishlistFriendService;
    
    //Data Model\\
	public EventTabDelegate eventService;
//	private WishListFriendsGUI wishlistFriendGui;
	public ArrayList<EventData> eventList=null;
	protected EventData currentEvent;
	public Birthdayplus entryPoint;
	
	private Label errorMsgLabel ;
	
	
	
	

	
	public void init(){
	//	eventList = new ArrayList<EventData>(); 

		mainPanel = new FlowPanel();
		entryPoint.tab.add(mainPanel, "Events");
		mainPanel.setStyleName("Panel");
	//	mainPanel.setSize("100%","350px");
	
		eventPanel = new FlowPanel();
		mainPanel.add(eventPanel);
		eventPanel.setStyleName("Panel");
	//	eventPanel.setSize("100%", "350px");
		
		buttonPanel = new HorizontalPanel();
		eventPanel.add(buttonPanel);
		buttonPanel.setStyleName("buttonPanel");
		
		buttonPanel.setSpacing(1);
		
		googleButton = new Button("Remind me");
		buttonPanel.add(googleButton);
	//	googleButton.setSize("100px", "25px");
		
		btnAddEvent = new Button("Add Event");
		buttonPanel.add(btnAddEvent);
	//	btnAddEvent.setSize("100px","25px");
		

		
		
		buildEventTable();
		buildEventDialogBox();
	
		
		
		//create DialogBox for user's friend wishlist
		wishlistFriendGUI = new WishListFriendsGUI();
	    wishlistFriendService = new WishListFriendsDelegate();
	    
	    wishlistFriendGUI.wishlistService = wishlistFriendService;
	    wishlistFriendService.gui = wishlistFriendGUI;
	    wishlistFriendService.entryPoint = this.entryPoint;
	    
	    wishlistFriendGUI.parent = this;
	    wishlistFriendGUI.init();
		wishlistFriendGUI.wireWishlistFriendGUIEvents();
		
	}
	
	/*
	* create flex table for wishlist items
	*/
	private void buildEventTable(){
		eventTableHeader = new FlexTable();
		eventPanel.add(eventTableHeader);
		eventTableHeader.addStyleName("TableHeader");
		//eventTableHeader.setSize("100%", "25px");
		
		eventTableHeader.getColumnFormatter().setWidth(0, "16px");
	//	eventTableHeader.getColumnFormatter().setWidth(1, "84px");
		eventTableHeader.getColumnFormatter().setWidth(1, "184px");
		eventTableHeader.getColumnFormatter().setWidth(2, "25px");
				
		eventTableHeader.setText(0, 0, "");
		eventTableHeader.setText(0, 1, "Event");
	//	eventTableHeader.setText(0, 2, "Event");
		eventTableHeader.setText(0, 2, "Due");
		eventTableHeader.getFlexCellFormatter().setColSpan(0, 2, 3);
		
		eventScrollPanel = new ScrollPanel();
		eventPanel.add(eventScrollPanel);
		eventScrollPanel.addStyleName("ScrollPanel");
//		eventScrollPanel.setSize("100%", "300px");
		
		
		eventTable = new HoverTable();
		eventScrollPanel.add(eventTable);
		eventTable.addStyleName("Table");
	//	eventTable.setWidth("100%");
		
		eventTable.getColumnFormatter().setWidth(0, "16px");
		eventTable.getColumnFormatter().setWidth(1, "184px");
	//	eventTable.getColumnFormatter().setWidth(2, "100px");
	//	eventTable.getColumnFormatter().setWidth(2, "25px");

		
		
		
	}
	
	
	
	
	 

	/**
	* Create a form that contains undisclosed advanced options.
	*/
	private void buildEventDialogBox() {
		eventDialogBox = new DialogBox();
	
		eventDialogBoxVerticalPanel = new VerticalPanel();
		eventDialogBox.add(eventDialogBoxVerticalPanel);
		
	    errorMsgLabel = new Label();
	    eventDialogBoxVerticalPanel.add(errorMsgLabel);
	    errorMsgLabel.setWidth("100%");
		errorMsgLabel.addStyleName("errorMessage");
		errorMsgLabel.setVisible(false);

		
		buildForm();
		
		 
		
		
		eventDialogBox.setVisible(false);
		
		
		
		
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
	    
	    layout.setText(2, 0, "Recurrence");
	    layout.setWidget(2, 1, chkRecurrence);
	    
	 
        layout.setWidget(3, 1, boxButton);
        layout.setWidget(3, 2, cancelButton);
	    
	    
	    
		
		
	}
	

	
	 /*
     * show the form
     */

    private void loadForm(EventData eventData,Actions action) {
		eventDialogBox.center();
		eventDialogBox.setText(action.toString()+ " Event");
		boxButton.setText(action.toString());
		eventDialogBox.show();
		txtName.setFocus(true);
		this.currentEvent = eventData;
		this.txtName.setText(this.currentEvent.getEventName());
		this.txtDate.setValue(currentEvent.getEventDate());
		this.chkRecurrence.setValue(this.currentEvent.getRecurrence());
	
	}
    
    /*
     * check if input is valid
     */
    private boolean copyFieldDateToEvent(){
    	if(txtName.getText().equals("")){
    		errorMsgLabel.setText("Enter event name ");
    		txtName.setFocus(true);
    		return false;

    	}
    	currentEvent.setEventName(txtName.getText());
    	
    	if(txtDate.getValue()== null){
    		errorMsgLabel.setText("Enter event date ");
    		txtDate.setFocus(true);
    		return false;
    	}
        currentEvent.setEventDate(txtDate.getValue());
        currentEvent.setRecurrence(chkRecurrence.getValue());
        return true;
    }
    
    private long daysBetween(Date d1, Date d2) {
    	if(d1== null || d2 == null)
    		return 0;
		final long ONE_HOUR = 60 * 60 * 1000L;
	    return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24))+1;
	    
	}
	
	
	/*
	 * on click in the table
	 */
	 public void gui_eventEventGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
        EventData event = this.eventList.get(row);
         
       
         if (col==UPDATE_LINK) {
        	 if(event.getUserId().equals(entryPoint.userId)){
               // this.addButton.setVisible(false);
               // this.updateButton.setVisible(true);
        		this.addEvent = false;
                this.btnAddEvent.setVisible(false);
                loadForm(event,Actions.UPDATE);
        	 }
         } else if (col==DELETE_LINK) {
             if(event.getUserId().equals(entryPoint.userId))
                this.eventService.deleteEvent(event);
         }else if(col==EVENT_LINK){
        	 if(! event.getUserId().equals(entryPoint.userId)){
        	      currentEvent = event;
        	      wishlistFriendGUI.title.setText("wishlist for " + entryPoint.userFriends.get(currentEvent.getUserId())+ "'s "+event.getEventName());
        	      eventPanel.setVisible(false);
        	      wishlistFriendGUI.wishlistBoxPanel.setVisible(true);
        	      this.wishlistFriendService.getWishlist(event.getUserId() , event.getEventId());
        	 }
         }
    }
	 
	 
	    /*
	     * add new event
	     */
	    public void gui_eventAddButtonClicked() {
	    	boolean valid=copyFieldDateToEvent();
	      
	        if(valid){
	            btnAddEvent.setVisible(true);
	            errorMsgLabel.setVisible(false);
	            eventDialogBox.hide();
	            this.eventService.createEvent(currentEvent);
	        }else{
	        	 errorMsgLabel.setVisible(true);

	        }
	    }
	    
	    /*
	     * update event
	     */
	    public void gui_eventUpdateButtonClicked() {
	    	boolean valid = copyFieldDateToEvent();
	        if(valid){
	            btnAddEvent.setVisible(true);
	            errorMsgLabel.setVisible(false);
	            eventDialogBox.hide();
	            this.eventService.updateEvent(currentEvent);
	        }else{
	        	errorMsgLabel.setVisible(true);
	        }
	    }
	 
	/*
     * on click on add item
     */
    public void gui_eventAddEventButtonClicked() {
        this.btnAddEvent.setVisible(false);
      //  this.updateButton.setVisible(false);
      //  this.addButton.setVisible(true);
        this.addEvent = true;
        loadForm(new EventData(entryPoint.userId),Actions.CREATE);
    }
    
   
    
    
    /*
     * on click on cancel button in dialog box
     */
    public void gui_eventCancelButtonClicked(){
    	btnAddEvent.setVisible(true);
    	errorMsgLabel.setVisible(false);
    	eventDialogBox.hide();
    }
    
    private void gui_eventGoogleButtonClicked(){
    	int row = -1;
    
    	for(int i=0;i<radioButtonList.size();i++){
    		if(radioButtonList.get(i).getValue()){
    			row =i;
    			radioButtonList.get(i).setValue(false);
    			break;
    		}
    	}	
    	if(row == -1)
    		System.out.println("You need to choose item");
    	else{
    		DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("yyyyMMdd");
    		String date = dateFormatter.format(eventList.get(row).getEventDate());
    		String event = eventTable.getText(row, 1);
    		String url = "http://www.google.com/calendar/event?action=TEMPLATE&text="+event+"&dates="+date+"/"+date+"&details=&location=&trp=false&sprop=&sprop=name:Birthdayplus";
    		Window.open(url, "_blank" , "height=800,width=800");
    		
    	}
    }
	
    
    
   public void service_eventGetEventsSuccessful(ArrayList<EventData> result) {
	    String due;
		this.eventList = result;
		this.eventTable.clear();
		radioButtonList = new ArrayList<RadioButton>();
		
	
		
		int row = 0;
	
		for (EventData event : eventList) {
			RadioButton radioButton = new RadioButton("event");
			eventTable.setWidget(row, 0, radioButton);
			radioButton.setWidth("16px");
		
		//	radioButton.setStyleName("eventRadioButton");
			radioButtonList.add(radioButton);
		
			
			if(event.getUserId().equals(entryPoint.userId)){
				eventTable.setWidget(row, 1, new Label("My"+ " "+event.getEventName()));
			//	 eventTable.setText(row, 2, event.getEventName());
				
			}
			else{
				Hyperlink eventLink = new Hyperlink( entryPoint.userFriends.get(event.getUserId())+"'s"+ " "+event.getEventName(),null);
				eventLink.setTitle("Click to see wishlist");
			    eventTable.setWidget(row, 1, eventLink);
			  //  eventTable.setWidget(row, 2, new Hyperlink(event.getEventName(),null));
			   
			}
			
			due = String.valueOf(daysBetween(new Date(), event.getEventDate()));
			Label lblEventDate = new Label(due);
			if(event.getEventDate()!= null){
				DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("EEE, dd MMM , yyyy");
    			lblEventDate.setTitle(dateFormatter.format(event.getEventDate()));
			}
			eventTable.setWidget(row, 2, lblEventDate);
			if(event.getUserId().equals( entryPoint.userId)){
			    Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
			    updateImage.setTitle("update event");
			    Image deleteImage = new Image( GWT.getModuleBaseURL() + "trash_16.png");
			    deleteImage.setTitle("delete event");
				eventTable.setWidget(row, 3, updateImage);
	    	    eventTable.setWidget(row,4, deleteImage); 
			}else{
				eventTable.setWidget(row, 4, new Label(""));
			}

			eventTable.getRowFormatter().addStyleName(row, "tablesRows");
			
			row++;
		}
		
	}
	
	
	public void service_eventCreateEventSuccessful() {
          // showMessage("Event was successfully created");
	        this.eventService.getEvents(entryPoint.getUserAndFriendsIds());
		
		
	}

	public void service_eventUpdateEventSuccessful() {
		//showMessage("Event was successfully updated");
		 this.eventService.getEvents(entryPoint.getUserAndFriendsIds());
		
		
	}

	public void service_eventDeleteEventSuccessful() {
//		showMessage("Event was successfully deleteded");
	 this.eventService.getEvents(entryPoint.getUserAndFriendsIds());
		
		
	}


	
	
	public void service_eventGetEventsFailed(Throwable caught) {
		Window.alert("Unable to get event list");
		
		
	}
	
	public void service_eventCreateEventFailed(Throwable caught) {
		Window.alert("Unable to create new event");
		
	}

	public void service_eventUpdateEventFailed(Throwable caught) {
		Window.alert("Unable to update event");
		
	}

	public void service_eventDeleteEventFailed(Throwable caught) {
		Window.alert("Unable to delete  event");
	}
	



	
	public void wireEventGUIEvents(){
		this.eventTable.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Cell cellForEvent = eventTable.getCellForEvent(event);
				if(cellForEvent!=null)
				   gui_eventEventGridClicked(cellForEvent);
			}
		});
		this.btnAddEvent.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	gui_eventAddEventButtonClicked();
            }});

		this.boxButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
            	if(addEvent)
            		gui_eventAddButtonClicked();
            	else 
            	    gui_eventUpdateButtonClicked();
            }});
       
        
		this.cancelButton.addClickHandler(new ClickHandler(){
        	public void onClick(ClickEvent event){
        		gui_eventCancelButtonClicked();
        	}
        });
		
		this.googleButton.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				gui_eventGoogleButtonClicked();
				
			}	
		});
		
	
	}

	

	

	
	
}
