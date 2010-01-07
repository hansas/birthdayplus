package com.tau.birthdayplus.addItem;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tau.birthdayplus.dto.client.WishlistItemData;

@RemoteServiceRelativePath("addItemSite")
public interface AddItemService extends RemoteService{
	void createWishlistItemSite(WishlistItemData item)throws AddItemException;

} 
