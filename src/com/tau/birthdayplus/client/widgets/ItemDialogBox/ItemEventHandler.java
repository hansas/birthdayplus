package com.tau.birthdayplus.client.widgets.ItemDialogBox;

import com.google.gwt.event.shared.EventHandler;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public interface ItemEventHandler extends EventHandler{
	void onCreateItem(WishlistItemData item);
	void onUpdateItem(WishlistItemData item);
	
}
