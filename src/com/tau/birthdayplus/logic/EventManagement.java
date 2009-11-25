package com.tau.birthdayplus.logic;

import java.util.ArrayList;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.client.Event;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dto.client.EventData;

public class EventManagement {
	
	public static void createEvent(EventData event){
		try{
			BusinessObjectDAL.createEvent(event);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public static void deleteEvent(String uId, EventData eventD) {
		if(KeyFactory.stringToKey(eventD.getEventId()).equals(uId)){
	    	BusinessObjectDAL.deleteEvent(eventD);
	    }else{
	    	throw new RuntimeException(new Exception("You cannot delete someone else's event!"));
	    }
	}

	public static void updatEvent(String uId, EventData eventD){
	    if(KeyFactory.stringToKey(eventD.getEventId()).equals(uId)){
	    	BusinessObjectDAL.updateEvent(eventD);
	    }else{
	    	throw new RuntimeException(new Exception("You cannot update someone else's event!"));
	    }
	}
	
	public static ArrayList<EventData> getEvents(ArrayList<String> UserIdList) {
		return BusinessObjectDAL.getEvents(UserIdList);
	}

}
