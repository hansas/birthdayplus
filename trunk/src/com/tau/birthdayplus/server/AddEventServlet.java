package com.tau.birthdayplus.server;
import java.io.IOException; 
import java.util.Calendar;
import java.util.Date;
import java.util.Properties; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session; 
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*; 

import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.logic.EventManagement;






public class AddEventServlet extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddEventServlet.class.getName());
	/*
	 * Invitation: vcxv dgdg @ Annually on June 6 (event@testrpcplus.appspotmail.com)
	 * Invitation: kkk kk skk @ Annually from 9pm to 10pm on June 26 (event@testrpcplus.appspotmail.com)
	 */
	private static final Pattern eventAnnualyPattern  = Pattern.compile("(?:Invitation:) ([\\p{L} ]+) (?:@ Annually(?: from [\\w:]+ to [\\w:]+)? on) (\\w{3,}) (\\d{1,2}) (\\(event@testrpcplus.appspotmail.com\\))");
	/*
	 * Invitation: something @ Thu Dec 24, 2009 (event@testrpcplus.appspotmail.com)
	 * Invitation: something @ Sat Jan 23 8:30am - 9:30am (event@testrpcplus.appspotmail.com)
	 */
    private static final Pattern eventOncePattern = Pattern.compile("(?:Invitation:) ([\\p{L} ]+) (?:@ \\w+) (\\w{3,}) (\\d{1,2})(?:, \\d+)?(?: [\\w :-]+)? (\\(event@testrpcplus.appspotmail.com\\))");

	/*
	 * Olya Vingurt <yalo_niv@yahoo.com>
	 */
    private static final Pattern emailPattern = Pattern.compile("(?:[\\w ]+) (?:<)([\\w@.]+)(>)");


	
    private enum Month {
		
		Jan(0,"Jan","January"),
		Feb(1,"Feb","February"),
		Mar(2,"Mar","March"),
		Apr(3,"Apr","April"),
		May(4,"May","May"),
		June(5,"June","June"),
		July(6,"July","July"),
		Aug(7,"Aug","August"),
		Sep(8,"Sep","September"),
		Oct(9,"Oct","October"),
		Nov(10,"Nov","November"),
		Dec(11,"Dec","December");
		
		
		private int index;
		private String shortName;
		private String fullName;
		private Month(int index,String shortName,String fullName){
			this.index = index;
			this.shortName = shortName;
			this.fullName = fullName;
		}
		
		
		public static int getIndex(String name){
			for(Month month : Month.values() ){
				if(month.shortName.equals(name) || month.fullName.equals(name))
					return month.index;
			}
			return -1;
			
		}
	}
	
	/**
	 * 
	 */
	
	
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  throws IOException {
    	 Properties props = new Properties(); 
         Session session = Session.getDefaultInstance(props, null); 
         try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			Address[] from = message.getFrom();
			String fromAddress = "";
			if (from.length > 0) {
			    fromAddress = from[0].toString();
			}
            Matcher emailMatcher = emailPattern.matcher(fromAddress);
            String email;
            if (emailMatcher.matches()){
    		    	email = emailMatcher.group(1);
    		    	log.info("mail is : "+email);
            }else{
            	return;
            }
            log.info(message.getContentType());
            log.info(message.getEncoding());
			log.info("the subject is : "+message.getSubject());
			EventData event = getEvent(message.getSubject());
			if(event == null )
				return;
			else{ 
				EventManagement.createEvent(event, email);
			}
		} catch (MessagingException e) {
			log.info("exception : "+e.getMessage());
			
		}catch (Exception ex){
        	log.log(Level.INFO, "the log from calling to createEvent with gmail", ex);

		}

    }
    
    
    private EventData getEvent(String subject){
		    Matcher eventAnnualyMatcher = eventAnnualyPattern.matcher( subject );
		    Matcher eventOnceMatcher = eventOncePattern.matcher(subject);
		    EventData data = null;
            
		    //match  event
		    log.info("match event");
		    log.info(subject);
		    
		    if (eventAnnualyMatcher.matches() || eventOnceMatcher.matches()){
		    	log.info("matching the subject");
		    	String event = eventAnnualyMatcher.matches() ? eventAnnualyMatcher.group(1) : eventOnceMatcher.group(1);
		    	log.info("event :" +event);
		        int  month = eventAnnualyMatcher.matches() ? Month.getIndex(eventAnnualyMatcher.group(2)) : Month.getIndex(eventOnceMatcher.group(2));
		        log.info("month :"+month);
		        if (month == -1 )
		        	return data;
		    	int day = Integer.parseInt(eventAnnualyMatcher.matches() ? eventAnnualyMatcher.group(3) : eventOnceMatcher.group(3));
		    	log.info("day : "+day);
		    	if(eventAnnualyMatcher.matches())
		    	   data = new EventData("","",event,buildDate(month,day),true,false);
		    	else
		    	   data = new EventData("","",event,buildDate(month,day),false,false);
		    }
		    return data;
    
    }
    
    private static Date buildDate(int month , int day){
    	Calendar calendar = Calendar.getInstance();
    	int currentYear = calendar.get(Calendar.YEAR);
    	int currentMonth = calendar.get(Calendar.MONTH);
    	calendar.clear();
    	if(currentMonth > month || (currentMonth == month && Calendar.DAY_OF_MONTH > day)){
    		calendar.set(currentYear+1, month, day);
    		
    	}
    	else	
        	calendar.set(currentYear, month, day);
    	
    	log.info("The date is : "+calendar.toString());
    	return calendar.getTime();
    }
}
