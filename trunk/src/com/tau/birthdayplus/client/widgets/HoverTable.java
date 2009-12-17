package com.tau.birthdayplus.client.widgets;


import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;



public class HoverTable extends FlexTable {

    /**
     * Constructor
     * 
     */
    public HoverTable() {
        super();

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
            int row = DOM.getChildIndex(body, tr);
            // Fire the event
            DOM.setStyleAttribute(tr, "backgroundColor", "#AFEEEE");
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


}
