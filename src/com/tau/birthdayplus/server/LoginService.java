package com.tau.birthdayplus.server;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.logic.UserManagement;

public class LoginService extends HttpServlet{
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
       
   
        log.info("query string is : "+req.getQueryString());
   
        
        if (user != null) {
        log.info("I know you");
        log.info("query string"+req.getQueryString());
        String id = req.getParameter("openSocialId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int day =0;
        int month =0;
        int year =0;
        try{
           day = Integer.parseInt(req.getParameter("day"));
           month = Integer.parseInt(req.getParameter("month"));
           year = Integer.parseInt(req.getParameter("year"));
        }catch(NumberFormatException ex){
        	
        }
        Date date = new Date(year-1900,month-1,day); 
        Guest guest = new Guest(id, firstName, lastName, date ,user.getEmail(),user.getUserId()) ;
        try{
        UserManagement.createProfile(guest);
        }catch(Exception e){
        	
        }
        
        resp.setContentType("text/html; charset=UTF-8");
        String html = "<body><script type='text/javascript'>try {window.opener.gadgets.io.oauthReceivedCallbackUrl_ = document.location.href;} catch (e) {}window.close();</script>Close this window.</body>";
        resp.getWriter().println(html);
     //   resp.getWriter().println("Hello, " + user.getNickname()+"your want to add "+req.getParameter("href"));
        } else {
        	
        	log.info("I don't know you");
        	// resp.setContentType("text/plain");
        	// resp.getWriter().println(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
        	
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()+"?"+req.getQueryString()));
         }
       }


}
