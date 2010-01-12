package com.tau.birthdayplus.cron;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.logic.WishlistManagement;
import com.tau.birthdayplus.logic.WishlistManagement;
import com.tau.birthdayplus.logic.EventManagement;


@SuppressWarnings("serial")  
public class cronItemJob extends HttpServlet {
	 private static final Logger _logger = Logger.getLogger(cronItemJob.class.getName());  

	 public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
	  try {  
	    WishlistManagement.cronItemJob();
	    _logger.info("Cron Item Job has been executed");
	 }  

	  catch (Exception ex) {  
		  _logger.severe("Cron Item Job failed: " + ex.getMessage());
		  _logger.log(Level.SEVERE, "the log from cron item job ", ex);
	 }  

	 }  
	 @Override 
	 public void doPost(HttpServletRequest req, HttpServletResponse resp)  

	 throws ServletException, IOException {  

		 doGet(req, resp);  

	 } 
}
