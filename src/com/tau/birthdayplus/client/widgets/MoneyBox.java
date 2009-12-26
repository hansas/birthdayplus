package com.tau.birthdayplus.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MoneyBox extends Composite {
	private DialogBox moneyDialogBox;
	private VerticalPanel moneyVerticalPanel;
	private Label errorMsgLabel ;
	private TextBox   enterSumTextBox;
	private HorizontalPanel moneyHorizontalPanel;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	
	
	public MoneyBox(){
		moneyDialogBox = new DialogBox(false,true);
		moneyDialogBox.setText("Enter a sum : ");
	
		
		moneyVerticalPanel  = new VerticalPanel();
		moneyDialogBox.add(moneyVerticalPanel);
		
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
		
		initWidget(moneyDialogBox);
		
		wireEvents();
	}
	
	public String prompt(){
		moneyDialogBox.center();
		moneyDialogBox.show();
		enterSumTextBox.setFocus(true);
		return null;
		
	}
	public void show(){
		this.moneyDialogBox.show();
		enterSumTextBox.setFocus(true);
	}
	
	public void showRelativeTo(Widget widget){
	    moneyDialogBox.showRelativeTo(widget);
        enterSumTextBox.setFocus(true);
	}
	
	public void enableInput(){
		enterSumTextBox.setEnabled(true);
	}
	
	public void disableInput(){
		enterSumTextBox.setEnabled(false);
	}
	
	public String getInput(){
		return this.enterSumTextBox.getText();
	}
	
	public boolean checkIfValidSum(){
		if(enterSumTextBox.equals("")){
			errorMsgLabel.setText("Enter the sum ");
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
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return false;
		}
		if (sum > 0){
			errorMsgLabel.setVisible(false);
			moneyDialogBox.hide();
			enterSumTextBox.setText("");
			return true;
		}else{
			errorMsgLabel.setText("Enter valid sum ");
    		enterSumTextBox.setFocus(true);
    		errorMsgLabel.setVisible(true);
    		return false;
		}
	}
	
	public Button getOkButton(){
		return this.okMoneyButton;
	}
	
	private void wireEvents(){
		this.cancelMoneyButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				errorMsgLabel.setVisible(false);
				moneyDialogBox.hide();
				enterSumTextBox.setText("");
			}
		});
	}

	

}
