package com.tau.birthdayplus.client.widgets.WelcomePanel;

import com.google.gwt.event.shared.EventHandler;
import com.tau.birthdayplus.client.CountryPreferences.Country;

public interface SignInEventHandler extends EventHandler
 {
   void onSignIn(int day, int month,int year,Country country);
}
