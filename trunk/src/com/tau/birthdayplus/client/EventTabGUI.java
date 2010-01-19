package com.tau.birthdayplus.client;


import java.util.ArrayList;
import java.util.Date;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;



import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;


import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.ScrollPanel;

import com.google.gwt.user.client.ui.HTMLTable.Cell;

import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.Action;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.client.widgets.Icons;

import com.tau.birthdayplus.client.widgets.StaticFunctions;
import com.tau.birthdayplus.client.widgets.EventDialogBox.EventDialogBox;
import com.tau.birthdayplus.client.widgets.EventDialogBox.GadgetEventHandler;
import com.tau.birthdayplus.dto.client.EventData;


public class EventTabGUI {
	private static final DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("EEE, dd MMM , yyyy");
	private static Icons icons = (Icons) GWT.create(Icons.class);
	
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	private static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
	
	 /*
     * constants
     */
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int REMIND_LINK = 0;
	private static final int EVENT_LINK = 1;
	private static final int UPDATE_LINK = 4;
    private static final int DELETE_LINK = 5;
    
    /*GUI Widgets*/
    protected FlowPanel mainPanel;
    //events
    private FlowPanelMenuTitle eventPanel;
	private ScrollPanel eventScrollPanel;
	private FlexTable eventTableHeader;
	private HoverTable eventTable;

	
	private EventDialogBox eventDialogBox;
    private Boolean addEvent;


   
   
    
    //Data Model\\
    public EventTabDelegate eventService;
    private  WishListFriendsGUI wishlistFriendGUI;
    private WishListFriendsDelegate wishlistFriendService;
	private ArrayList<EventData> eventList = null;
	private EventData currentEvent;
	protected Birthdayplus entryPoint;
	

	
	
	
	
	

	
	public void init(){
		mainPanel = new FlowPanel();
		entryPoint.tab.add(mainPanel, "Events");
		mainPanel.setStyleName("mainPanel");
	
		eventPanel = new FlowPanelMenuTitle();
		mainPanel.add(eventPanel);
		eventPanel.setStyleName("Panel");
		
	
		Command addEventCommand = new Command() {
			public void execute() {
				gui_eventAddEventButtonClicked();
		      }
		    };
		    
		eventPanel.addMenuItem("Add Event", addEventCommand).setTitle("Add your event");
		
		Command addCalendarGadget = new Command(){

			public void execute() {
				Window.open("http://www.google.com/calendar/render?gadgeturl=http://birthdayplus.googlecode.com/svn/trunk/BirthdayPlusForCalendar.xml","_blank" , null);
			}
		};
		
	    eventPanel.addMenuItem("Events from Google Calendar",addCalendarGadget).setTitle("Add small gagdet to your Google Calendar and share you events from the calendar through Birthday+");
	 
		buildEventTable();
		eventDialogBox = new EventDialogBox();
	
		wishlistFriendGUI = new WishListFriendsGUI();
	    wishlistFriendService = new WishListFriendsDelegate();
	    
	    wishlistFriendGUI.wishlistService = wishlistFriendService;
	    wishlistFriendService.gui = wishlistFriendGUI;
	    wishlistFriendService.entryPoint = this.entryPoint;
	    
	    wishlistFriendGUI.parent = this;
	    wishlistFriendGUI.init();
		wishlistFriendGUI.wireWishlistFriendGUIEvents();
		
		 
		
	
		
	}
	
	protected void showEventTab(){
		if (eventList == null){
			ArrayList<String> userAndFriends = entryPoint.getUserAndFriendsIds();
			if(userAndFriends.size() == 1)
				entryPoint.messages.setText("You can add friends by clicking on icon and share this gadget by clicking on the icon in the gadget panel");
			 this.eventService.getEvents(userAndFriends);
		}
		wishlistFriendGUI.closeTab();
		eventPanel.setVisible(true);
	}
	
	
	
	
	/*
	* create flex table for wishlist items
	*/
	private void buildEventTable(){
		eventTableHeader = new FlexTable();
		eventPanel.add(eventTableHeader);
		eventTableHeader.addStyleName("TableHeader");
		//eventTableHeader.setSize("100%", "25px");
		eventTableHeader.setCellSpacing(0);
		
		eventTableHeader.getColumnFormatter().setWidth(0, "22px");
		eventTableHeader.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(65,62)+"px");
	//	eventTableHeader.getColumnFormatter().setWidth(1, "178px");
			
