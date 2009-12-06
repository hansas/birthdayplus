package com.tau.birthdayplus.logic;


import java.util.Calendar;
import java.util.Date;

import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.GuestData;

public class UserManagement {
	public static GuestData GuestToGuestData(Guest guest){
		return new GuestData(guest.getId(), guest.getFirstName(), guest.getLastName(), guest.getBirthday());
	}
	
	 public static GuestData loadGuestData(String guestId){
         Guest guest = BusinessObjectDAL.loadGuest(guestId);
         GuestData guestData = GuestToGuestData(guest);
         return guestData;
 }

	 @SuppressWarnings("deprecation")
	 public static void createProfile(GuestData guestData) {
         Guest guest = new Guest(guestData);
         int guestDom = guest.getBirthday().getDate();
         int guestMonth = guest.getBirthday().getMonth();
         Calendar cal = Calendar.getInstance();
         Date birthday = new Date(cal.get(Calendar.YEAR), guestMonth, guestDom);
         int currentDom = cal.get(Calendar.DAY_OF_MONTH);
         int currentMonth = cal.get(Calendar.MONTH) + 1;
         if ((currentMonth > guestMonth)
                         || ((currentMonth == guestMonth) && (currentDom > guestDom))) {
                 birthday.setYear(cal.get(Calendar.YEAR) + 1);
         }
         Event e = new Event("Birthday", guest.getId(), birthday, true);
         BusinessObjectDAL.createProfile(guest,e);
 }

	
	public static Guest loadGuest(String guestId){
		return BusinessObjectDAL.loadGuest(guestId);
	}
	
	public static void updateProfile(GuestData profile) {
		BusinessObjectDAL.updateGuest(profile);
	}
	
}
