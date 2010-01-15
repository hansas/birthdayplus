package com.tau.birthdayplus.server;

import java.io.IOException; 
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties; 
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Part;
import javax.mail.Session; 
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.*; 
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.logic.EventParser;






/*
 * get the .ics attachment from the email with event details
 */
public class AddEventServlet extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddEventServlet.class.getName());
	
	 /**
     * The default buffer size to use.
     */
	/*
	 * Invitation: vcxv dgdg @ Annually on June 6 (event@testrpcplus.appspotmail.com)
	 * Invitation: kkk kk skk @ Annually from 9pm to 10pm on June 26 (event@testrpcplus.appspotmail.com)
	 */
	private static final Pattern eventAnnualyPattern  = Pattern.compile("(?:Invitation:) ([\\p{L} ]+) (?:@ Annually(?: from [\\w:]+ to [\\w:]+)? on) (\\w{3,}) (\\d{1,2}) (\\(event@birthdayplus.appspotmail.com\\))");
	/*
	 * Invitation: something @ Thu Dec 24, 2009 (event@testrpcplus.appspotmail.com)
	 * Invitation: something @ Sat Jan 23 8:30am - 9:30am (event@testrpcplus.appspotmail.com)
	 */
    private static final Pattern eventOncePattern = Pattern.compile("(?:Invitation:) ([\\p{L} ]+) (?:@ \\w+) (\\w{3,}) (\\d{1,2})(?:, \\d+)?(?: [\\w :-]+)? (\\(event@birthdayplus.appspotmail.com\\))");

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
			
			String contentType = message.getContentType();
			InputStream inContent = null;
			logger.info("Message ContentType: ["+contentType+"]");
				 
			if (contentType.indexOf("multipart") > -1) {
			    DataHandler dataHandler = message.getDataHandler();
			    DataSource dataSource = dataHandler.getDataSource();
				MimeMultipart mimeMultipart = new MimeMultipart(dataSource);
				logger.info("The number of parts :"+mimeMultipart.getCount());
				
				for(int i=0;i<mimeMultipart.getCount();i++){
					Part part = mimeMultipart.getBodyPart(i);           
				    contentType = part.getContentType();
					logger.info("Part "+i+"ContentType: ["+contentType+"]");
					if (part.getDisposition() == null) {
						//Assume text/plain
						
                    } else if (part.getDisposition().equals("attachment")) { 
                    	// Create a new ByteArrayDataSource with this part
                        MimeBodyPart inboundMimeBodyPart = (MimeBodyPart)part;
                        contentType = inboundMimeBodyPart.getContentType();
                        logger.info("Attachment type :"+contentType);
                        inContent = part.getInputStream();
                        
                        if(contentType.indexOf("application/ics")>-1){
                        	logger.info("it's ics file,lets read it");
                            EventParser.parseEvent(inContent, fromAddress);
                        }	
                    }
				}
			} else {
			    //Assume text/plain
			   
			}
			logger.info("Received email from=["+fromAddress+"] subject=["+message.getSubject()+"]");
		
         }catch (Exception ex){
         	logger.log(Level.INFO, "the log from reading email for event", ex);

 		}
		return ;
		
    }
    
    
   
    private EventData getEvent(String subject){
		    Matcher eventAnnualyMatcher = eventAnnualyPattern.matcher( subject );
		    Matcher eventOnceMatcher = eventOncePattern.matcher(subject);
		    EventData data = null;
            
		    //match  event
		    logger.info("match event");
		    logger.info(subject);
		    
		    if (eventAnnualyMatcher.matches() || eventOnceMatcher.matches()){
		    	logger.info("matching the subject");
		    	String event = eventAnnualyMatcher.matches() ? eventAnnualyMatcher.group(1) : eventOnceMatcher.group(1);
		    	logger.info("event :" +event);
		        int  month = eventAnnualyMatcher.matches() ? Month.getIndex(eventAnnualyMatcher.group(2)) : Month.getIndex(eventOnceMatcher.group(2));
		        logger.info("month :"+month);
		        if (month == -1 )
		        	return data;
		    	int day = Integer.parseInt(eventAnnualyMatcher.matches() ? eventAnnualyMatcher.group(3) : eventOnceMatcher.group(3));
		    	logger.info("day : "+day);
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
    	
    	logger.info("The date is : "+calendar.toString());
    	return calendar.getTime();
    }
}
