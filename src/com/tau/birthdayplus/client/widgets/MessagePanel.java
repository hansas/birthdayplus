package com.tau.birthdayplus.client.widgets;




import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class MessagePanel extends PopupPanel{
	
	private VerticalPanel messagePanel;
    private Label message ;
    private Anchor closeAnchor;
	
	
	
	public MessagePanel(){
		super(false,true);
		this.setAnimationEnabled(true);
		this.setStyleName("alertBox");
		
		messagePanel = new VerticalPanel();
		this.add(messagePanel);
		int width = Window.getClientWidth()-50;
		messagePanel.setSize(width+"px", "70px");
		messagePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		message = new Label();
		messagePanel.add(message);
	
		message.setStyleName("alertBoxMessage");
		
		
		closeAnchor = new Anchor("close");
		messagePanel.add(closeAnchor);
		
		
		closeAnchor.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				MessagePanel.this.hide();
				
			}
		});
		
		
		
		
		
		
	}
	

	public void showMessage(String text){
		message.setText(text);
		
		if(this.isShowing())
			return;
		else{
			this.center();
			this.show();
		}
	}
}
	
	


