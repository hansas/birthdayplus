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

import com.tau.birthdayplus.dto.client.WishlistItemData;


public class AddWishlistItem  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddWishlistItem.class.getName());
	/**
	 * 
	 */


	protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException
 {
    	log.info("I'm here");
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
       
   
        log.info(req.getParameter("href"));
        
        if (user != null) {
        try{
        	WishlistItemData  data = new WishlistItemData();
        	Parse(data, req.getParameter("href"));
        	
        }catch(Exception ex){
        	
        }
        resp.setContentType("text/html; charset=UTF-8");
        String html = "<div id='pnl_AfterAction'><table width='100%'><tr><td  align='center' class='ablack' dir='rtl'><b><br />המוצר שנבחר התווסף לרשימה שלך בהצלחה. <br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>לחץ כאן לסגירת החלון</u></a></b></td></tr></table></div></div>";
        resp.getWriter().println(html);
     //   resp.getWriter().println("Hello, " + user.getNickname()+"your want to add "+req.getParameter("href"));
        } else {
        	
        	log.info("I don't know you");
        	 resp.setContentType("text/plain");
        	// resp.getWriter().println(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
        	
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
         }
       }
	
	
	private static void Parse(WishlistItemData item,String url){
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  while ( scanner.hasNextLine() ){
		        if(findLine( scanner.nextLine(),item))
		        		break;
		      }
	
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		

	}
	
	
	 private static boolean findLine(String aLine,WishlistItemData item){
		    Pattern pattern = Pattern.compile("onSessionCreated, connected, loginUser,'(\\d)+','[\\w ]+','[\\w/.]+'" );
		    Matcher matcher = pattern.matcher( aLine );

		    if (matcher.find() ){
		      String[] temp = matcher.group(0).split("[,;]");
		      item.setItemName(temp[4].replace("'", " ").trim());
		      log.info("item name is : "+item.getItemName());
		      item.setThumbnail("http://img.zap.co.il/pics/"+temp[5].replace("'", " ").trim());
		      log.info("item thumbnail is : "+item.getThumbnail());
		      return true;
		    }
		    return false;
		    
		  }



}