		eventTableHeader.setText(0, 0, "");
		eventTableHeader.setText(0, 1, "Event");
	//	eventTableHeader.setText(0, 2, "Event");
		eventTableHeader.setText(0, 2, "Due");
		eventTableHeader.getFlexCellFormatter().setColSpan(0, 2, 3);
		
		eventScrollPanel = new ScrollPanel();
		eventPanel.add(eventScrollPanel);
		eventScrollPanel.addStyleName("ShortScrollPanel");
//		eventScrollPanel.setSize("100%", "300px");
		
		
		eventTable = new HoverTable(0,6);
		eventScrollPanel.add(eventTable);
		eventTable.addStyleName("Table");
	//	eventTable.setWidth("100%");
		eventTable.setCellSpacing(0);
		
		eventTable.getColumnFormatter().setWidth(0, "22px");
	//	eventTable.getColumnFormatter().setWidth(1, "178px");
	//	eventTable.getColumnFormatter().setWidth(2, "40px");
		eventTable.getColumnFormatter().setWidth(1, StaticFunctions.getPercentWidthPixels(65,62)+"px");
		eventTable.getColumnFormatter().setWidth(2, StaticFunctions.getPercentWidthPixels(20,62)+"px");
		eventTable.getColumnFormatter().setWidth(UPDATE_LINK, "20px");
		eventTable.getColumnFormatter().setWidth(DELETE_LINK, "20px");
		
		eventTable.getColumnFormatter().addStyleName(2, "dueColumn");

	}
	

	
	 /*
     * show the form
     */

    private void loadForm(EventData event,Action action) {
    	this.currentEvent = event;
    	switch(action){
    	case CREATE :eventDialogBox.show(action);
    	             break;
    	case UPDATE: eventDialogBox.show(event.getEventName(), event.getEventDate(), event.getRecurrence(), action);
    	             break;
    	}
	
	}
    
 
 
    private long daysBetween(Date d1, Date d2) {
    	if(d1== null || d2 == null)
    		return -1;
    	long due = 0;
    	if (d2.after(d1))
    		due = 1;
		due += (d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24);
		return due;
	    
	}
	
	
	/*
	 * on click in the table
	 */
	 public void gui_eventEventGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
        EventData event = this.eventList.get(row);
        
        
        switch(col){
        case REMIND_LINK : gui_eventRemindButtonClicked(event,eventTable.getText(row, 1));
                           break;
                           
        case UPDATE_LINK : if(event.getUserId().equals(entryPoint.userId)){
                              this.addEvent = false;
                              loadForm(event,Action.UPDATE);
                           }
                           break;
                           
        case DELETE_LINK : if(event.getUserId().equals(entryPoint.userId))
                              this.eventService.deleteEvent(event);
                           break;
                           
        case EVENT_LINK  : if(!event.getUserId().equals(entryPoint.userId)){
        	                  eventPanel.setVisible(false);
        	                  wishlistFriendGUI.showDialogBox(event);
                           }
   		                   break;
        }
         
    }
	 
	/*
     * on click on add item
     */
    public void gui_eventAddEventButtonClicked() {
        this.addEvent = true;
        loadForm(new EventData(entryPoint.userId),Action.CREATE);
    }
    
   
    
   
    private void gui_eventRemindButtonClicked(EventData eventData,String eventName ){
    	
    		DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("yyyyMMdd");
    		String eventDay = dateFormatter.format(eventData.getEventDate());
    		String eventNextDay = dateFormatter.format(new Date(eventData.getEventDate().getTime() + MILLIS_IN_DAY));
    		
    	
    		String url = "http://www.google.com/calendar/event?action=TEMPLATE&text="+eventName+"&dates="+eventDay+"/"+eventNextDay+"&details=&location=&trp=false&sprop=&sprop=name:Birthdayplus";
    		Window.open(url, "_blank" , "height=800,width=800");
    		
    	
    }
	
    
    
   private void setTitle(int numEvents){
	   String title = "";
	   switch(numEvents){
	   case 0: title = "In this month there are no events";
	           break;	           
	   case 1: title = "In this month there is one event";
	           break;	           
	   default : title = "In this month there are "+numEvents+" events"; 
	   }   
		eventPanel.setTitle(title);

	   
   }
   @SuppressWarnings("deprecation")
