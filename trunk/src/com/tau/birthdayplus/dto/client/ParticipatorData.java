package com.tau.birthdayplus.dto.client;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ParticipatorData implements IsSerializable{
	   @PrimaryKey
	   @Persistent
	   String userId;
	   @Persistent
	   private
	   String userFirstName;
	   @Persistent
	   private
	   String userLastName;
	   @Persistent
	   Integer money;
	   
	   /*
	    * empty constructor for isSerializable
	    */
       protected ParticipatorData(){
    	   
       }
	   public ParticipatorData(String userId,String userFirstName,String userLastName,Integer money){
		   this.userId = userId;
		   this.userFirstName = userFirstName;
		   this.userLastName = userLastName;
		   this.setMoney(money);
	   }
	        	
	   String getUserId() {
		   return userId;
	   }

	   void setMoney(Integer money) {
		    this.money = money;
    	}

	   Integer getMoney() {
		    return money;
    	}

		String getUserFirstName() {
			return userFirstName;
		}

		String getUserLastName() {
			return userLastName;
		}

}
