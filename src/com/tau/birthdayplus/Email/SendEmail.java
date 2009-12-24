package com.tau.birthdayplus.Email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
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

import com.tau.birthdayplus.server.AddWishlistItem;


public class SendEmail {
	private static final Logger log = Logger.getLogger(SendEmail.class.getName());
    private static String emailAddress = "gadgetBirthdayPlus@gmail.com ";	
	public SendEmail(){
		
	}
	
	
	
	public static void sendEmailToGroup(String htmlEmail){
	Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    String msgBody = "hello, it's my first message";

    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress, "Olga Vingurt"));
        msg.addRecipient(Message.RecipientType.TO,
                         new InternetAddress("yalo_niv@yahoo.com", "Olga Vingurt"));
        msg.setSubject("Your first message from app engine");
        Multipart mp = new MimeMultipart();

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlEmail, "text/html");
        mp.addBodyPart(htmlPart);
         
        msg.setContent(mp);
      //  msg.setText(htmlEmail);
        Transport.send(msg);

    } catch (AddressException e) {
        log.info(e.getMessage());
    } catch (MessagingException e) {
        log.info(e.getMessage());
    } catch (UnsupportedEncodingException e) {
	    log.info(e.getMessage());
	}

}
	
	private String participantsHTML(){
		String table ="";
		table = "<head><title>Participants are : </title></head>";
		table += "<table cellspacing='0' cellpadding='0'><tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><span style='font-family:Courier New,monospace'> ;</span></td>";
		table+= "<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div><div style='margin:0 0 0.3em 0'>";
		table+= "olga vingurt";
		table+= "<span style='font-size:11px;color:#888'> - buyer</span></div></div></td></tr>";
		
		table+="<tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>";
		table+="<span style='font-family:Courier New,monospace'>&nbsp;</span></td>";
		table+="<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>";
		table+="<div><div style='margin:0 0 0.3em 0'>";
		table+="name";
		table+="</div></div></td></tr>";
		
	return table;	
		
	}
	
	
	
}
