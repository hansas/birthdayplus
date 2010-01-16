package com.tau.birthdayplus.client.widgets;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.tau.birthdayplus.client.widgets.RichTextToolbar.RichTextToolbar;
import com.tau.birthdayplus.dto.client.ParticipatorData;

public class EmailDialogBox extends Composite implements ClickHandler{
	private   NumberFormat shortMoneyFormat ;

	public static enum Status{
		CLOSE_GROUP("<FONT color=red >You are responsible for buying this present.</FONT><br /> Please enter the actual price for the item and a short message for the group.We will send an email to the group.<br /><FONT color=red >You can reopen this group by clicking on the reopen button in IBuy tab.</FONT >"),
		REOPEN_GROUP(" Please enter a short message for the group about reopening .We will send an email to the group.");
		
		
		private String helpMessage;
		
		private Status(String helpMessage){
			this.helpMessage = helpMessage;
		}
		
		public String getHelpMessage(){
			return this.helpMessage;
		}
	}
	
	//text area for message to group
	private  DialogBox emailPanel;
	private  RichTextArea emailTextArea;
	private  FlexTable emailGrid;
	private  TextBox actualPrice;
	private Label errorMessage;
	private Anchor sendEmail;
	private Anchor cancelEmail;
	private Status currentStatus;
	private ArrayList<ParticipatorData> participators;
	private ArrayList<GroupEventHandler>   handlers = new ArrayList<GroupEventHandler>()  ;
	
	
	public EmailDialogBox(String currency){
		emailPanel = new DialogBox(false,true); 
		
	    this.shortMoneyFormat = NumberFormat.getFormat(currency+"#,##0");
		emailTextArea = new RichTextArea();
	    emailTextArea.setSize("288px", "9em");
	    
	    RichTextToolbar emailTextToolbar = new RichTextToolbar(emailTextArea);
	    emailTextToolbar.setWidth("290px");
	    
	    sendEmail = new Anchor("send this message");
	    sendEmail.addClickHandler(this);
	    cancelEmail = new Anchor("cancel");
	    cancelEmail.addClickHandler(this);
	    
	    actualPrice = new TextBox();
	    actualPrice.setTabIndex(1);
	    emailTextArea.setTabIndex(2);
	    actualPrice.setVisibleLength(18);
	    actualPrice.setMaxLength(9);
	    
	    errorMessage = new Label();
	    
	    
	    // Add the components to a panel
	    emailGrid = new FlexTable();
	    emailPanel.add(emailGrid);
	    emailGrid.addStyleName("emailGrid");
	    emailGrid.setWidth("290px");
	    emailGrid.setCellSpacing(0);
	    emailGrid.setCellPadding(3);
	    
	    emailGrid.setHTML(0, 0, Status.CLOSE_GROUP.getHelpMessage());
	    emailGrid.getFlexCellFormatter().setColSpan(0, 0, 2);
	    emailGrid.setHTML(1, 0, "<STRONG> Actual price in "+currency+ ":</STRONG>");
	    emailGrid.setWidget(1, 1,actualPrice);
	    
	    emailGrid.setWidget(2, 0, errorMessage);
	    emailGrid.getFlexCellFormatter().setColSpan(2, 0, 2);
	    emailGrid.getRowFormatter().setVisible(2, false);

	    
	    emailGrid.setWidget(3, 0, emailTextToolbar);
	    emailGrid.getFlexCellFormatter().setColSpan(3, 0, 2);
	    
	    emailGrid.setWidget(4, 0, emailTextArea);
	    emailGrid.getFlexCellFormatter().setColSpan(4, 0, 2);
	    
	    emailGrid.setWidget(5, 0, sendEmail);
	    emailGrid.setWidget(5, 1, cancelEmail);
	    
	    errorMessage.setStyleName("errorMessage");
	    emailGrid.getRowFormatter().addStyleName(1, "emailActualPrice");
	    emailGrid.getRowFormatter().addStyleName(4, "emailTextArea");
	    emailGrid.getFlexCellFormatter().setHorizontalAlignment(5, 1,HasHorizontalAlignment.ALIGN_RIGHT );

	}
	
