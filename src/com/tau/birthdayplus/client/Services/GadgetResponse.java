/**
 * 
 */
package com.tau.birthdayplus.client.Services;

import com.google.gwt.core.client.JavaScriptObject;

public class GadgetResponse  extends JavaScriptObject {
	protected GadgetResponse() {
	}
	
	public final native String getText() /*-{ return this.text; }-*/;
	public final native String[] getErrors()  /*-{ return this.errors;  }-*/;
	public final native String getData() /*-{ return this.data; }-*/;
}