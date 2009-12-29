package com.tau.birthdayplus.Email;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
	private static 	SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM , yyyy");
	private static  DecimalFormat shortMoneyFormat = new DecimalFormat("\u20AA#,##0");

    
    
  	/*
	 * group - the group for this present
	 * htmlMessage - message from the buyer
	 * closeGrpoup - is the email about closing group or opening the group
	 */
	public static void sendEmailToGroup(GroupEmail group,String htmlMessage,boolean closeGroup) throws EmailException{
	//	ArrayList<InternetAddress> addresses = new ArrayList<InternetAddress>();
		InternetAddress[] addresses = new InternetAddress[group.getParticipators().size()-1];
		String buyerName = "";
		InternetAddress buyerAddress = null;
    	Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String message = "<body style='font-size:13px;color:#222;font-family:Arial,Sans-serif'>";
        
        String htmlTable= "<p><br /><div style='padding:2px'>" +
        		         "<h1 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:14px;font-weight:bold;color:#222'>Participants are :</h1>" +
        		         "<table cellspacing='0' cellpadding='0'> ";
        int i =0;
        try {
        for(ParticipatorEmail participator:group.getParticipators()){
        	String name = participator.getUserFirstName()+" "+participator.getUserLastName();
    		htmlTable+="<tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><span style='font-family:Courier New,monospace'>&nbsp;</span></td>";
        	htmlTable+= "<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div><div style='margin:0 0 0.3em 0'>";
        	if(participator.getUserId().equals(group.getBuyerId())){
        	    buyerName =	name;	
			    buyerAddress = new InternetAddress(participator.getEmail(),buyerName);
		        htmlTable+= buyerName+"<span style='font-size:11px;color:#888'> - buyer</span>"+" - "+shortMoneyFormat.format(participator.getMoney());
        	}else{
        		addresses[i] = new InternetAddress(participator.getEmail(),name);
        		htmlTable+=name+" - "+shortMoneyFormat.format(participator.getMoney());
        	}
        	htmlTable+="</div></div></td></tr>";
        	i++;
        }
        htmlTable+="</table></div></p>";
        } catch (UnsupportedEncodingException e) {
			log.log(Level.INFO, "the log from calling to sendEmailToGroup", e);
			throw new EmailException(" a problem occured while sending an email");
		}
        if(closeGroup)
            message+="<p>You are participating in the group that will buy a "+group.getItemName()+" for "+group.getUserName()+" .<br /> "+buyerName+" will buy a present and here is a short message from  "+buyerName.split(" ")[0]+": "+"</p>";
        else 
            message+="<p>You are participating in the group that will buy a "+group.getItemName()+" for "+group.getUserName()+" .<br /> "+buyerName+" reopened this group and here is a short message from  "+buyerName.split(" ")[0]+": "+"</p>";

        message+=htmlMessage;
       
        message+=htmlTable;
        message+="</body>";
         
    

    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress, "Birthday+"));
            
        InternetAddress[]  replyTo = {buyerAddress};
        
        msg.setReplyTo(replyTo);
        
        msg.addRecipients(Message.RecipientType.TO, addresses);
        
        msg.addRecipient(Message.RecipientType.CC,
                         buyerAddress);
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress("yalo_niv@yahoo.com","Olga Vingurt"));
       
        msg.setSubject("A present for "+group.getUserName()+"'s "+group.getEventName()+" on "+dateFormatter.format(group.getEventDate()));
        
        Multipart mp = new MimeMultipart();

        MimeBodyPart htmlBuyerPart = new MimeBodyPart();
        htmlBuyerPart.setContent(message, "text/html");
        mp.addBodyPart(htmlBuyerPart);
        msg.setContent(mp);
        Transport.send(msg);

    } catch (AddressException e) {
    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);  	
		throw new EmailException(" a problem occured while sending an email");

    } catch (MessagingException e) {
    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
		throw new EmailException(" a problem occured while sending an email");

    } catch (UnsupportedEncodingException e) {
    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
		throw new EmailException(" a problem occured while sending an email");

	}catch (Exception e){
    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
		throw new EmailException(" a problem occured while sending an email");


	}

}
	
	

	
	
	
	
	
}
