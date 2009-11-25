package com.tau.birthdayplus.client.widgets;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.tau.birthdayplus.client.CwConstants;
import com.tau.birthdayplus.dto.client.EventData;

public class EventTab extends Composite {
	public VerticalPanel vPanel = new VerticalPanel();;
	private CwConstants constants;
	final public FlexTable flexTable = new FlexTable();;
	final public Button btnAddEvent; 
	
	public EventTab(List<EventData> listEvents){
	    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
	    flexTable.addStyleName("cw-FlexTable");
	    // Add some text
	    cellFormatter.setHorizontalAlignment(0, 1,
	    		HasHorizontalAlignment.ALIGN_LEFT);
	    cellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
	    
	    addTitle(flexTable);
	    for (EventData event : listEvents)
	    	addRow(flexTable, event);
	    
	    btnAddEvent = new Button("Add Event");
	    btnAddEvent.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//dialogBox.center();
				//dialogBox.show();
			}
		});
	    vPanel.add(flexTable);
	    vPanel.add(btnAddEvent);
	    initWidget(vPanel);
	  }

	  /**
	   * Add a row to the flex table.
	   */
	  private void addRow(FlexTable flexTable, EventData event) {
	    int numRows = flexTable.getRowCount();
	    flexTable.setWidget(numRows, 0, new Label(event.getEventName()));
	    flexTable.setWidget(numRows, 1, new Label(event.getEventDate().toString()));
	    flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
	  }
	  private void addTitle(FlexTable flexTable) {
		    int numRows = flexTable.getRowCount();
		    
		    flexTable.setWidget(numRows, 0, new Label("Event"));
		    flexTable.setWidget(numRows, 1, new Label("Due"));
		    flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
		  }
	  /**
	   * Remove a row from the flex table.
	   */
	  private void removeRow(FlexTable flexTable) {
	    int numRows = flexTable.getRowCount();
	    if (numRows > 1) {
	      flexTable.removeRow(numRows - 1);
	      flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows - 1);
	    }
	  }

}
