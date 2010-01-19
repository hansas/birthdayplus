package com.tau.birthdayplus.client.widgets.EventDialogBox;

import java.util.Date;

import com.google.gwt.event.shared.EventHandler;

public interface GadgetEventHandler extends EventHandler {
	void onSaveGadgetEvent(String eventName,Date eventDate,boolean recurrence);

}
