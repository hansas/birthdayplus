package com.tau.birthdayplus.server;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.tau.birthdayplus.Email.SendEmail;
import com.tau.birthdayplus.dto.client.WishlistItemData;


public class AddWishlistItem  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddWishlistItem.class.getName());
	private static final Pattern pricePattern = Pattern.compile("(<input type=\"hidden\" name=\"minPrice\" id=\"minPrice\" value=\")(\\d+)(\" />)");
    private static final Pattern titleThumbnailPattern = Pattern.compile("(onSessionCreated, connected, loginUser,')(\\d+',')([\\w ]+)(',')([\\w/.]+)(')" );

	/**
	 * 
	 */


	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
 {
    
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
       
        
        if (user != null) {
        try{
        	WishlistItemData  data = new WishlistItemData();
        	Parse(data, req.getParameter("href"));
        	
        }catch(Exception ex){
        	
        }
        resp.setContentType("text/html; charset=UTF-8");
        String html = "<div id='pnl_AfterAction'><table width='100%'><tr><td  align='center' class='ablack' dir='rtl'><b><br />המוצר שנבחר התווסף לרשימה שלך בהצלחה. <br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>לחץ כאן לסגירת החלון</u></a></b></td></tr></table></div></div>";
        resp.getWriter().println(html);
        } else {
        	// resp.setContentType("text/plain");
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
         }
       }
	
	
	private static void Parse(WishlistItemData item,String url){
		URLConnection connection = null;
		boolean hasPrice = false;
		boolean hasTitleThumbnail = false;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  while ( scanner.hasNextLine() ){
			  String line =scanner.nextLine();
		      if(!hasPrice)
		    	  hasPrice = findPrice(line,item);
		      if (!hasTitleThumbnail)
		    	  hasTitleThumbnail = findTitleThumbnail(line,item);
		      if(hasPrice && hasTitleThumbnail)
		    	  break;
		      }
		  
	
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		

	}
	
	
	 private static boolean findTitleThumbnail(String aLine,WishlistItemData item){
		    Matcher matcher = titleThumbnailPattern.matcher( aLine );
		   
		    if (matcher.find() ){
		      item.setItemName(matcher.group(3));
		      item.setThumbnail("http://img.zap.co.il/pics/"+ matcher.group(5));
		      log.info("item name is : "+item.getItemName());
		      log.info("item thumbnail is : "+item.getThumbnail());
		      return true;
		    }
		    return false;
		    
		  }
	 
	 
	 private static boolean findPrice(String aLine , WishlistItemData item){
		 Matcher priceMatcher = pricePattern.matcher(aLine);
		 if (priceMatcher.matches()){
		    	String price = priceMatcher.group(2);
		        item.setPrice(Double.parseDouble(price));
		        return true;
		 }
		 return false;
	 }



}
