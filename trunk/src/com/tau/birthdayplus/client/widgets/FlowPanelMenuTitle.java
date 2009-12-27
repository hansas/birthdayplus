package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class FlowPanelMenuTitle extends FlowPanel{
	private MenuBar menu ;
    private Label title;
    
    
    public FlowPanelMenuTitle(){
    	setStyleName("Panel");
    	title = new Label("");
		add(title);
		title.addStyleName("Label");
		
		menu = new MenuBar();
		add(menu);
		menu.addStyleName("buttonPanel");
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
    	
    }
    
    public void setTitle(String title){
    	this.title.setText(title);
    }
    
    public MenuItem addMenuItem(MenuItem item){
    	return menu.addItem(item);
    }
    
    public MenuItem addMenuItem(String text,boolean asHTML,Command cmd){
    	return this.menu.addItem(text, asHTML, cmd);
    	
    }
    
    public MenuItem addMenuItem(String text,boolean asHTML,MenuBar popup){
    	return this.menu.addItem(text, asHTML, popup);
    	
    }
    
    public MenuItem addMenuItem(String text,Command cmd){
    	return this.menu.addItem(text, cmd);
    }
    
    public MenuItem addMenuItem(String text,MenuBar popup){
    	return this.menu.addItem(text, popup);
    }
    
    
    
    

}
