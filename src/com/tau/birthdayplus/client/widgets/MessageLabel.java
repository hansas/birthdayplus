package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

public class MessageLabel extends Label{
	int delay;
	Timer timer;
	
	public MessageLabel(int delay){
		this.addStyleName("messageLabel");
		this.delay = delay;
		timer = new Timer(){
			public void run() {
				MessageLabel.this.cleanText();
			}
			
		};
		this.setVisible(false);
		
	}
	
	public void setText(String text){
		super.setText(text);
		timer.schedule(delay);
		this.setVisible(true);
	}
	
	private void cleanText(){
		super.setText("");
		this.setVisible(false);
	}

}
