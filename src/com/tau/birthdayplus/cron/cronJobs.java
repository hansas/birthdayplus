package com.tau.birthdayplus.cron;

import java.io.IOException;  
import java.util.logging.Level;
import java.util.logging.Logger;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  

import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.logic.EventManagement;



 @SuppressWarnings("serial")  
public class cronJobs extends HttpServlet {  

 private static final Logger _logger = Logger.getLogger(cronJobs.class.getName());  

 public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
  try {  
    EventManagement.cronDeleteEventAndUpdateRecurrent();
    _logger.info("Cron Job has been executed");
 }  

  catch (Exception ex) {  
	  _logger.severe("Cron Job failed: " + ex.getMessage());
	  _logger.log(Level.SEVERE, "the log from cron job ", ex);
 }  

 }  
 @Override 
 public void doPost(HttpServletRequest req, HttpServletResponse resp)  

 throws ServletException, IOException {  

 doGet(req, resp);  

 }  
} 
