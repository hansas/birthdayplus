package com.tau.birthdayplus.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.DALWrapper;
import com.tau.birthdayplus.dto.client.EventData;

public class EventManagement {
	
	public static final Comparator<EventData> EVENT_DATA_ORDER =
        new Comparator<EventData>() {
			public int compare(EventData e1, EventData e2) {
					if ((e1.getEventDate() == null) || (e2.getEventDate()==null)){
						return 0;
					}
					int result = e2.getEventDate().compareTo(e1.getEventDate());
					if(result==-1){
						return 1;
					}
					else if (result==1){
						return -1;
					}
					else{
						return 0;
					}
				}
		};

	public static void createEvent(EventData event){
		try{
			BusinessObjectDAL.createEvent(event);
		}catch(Exception ex){
			throw new RuntimeException("createEvent(EventData event) failed", ex);
		}
	}
	
	public static void deleteEvent( EventData eventD) {
	//	if(KeyFactory.stringToKey(eventD.getEventId()).equals(uId)){
	    	BusinessObjectDAL.deleteEvent(eventD);
	 //   }else{
	  //  	throw new RuntimeException(new Exception("You cannot delete someone else's event!"));
	 //   }
	}

	public static void updateEvent( EventData eventD){
	//    if(KeyFactory.stringToKey(eventD.getEventId()).equals(uId)){
	    	BusinessObjectDAL.updateEvent(eventD);
	  //  }else{
	  //  	throw new RuntimeException(new Exception("You cannot update someone else's event!"));
	   // }
	}
	
	public static EventData eventToEventData(Event event,DALWrapper wrapper){
		Guest guest = wrapper.getGuestByKey(event.getKey().getParent());
		return new EventData(KeyFactory.keyToString(event.getKey()),guest.getId(),event.getEventName(),event.getEventDate(),event.getRecurrence());
	}
	
	public static ArrayList<EventData> getEvents(ArrayList<String> UserIdList) {
		DALWrapper wrapper = new DALWrapper();
		try{
		return getEventsByGuests(wrapper.getGuestsById(UserIdList),wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<EventData> getEventsByGuests(List<Guest> guests,DALWrapper wrapper){
		ArrayList<EventData> events = new ArrayList<EventData>();
		for (Guest guest: guests){
			List<Event> guestEvents = guest.getEvents();
			if ((guest!=null) && (!guestEvents.isEmpty())){
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DATE, 7);
				Calendar eDate = Calendar.getInstance();
				for (Event event: guestEvents){
					int eMonth = event.getEventDate().getMonth();
					int eDay = event.getEventDate().getDate();
					eDate.clear();
					eDate.set(Calendar.YEAR, event.getEventDate().getYear());
					eDate.set(Calendar.MONTH, eMonth);
					eDate.set(Calendar.DATE, eDay);
					if (eDate.after(cal)){
						events.add(EventManagement.eventToEventData(event,wrapper));
					}
					else if(event.getRecurrence()==true){
						Date newEDate = new Date(cal.get(Calendar.YEAR)-1900,eMonth,eDay);
						int currentDom = cal.get(Calendar.DAY_OF_MONTH);
						int currentMonth = cal.get(Calendar.MONTH) + 1;
						if ((eMonth<currentMonth)||((eMonth==currentMonth)&&(eDay<currentDom))){
							newEDate.setYear(cal.get(Calendar.YEAR)+1);
						}
						event.setEventDate(newEDate);
						updateEvent(eventToEventData(event, wrapper));
						events.add(EventManagement.eventToEventData(event,wrapper));
					}
				}
			}
		}
		Collections.sort(events, EventManagement.EVENT_DATA_ORDER);
		return events;
	}

}
