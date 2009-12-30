package com.tau.birthdayplus.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.KeyFactory;

import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.DALWrapper;
import com.tau.birthdayplus.dto.client.EventData;

public class EventManagement {
	private static final Logger log = Logger.getLogger(EventManagement.class.getName());
	
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

	public static void createEvent(EventData event) throws UserNotFoundException{
		DALWrapper wrapper = new DALWrapper();
		try{
			BusinessObjectDAL.createEvent(event,wrapper);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void createEvent(EventData event,String gmail){
		DALWrapper wrapper = new DALWrapper();
		try{
			Guest guest = wrapper.loadGuestByGmail(gmail);
			wrapper.newCreateEvent(event, guest);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void deleteEvent( EventData eventD) throws UserException {
	    BusinessObjectDAL.deleteEvent(eventD);
	}

	public static void updateEvent( EventData eventD){
		BusinessObjectDAL.updateEvent(eventD);
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
				Calendar eDate = Calendar.getInstance();
				for (Event event: guestEvents){
					int eMonth = event.getEventDate().getMonth();
					int eDay = event.getEventDate().getDate();
					log.info(event.getEventName()+" date: "+event.getEventDate());
					System.out.println(eDay);
					eDate.clear();
					eDate.set(Calendar.YEAR, event.getEventDate().getYear()+1900);
					eDate.set(Calendar.MONTH, eMonth);
					eDate.set(Calendar.DATE, eDay);
					log.info("eDate: "+eDate.get(Calendar.DATE)+" "+eDate.get(Calendar.MONTH)+" "+(eDate.get(Calendar.YEAR)-1900));
					if (eDate.after(cal)||eDate.equals(cal)){
						events.add(EventManagement.eventToEventData(event,wrapper));
					}
				}
			}
		}
		Collections.sort(events, EventManagement.EVENT_DATA_ORDER);
		return events;
	}
	
	
	

}
