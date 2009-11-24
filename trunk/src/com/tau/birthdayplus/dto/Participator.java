package com.tau.birthdayplus.dto;

import com.google.gwt.user.client.rpc.IsSerializable;



public class Participator implements IsSerializable{
	   String userId;
	   
	   Integer money;
	   
	   /*
	    * empty constructor for isSerializable
	    */
       private Participator(){
    	   
       }
	   public Participator(String userId, Integer money){
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
