package com.tau.birthdayplus.dto;



public class Participator {
	   String wishlistItemId;
	   
	   String userId;
	   
	   Integer money;

	   public Participator(String wishlistItemId,String userId, Integer money){
		   this.wishlistItemId=wishlistItemId;
		   this.userId = userId;
		   this.setMoney(money);
	   }
	   
       String getWishlistItemId(){
    	   return this.wishlistItemId;
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
