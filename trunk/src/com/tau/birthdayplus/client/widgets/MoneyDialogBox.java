package com.tau.birthdayplus.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MoneyDialogBox extends DialogBox {
	//private DialogBox moneyDialogBox;
	private VerticalPanel moneyVerticalPanel;
	private Label errorMsgLabel ;
	private TextBox   enterSumTextBox;
	private HorizontalPanel moneyHorizontalPanel;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	private boolean valid;
	
	
	public MoneyDialogBox(){
	//	moneyDialogBox = new DialogBox(false,true);
	    setText("Enter a sum : ");
	
		
		moneyVerticalPanel  = new VerticalPanel();
		add(moneyVerticalPanel);
		
		errorMsgLabel = new Label();
		moneyVerticalPanel.add(errorMsgLabel);
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		
		
		
		enterSumTextBox = new TextBox();
		moneyVerticalPanel.add(enterSumTextBox);
		
		moneyHorizontalPanel = new HorizontalPanel();
		moneyVerticalPanel.add(moneyHorizontalPanel);
		moneyHorizontalPanel.setSpacing(20);
		
		okMoneyButton = new Button();
		moneyHorizontalPanel.add(okMoneyButton);
		okMoneyButton.setText("Participate");
		
		cancelMoneyButton = new Button();
		moneyHorizontalPanel.add(cancelMoneyButton);
		cancelMoneyButton.setText("Cancel");
		
	//	initWidget(moneyDialogBox);
		valid = false;
		
		wireEvents();
	}
	

	public void show(){
		valid = false;
		enterSumTextBox.setText("");
		enterSumTextBox.setEnabled(true);
		super.show();
		enterSumTextBox.setFocus(true);
	}
	
	public void showRelativeTo(Widget widget){
		enterSumTextBox.setText("");
		enterSumTextBox.setEnabled(true);
	    super.showRelativeTo(widget);
        enterSumTextBox.setFocus(true);
	}
	
	
	
	public String getInput(){
		if(valid)
		   return this.enterSumTextBox.getText();
		else
			return null;
	}
	
	private boolean checkIfValidSum(){
		enterSumTextBox.setEnabled(false);
		if(enterSumTextBox.equals("")){
			errorMsgLabel.setText("Enter the sum ");
			enterSumTextBox.setEnabled(true);
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return false;
		}
		//full , try to parse
		Integer sum = 0;
		try{
		   sum = Integer.parseInt(enterSumTextBox.getText());
		}catch(NumberFormatException ex){
			errorMsgLabel.setText("Enter valid sum ");
			enterSumTextBox.setEnabled(true);
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return false;
		}
		if (sum > 0){
			return true;
		}else{
			errorMsgLabel.setText("Enter valid sum ");
			enterSumTextBox.setEnabled(true);
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return false;
		}
	}
	

	
	private void wireEvents(){
		this.cancelMoneyButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				errorMsgLabel.setVisible(false);
				hide();
			}
		});
	
	
	
	this.okMoneyButton.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			if(checkIfValidSum()){
				valid = true;
				errorMsgLabel.setVisible(false);
				hide();
			}
				
		}
	});
	

	
	this.enterSumTextBox.addKeyUpHandler(new KeyUpHandler(){
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
				if(checkIfValidSum()){
					valid = true;
					errorMsgLabel.setVisible(false);
					hide();
				}
			}
	});
	}
	

}
