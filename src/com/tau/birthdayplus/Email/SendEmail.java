package com.tau.birthdayplus.Email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class SendEmail {
	private static final Logger log = Logger.getLogger(SendEmail.class.getName());
    private static String emailAddress = "gadgetBirthdayPlus@gmail.com ";	
    
    
	
	
	
	/*
	 * group - the group for this present
	 * htmlMessage - message from the buyer
	 */
	public static void sendEmailToGroup(GroupEmail group,String htmlMessage){
	Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    String msgBody = "You are participating in the group that will buy a car for Olga Vingurt . Olga Vingurt will buy a present .";

    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress, "Birthday+"));
        
        ArrayList<InternetAddress>  replyTo = new ArrayList<InternetAddress>();
        replyTo.add(new InternetAddress("yalo_niv@yahoo.com","Olga Vingurt"));
        
        msg.addRecipient(Message.RecipientType.TO,
                         new InternetAddress("yalo_niv@yahoo.com", "Olga Vingurt"));
        
        msg.setSubject("A present for Olga Vingurt's Birthday");
        msg.setText(msgBody);
        
        Multipart mp = new MimeMultipart();

        MimeBodyPart htmlBuyerPart = new MimeBodyPart();
        htmlBuyerPart.setContent(htmlMessage, "text/html");
        mp.addBodyPart(htmlBuyerPart);
         
        msg.setContent(mp);
        
        MimeBodyPart htmlParticipantsPart = new MimeBodyPart();
        htmlParticipantsPart.setContent(participantsHTML(),"text/html");
        mp.addBodyPart(htmlParticipantsPart);
        
        
        Transport.send(msg);

    } catch (AddressException e) {
    	log.log(Level.INFO, "the log from calling to sendEmailToGroup", e);
    } catch (MessagingException e) {
    	log.log(Level.INFO, "the log from calling to sendEmailToGroup", e);
    } catch (UnsupportedEncodingException e) {
    	log.log(Level.INFO, "the log from calling to sendEmailToGroup", e);
	}catch (Exception e){
    	log.log(Level.INFO, "the log from calling to sendEmailToGroup", e);

	}

}
	
	private static String participantsHTML(){
        String table ="";
		
		table += "<body style='font-size:13px;color:#222;font-family:Arial,Sans-serif'>";
		table+= "<div style='padding:2px'><h1 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'>Participants are :</h1>";
		
		table += "<table cellspacing='0' cellpadding='0'>" +
				 "<tr>" +
				 "<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><span style='font-family:Courier New,monospace'>&nbsp;</span></td>";
		table+= "<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>" +
				"<div><div style='margin:0 0 0.3em 0'>";
		table+= "olga vingurt";
		table+= "<span style='font-size:11px;color:#888'> - buyer</span>" +
				"</div></div></td></tr>";
		
		for (int i=0;i<5;i++){
		table+="<tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>";
		table+="<span style='font-family:Courier New,monospace'>&nbsp;</span></td>";
		table+="<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>";
		table+="<div><div style='margin:0 0 0.3em 0'>";
		table+="name"+i;
		table+="</div></div></td></tr>";
		}
		
		
		table+= "</table></div></body>";
       
		
	return table;	
		
	}
	
	
	
}
