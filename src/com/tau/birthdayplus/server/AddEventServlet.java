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
	private static Pattern emailPattern = Pattern.compile("(?:[\\w ]+) (?:<)([\\w@.]+)(>)");
	
	
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
			}else
				return;
			
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
                            EventParser.parseEvent(inContent, email);
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
    
    
   
   
    
   
}
