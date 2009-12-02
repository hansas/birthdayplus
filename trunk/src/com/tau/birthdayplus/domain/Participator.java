package com.tau.birthdayplus.domain;

//import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Participator
{
	   @PrimaryKey
	   @Persistent
	   String userId;
//	   @Persistent
//	   private
//	   String userFirstName;
//	   @Persistent
//	   private
//	   String userLastName;
	   @Persistent
	   Integer money; 
	  
	   

	   public Participator(String userId,Integer money) {
		   this.userId = userId;
//		   this.userFirstName = userFirstName;
//		   this.userLastName = userLastName;
		   this.money =  money;
	   }
	   
	   public Participator(Guest guest, Integer money) {
		   this(guest.getId(),money);
	   }
	   
	   public String getUserId() {
		   return userId;
	   }

	   public void setMoney(Integer money) {
		    this.money = money;
    	}

	   public Integer getMoney() {
		    return money;
    	}

//		String getUserFirstName() {
//			return userFirstName;
//		}
//
//		String getUserLastName() {
//			return userLastName;
//		}

}
