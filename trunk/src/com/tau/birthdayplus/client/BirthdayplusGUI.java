package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public class BirthdayplusGUI {
	BirthdayplusServiceDelegate BplusService ;
	
	/* Constants. */

	
	/* GUI Widgets */

	
	 /* Data model */
     private ArrayList<EventData> events;
     private EventData currentEvent;
     private WishlistItemData currentWishlistItem;
	
	
	///////////////////////////////////////Events\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void service_eventListContactsFailed(Throwable caught) {
		
		
	}

	public void service_eventListRetrievedFromService(
			ArrayList<EventData> result) {
		// TODO Auto-generated method stub
		
	}

	public void service_eventCreateEventFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void service_eventCreateEventSuccessful() {
		// TODO Auto-generated method stub
		
	}

	public void service_eventUpdateEventFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void service_eventUpdateEventSuccessful() {
		// TODO Auto-generated method stub
		
	}

	public void service_eventDeleteEventFailed(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void service_eventDeleteEventSuccessful() {
		// TODO Auto-generated method stub
		
	}
	
	////////////////////////endEvents\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

}
