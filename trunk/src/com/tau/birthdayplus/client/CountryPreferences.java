package com.tau.birthdayplus.client;

import com.google.gwt.gadgets.client.EnumPreference;

import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.EnumPreference.EnumDisplayValue;
import com.google.gwt.gadgets.client.UserPreferences.PreferenceAttributes.Options;



public interface CountryPreferences extends UserPreferences{
	 public static enum Country {
		   @EnumDisplayValue("Israel")
		   ISRAEL('\u20AA'),
		   @EnumDisplayValue("USA")
		   USA('$');
		   
		   private char currency;
		   
		   private Country(char currency){
			   this.currency = currency;
		   }
		   
		   public char getCurrency(){
			   return this.currency;
		   }
		   
		 
		 
		 }
	
	 @PreferenceAttributes(display_name = "Country",		  
		      default_value = "ISRAEL", 
		      options = Options.REQUIRED)
		    
		      EnumPreference<Country> getCountry();
	 
	
		  

}
