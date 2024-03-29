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

import com.tau.birthdayplus.Email.ParticipatorEmail;
import com.tau.birthdayplus.Email.SendEmail;
import com.tau.birthdayplus.Email.SendEmail.CancelFor;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dal.DALWrapper;
import com.tau.birthdayplus.dal.BusinessObjectDAL.GroupStatus;
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

	public static Event createEvent(EventData event) throws UserNotFoundException, UserException{
		DALWrapper wrapper = new DALWrapper();
		Event e = null;
		try{
			e = wrapper.createEvent(event);
		}
		finally{
			wrapper.close();
		}
		return e;
	}
	
	public static Event createEventWithWrapper(EventData event,DALWrapper wrapper) throws UserNotFoundException, UserException{
		return wrapper.createEvent(event);
	}
	
	public static void createEvent(EventData event,String gmail) throws UserException{
		DALWrapper wrapper = new DALWrapper();
		try{
			Guest guest = wrapper.loadGuestByGmail(gmail);
			wrapper.newCreateEvent(event, guest);
		}
		finally{
			wrapper.close();
		}
	}
	
	public static void deleteEvent( EventData eventD) throws Exception {
		DALWrapper wrapper = new DALWrapper();
		ArrayList<WishlistItem> itemParticipatorDelete = new ArrayList<WishlistItem>();
		Event e = new Event("Dummy from delete event", "", new Date(), false, false);
	    wrapper.deleteEvent(eventD,itemParticipatorDelete,e);
	 //   log.info("The event that was reterned by BusinessObjectDAL.deleteEvent is: "+e.getEventName());
	    try{
		    if (itemParticipatorDelete!=null){
		    //	log.info("there is participators in items that should be deleted");
			    for (WishlistItem item : itemParticipatorDelete){
			    	sendEmailAndDeleteParticipators(item,e,wrapper,false);
			    }
		    }
	    }
	    finally{
			wrapper.close();
		}
	}

	public static void updateEvent( EventData eventD) throws UserException{
		BusinessObjectDAL.updateEvent(eventD,eventD.getEventId());
	}
	
	public static EventData eventToEventData(Event event,DALWrapper wrapper){
		Guest guest = wrapper.getGuestByKey(event.getKey().getParent());
		return new EventData(KeyFactory.keyToString(event.getKey()),guest.getId(),event.getEventName(),event.getEventDate(),event.getRecurrence(),event.getIsDeleted());
	}
	
	public static ArrayList<EventData> getEvents(ArrayList<String> UserIdList) throws UserException {
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
					if (!event.getIsDeleted()){
						int eMonth = event.getEventDate().getMonth();
						int eDay = event.getEventDate().getDate();
						eDate.clear();
						eDate.set(Calendar.YEAR, event.getEventDate().getYear()+1900);
						eDate.set(Calendar.MONTH, eMonth);
						eDate.set(Calendar.DATE, eDay);
						if (eDate.after(cal)||(((eDate.get(Calendar.DATE)+1)==cal.get(Calendar.DATE))&&(eDate.get(Calendar.MONTH)==cal.get(Calendar.MONTH))
								&&(cal.get(Calendar.YEAR)==eDate.get(Calendar.YEAR)))||((eDate.get(Calendar.DATE)==cal.get(Calendar.DATE))&&(eDate.get(Calendar.MONTH)==cal.get(Calendar.MONTH))
								&&(cal.get(Calendar.YEAR)==eDate.get(Calendar.YEAR)))){
							events.add(EventManagement.eventToEventData(event,wrapper));
						}
					}
				}
			}
		}
		Collections.sort(events, EventManagement.EVENT_DATA_ORDER);
		return events;
	}
	
	public static void sendEmailAndDeleteParticipators(WishlistItem item,Event e,DALWrapper wrapper,Boolean isCron) throws Exception{
		ArrayList<Participator> participators = item.getParticipators();
		ArrayList<Participator> participatorsToDelete = new ArrayList<Participator>();
	    ArrayList<ParticipatorEmail> participatorsE = WishlistManagement.getParticipatorEmailList(participators,wrapper);
		GroupStatus status = GroupStatus.CANCEL;
		CancelFor cancel;
		if (isCron){
			cancel = CancelFor.CRON;
		}
		else{
			cancel = CancelFor.EVENT;
		}
		wrapper.sendEmailToGroup(KeyFactory.keyToString(item.getKey()), wrapper.getGuestByKey(item.getKey().getParent()).getId(), "", participatorsE, 0.0, status,cancel,e);
		for (Participator p : participators){
			participatorsToDelete.add(p);
		}
		for (Participator p : participatorsToDelete){
			item.removeParticipator(p);
			wrapper.deleteParticipator(p);
		}
		wrapper.makePersistantItem(item);
	}
	
	public static void cronDeleteEventAndUpdateRecurrent() throws Exception{
		ArrayList<WishlistItem> itemParticipatorDelete = new ArrayList<WishlistItem>();
		DALWrapper wrapper = new DALWrapper();
		Event e = new Event("Dummy from cron", "", new Date(), false, false);
		wrapper.cronDeleteEventAndUpdateRecurrent(itemParticipatorDelete,e);
	    try{
		    if (itemParticipatorDelete!=null){
		    	for (WishlistItem item : itemParticipatorDelete){
			    	sendEmailAndDeleteParticipators(item,e,wrapper,true);
			    }
		    }
	    }
	    finally{
			wrapper.close();
		}
	}
	
	
	/*
	 * find guest by gmail and if he has event with googleUID == googleUID then delete this event
	 */
	public static void deleteEvent(String gmail,String googleUID) throws Exception{
		DALWrapper wrapper = new DALWrapper();
		try{
			Event event = wrapper.deleteCalendarEvent(gmail,googleUID);
			if (event!=null){
				EventData eventD = eventToEventData(event, wrapper);
				deleteEvent(eventD);
			}
		}
		finally{
			wrapper.close();
		}
	}
	/*
	 * find guest by gmail and if he has event with googleUID == googleUID then update this event 
	 * with data from event , else create new event for this guest with given googleUID
	 */
	public static void createOrUpdateEvent(String gmail,String googleUID,EventData eventD) throws UserException, UserNotFoundException{
		DALWrapper wrapper = new DALWrapper();
		try{
			Boolean exists = wrapper.createOrUpdateEvent(gmail,googleUID,eventD);
			if (exists==null){
				return;
			}
			if (!exists){
				Guest guest = wrapper.loadGuestByGmail(gmail);
				Event e = wrapper.newCreateEvent(eventD, guest);
				e.setGoogleUID(googleUID);
				wrapper.makePersistant(e);
			}
		}
		finally{
			wrapper.close();
		}
	}
	
	
	

}
