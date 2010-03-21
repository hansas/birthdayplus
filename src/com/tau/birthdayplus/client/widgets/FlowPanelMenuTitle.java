package com.tau.birthdayplus.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class FlowPanelMenuTitle extends FlowPanel{
	private MenuBar menu ;
//	private FlexTable panel;
    private Label title;
    
    
    public FlowPanelMenuTitle(){
    	setStyleName("Panel");
    	
		menu = new MenuBar();
		add(menu);
		menu.addStyleName("buttonPanel");
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		
		Grid panel = new Grid(1,3);
		add(panel);
		panel.addStyleName("titlePanel");
		
	    Image bar1 = new Image(GWT.getModuleBaseURL()+"bar1.gif");
	    panel.setWidget(0, 0, bar1);
	    panel.getCellFormatter().setWidth(0, 0,"21px");
	    bar1.addStyleName("leftBar");
	    
	    
		title = new Label("");
		panel.setWidget(0, 1,title);
		panel.getCellFormatter().setHeight(0, 1, "16px");
		
		panel.getCellFormatter().addStyleName(0, 1,"Label");
		
		Image bar3 = new Image(GWT.getModuleBaseURL()+"bar3.gif");
        panel.setWidget(0, 2,bar3);
        panel.getCellFormatter().setWidth(0, 2,"21px");
        bar3.addStyleName("rightbar");
        
       
    	
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
