package com.tau.birthdayplus.client.widgets.WelcomePanel;

import java.util.ArrayList;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.client.CountryPreferences.Country;


public class WelcomePanel extends Composite implements ClickHandler, ChangeHandler{
	
	private static CwConstants constants = GWT.create(CwConstants.class);
	private ArrayList<SignInEventHandler>   handlers = new ArrayList<SignInEventHandler>()  ;
	private ListBox dayListBox;
	private ListBox monthListBox;
	private  TextBox yearTextBox ;
	private  Label errorMessage;
	private ListBox countryListBox;

	
	
	public WelcomePanel(String firstName){	
    //	DecoratorPanel	 decPanel = new DecoratorPanel();
     //   initWidget(decPanel);
     //   decPanel.setSize("100%","95%" );
        
       
        VerticalPanel	 panel = new VerticalPanel();
        initWidget(panel);
      //  panel.setSize("100%", "95%");
       // decPanel.setWidget(panel);
    	panel.setSpacing(20);
    	panel.addStyleName("welcomePanel");
	
    	Image logo = new Image( GWT.getModuleBaseURL() + "logoBlue.gif");
    	panel.add(logo);
    	panel.setCellHorizontalAlignment(logo, HasHorizontalAlignment.ALIGN_CENTER);
	
    	Label title1 = new Label(firstName+", welcome to Birthdayplus!");
    	panel.add(title1);
    	panel.setCellHorizontalAlignment(title1, HasHorizontalAlignment.ALIGN_CENTER);

    	Label title2 = new Label("We just need you to confirm a few things before you start using Birthdayplus.");
    	panel.add(title2);
    	title2.addStyleName("welcomePanelPart");

    	Label questionLabel= new Label("Sorry if we're being rude, but when were you born?");
    	panel.add(questionLabel);
    	questionLabel.addStyleName("wellcomePanelPart");
	
    	HorizontalPanel datePanel = new HorizontalPanel();
    	panel.add(datePanel);
    	datePanel.addStyleName("welcomePanelPart");
    	panel.setCellHorizontalAlignment(datePanel, HasHorizontalAlignment.ALIGN_CENTER);

	
    	monthListBox= new ListBox(false);
    	datePanel.add(monthListBox);
    	String[] listTypes = constants.cwListBoxMonths();
        for (int i = 0; i < listTypes.length; i++) {
            monthListBox.addItem(listTypes[i]);
        }
        
        monthListBox.addChangeHandler(this);
        
    	dayListBox = new ListBox(false);
    	datePanel.add(dayListBox);
    	String[] listDayTypes = constants.cwListBoxDays();
        for (int i = 0; i < listDayTypes.length; i++) {
            dayListBox.addItem(listDayTypes[i]);
         }
    	
	
        
    
        yearTextBox = new TextBox();
        datePanel.add(yearTextBox);
        yearTextBox.setWidth("4em");
        yearTextBox.setMaxLength(4);
    
        Label text = new Label("(your birth year, such as  1985)");
        panel.add(text);
	    panel.setCellHorizontalAlignment(text, HasHorizontalAlignment.ALIGN_CENTER);
	    

    	errorMessage= new Label();
        panel.add(errorMessage);
        errorMessage.setVisible(false);
        errorMessage.addStyleName("errorMessage");
        
        HorizontalPanel countryPanel = new HorizontalPanel();
        panel.add(countryPanel);
        countryPanel.addStyleName("welcomePanelPart");
        countryPanel.setSpacing(10);
        
        Label countryLabel = new Label("country:");
        countryPanel.add(countryLabel);
        
        countryListBox = new ListBox(false);
        countryPanel.add(countryListBox); 
        Country[] countries = Country.values();
        int index =0;
        for(int i = 0;i< countries.length; i++){
        	countryListBox.addItem(countries[i].getName());
        	if(countries[i] == Country.Israel)
        		index =i;
        }
        countryListBox.setSelectedIndex(index);
       
        Anchor submit = new Anchor("all done, create my account!");
        panel.add(submit);
    
	    panel.setCellHorizontalAlignment(submit, HasHorizontalAlignment.ALIGN_CENTER);

        submit.addClickHandler(this);
    
}
	
	
	public void addSignInEvent(SignInEventHandler handler){
		handlers.add(handler);
	}
	
	public void removeSignInEvent(SignInEventHandler handler){
		handlers.remove(handler);
	}
	
	private void onSignIn(int day,int month,int year,Country country){
		 for(Iterator<SignInEventHandler> it = handlers.iterator(); it.hasNext();)
	        {
	            SignInEventHandler handler = it.next();
	            handler.onSignIn(day, month, year,country);
	        }
	    }
	
	public void onClick(ClickEvent event) {
		if(yearTextBox.getText().equals("")){
			errorMessage.setText(" please enter your birth year (you want people to give you birthday presents,right?)");
			errorMessage.setVisible(true);
		}else{	
			boolean valid = true;
			int day = dayListBox.getSelectedIndex()+1;
			int month = monthListBox.getSelectedIndex()+1;
			int year = 0;
		    Country country = Country.getByIndex(countryListBox.getSelectedIndex());
			try{
			   year = Integer.parseInt(yearTextBox.getText());
			}catch( NumberFormatException ex){
				errorMessage.setText("please enter valid year");
				valid= false;
			}
			
			if(valid){
				onSignIn(day,month,year,country);
			}
		}
	}

    private void fillDays(int numDaysInMonth){
    	dayListBox.clear();
        String[] listDayTypes = constants.cwListBoxDays();
        for (int i = 0; i < numDaysInMonth; i++) {
            dayListBox.addItem(listDayTypes[i]);
         }
    }
	public void onChange(ChangeEvent event) {
		if(event.getSource() != monthListBox)
			return;
		switch(monthListBox.getSelectedIndex()){
		case (0) : 
		case (2) :
		case (4) :
		case (6) :
		case (7) :
		case (9) :
		case (11): fillDays(31);
		           break;
		case (1) : fillDays(29);
		           break;
		default  : fillDays (30);
		           break;
		}
		
		
	}

		
	}



