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
	private static final Pattern pricePattern = Pattern.compile("(<input type=\"hidden\" name=\"minPrice\" id=\"minPrice\" value=\")(\\d+)(\" />)");
    private static final Pattern titleThumbnailPattern = Pattern.compile("(onSessionCreated, connected, loginUser,')(\\d+',')([\\w ]+)(',')([\\w/.]+)(')" );

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
        //	String wish = URLDecoder.decode(req.getParameter("wish"), "UTF-8");
        	//log.info(wish);
        	log.info(req.getParameter("link"));
        	log.info(req.getParameter("wish"));
        	
        
        	log.info(req.getParameter("thumbnail"));
        //	log.info("עברית");
        		
        	WishlistItemData data = Parse( req.getParameter("link"));
        	if((data.getPrice()!=null)&& (req.getParameter("wish")!=null)){
        		log.info("calling to create wishlistItem");
        		data.setLink(req.getParameter("link"));
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
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
         }
       }
	
	
	private static WishlistItemData Parse(String url){
		URLConnection connection = null;
		boolean hasPrice = false;
	//	boolean hasTitleThumbnail = false;
		WishlistItemData item = new WishlistItemData();
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  while ( scanner.hasNextLine() ){
			  String line =scanner.nextLine();
		      if(!hasPrice)
		    	  hasPrice = findPrice(line,item);
		 //     if (!hasTitleThumbnail)
		  //  	  hasTitleThumbnail = findTitleThumbnail(line,item);
		      if(hasPrice/* && hasTitleThumbnail*/)
		    	  return item;
		      }
		  
	
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		return item;
		

	}
	
	
	 private static boolean findTitleThumbnail(String aLine,WishlistItemData item){
		    Matcher matcher = titleThumbnailPattern.matcher( aLine );
		   
		    if (matcher.find() ){
		      item.setItemName(matcher.group(3));
		 //     item.setThumbnail("http://img.zap.co.il/pics/"+ matcher.group(5));
		      log.info("item name is : "+item.getItemName());
		   //   log.info("item thumbnail is : "+item.getThumbnail());
		      return true;
		    }
		    return false;
		    
		  }
	 
	 
	 private static boolean findPrice(String aLine , WishlistItemData item){
		 Matcher priceMatcher = pricePattern.matcher(aLine);
		 if (priceMatcher.matches()){
		    	String price = priceMatcher.group(2);
		        item.setPrice(Double.parseDouble(price));
		        log.info("the price is :"+price);
		        return true;
		 }
		 return false;
	 }
	 
	 
	



}
