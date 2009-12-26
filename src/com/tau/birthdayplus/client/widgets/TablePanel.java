package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.ColumnFormatter;

public class TablePanel extends Composite{
	FlowPanel mainPanel;
	private MenuBar menu ;
    private Label title;
    
    protected FlowPanel innerPanel;
	private ScrollPanel scrollPanel;
	private FlexTable   tableHeader;
	private HoverTable  table;
	
	
	public TablePanel(){
		mainPanel = new FlowPanel();
		initWidget(mainPanel);
		mainPanel.setStyleName("Panel");
	
		title = new Label();
		mainPanel.add(title);
		title.addStyleName("Label");
		
		menu = new MenuBar();
		mainPanel.add(menu);
		menu.addStyleName("buttonPanel");
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		
		tableHeader = new FlexTable();
		mainPanel.add(tableHeader);
		tableHeader.addStyleName("TableHeader");
		//eventTableHeader.setSize("100%", "25px");
		tableHeader.setCellSpacing(0);
		
		
		
		scrollPanel = new ScrollPanel();
		mainPanel.add(scrollPanel);
		scrollPanel.addStyleName("ShortScrollPanel");
//		eventScrollPanel.setSize("100%", "300px");
		
		
		table = new HoverTable();
		scrollPanel.add(table);
		table.addStyleName("Table");
	//	eventTable.setWidth("100%");
		table.setCellSpacing(0);
		
		


	}
	
	public FlowPanel getMainPanel(){
		return this.mainPanel;
	}
	
	public void add(Widget w){
		mainPanel.add(w);
	}
	
	public ColumnFormatter getHeaderFormatter() {
		return tableHeader.getColumnFormatter();
	}
	
	public void setTextHeader(int row,int col,String text){
		tableHeader.setText(row, col, text);
		
	}
	
	public FlexCellFormatter getHeaderFlexCellFormatter(){
		return tableHeader.getFlexCellFormatter();
	}
	
	public ColumnFormatter getTableColumnFormatter(){
		return this.table.getColumnFormatter();
	}
	
	public HTMLTable getTable(){
		return this.table;
	}
	
	public MenuBar getMenu(){
		return this.menu;
	}
	
	public void setTitle(String title){
		this.title.setText(title);
	}

}
