package com.tau.birthdayplus.client.widgets;


import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;



public class HoverTable extends Grid {

    /**
     * Constructor
     * 
     */
    public HoverTable(int row,int col) {
        super(row,col);
        sinkEvents(Event.ONMOUSEOVER);
        sinkEvents(Event.ONMOUSEOUT);
    }
    
 



    public void onBrowserEvent(Event event) {
        switch (DOM.eventGetType(event)) {
        case Event.ONMOUSEOVER: {
            // Find out which cell was entered.
            Element td = getEventTargetCell(event);
            if (td == null)
                return;
            Element tr = DOM.getParent(td);
            if (tr == null)
                return;
            Element body = getBodyElement();
           
            // Fire the event
            DOM.setStyleAttribute(tr, "backgroundColor", "#D0E4F6");
            DOM.setStyleAttribute(tr, "cursor", "default");
            break;
        }
        case Event.ONMOUSEOUT: {
            // Find out which cell was exited.
            Element td = getEventTargetCell(event);
            if (td == null)
                return;
            Element tr = DOM.getParent(td);
            if (tr == null)
                return;
            Element body = getBodyElement();
            int row = DOM.getChildIndex(body, tr);
            // Fire the event.
            DOM.setStyleAttribute(tr, "backgroundColor","#ffffff");
            break;
        }
        }
        super.onBrowserEvent(event);
    }
    
    
    public void clear(boolean clearInnerHTML) {
        for (int row = 0; row < getRowCount(); ++row) {
          for (int col = 0; col < getCellCount(row); ++col) {
            cleanCell(row, col, clearInnerHTML);
          }
        }
      }
    
    private Element cleanCell(int row, int column, boolean clearInnerHTML) {
        // Clear whatever is in the cell.
        Element td = getRawElement(row, column);
        internalClearCell(td, clearInnerHTML);
        return td;
      }
    
    private Element getRawElement(int row, int column) {
        return getCellElement(getBodyElement(), row, column);
      }
    
    private native Element getCellElement(Element table, int row, int col) /*-{
    return table.rows[row].cells[col];
  }-*/;

    }



    
   



