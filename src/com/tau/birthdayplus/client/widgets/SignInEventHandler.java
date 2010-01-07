package com.tau.birthdayplus.client.widgets;

import com.google.gwt.event.shared.EventHandler;

public interface SignInEventHandler extends EventHandler
 {
   void onSignIn(int day, int month,int year);
}
