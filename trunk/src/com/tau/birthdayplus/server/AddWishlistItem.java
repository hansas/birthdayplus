package com.tau.birthdayplus.server;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.logging.Level;
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
import com.google.gwt.user.server.rpc.RPCServletUtils;

import com.tau.birthdayplus.Email.SendEmail;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.logic.WishlistManagement;


public class AddWishlistItem  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddWishlistItem.class.getName());
    private static final Pattern priceAfterPattern = Pattern.compile("([1-9]\\d*)(?:,(\\d+))?( ?\\p{Sc})");
    private static final Pattern priceBeforePattern = Pattern.compile("(?:\\p{Sc} ?)([1-9]\\d*)(?:,(\\d+))?()");

    private static final Pattern contentType = Pattern.compile("(?:charset(?: *))(?:=)(?: *)([^;]+)(;{0,1})");



	/**
	 * 
	 */


	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
 {
        boolean created = false;
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
       
        
        if (user != null) {
        try{
        	if(req.getCharacterEncoding() == null) {
        		   req.setCharacterEncoding("UTF-8");
        		}
        	log.info("request query string"+req.getQueryString());
        	log.info(req.getParameter("link"));
        	log.info(req.getParameter("wish"));
        	log.info(req.getParameter("thumbnail"));
        		
        	WishlistItemData data = Parse( req.getParameter("link"));
        	if((data!=null)&& (req.getParameter("wish")!=null)){
        		log.info("calling to create wishlistItem");
        		data.setLink(req.getParameter("link"));
        		if(!req.getParameter("thumbnail").equals("null"))
        		   data.setThumbnail(req.getParameter("thumbnail"));
        		data.setItemName(req.getParameter("wish"));
        	    WishlistManagement.createWishlistItem(data, user.getUserId());
        	    created = true;
        	}
        }catch(Exception ex){
        	log.log(Level.INFO, "the log from calling to createWishlistItem with googleId", ex);
        }
        resp.setContentType("text/html; charset=UTF-8");
        String html = "";
        if (created)
            html = "<div id='pnl_AfterAction'><table width='100%'><tr><td  align='center' class='ablack' dir='rtl'><b><br />המוצר שנבחר התווסף לרשימה שלך בהצלחה. <br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>לחץ כאן לסגירת החלון</u></a></b></td></tr></table></div></div>";
        else 
            html = "<div id='pnl_AfterAction'><table width='100%'><tr><td  align='center' class='ablack' dir='rtl'><b><br />המוצר שנבחר לא הוסף לרשימה שלך . <br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>לחץ כאן לסגירת החלון</u></a></b></td></tr></table></div></div>";

        resp.getWriter().println(html);
        } else {
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()+"?"+req.getQueryString()));
         }
       }
	
	
	private  static WishlistItemData Parse(String url){
		URLConnection connection = null;
		WishlistItemData item = null;
		Double price ;
		try {
		  connection =  new URL(url).openConnection();
		  Matcher contentMatcher = contentType.matcher(connection.getContentType());
		  String charset = null;
		    if (contentMatcher.find()){
		    	charset = contentMatcher.group(1);
		    }
		  Scanner scanner;
		  log.info("charset is : "+charset);
		  if(charset!=null)  
		     scanner = new Scanner(connection.getInputStream(),charset);
		  else
			 scanner = new Scanner(connection.getInputStream()); 
		 
		  while ( scanner.hasNextLine() ){
			  String line =scanner.nextLine();
		      
		      if((price = findPrice(line))>=0){
		    	  item = new WishlistItemData();
		    	  item.setPrice(price);
		    	  return item;
		      }
		  }
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		return item;
		

	}
	
	
	
	 
	 
	 private  static Double findPrice(String aLine ){
		 Matcher priceBeforeMatcher = priceBeforePattern.matcher(aLine);
		 Matcher priceAfterMatcher = priceAfterPattern.matcher(aLine);
		 Double price = -1.0;
		
		 if (priceBeforeMatcher.find()) 
			 price = getPrice(priceBeforeMatcher);
		 else
			 if(priceAfterMatcher.find())
				 price = getPrice(priceAfterMatcher);
		 return price;
	 }
	 
	 private static Double getPrice(Matcher priceMatcher){
		 Double price = -1.0;
		   String afterComma = priceMatcher.group(2);
		    if(afterComma == null)
		    	price = Double.parseDouble(priceMatcher.group(1));
		    else
		    	price = Double.parseDouble(priceMatcher.group(1)+priceMatcher.group(2));
		    System.out.println("the price is :"+price);
		    return price;
		 
	 }

	 
	 
	



}
