package com.tau.birthdayplus.dto.client;

import com.google.gwt.user.client.rpc.IsSerializable;



public class ParticipatorData implements IsSerializable{
	   String userId;
	   
	   Integer money;
	   
	   /*
	    * empty constructor for isSerializable
	    */
       private ParticipatorData(){
    	   
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
