package com.tau.birthdayplus.client;


import java.util.ArrayList;
import java.util.Date;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
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
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tau.birthdayplus.client.Birthdayplus;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.widgets.EventDialogBox;
import com.tau.birthdayplus.client.widgets.FlowPanelMenuTitle;
import com.tau.birthdayplus.client.widgets.HoverTable;
import com.tau.birthdayplus.dto.client.EventData;


public class EventTabGUI {
	private static final DateTimeFormat dateFormatter = 	DateTimeFormat.getFormat("EEE, dd MMM , yyyy");
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	
	 /*
     * constants
     */
	CwConstants constants = GWT.create(CwConstants.class);
	private static final int EVENT_LINK = 1;
	private static final int UPDATE_LINK = 3;
    private static final int DELETE_LINK = 4;
    
    /*GUI Widgets*/
    protected FlowPanel mainPanel;
    //events
    private FlowPanelMenuTitle eventPanel;
	private ScrollPanel eventScrollPanel;
	private FlexTable eventTableHeader;
	private HoverTable eventTable;
	private ArrayList<RadioButton> radioButtonList;
	
	private EventDialogBox eventDialogBox;
    private Boolean addEvent;
    
   
    
    //Data Model\\
    public EventTabDelegate eventService;
    private  WishListFriendsGUI wishlistFriendGUI;
    private WishListFriendsDelegate wishlistFriendService;
	private ArrayList<EventData> eventList = null;
	private EventData currentEvent;
	protected Birthdayplus entryPoint;
	
//	private Label errorMsgLabel ;
	
	
	private enum Actions {
		CREATE("Add"), 
		REMOVE("Delete"), 
		UPDATE("Update");
		
		private String description;
		
		private Actions(String description){
			this.description = description;
		}
		
