package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class ListItem extends ComplexPanel implements HasText {
	  public ListItem(String text) {
          setElement(DOM.createElement("LI"));
          setText(text);
  }

  public void add(Widget w) {
          super.add(w, getElement());
  }

  public void insert(Widget w, int beforeIndex) {
          super.insert(w, getElement(), beforeIndex, true);
  }

  public String getText() {
          return DOM.getInnerText(getElement());
  }

  public void setText(String text) {
          DOM.setInnerText(getElement(), (text == null) ? "" : text);
  } 
  
 

}
