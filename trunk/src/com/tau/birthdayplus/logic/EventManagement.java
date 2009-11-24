package com.tau.birthdayplus.logic;

import java.util.ArrayList;

import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.dto.Event;



public class EventManagement {
	
	public void createEvent(String id, Event event) {
		try{
			BusinessObjectDAL.createEvent(event);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
	}

	public void deleteEvent(String id, Event event) {
		// TODO Auto-generated method stub
		
	}

	
	public static void updatEvent(String id, Event event){
	    if(event.getUserId().equals(id)){
	    	try{
	    	   BusinessObjectDAL.updateEvent(event);
	    	}catch(Exception ex){
	    		throw new RuntimeException(ex);
	    	}
	    }else{
	    	throw new RuntimeException(new Exception("You cannot update someone else's event "));
	    }
		
	}
	
	public ArrayList<Event> getEvents(ArrayList<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

}