		public String toString(){
			return description;
		}
	
	}
	
	

	
	public void init(){
	//	eventList = new ArrayList<EventData>(); 

		mainPanel = new FlowPanel();
		entryPoint.tab.add(mainPanel, "Events");
		mainPanel.setStyleName("Panel");
	//	mainPanel.setSize("100%","350px");
	
	//	eventPanel = new FlowPanel();
		eventPanel = new FlowPanelMenuTitle();
		mainPanel.add(eventPanel);
		eventPanel.setStyleName("Panel");
	//	eventPanel.setSize("100%", "350px");
		
		Command remindMeCommand = new Command(){
			public void execute() {
				gui_eventGoogleButtonClicked();
		      }
		    };
		
		eventPanel.addMenuItem("Remind Me",remindMeCommand).setTitle("Add event reminder to your Google Calendar");
		
		Command addEventCommand = new Command() {
			public void execute() {
				gui_eventAddEventButtonClicked();
		      }
		    };
		eventPanel.addMenuItem("Create Event", addEventCommand).setTitle("Add your event");
		
		Command addCalendarGadget = new Command(){

			public void execute() {
				Window.open("http://www.google.com/calendar/render?gadgeturl=http://birthdayplus.googlecode.com/svn/trunk/BirthdayPlusForCalendar.xml","_blank" , null);
			}
		};
		
	
	    eventPanel.addMenuItem("Calendar",addCalendarGadget).setTitle("Add small gagdet to your Google Calendar and share you events from the calendar through Birthday+");
	 
		buildEventTable();
		eventDialogBox = new EventDialogBox();
	
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
	
	protected void showEventTab(){
		if (eventList == null)
			 this.eventService.getEvents(entryPoint.getUserAndFriendsIds());
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
		eventTableHeader.getColumnFormatter().setWidth(1, "178px");
			
		eventTableHeader.setText(0, 0, "");
		eventTableHeader.setText(0, 1, "Event");
	//	eventTableHeader.setText(0, 2, "Event");
		eventTableHeader.setText(0, 2, "Due");
		eventTableHeader.getFlexCellFormatter().setColSpan(0, 2, 3);
		
		eventScrollPanel = new ScrollPanel();
		eventPanel.add(eventScrollPanel);
		eventScrollPanel.addStyleName("ShortScrollPanel");
//		eventScrollPanel.setSize("100%", "300px");
		
		
		eventTable = new HoverTable(0,5);
		eventScrollPanel.add(eventTable);
		eventTable.addStyleName("Table");
	//	eventTable.setWidth("100%");
		eventTable.setCellSpacing(0);
		
		eventTable.getColumnFormatter().setWidth(0, "22px");
		eventTable.getColumnFormatter().setWidth(1, "178px");
		eventTable.getColumnFormatter().setWidth(2, "40px");
		
		eventTable.getColumnFormatter().addStyleName(2, "dueColumn");

	}
	

	
	 /*
     * show the form
     */

    private void loadForm(EventData eventData,Actions action) {
    	this.currentEvent = eventData;
		eventDialogBox.center();
		eventDialogBox.setText(action.toString()+ " Event");
		eventDialogBox.setButtonText(action.toString());
		eventDialogBox.show(eventData);
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
        case UPDATE_LINK : if(event.getUserId().equals(entryPoint.userId)){
                              this.addEvent = false;
                              loadForm(event,Actions.UPDATE);
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
        loadForm(new EventData(entryPoint.userId),Actions.CREATE);
    }
    
   
    
    
    private void gui_eventGoogleButtonClicked(){
    	int row = -1;
    
    	if(radioButtonList == null)
    		return;
    	
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
		radioButtonList = new ArrayList<RadioButton>();
		int countEvents = 0;
		Date today = new Date();
		
		int row = 0;
	
		for (EventData event : eventList) {
			Boolean myEvent = event.getUserId().equals(entryPoint.userId);
			RadioButton radioButton = new RadioButton("event");
			eventTable.setWidget(row, 0, radioButton);
			radioButtonList.add(radioButton);
			radioButton.setTitle("add reminder to Google Calendar");
			
			if(myEvent){
				eventTable.setText(row, 1, "My"+ " "+event.getEventName());
			}
			else{
				Hyperlink eventLink = new Hyperlink( entryPoint.userFriends.get(event.getUserId())+"'s"+ " "+event.getEventName(),null);
				eventLink.setTitle("Click to see wishlist");
			    eventTable.setWidget(row, 1, eventLink);
			   
			}
			
			due = String.valueOf(daysBetween(new Date(), event.getEventDate()));
			Label lblEventDate = new Label(due);
			if(event.getEventDate()!= null)	
    			lblEventDate.setTitle(dateFormatter.format(event.getEventDate()));
			eventTable.setWidget(row, 2, lblEventDate);
			
			if(myEvent){
			    Image updateImage = new Image( GWT.getModuleBaseURL() + "pencil_16.png");
			    updateImage.setTitle("update event");
			    Image deleteImage = new Image( GWT.getModuleBaseURL() + "trash_16.png");
			    deleteImage.setTitle("delete event");
				eventTable.setWidget(row, 3, updateImage);
	    	    eventTable.setWidget(row,4, deleteImage); 
			}
			row++;
			
			if((event.getEventDate().getYear() == today.getYear()) && (event.getEventDate().getMonth()== today.getMonth()))
				countEvents+=1;
		}
		
		setTitle(countEvents);
		
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
//		Window.alert("Unable to get event list");
		
		
	}
	
	public void service_eventCreateEventFailed(Throwable caught) {
	//	Window.alert("Unable to create new event");
		
	}

	public void service_eventUpdateEventFailed(Throwable caught) {
	//	Window.alert("Unable to update event");
		
	}

	public void service_eventDeleteEventFailed(Throwable caught) {
	//	Window.alert(caught.getMessage());
	}
	



	
	public void wireEventGUIEvents(){
		this.eventTable.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Cell cellForEvent = eventTable.getCellForEvent(event);
				if(cellForEvent!=null)
				   gui_eventEventGridClicked(cellForEvent);
			}
		});
		
		this.eventDialogBox.addCloseHandler(new CloseHandler<PopupPanel>(){

			public void onClose(CloseEvent<PopupPanel> event) {
				if(event.isAutoClosed())
					return;
				else{
					EventData data = eventDialogBox.getInput();
					if(data == null)
						return;
					if(addEvent)
						 eventService.createEvent(data);
					else
						eventService.updateEvent(data);
				}
			}
		});

	
	}

	

	

	
	
}
