package com.tau.birthdayplus.domain;

import java.util.List;

//import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.ParticipatorData;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Participator extends ParticipatorData implements IsSerializable{
	   
	   private Participator(){ }
	   

	   public Participator(String userId, Integer money) {
		   super(userId, money);
	   }
	   
	   public Participator(Guest guest, Integer money) {
		   this(guest.getId(), money);
	   }
	   

	/*void setUserId(String userId) {
		this.userId = userId;
	}*/

}