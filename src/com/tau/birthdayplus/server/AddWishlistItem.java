package com.tau.birthdayplus.server;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
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

import com.tau.birthdayplus.dto.client.WishlistItemData;



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
      
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        Double price =0.0;
        
        
        try{
        	if(req.getCharacterEncoding() == null) {
        		   req.setCharacterEncoding("UTF-8");
        		}
        	log.info("request query string"+req.getQueryString());
        	log.info(req.getParameter("link"));
        	log.info(req.getParameter("wish"));
        	log.info(req.getParameter("thumbnail"));
        		
        	price = Parse( req.getParameter("link"));
        	
        }catch(Exception ex){
        	log.log(Level.INFO, "the log from calling to createWishlistItem with googleId", ex);
        }
        if (user != null) {
        	resp.sendRedirect("/Birthdayplus.html"+"?"+req.getQueryString()+"&price="+price);
         
        } else {
        	resp.sendRedirect(userService.createLoginURL("/Birthdayplus.html"+"?"+req.getQueryString()+"&price="+price));
         }
       }
	
	
	private  static Double Parse(String url){
		URLConnection connection = null;
		Double price = 0.0;
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
		    	  return price;
		      }
		  }
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		return price;
		

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
