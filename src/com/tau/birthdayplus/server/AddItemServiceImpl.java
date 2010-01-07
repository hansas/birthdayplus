package com.tau.birthdayplus.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tau.birthdayplus.addItem.AddItemException;
import com.tau.birthdayplus.addItem.AddItemService;


import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.logic.WishlistManagement;


public class AddItemServiceImpl extends RemoteServiceServlet implements
AddItemService{
	private static final Logger log = Logger.getLogger(AddItemServiceImpl.class.getName());


	public void createWishlistItemSite(WishlistItemData item) throws AddItemException {

		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null)
			try {
				WishlistManagement.createWishlistItem(item, user.getUserId());
			} catch (UserNotFoundException e) {
				throw new AddItemException("Can't get your profile");
			}catch(Exception e){
				throw new AddItemException("Can't add this item");
			}
		else{
        	log.info("user not found");
        	throw new AddItemException("Can't get your profile");
		}
        	
        	
 
 

}
}
