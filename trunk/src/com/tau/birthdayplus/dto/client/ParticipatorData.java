package com.tau.birthdayplus.dto.client;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;



public class ParticipatorData implements IsSerializable{
	   @PrimaryKey
	   @Persistent
	   String userId;
	   @Persistent
	   Integer money;
	   
	   /*
	    * empty constructor for isSerializable
	    */
       protected ParticipatorData(){
    	   
       }
	   public ParticipatorData(String userId, Integer money){
		   this.userId = userId;
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

}
