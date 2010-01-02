package com.tau.birthdayplus.addItem;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.WishlistItemData;

public interface AddItemServiceAsync {

	void createWishlistItemSite(WishlistItemData item, AsyncCallback<Void> callback);

}
