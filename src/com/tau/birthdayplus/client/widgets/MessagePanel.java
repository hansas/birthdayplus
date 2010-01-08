package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class MessagePanel extends PopupPanel{
	private Label message ;
	private int delay;
	private Timer timer;
	
	
	public MessagePanel(int delay){
		super(false,false);
		this.setStyleName("none");
		this.setAnimationEnabled(true);
		
		message = new Label();
		this.add(message);
		message.addStyleName("messageLabel");
		
		int width = Window.getClientWidth()-10;
		message.setWidth(width+"px");
		
		this.delay = delay;
		
		
		timer = new Timer(){
			public void run() {
				MessagePanel.this.cleanText();
			}
			
		};
		
		this.setPopupPosition(0, 390);
		
		
	}
	
	public void setText(String text){
		message.setText(text);
		timer.schedule(delay);
		this.show();
	}
	
	private void cleanText(){
		message.setText("");
		this.hide();
	}

}
