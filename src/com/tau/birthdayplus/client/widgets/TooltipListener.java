package com.tau.birthdayplus.client.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class TooltipListener extends MouseListenerAdapter{
	private static final String DEFAULT_TOOLTIP_STYLE = "TooltipPopup";
	  private static final int DEFAULT_OFFSET_X = 10;
	  private static final int DEFAULT_OFFSET_Y = 35;

	  private static class Tooltip extends PopupPanel {
	    private int delay;
	    private boolean setDelay;

	    public Tooltip(Widget sender, int offsetX, int offsetY, 
	        final String text, final int delay, final String styleName,final boolean setDelay) {
	      super(true);

	      this.delay = delay;
	      this.setDelay = setDelay;

	      HTML contents = new HTML(text);
	      add(contents);
	    
	      setStyleName(styleName);
	    }

	    public void show() {
	      super.show();
	      

	      Timer t = new Timer() {

	        public void run() {
	          Tooltip.this.hide();
	       }

	      };
	      if(setDelay)
	         t.schedule(delay);
	    }
	  }

	  private Tooltip tooltip;
	  private String text;
	  private String styleName;
	  private boolean setDelay;
	  private int delay;
	  private int offsetX = DEFAULT_OFFSET_X;
	  private int offsetY = DEFAULT_OFFSET_Y;

	  public TooltipListener(String text, int delay,boolean setDelay) {
	    this(text, delay, DEFAULT_TOOLTIP_STYLE,setDelay);
	  }

	  public TooltipListener(String text, int delay, String styleName,boolean setDelay) {
	    this.text = text;
	    this.delay = delay;
	    this.styleName = styleName;
	    this.setDelay = setDelay;
	  }

	  public void onMouseEnter(Widget sender) {
	    if (tooltip != null) {
	      tooltip.hide();
	    }
	    tooltip = new Tooltip(sender, offsetX, offsetY, text, delay, styleName,setDelay);
	//    tooltip.show();
	    tooltip.showRelativeTo(sender);
	  }

	  public void onMouseLeave(Widget sender) {
	    if (tooltip != null) {
	      tooltip.hide();
	    }
	  }

	  public String getStyleName() {
	    return styleName;
	  }

	  public void setStyleName(String styleName) {
	    this.styleName = styleName;
	  }

	  public int getOffsetX() {
	    return offsetX;
	  }

	  public void setOffsetX(int offsetX) {
	    this.offsetX = offsetX;
	  }

	  public int getOffsetY() {
	    return offsetY;
	  }

	  public void setOffsetY(int offsetY) {
	    this.offsetY = offsetY;
	  }

}
