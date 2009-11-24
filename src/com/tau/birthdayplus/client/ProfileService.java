package com.tau.birthdayplus.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.Guest;

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
	void createProfile(Guest guset);
	/*
	 * update profile
	 */
    void updateProfile(Guest profile);
    /*
     * 
     */
    Guest getProfile();
}
