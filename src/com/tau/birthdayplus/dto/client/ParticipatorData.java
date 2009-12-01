package com.tau.birthdayplus.dto.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class ParticipatorData implements IsSerializable{

	   String userId;
	   private String userFirstName;
	   private String userLastName;
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
	        	
	   public String getUserId() {
		   return userId;
	   }

	   public void setMoney(Integer money) {
		    this.money = money;
    	}

	   public Integer getMoney() {
		    return money;
    	}

		public String getUserFirstName() {
			return userFirstName;
		}

		public String getUserLastName() {
			return userLastName;
		}

}
