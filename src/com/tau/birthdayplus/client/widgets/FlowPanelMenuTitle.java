package com.tau.birthdayplus.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class FlowPanelMenuTitle extends FlowPanel{
	private MenuBar menu ;
	private HorizontalPanel panel;
    private Label title;
    
    
    public FlowPanelMenuTitle(){
    	setStyleName("Panel");
    	
		menu = new MenuBar();
		add(menu);
		menu.addStyleName("buttonPanel");
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		
		panel = new HorizontalPanel();
		add(panel);
		panel.addStyleName("titlePanel");
		
	    Image bar1 = new Image(GWT.getModuleBaseURL()+"bar1.gif");
	    panel.add(bar1);
	    panel.setCellWidth(bar1, "21px");
	    
	    
		title = new Label("");
		panel.add(title);
		title.addStyleName("Label");
		
		Image bar3 = new Image(GWT.getModuleBaseURL()+"bar3.gif");
        panel.add(bar3);
        panel.setCellWidth(bar3, "21px");
        
        title.setText(" ");
    	
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
