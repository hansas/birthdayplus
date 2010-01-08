package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class MessagePanel extends PopupPanel{
	Label message ;
	private int delay;
	private Timer timer;
	boolean setTimer;
	
	public MessagePanel(int delay,boolean setTimer){
		super(false,false);
		this.setStyleName("none");
		this.setAnimationEnabled(true);
		
		message = new Label();
		this.add(message);
		message.addStyleName("messageLabel");
		
		this.delay = delay;
		this.setTimer = setTimer;
		timer = new Timer(){
			public void run() {
				MessagePanel.this.cleanText();
			}
			
		};
		message.setText("this is a popup, it won't be here everytime");
	//	int height=Window.getClientHeight();
		this.setPopupPosition(0, 400);
		this.show();
		
	}
	
	public void setText(String text){
		message.setText(text);
		timer.schedule(delay);
		this.show();
	}
	
	private void cleanText(){
		message.setText("");
		if(setTimer)
		   this.hide();
	}

}
