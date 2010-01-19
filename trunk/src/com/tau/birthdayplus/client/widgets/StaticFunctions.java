package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.tau.birthdayplus.dto.client.ParticipatorData;

public class StaticFunctions {
	

	
	/*
	 * return % of the total width in pixels
	 */
	public static int getPercentWidthPixels(int part,int constantPart){
		int totalWidth = Window.getClientWidth()-constantPart;
		return (part*totalWidth)/100;
	}
	
	/*
	 * return anchor with thumbnail tooltip
	 */
	public static Anchor getAnchorWithThumbnail(String url,String title,String thumbnailUrl){
		Anchor anchor =new Anchor(title,url,"_blank");
		TooltipListener listener  = new TooltipListener(
	        		"<img   src="+"'"+thumbnailUrl+"'"+"alt='"+title+"' height='90' width='90' style = 'background-color: #f7d8a9; padding: 3px; border: 1px solid #6f3d29;'>", 5000 ,true);
		anchor.addMouseOverHandler( listener);
		anchor.addMouseOutHandler(listener);
		
		
		return anchor;
		
		
	}
	
	

	public static ParticipatorList getParticipatorsList(final ArrayList<ParticipatorData> participators,final Date eventDate,final String userId,NumberFormat moneyFormat){
		Integer totalAmount = 0;
		Integer userPart = 0;
		String html =" <div style='background-color:#FFFFCC;border:1px solid #FFCC35;'><p style ='color:#224499;font-weight:bold;'>Participators are :<p><UL style='list-style-type: square;padding:1px 10px 1px 10px !important;margin:0px !important; list-style-position:inside;'>";
		
	    for(ParticipatorData user : participators){
	    	totalAmount+=user.getMoney();
	    	
	    	if(user.getUserId().equals(userId)){
	    	    userPart=user.getMoney();
     		    html+="<LI style='color:#224499'>"+user.getUserFirstName()+" "+user.getUserLastName()+" - "+moneyFormat.format(user.getMoney());
	         }else
 		        html+="<LI>"+user.getUserFirstName()+" "+user.getUserLastName()+" - "+moneyFormat.format(user.getMoney());
	    	}
	    	html+="</UL></div>";
	    	
	    	return new ParticipatorList(html,userPart,totalAmount);
	}
	
	
	 public static Image createIcon(AbstractImagePrototype  img, String tip){
	    	Image image = img.createImage();
	    	image.setTitle(tip);
	    	return image;
	 }
	 
	

}
