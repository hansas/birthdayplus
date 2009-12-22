package com.tau.birthdayplus.Email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.tau.birthdayplus.server.AddWishlistItem;


public class SendEmail {
	private static final Logger log = Logger.getLogger(SendEmail.class.getName());
    private static String emailAddress = "olga.vingurt@gmail.com";	
	public SendEmail(){
		
	}
	
	
	
	public static void sendEmail(){
	Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    String msgBody = "hello, it's my first message";

    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress, "Olga Vingurt"));
        msg.addRecipient(Message.RecipientType.TO,
                         new InternetAddress("yalo_niv@yahoo.com", "Olga Vingurt"));
        msg.setSubject("Your first message from app engine");
        msg.setText(msgBody);
        Transport.send(msg);

    } catch (AddressException e) {
        log.info(e.getMessage());
    } catch (MessagingException e) {
        log.info(e.getMessage());
    } catch (UnsupportedEncodingException e) {
	    log.info(e.getMessage());
	}

}
	
}
