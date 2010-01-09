package com.tau.birthdayplus.Email;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
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
	private static  DecimalFormat shortMoneyFormat = new DecimalFormat("\u20AA#,##0");
	
	public static enum CancelFor{
		ITEM("item"),
		EVENT("event"),
		NONE("NONE");
		
		private String description;
		
		private CancelFor(String description){
			this.description = description ;
		}
		
		public String toString(){
			return this.description;
		}
	} 

    
    
  	/*
	 * group - the group for this present
	 * htmlMessage - message from the buyer
	 * closeGrpoup - is the email about closing group or opening the group
	 */
	public static void sendEmailCloseGroup(GroupEmail group,String htmlMessage,Double actualPrice) throws EmailException{
		sendEmailAboutGroup(group,htmlMessage,actualPrice,true);
    }
	
	public static void sendEmailOpenGroup(GroupEmail group,String htmlMessage)throws EmailException{
		sendEmailAboutGroup(group,htmlMessage,group.getPrice(),false);
	}
	
	public static void sendEmailCancelGroup(GroupEmail group,CancelFor cancel)throws EmailException{
		int groupSize = group.getParticipators().size();
		InternetAddress[] addresses = new InternetAddress[groupSize];
		
		int i=0;
		
		if(group.getParticipators().isEmpty()){
			log.info("can't send email to empty group - cancel group)");
			return;
		}
		try{
		    for(ParticipatorEmail participator:group.getParticipators()){
            	String name = participator.getUserFirstName()+" "+participator.getUserLastName();
                addresses[i++] = new InternetAddress(participator.getEmail(),name);
			}
        }catch (UnsupportedEncodingException e) {
	    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
			throw new EmailException(" a problem occured while sending an email");
		}
        
        String message = "<body style='font-size:13px;color:#222;font-family:Arial,Sans-serif'>";
        message+= "<img src=\"http://birthdayplus.googlecode.com/svn/trunk/logo.gif\" align='center'/>"; 
        message += "<p>You are participating in the group that wants to buy a " +group.getItemName()+" for "+group.getUserName()+" .<br /><br />";
        message+="The group has been canceled because "+group.getUserName()+" has deleted this "+cancel.toString()+".";
        message+="</p></body>";
        
        try {
			sendMails(new InternetAddress(emailAddress, "Birthday+") ,null,addresses,null,"A present for "+group.getUserName()+"'s "+group.getEventName(),message);
		} catch (UnsupportedEncodingException e) {
			log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
			throw new EmailException(" A problem occured while sending an email");
		}
        
		
	
	}
	
	private static void sendEmailAboutGroup(GroupEmail group,String htmlMessage,Double actualPrice,boolean closeGroup)throws EmailException{
		int groupSize = group.getParticipators().size();
		InternetAddress[] addresses = new InternetAddress[groupSize-1];
		String buyerName = "";
		InternetAddress buyerAddress = new InternetAddress();
		String[] names = new String[groupSize];
		double[] participation = new double[groupSize]; 
		
		if(group.getParticipators().isEmpty()){
			log.info("can't send email to empty group - sendEmailAboutGroup)");
			return;
		}
    	
        String message = "<body style='font-size:13px;color:#222;font-family:Arial,Sans-serif'>";
        
        message+= "<img src=\"http://birthdayplus.googlecode.com/svn/trunk/logo.gif\" align='center'/>"; 
        
        String htmlTable= "<p><br /><div style='padding:2px'>" +
        		         "<h1 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:14px;font-weight:bold;color:#222;text-decoration:underline;'>Participants are :</h1>" +
        		         "<table cellspacing='0' cellpadding='0'> ";
        
        buyerName =  getGroupInfo(group,addresses,names,participation,buyerAddress);
      
        if(closeGroup)
        	calculateActualParticipation(actualPrice , participation);
        
        for(int i=0;i<groupSize;i++){
    		htmlTable+="<tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><span style='font-family:Courier New,monospace'>&nbsp;</span></td>";
        	htmlTable+= "<td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div><div style='margin:0 0 0.3em 0'>";
        	htmlTable+=names[i]+" - "+shortMoneyFormat.format(participation[i]);   	
        	htmlTable+="</div></div></td></tr>";
        }
        htmlTable+="</table></div></p>";
        
        String buyerMessage = message;
       
        if(closeGroup)
        {
            message+="<p style='line-height: 150%;'>You are participating in the group that will buy a "+group.getItemName()+" for "+group.getUserName()+" .<br /> "+"The actual price of the "+group.getItemName()+" is "+shortMoneyFormat.format(actualPrice)+".<br /><br />"+buyerName+" will buy a present and here is a short message from  "+buyerName.split(" ")[0]+": "+"</p>";
            buyerMessage+="<p style='line-height: 150%;'>You are buying a group present "+group.getItemName()+" for "+group.getUserName()+" .<br /> "+"The actual price of the "+group.getItemName()+" is "+shortMoneyFormat.format(actualPrice)+".<br /><br />"+"You can reopen this group in IBuy tab in Birthday+ gadget."+"</p>";
        }
        else
        {
            message+="<p style='line-height: 150%;'>You are participating in the group that wants to buy a "+group.getItemName()+" for "+group.getUserName()+" .<br /><br /> "+buyerName+" reopened this group and here is a short message from  "+buyerName.split(" ")[0]+": "+"</p>";
            buyerMessage+="<p style='line-height: 150%;'>You have reopened the group that wants to buy a "+group.getItemName()+" for "+group.getUserName()+" .</p>";

        }
        
       

        message+=htmlMessage;
       
        message+=htmlTable;
        message+="</body>";
        
        buyerMessage+=htmlTable;
        buyerMessage+="</body>";
        
        Address[]  replyTo = {buyerAddress};
        
      
        
        try {
        	if(group.getParticipators().size()>1)
			   sendMails(new InternetAddress(emailAddress, "Birthday+") ,replyTo,addresses,null,"A present for "+group.getUserName()+"'s "+group.getEventName(),message);
		} catch (UnsupportedEncodingException e) {
			log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
			throw new EmailException(" A problem occured while sending an email");
		}
		
		try{
			sendMails(new InternetAddress(emailAddress, "Birthday+"),null,replyTo,null,"A present for "+group.getUserName()+"'s "+group.getEventName(),buyerMessage );
		} catch (UnsupportedEncodingException e) {
			log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
			throw new EmailException(" A problem occured while sending an email");
		}
		
		
	}
	
	private static String  getGroupInfo(GroupEmail group ,InternetAddress[] groupAddresses ,String[] groupNames,double[] participation,InternetAddress buyerAddress)throws EmailException{
		String buyerName = "";
		
		int i = 0;
		int j= 0;
	    try {
		for(ParticipatorEmail participator:group.getParticipators()){
        	String name = participator.getUserFirstName()+" "+participator.getUserLastName();
        	if(participator.getUserId().equals(group.getBuyerId())){
        	    buyerName =	name;	
        	    buyerAddress.setAddress(participator.getEmail());
        	    buyerAddress.setPersonal(buyerName);
			}else{
				groupAddresses[j++] = new InternetAddress(participator.getEmail(),name);
			}
        	groupNames[i] = name;
        	participation[i] = participator.getMoney();
        	i++;
		}
	    } catch (UnsupportedEncodingException e) {
	    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
			throw new EmailException(" A problem occured while sending an email");
			
		}
		
		return buyerName;
		
		
	}
	
	private static void sendMails(Address from,Address[]  replyTo,Address[] recipients, Address[] sendCopyTo,String subject,String htmlBody)throws EmailException{
		 try {
			    Properties props = new Properties();
		        Session session = Session.getDefaultInstance(props, null);
		        
		        Message msg = new MimeMessage(session);
		        msg.setFrom(from);
		        
		        if(replyTo!=null)
		           msg.setReplyTo(replyTo);
		        
		        msg.addRecipients(Message.RecipientType.TO, recipients);
		        
		        if(sendCopyTo!=null){
		        	log.info("send copy to null");
		           msg.addRecipients(Message.RecipientType.CC,sendCopyTo);
		        }
		        msg.setSubject(subject);
		        
		        Multipart mp = new MimeMultipart();

		        MimeBodyPart htmlBuyerPart = new MimeBodyPart();
		        htmlBuyerPart.setContent(htmlBody, "text/html");
		        mp.addBodyPart(htmlBuyerPart);
		        msg.setContent(mp);
		        Transport.send(msg);

		    } catch (AddressException e) {
		    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);  	
				throw new EmailException(" A problem occured while sending an email");

		    } catch (MessagingException e) {
		    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
				throw new EmailException(" A problem occured while sending an email");

		    }catch (Exception e){
		    	log.log(Level.INFO, "the log from calling to  sendEmailToGroup", e);
				throw new EmailException(" A problem occured while sending an email");
			}
		
		
	}
	
	private static void calculateActualParticipation(Double actualPrice , double[] participation){
		    double total = 0.0;
		    for(double sum : participation)
		    	total+=sum;
		
	        double percent = (actualPrice/total);
	        
	        boolean roundFloor=true;
	        total=0.0;
	        for(int i=0;i<participation.length-1;i++){
	        	if(roundFloor)
	        		participation[i] = Math.floor(participation[i]*percent);
	        	else
	        		participation[i] = Math.ceil(participation[i]*percent);
	        	total+=participation[i];
	        	roundFloor= !roundFloor;
	        }
	        participation[participation.length-1] = actualPrice - total;
	       
	}
	
	

	
	
	
	
	
}
