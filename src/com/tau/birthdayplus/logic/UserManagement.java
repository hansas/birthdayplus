package com.tau.birthdayplus.logic;


import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.cache.Cache;

import org.mortbay.log.Log;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.tau.birthdayplus.cache.Caching;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.GuestData;

public class UserManagement {
	
	private static final Logger log = Logger.getLogger(UserManagement.class.getName());
	
	public static GuestData GuestToGuestData(Guest guest){
		return new GuestData(guest.getId(), guest.getFirstName(), guest.getLastName(), guest.getBirthday(),guest.getEmail());
	}
	
	 public static GuestData loadGuestData(String guestId) throws UserNotFoundException, UserException{
         Guest guest = BusinessObjectDAL.loadGuest(guestId);
         GuestData guestData = GuestToGuestData(guest);
         return guestData;
 }
	 
	 public static GuestData loadGuestDataCached (String guestId) throws UserNotFoundException, UserException{
		 Cache guestCache = Caching.getGuestCache();
		 String guestKey = Caching.generateGuestIDKey(guestId);
		 GuestData guest = (GuestData)guestCache.get(guestKey); 
		 if (guest == null){
			guest = loadGuestData(guestId);
			guestCache.put(guestKey, guest);
		 }
		 return guest;
	 }

	 @SuppressWarnings("deprecation")
	 public static void createProfile(Guest guest) throws UserException {
         int guestDom = guest.getBirthday().getDate();
         int guestMonth = guest.getBirthday().getMonth()+1;
         Calendar cal = Calendar.getInstance();
         Date birthday = new Date(cal.get(Calendar.YEAR)-1900, guestMonth-1, guestDom);
         int currentDom = cal.get(Calendar.DAY_OF_MONTH);
         int currentMonth = cal.get(Calendar.MONTH) + 1;
         if ((currentMonth > guestMonth)
                         || ((currentMonth == guestMonth) && (currentDom > guestDom))) {
                 birthday.setYear(cal.get(Calendar.YEAR) + 1-1900);
         }
         Event e = new Event("Birthday", guest.getId(), birthday, true, false);
         
         BusinessObjectDAL.createProfile(guest,e);
 }

	
	public static Guest loadGuest(String guestId) throws UserNotFoundException, UserException{
		return BusinessObjectDAL.loadGuest(guestId);
	}
	
	public static void updateProfile(GuestData profile) throws UserNotFoundException, UserException {
		BusinessObjectDAL.updateGuest(profile);
		Cache guestCache = Caching.getGuestCache();
		String guestKey = Caching.generateGuestIDKey(profile.getId());
		guestCache.put(guestKey, profile);
	}
	
}
