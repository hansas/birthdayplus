package com.tau.birthdayplus.client.widgets.ItemDialogBox;

import com.google.gwt.event.shared.EventHandler;

public interface ItemEventHandler extends EventHandler{
	void onSaveItem(String itemName,Boolean priority,String link,Double price,String thumbnail);
	
}
