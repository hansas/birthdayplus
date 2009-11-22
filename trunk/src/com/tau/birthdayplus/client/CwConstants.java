package com.tau.birthdayplus.client;

import com.google.gwt.i18n.client.Constants;

public interface CwConstants extends Constants {
	@DefaultStringValue("cwDisclosurePanelDescription")
	String cwDisclosurePanelDescription();
	
	@DefaultStringValue("cwDisclosurePanelFormAdvancedCriteria")
    String cwDisclosurePanelFormAdvancedCriteria();
	
	@DefaultStringValue("cwDisclosurePanelFormDescription")
    String cwDisclosurePanelFormDescription();
    
    @DefaultStringValue("cwDisclosurePanelFormGender")
    String cwDisclosurePanelFormGender();
    
    @DefaultStringArrayValue({"male", "female"})
    String[] cwDisclosurePanelFormGenderOptions();
    
    @DefaultStringValue("cwDisclosurePanelFormLocation")
    String cwDisclosurePanelFormLocation();
    
    @DefaultStringValue("cwDisclosurePanelFormName")
    String cwDisclosurePanelFormName();
    
    @DefaultStringValue("cwDisclosurePanelFormTitle")
    String cwDisclosurePanelFormTitle();
    
    @DefaultStringValue("cwDisclosurePanelName")
    String cwDisclosurePanelName();

}
