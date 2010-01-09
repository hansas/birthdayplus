package com.tau.birthdayplus.client.Services;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.client.GuestData;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("profile")

public interface ProfileService extends RemoteService{
	/*
	 * function that checks that client can call to the remote service
	 */
	void printHello();
	
	/*
	 * create new profile
	 */
//	void createProfile(GuestData guest);
	/*
	 * update profile
	 */
    void updateProfile(GuestData profile)throws UserNotFoundException, UserException;
    /*
     * get user's profile
     */
    GuestData getProfile(String userId) throws UserNotFoundException;
    
    
}
