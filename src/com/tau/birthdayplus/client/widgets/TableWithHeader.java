package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class TableWithHeader  extends FlexTable{
	private Element head;  
	 private Element headerTr;  
	  
	 public TableWithHeader() {  
	  super();  
	  head = DOM.createTHead();  
	  headerTr = DOM.createTR();  
	  DOM.insertChild(this.getElement(), head, 0);  
	  DOM.insertChild(head, headerTr, 0);  
	  Element tBody = getBodyElement();  
	  DOM.setElementAttribute(tBody, "style", "overflow: auto; text-align: center;");  
	  DOM.setElementAttribute(head, "style", "background-color: white; text-align: center;height:14px; font-size:13px;color:black;font-family:Arial;font-weight:bold;");
	  
	 }  
	   
	 public void setHeight(String height) {  
	  DOM.setElementAttribute(getBodyElement(), "height", height);  
	 }  
	   
	 public void setHeader(int column,String text){  
	  prepareHeader(column);  
	     if (text != null) {  
	        DOM.setInnerText(DOM.getChild(headerTr, column), text);  
	  }  
	 }  
	   
	 private void prepareHeader(int column) {  
	  if (column < 0) {  
	        throw new IndexOutOfBoundsException(  
	             "Cannot create a column with a negative index: " + column);  
	   }  
	   int cellCount = DOM.getChildCount(headerTr);  
	      int required = column + 1 - cellCount;  
	      if (required > 0) {  
	        addCells(head, 0, required);  
	      }  
	 }  
	   
	   
	 public void setHeaderWidget(int column, Widget widget) {  
	    prepareHeader(column);  
	    if (widget != null) {  
	       widget.removeFromParent();  
	       // Physical attach.  
	       DOM.appendChild(DOM.getChild(headerTr, column), widget.getElement());  
	   
	       adopt(widget);  
	     }  
	   }  
	   
	 private native void addCells(Element table, int row, int num)/*-{ 
	      var rowElem = table.rows[row]; 
	      for(var i = 0; i < num; i++){ 
	        var cell = $doc.createElement("td"); 
	        rowElem.appendChild(cell);   
	      } 
	   }-*/;  	

}
