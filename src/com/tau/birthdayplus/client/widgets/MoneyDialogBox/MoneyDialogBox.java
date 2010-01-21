package com.tau.birthdayplus.client.widgets.MoneyDialogBox;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class MoneyDialogBox  {
	private ArrayList<MoneyEventHandler>   handlers = new ArrayList<MoneyEventHandler>()  ;
	private DialogBox moneyDialogBox;
	private FlexTable layout;
	private HTML helpMessage;
	private Label errorMsgLabel ;
	private TextBox   enterSumTextBox;
	private Button    okMoneyButton;
	private Button    cancelMoneyButton;
	
	
	
	
	public MoneyDialogBox(String help,String currency){
	    moneyDialogBox = new DialogBox(false,true);	
	
	    VerticalPanel moneyVerticalPanel  = new VerticalPanel();
		moneyDialogBox.add(moneyVerticalPanel);
		moneyVerticalPanel.setWidth("240px");
	
		helpMessage = new HTML(help);
		moneyVerticalPanel.add(helpMessage);
		helpMessage.addStyleName("helpMessage");
		
		errorMsgLabel = new Label();
		moneyVerticalPanel.add(errorMsgLabel);
		errorMsgLabel.setStyleName("errorMessage");
		
		enterSumTextBox = new TextBox();
		enterSumTextBox.setText("0");
		enterSumTextBox.setVisibleLength(12);
		enterSumTextBox.setMaxLength(10);
	
	
		okMoneyButton = new Button("Ok");
	
		cancelMoneyButton = new Button("Cancel");
		
		layout = new FlexTable();
		
		layout.setCellSpacing(0);
		layout.setCellPadding(5);
	   
		moneyVerticalPanel.add(layout);
		
		layout.setWidget(0, 0, errorMsgLabel);
		layout.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		layout.setText(1,0, "Amount you are willing to spend in "+currency );
        layout.setWidget(1, 1, enterSumTextBox);
        layout.getCellFormatter().addStyleName(1,0, "amountLabel");
		layout.getCellFormatter().addStyleName(1,1, "amount");
		
		layout.setWidget(2, 0, okMoneyButton);
		layout.setWidget(2, 1, cancelMoneyButton);
		layout.getFlexCellFormatter().setHorizontalAlignment(2, 1,HasHorizontalAlignment.ALIGN_LEFT );
		layout.getFlexCellFormatter().setHorizontalAlignment(2, 1,HasHorizontalAlignment.ALIGN_RIGHT );
		
		wireEvents();
	}
	
	
	

	public void show(){
		moneyDialogBox.center();
	    moneyDialogBox.show();
		enterSumTextBox.setFocus(true);
		enterSumTextBox.selectAll();
	}
	
	public void show(int currentAmount){
		enterSumTextBox.setText(currentAmount+"");
		show();
	}
	
	
	
	public void setText(String title,String buttonText){
		moneyDialogBox.setText(title);
		okMoneyButton.setText(buttonText);
	}
	
	 public void addMoneyEvent(MoneyEventHandler handler){
			handlers.add(handler);
		}
		
		public void removeMoneyEvent(MoneyEventHandler handler){
			handlers.remove(handler);
		}
		
		
		
		private void onMoneySave(int amount){
			for(Iterator<MoneyEventHandler> it = handlers.iterator(); it.hasNext();)
	        {
	            MoneyEventHandler handler = it.next();
	            handler.onMoneySave(amount);
	        }
		}
		
	
	
	
	private void showErrorMessage(String message){
		errorMsgLabel.setText(message);
		errorMsgLabel.setVisible(true);
		enterSumTextBox.setEnabled(true);
		enterSumTextBox.setFocus(true);
		enterSumTextBox.selectAll();		
	}
	
	private boolean checkIfValidSum(){
		enterSumTextBox.setEnabled(false);
		if(enterSumTextBox.equals("")){
			showErrorMessage("Please enter an amount");
    		return false;
		}
		//full , try to parse
		Integer sum = 0;
		try{
		   sum = Integer.parseInt(enterSumTextBox.getText());
		}catch(NumberFormatException ex){
			showErrorMessage("Please enter a valid amount");
    		return false;
		}
		if (sum > 0){
			return true;
		}else{
			showErrorMessage("Please enter an amount bigger than 0");
    		return false;
		}
	}
	
	
	private void cleanBox(){
		errorMsgLabel.setVisible(false);
		enterSumTextBox.setText("0");
		enterSumTextBox.setEnabled(true);
	}
	

	
	private void wireEvents(){
		this.cancelMoneyButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				moneyDialogBox.hide();
				cleanBox();
			}
		});
	
	
	
	this.okMoneyButton.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			if(checkIfValidSum()){
				int amount = Integer.parseInt(enterSumTextBox.getText());
				moneyDialogBox.hide();
				onMoneySave(amount);
				cleanBox();
			}
				
		}
	});
	

	
	this.enterSumTextBox.addKeyUpHandler(new KeyUpHandler(){
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
				okMoneyButton.click();
				
			}
	});
	}
	

}