	public void show(Status status,ArrayList<ParticipatorData> participators){
		this.currentStatus = status;
		this.participators = participators;
		emailGrid.clearCell(0, 0);
		switch (status){
    		case CLOSE_GROUP  : emailPanel.setText("Buy a present for the group");
	                            emailGrid.setHTML(0, 0, Status.CLOSE_GROUP.getHelpMessage()); 
                        	    emailGrid.getRowFormatter().setVisible(1, true);
                        	    actualPrice.setFocus(true);
                        	    break;
                        	    
    		case REOPEN_GROUP : emailPanel.setText("Reopen this group");
		                        emailGrid.setHTML(0, 0,Status.REOPEN_GROUP.getHelpMessage());
		                        emailGrid.getRowFormatter().setVisible(1, false);
		                        emailTextArea.setFocus(true);  
		                        break;
		}
		
		emailGrid.getRowFormatter().setVisible(2, false);

		emailPanel.center();
        emailPanel.show();
	}
	
	public void addGroupEvent(GroupEventHandler handler){
		handlers.add(handler);
	}
	
	public void removeGroupEvent(GroupEventHandler handler){
		handlers.remove(handler);
	}
	
	private void onCloseGroup(Double actualPrice,String htmlMessage){
		 for(Iterator<GroupEventHandler> it = handlers.iterator(); it.hasNext();)
	        {
	            GroupEventHandler handler = it.next();
	            handler.onCloseGroup(actualPrice, htmlMessage);
	        }
	    }
	
	private void onReopenGroup(String htmlMessage){
		 for(Iterator<GroupEventHandler> it = handlers.iterator(); it.hasNext();)
	        {
	            GroupEventHandler handler = it.next();
	            handler.onReopenGroup(htmlMessage);
	        }
	    }
	
	
	private Double getTotal(){
		Double sum = 0.0;
		for(ParticipatorData user : participators){
    		sum+=user.getMoney();
		}
		return sum;
	}
	
	
	private void gui_eventSendEmailButtomClicked(){
		Double price = 0.0;
		if(emailTextArea.getText().equals("")){
			errorMessage.setText("Please, enter the message for the group");
			emailGrid.getRowFormatter().setVisible(2, true);
			return;
		}
		if(currentStatus == Status.CLOSE_GROUP){
			try{
			    price = Double.parseDouble(actualPrice.getText());
			    Double total = this.getTotal();
			    if(price > total){
			    	errorMessage.setText("Actual price greater than the group total :"+shortMoneyFormat.format(total));
					emailGrid.getRowFormatter().setVisible(2, true);
					actualPrice.setFocus(true);
					return;
			    }
			}catch(NumberFormatException e){
				errorMessage.setText("Please enter a valid price");
				emailGrid.getRowFormatter().setVisible(2, true);
				actualPrice.setFocus(true);
				return;
			}
		}
		
		if(currentStatus == Status.CLOSE_GROUP)
			onCloseGroup(price,emailTextArea.getHTML());
		else
			if(currentStatus == Status.REOPEN_GROUP)
				onReopenGroup(emailTextArea.getHTML());
				
		emailPanel.hide();
		actualPrice.setText("");
		emailTextArea.setText("");
		
	}
	
	private void gui_eventCanelEmailButtonClicked(){
		actualPrice.setText("");
		emailTextArea.setText("");
	    emailPanel.hide();
	}

	public void onClick(ClickEvent event) {
		if(event.getSource() == sendEmail){
			gui_eventSendEmailButtomClicked();
		}else
			if(event.getSource() == cancelEmail){
				gui_eventCanelEmailButtonClicked();
			}
		
	}
	
	

}
