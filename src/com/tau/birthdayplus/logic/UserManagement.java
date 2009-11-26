package com.tau.birthdayplus.logic;


import com.tau.birthdayplus.dal.BusinessObjectDAL;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.dto.client.GuestData;

public class UserManagement {
	
	public static GuestData loadGuestData(String guestId){
		return BusinessObjectDAL.loadGuestData(guestId);
	}
	
	public static void createProfile(GuestData guestData) {
		Guest guest = new Guest(guestData);
		BusinessObjectDAL.createProfile(guest,guestData);
	}
	
}
