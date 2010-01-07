package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

public class MessageLabel extends Label{
	private int delay;
	private Timer timer;
	boolean setTimer;
	
	public MessageLabel(int delay,boolean setTimer){
		this.addStyleName("messageLabel");
		this.delay = delay;
		this.setTimer = setTimer;
		timer = new Timer(){
			public void run() {
				MessageLabel.this.cleanText();
			}
			
		};
		if(setTimer)
		   this.setVisible(false);
		
	}
	
	public void setText(String text){
		super.setText(text);
		timer.schedule(delay);
		this.setVisible(true);
	}
	
	private void cleanText(){
		super.setText("");
		if(setTimer)
		   this.setVisible(false);
	}

}
