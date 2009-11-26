package com.tau.birthdayplus.domain;

import java.util.List;

//import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Participator extends ParticipatorData
{
	   
	  
	   

	   public Participator(String userId,String userFirstName,String userLastName,Integer money) {
		   super(userId,userFirstName,userLastName,money);
	   }
	   
	   public Participator(Guest guest, Integer money) {
		   this(guest.getId(),guest.getFirstName(),guest.getLastName(),money);
	   }
	   

	/*void setUserId(String userId) {
		this.userId = userId;
	}*/

}
