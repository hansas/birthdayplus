package com.tau.birthdayplus.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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
	private Integer sum ;
	
	
	public MoneyDialogBox(){
	//	moneyDialogBox = new DialogBox(false,true);
	    setText("Enter the amount you're willing to spend on this present : ");
	    setWidth("300px");
	
		
		moneyVerticalPanel  = new VerticalPanel();
		add(moneyVerticalPanel);
		moneyVerticalPanel.addStyleName("moneyVerticalPanel");
		moneyVerticalPanel.setWidth("100%");
		moneyVerticalPanel.setHeight("40px");
		
		errorMsgLabel = new Label();
		moneyVerticalPanel.add(errorMsgLabel);
		errorMsgLabel.setWidth("100%");
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		
		
		
		enterSumTextBox = new TextBox();
		moneyVerticalPanel.add(enterSumTextBox);
		
		moneyHorizontalPanel = new HorizontalPanel();
		moneyVerticalPanel.add(moneyHorizontalPanel);
		moneyHorizontalPanel.setWidth("100%");
		moneyHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	//	moneyHorizontalPanel.setSpacing(20);
		
		okMoneyButton = new Button();
		moneyHorizontalPanel.add(okMoneyButton);
		moneyHorizontalPanel.setCellWidth(okMoneyButton, "50%");
		
		okMoneyButton.setText("Ok");
		okMoneyButton.setWidth("90%");
	//	okMoneyButton.setWidth("100%");
		
		cancelMoneyButton = new Button();
		moneyHorizontalPanel.add(cancelMoneyButton);
		moneyHorizontalPanel.setCellWidth(cancelMoneyButton, "50%");
		cancelMoneyButton.setText("Cancel");
		cancelMoneyButton.setWidth("90%");
		
	//	initWidget(moneyDialogBox);
	    sum = null;
		
		wireEvents();
	}
	

	public void show(){
		sum = null;
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
	
	
	
	public Integer getInput(){
		return sum;
		
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
				sum = Integer.parseInt(enterSumTextBox.getText());
				errorMsgLabel.setVisible(false);
				hide();
			}
				
		}
	});
	

	
	this.enterSumTextBox.addKeyUpHandler(new KeyUpHandler(){
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
				if(checkIfValidSum()){
					sum = Integer.parseInt(enterSumTextBox.getText());
					errorMsgLabel.setVisible(false);
					hide();
				}
			}
	});
	}
	

}
