package com.tau.birthdayplus.client.widgets;

import com.google.gwt.event.shared.EventHandler;

public interface GroupEventHandler extends EventHandler
{
	void onCloseGroup(Double actualPrice,String htmlMessage);
    void onReopenGroup(String htmlMessage);
}
