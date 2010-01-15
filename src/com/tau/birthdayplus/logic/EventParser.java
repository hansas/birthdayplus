package com.tau.birthdayplus.logic;


import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.tau.birthdayplus.dto.client.EventData;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Uid;






public class EventParser {
	private static final Logger logger = Logger.getLogger(EventParser.class.getName());

	
	/*
	 * The process of moving from this folded multiple line representation 
	 * to its single line representation is called "unfolding".
	 * Unfolding is accomplished by removing the CRLF character and 
	 * the linear whitespace character that immediately follows.
	 */
	private static String unfolding(InputStream content){
		Scanner scanner = new Scanner(content,"UTF-8");
		String unfolding="";
		
		scanner.useDelimiter("\r\n[ \t]");
		
		while(scanner.hasNext())
			 unfolding+=scanner.next();
		
		scanner.close();
		return unfolding;
		
	}
	
	/*
	 * avoids fatal error on empty property
	 * iCal4j throws fatal error on empty property
	 * in iCal file from google you have empty properties
	 * fill empty properties with "empty" string
	 */
	private static String fillEmptyProperties(String content){
		Scanner scanner= new Scanner(content);
        scanner.useDelimiter("\r\n");
        
        String noEmpty = "";
		while(scanner.hasNext()){
			noEmpty+=fillEmptyProperty(scanner.next());
			noEmpty+="\r\n";
			
		}
		scanner.close();
		return noEmpty;
		
		
	}
	
	private static String fillEmptyProperty(String property){
		 boolean opened = false;
		  int index = 0;
		  for(int i=0; i < property.length();i++){
			  char c =property.charAt(i);
			  if(c == '"')
				  opened = !opened;
			  else
				  if (c == ':'){
					  if(!opened){
						 index =i;
					     break;
					  }
				  }
		  }
		  
	     if( index+1 == property.length()){
	    	 return property+"empty";
	     }else
	    	 return property;
	}
	
	
	private static EventData  getEvent(PropertyList properties){
		//when
    	DtStart dateStart;
    	//what
    	Summary summary;
        EventData data = new EventData();
    	
    	dateStart = (DtStart) properties.getProperty(Property.DTSTART);
    	if(dateStart == null)
    	   return null;
    	
    	summary = (Summary) properties.getProperty(Property.SUMMARY);
    	if(summary == null)
    		return null;
    	
        data.setEventDate(dateStart.getTime());
        data.setEventName(summary.getValue());
        return data;
    	
    	
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void parseEvent(InputStream inContent,String from){
		//unfolding
		String content = unfolding(inContent);
		//avoids empty properties
		content = fillEmptyProperties(content);
		
		//parse calendar entry
		StringReader reader = new StringReader(content);
		logger.info(content);
		
		Calendar calendar = getCalendarInfo(reader);
		if(calendar!= null){
			
	    	Method method = (Method) calendar.getProperties().getProperty(Property.METHOD);
            if(method == null){
            	logger.info("no method property");
            	return;
            }
			for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
			   Component component = (Component) i.next();
			    logger.info("Component [" + component.getName() + "]");
			    //event component
			    if(component instanceof VEvent){
			    	Uid uid;//unique id
			    	EventData data = null;
			    	PropertyList properties = component.getProperties();
			    	
			    	if(properties.isEmpty()){
			    		logger.info("no properties");
			    		return;
			    	}
			    	else
			    	{
			    		uid = (Uid) properties.getProperty(Property.UID);
			    		if(uid == null){
			    			logger.info("no uid for event");
			    			return;
			    		}
			    		//cancel the event
			    		if(method.getValue().equalsIgnoreCase("CANCEL")){
			    			logger.info("cancel event for "+from+" uid :"+uid.toString());
			    			try{
			    		    	EventManagement.deleteEvent(from, uid.getValue());
			    			}catch(Exception ex){
			    				logger.log(Level.INFO, "the log from parsing event", ex);
			    			}
			    		}else
			    			//add event or update event
			    			if(method.getValue().equalsIgnoreCase("REQUEST")){
			    			    data = getEvent(properties);
			    			    if(data == null){
			    			    	logger.info("can't parse this event");
			    			    	return;
			    			    }else{
			    			        logger.info("add or update event "+data.getEventName()+" "+data.getEventDate().toGMTString()+" "+data.getRecurrence());
			    			        EventManagement.createOrUpdateEvent(from, uid.getValue(), data);
			    			    }
			    			}

			    	}
			    }
			
			}
		}
	
		    
	}
	
	 private static Calendar getCalendarInfo(Reader reader){
		 Calendar calendar = null;
		try {		
			CalendarBuilder builder = new CalendarBuilder();	
			calendar = builder.build(reader);
		 }catch(Exception ex){
				logger.log(Level.INFO, "the log from parsinf event", ex);
		 }
		 
		 return calendar;

	 }
	 
}
