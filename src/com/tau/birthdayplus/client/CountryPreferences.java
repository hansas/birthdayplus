package com.tau.birthdayplus.client;

import com.google.gwt.gadgets.client.EnumPreference;

import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.EnumPreference.EnumDisplayValue;
import com.google.gwt.gadgets.client.UserPreferences.PreferenceAttributes.Options;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;



public interface CountryPreferences extends UserPreferences{
	 public static enum Country {
		   
		   @EnumDisplayValue("Argentina")
		   Argentina ("$","Argentina"),
		   @EnumDisplayValue("Australia")
		   Australia("$","Australia"),
		   @EnumDisplayValue("Austria")
		   Austria("\u20AC","Austria"),
		   @EnumDisplayValue("Belgium")
		   Belgium("\u20AC","Belgium"),
		   @EnumDisplayValue("Brazil")
		   Brazil("R$","Brazil"),
		   @EnumDisplayValue("Bulgaria")
		   Bulgaria("\u043b\u0432","Bulgaria"),
		   @EnumDisplayValue("Canada")
		   Canada("$","Canada"),
		   @EnumDisplayValue("China")
		   China("\u5143","China"),
		   @EnumDisplayValue("Colombia")
		   Colombia("\u0024","Colombia"),
		   @EnumDisplayValue("Croatia")
		   Croatia("\u006b\u006E","Croatia"),
		   @EnumDisplayValue("Cyprus")
		   Cyprus("\u00A3","Cyprus"),
		   @EnumDisplayValue("Czech Republic")
		   CzechRepublic("\u004b\u0010d","Czech Republic"),
		   @EnumDisplayValue("Denmark")
		   Denmark("\u006b\u0072","Denmark"),
		   @EnumDisplayValue("Dominican Republic")
		   DominicanRepublic("\u0052\u0044\u0024","Dominican Republic"),
		   @EnumDisplayValue("Egypt")
		   Egypt("\u00A3","Egypt"),
		   @EnumDisplayValue("Estonia")
		   Estonia("\u006b\u0072","Estonia"),
		   @EnumDisplayValue("France")
		   France("\u20A3","France"),
		   @EnumDisplayValue("Germany")
		   Germany("\u20AC","Germany"),
		   @EnumDisplayValue("Greece")
		   Greece("\u20AF","Greece"),
		   @EnumDisplayValue("Hong Kong")
		   HongKong("\u0048\u004b\u0024","Hong Kong"),
		   @EnumDisplayValue("Hungary")
		   Hungary("\u0046\u0074","Hungary"),
		   @EnumDisplayValue("Iceland")
		   Iceland("\u006b\u0072","Iceland"),
		   @EnumDisplayValue("India")
		   India("\u20a8","India"),
		   @EnumDisplayValue("Ireland")
		   Ireland("\u20AC","Ireland"),
		   @EnumDisplayValue("Israel")
		   Israel("\u20AA","Israel"),
		   @EnumDisplayValue("Italy")
		   Italy("\u20A4","Italy"),
		   @EnumDisplayValue("Japan")
		   Japan("\u00A5","Japan"),
		   @EnumDisplayValue("Kazakhstan")
		   Kazakhstan("\u043b\u0432","Kazakhstan"),
		   @EnumDisplayValue("Latvia")
		   Latvia("\u004c\u0073","Latvia"),
		   @EnumDisplayValue("Malta")
		   Malta("\u20A4","Malta"),
		   @EnumDisplayValue("Mexico")
		   Mexico("$","Mexico"),
		   @EnumDisplayValue("Netherlands")
		   Netherlands("\u0192","Netherlands"),
		   @EnumDisplayValue("New Zealand")
		   NewZealand("$","New Zealand"),
		   @EnumDisplayValue("Norway")
		   Norway("\u006b\u0072","Norway"),
		   @EnumDisplayValue("Poland")
		   Poland("\u007a\u0142","Poland"),
		   @EnumDisplayValue("Russian Federation")
		   RussianFederation("\u0440\u0443\u0431","Russian Federation"),
		   @EnumDisplayValue("Spain")
		   Spain("\u20A7","Spain"),
		   @EnumDisplayValue("Thailand")
		   Thailand("\u0E3F","Thailand"),
		   @EnumDisplayValue("Turkey")
		   Turkey("\u20A4","Turkey"),
		   @EnumDisplayValue("Ukraine")
		   Ukraine("\u20b4","Ukraine"),
		   @EnumDisplayValue("United Kingdom")
		   UnitedKingdom("\u00a3","United Kingdom"),
		   @EnumDisplayValue("United States")
		   UnitedStates("$","United States"),
		   @EnumDisplayValue("Uzbekistan")
		   Uzbekistan("\u043b\u0432","Uzbekistan");
		 
		 
		   private String currency;
		   private String name;
		   
		   private Country(String currency,String name){
			   this.currency = currency;
			   this.name = name;
		   }
		   
		   public String getCurrency(){
			   return this.currency;
		   }
		   
		   public String getName(){
			   return this.name;
		   }
		   
		   
		   public static Country getByIndex(int index){
			   if(index < 0 || index > Country.values().length -1)
				   return null;
			   return Country.values()[index];
		   }
		   
		 
		 
		 }
	
	 @PreferenceAttributes(display_name = "Country",		  
		      default_value = "Israel", 
		      options = Options.REQUIRED)
		    
		      EnumPreference<Country> getCountry();
	 
	
		  

}
