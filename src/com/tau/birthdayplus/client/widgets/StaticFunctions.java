package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.tau.birthdayplus.dto.client.ParticipatorData;

public class StaticFunctions {
	private static  NumberFormat shortMoneyFormat = NumberFormat.getFormat("\u20AA#,##0");

	
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
	@SuppressWarnings("deprecation")
	public static Anchor getAnchorWithThumbnail(String url,String title,String thumbnailUrl){
		Anchor anchor =new Anchor(title,url,"_blank");
		TooltipListener listener  = new TooltipListener(
	        		"<img   src="+"'"+thumbnailUrl+"'"+"alt='"+title+"' height='90' width='90' style = 'background-color: #f7d8a9; padding: 3px; border: 1px solid #6f3d29;'>", 5000 ,true);
		anchor.addMouseListener( listener);
		
		return anchor;
		
		
	}
	
	
	@SuppressWarnings("deprecation")
	public static ParticipatorList getParticipatorsList(final ArrayList<ParticipatorData> participators,final Date eventDate,final String userId){
		Integer totalAmount = 0;
		Integer userPart = 0;
		Date today = new Date();
		 String html =" <div style='background-color:#FFFFCC;border:1px solid #FFCC35;'><p style ='color:#224499;font-weight:bold;'>Participators are :<p><UL style='list-style-type: square;padding:1px 10px 1px 10px !important;margin:0px !important; list-style-position:inside;'>";
	    	for(ParticipatorData user : participators){
	    		totalAmount+=user.getMoney();
	    		if(user.getUserId().equals(userId)){
	    			if((eventDate.getYear()==today.getYear()) && (eventDate.getMonth() == today.getMonth()))
	    	    		userPart=user.getMoney();
     		    html+="<LI style='color:#224499'>"+user.getUserFirstName()+" "+user.getUserLastName()+" - "+shortMoneyFormat.format(user.getMoney());

	    		}else
 		        html+="<LI>"+user.getUserFirstName()+" "+user.getUserLastName()+" - "+shortMoneyFormat.format(user.getMoney());

	    	}
	    	html+="</UL></div>";
	    	
	    	return new ParticipatorList(html,userPart,totalAmount);
	}

}
