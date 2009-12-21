package com.tau.birthdayplus.server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


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
        log.info("I know you");
        log.info("query string"+req.getQueryString());
        log.info(req.getParameter("href"));
        
      //  resp.setContentType("text/plain");
        resp.setContentType("text/html; charset=UTF-8");
        String html = "<div id='pnl_AfterAction'><table width='100%'><tr><td  align='center' class='ablack' dir='rtl'><b><br />	hamozar nimhak beazlaha<br /><br /><a href='javascript:void(0);' onClick='window.close()'><u>lahaz kan lesgiral hahalon</u></a></b></td></tr></table></div></div>";
        resp.getWriter().println(html);
     //   resp.getWriter().println("Hello, " + user.getNickname()+"your want to add "+req.getParameter("href"));
        } else {
        	
        	log.info("I don't know you");
        	 resp.setContentType("text/plain");
        	// resp.getWriter().println(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
        	
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()+"?href="+req.getParameter("href")));
         }
       }


}
