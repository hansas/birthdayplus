package com.tau.birthdayplus.domain;

import java.util.List;

//import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Participator{
	   @PrimaryKey
	   @Persistent
	   String userId;
	   @Persistent
	   Integer money;

	   public Participator(String id, Integer money){
		   this.userId = id;
		   this.setMoney(money);
	   }
	   /*
	   public Participator(User googleAccount, Integer money) {
		   this(googleAccount.getUserId(), money);
	   }
	   */

	   public Participator(Guest guest, Integer money) {
		   this(guest.getId(), money);
	   }
	   

	/*void setUserId(String userId) {
		this.userId = userId;
	}*/

	String getUserId() {
		return userId;
	}

	void setMoney(Integer money) {
		this.money = money;
	}

	Integer getMoney() {
		return money;
	}
}
