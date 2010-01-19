package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface Icons extends ImageBundle{ 
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/pencil_16.png") 
	  public AbstractImagePrototype updateIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/trash_16.png") 
      public AbstractImagePrototype deleteIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/delete_16.png") 
      public AbstractImagePrototype cancelIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/group_16.png") 
      public AbstractImagePrototype groupIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/present_16.png") 
      public AbstractImagePrototype presentIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/reload16.png") 
      public AbstractImagePrototype reloadIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/chat-icon.png") 
      public AbstractImagePrototype chatIcon();
	  
	  @ImageBundle.Resource("com/tau/birthdayplus/public/icons/alarm-icon.png") 
      public AbstractImagePrototype alarmIcon();
	  
	  


	  
      

}
