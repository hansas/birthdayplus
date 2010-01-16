package com.tau.birthdayplus.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MoneyDialogBox extends DialogBox {
	//private DialogBox moneyDialogBox;
	private VerticalPanel moneyVerticalPanel;
	private FlexTable layout;
	private HTML helpMessage;
	private Label errorMsgLabel ;
	private TextBox   enterSumTextBox;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	private Integer sum ;
	
	
	public MoneyDialogBox(String help,String currency){
		
	
		moneyVerticalPanel  = new VerticalPanel();
		add(moneyVerticalPanel);
		moneyVerticalPanel.setWidth("240px");
	
		
		helpMessage = new HTML(help);
		moneyVerticalPanel.add(helpMessage);
		helpMessage.addStyleName("helpMessage");
		
		errorMsgLabel = new Label();
		moneyVerticalPanel.add(errorMsgLabel);
	//	errorMsgLabel.setWidth("100%");
	
		errorMsgLabel.setStyleName("errorMessage");
	
		
		
		
		enterSumTextBox = new TextBox();
		enterSumTextBox.setVisibleLength(12);
		enterSumTextBox.setMaxLength(10);
	
	
		okMoneyButton = new Button("Ok");
	
		cancelMoneyButton = new Button("Cancel");
		
		layout = new FlexTable();
	
		
		layout.setCellSpacing(0);
		layout.setCellPadding(5);
	   
		moneyVerticalPanel.add(layout);
		
		layout.setWidget(0, 0, errorMsgLabel);
		layout.getFlexCellFormatter().setColSpan(0, 0, 1);
		
		layout.setText(1,0, "Amount you are willing to spend in "+currency );
        layout.setWidget(1, 1, enterSumTextBox);
        layout.getCellFormatter().addStyleName(1,0, "amountLabel");
		layout.getCellFormatter().addStyleName(1,1, "amount");
		
		layout.setWidget(2, 0, okMoneyButton);
		layout.setWidget(2, 1, cancelMoneyButton);
		layout.getFlexCellFormatter().setHorizontalAlignment(2, 1,HasHorizontalAlignment.ALIGN_LEFT );
		layout.getFlexCellFormatter().setHorizontalAlignment(2, 1,HasHorizontalAlignment.ALIGN_RIGHT );
		

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
	
	public void setText(String title,String buttonText){
		this.setText(title);
		okMoneyButton.setText(buttonText);
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
