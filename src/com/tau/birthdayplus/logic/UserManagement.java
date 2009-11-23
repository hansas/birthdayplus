package com.tau.birthdayplus.logic;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.domain.Guest;

public class UserManagement {
	public static Guest loadGuest(String guestId){
		return BusinessObjectDAL.loadGuest(guestId);
	}
	
	public static Guest loadMyGuest(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null){
			return null;
		}
		return BusinessObjectDAL.loadGuest(user.getUserId());
	}
}
