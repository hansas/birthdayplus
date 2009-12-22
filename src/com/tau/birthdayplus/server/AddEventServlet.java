package com.tau.birthdayplus.server;
import java.io.IOException; 
import java.util.Calendar;
import java.util.Properties; 
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
	
    private enum Month {
		
		Jan(0,"Jan"),
		Feb(1,"Feb"),
		Mar(2,"Mar"),
		Apr(3,"Apr"),
		May(4,"May"),
		June(5,"June"),
		July(6,"July"),
		Aug(7,"Aug"),
		Sep(8,"Sep"),
		Oct(9,"Oct"),
		Nov(10,"Nov"),
		Dec(11,"Dec");
		
		
		private int index;
		private String name;
		private Month(int index,String name){
			this.index = index;
			this.name = name;
		}
		
		
		public static int getIndex(String name){
			for(Month month : Month.values() ){
				if(month.name.equals(name))
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
		    Pattern emailPattern = Pattern.compile("([\\w ]+) (<)([\\w@.]+)(>)");
            Matcher emailMatcher = emailPattern.matcher(fromAddress);
            String email;
            if (emailMatcher.matches()){
    		    	email = emailMatcher.group(3);
    		    	log.info("mail is : "+email);
            }else{
            	return;
            }
			log.info(fromAddress);
			log.info(message.getSubject());
			EventData event = getEvent(message.getSubject());
			if(event == null )
				return;
			else{ 
				EventManagement.createEvent(event, email);
			}
		} catch (MessagingException e) {
			log.info("exception : "+e.getMessage());
			
		}

    }
    
    
    private EventData getEvent(String subject){
    	    Pattern eventAnnualy  = Pattern.compile("(Invitation:) ([\\w ]+) (@ Annually on) (\\w{3,4}+) (\\d{1,2}) (\\(event@testrpcplus.appspotmail.com\\))");		
		    Matcher matcher = eventAnnualy.matcher( subject );
		    EventData data = null;
            
		    //match annual event 
		    if (matcher.matches()){
		    	String event = matcher.group(2);
		        int  month = Month.getIndex(matcher.group(4));
		        if (month == -1 )
		        	return data;
		    	int day = Integer.parseInt(matcher.group(5));
		    	Calendar calendar = Calendar.getInstance();
		    	calendar.clear();
		    	calendar.set(Calendar.YEAR, month, day);
		    	data = new EventData("","",event,calendar.getTime(),true);
		    }
		    return data;
    
    }
}
