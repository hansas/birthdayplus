package com.tau.birthdayplus.logic;


import com.tau.birthdayplus.dal.BusinessObjectDAL;
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
	
	public static void createProfile(GuestData guestData) {
		Guest guest = new Guest(guestData);
		BusinessObjectDAL.createProfile(guest);
	}
	
	public static Guest loadGuest(String guestId){
		return BusinessObjectDAL.loadGuest(guestId);
	}
	
	public static void updateProfile(GuestData profile) {
		BusinessObjectDAL.updateGuest(profile);
	}
	
}