public void service_eventGetEventsSuccessful(ArrayList<EventData> result) {
	    String due;
		this.eventList = result;
		this.eventTable.clear(true);
		eventTable.resizeRows(result.size());
		
		int countEvents = 0;
		Date today = new Date();
		
		int row = 0;
	
		for (EventData event : eventList) {
			Boolean myEvent = event.getUserId().equals(entryPoint.userId);
		//	Image alarmImage = new Image(GWT.getModuleBaseURL() + "alarm-icon.png");
			eventTable.setWidget(row, 0,StaticFunctions.createIcon(icons.alarmIcon(), "Remind me with Google Calendar") );
//			alarmImage.setTitle("Remind me with Google Calendar");
			
			
			if(myEvent){
				eventTable.setText(row, 1, "My"+ " "+event.getEventName());
			}
			else{
				Anchor eventLink = new Anchor( entryPoint.userFriends.get(event.getUserId())+"'s"+ " "+event.getEventName());
				eventLink.setTitle("Click to see wishlist");
			    eventTable.setWidget(row, 1, eventLink);
			   
			}
			
			due = String.valueOf(daysBetween(new Date(), event.getEventDate()));
			Label lblEventDate = new Label(due);
			if(event.getEventDate()!= null)	
    			lblEventDate.setTitle(dateFormatter.format(event.getEventDate()));
			eventTable.setWidget(row, 2, lblEventDate);
			
			if(myEvent){
			//    Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
			 //   updateImage.setTitle("update event");
			  //  Image deleteImage = new Image( GWT.getModuleBaseURL() + "trash_16.png");
			  //  deleteImage.setTitle("delete event");
				eventTable.setWidget(row, UPDATE_LINK, StaticFunctions.createIcon(icons.updateIcon(),"update event"));
	    	    eventTable.setWidget(row,DELETE_LINK, StaticFunctions.createIcon(icons.deleteIcon(), "delete event")); 
			}
			row++;
			
			
			if((event.getEventDate().getYear() == today.getYear()) && (event.getEventDate().getMonth()== today.getMonth()))
				countEvents+=1;
		}
		
		setTitle(countEvents);
		
	}
	
	
	public void service_eventCreateEventSuccessful() {
	
		this.eventService.getEvents(entryPoint.getUserAndFriendsIds());		
	}

	public void service_eventUpdateEventSuccessful() {
	
		 this.eventService.getEvents(entryPoint.getUserAndFriendsIds());		
	}

	public void service_eventDeleteEventSuccessful() {
		
	     this.eventService.getEvents(entryPoint.getUserAndFriendsIds());				
	}
	
	public void service_eventGetEventsFailed(Throwable caught) {
		this.eventTable.clear(true);
		eventTable.resizeRows(0);
		entryPoint.messages.setText("GetEventsFailed"+caught.getMessage());
	}
	
	public void service_eventCreateEventFailed(Throwable caught) {
		entryPoint.messages.setText("CreateEventFAiled"+caught.getMessage());
	}

	public void service_eventUpdateEventFailed(Throwable caught) {
		entryPoint.messages.setText("UpdateEventFailed"+caught.getMessage());
	}

	public void service_eventDeleteEventFailed(Throwable caught) {
		entryPoint.messages.setText("DeleteEventFailed"+caught.getMessage());
	}
	

	
	public void wireEventGUIEvents(){
		this.eventTable.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Cell cellForEvent = eventTable.getCellForEvent(event);
				if(cellForEvent!=null)
				   gui_eventEventGridClicked(cellForEvent);
			}
		});
		 
		this.eventDialogBox.addGagdetEventEvent(new GadgetEventHandler(){

			public void onCreateGadgetEvent(String eventName, Date eventDate,
					boolean recurrence) {
				currentEvent.setEventName(eventName);
				currentEvent.setEventDate(eventDate);
				currentEvent.setRecurrence(recurrence);
				eventService.createEvent(currentEvent);
			}

			public void onUpdateGadgetEvent(String eventName, Date eventDate,
					boolean recurrence) {
				currentEvent.setEventName(eventName);
				currentEvent.setEventDate(eventDate);
				currentEvent.setRecurrence(recurrence);
				eventService.updateEvent(currentEvent);
				
			}
			
		});
		

	
	}

	

	

	
	
}
