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
    
    @DefaultStringValue("Name")
    String cwDisclosurePanelFormName();
    
    @DefaultStringValue("cwDisclosurePanelFormTitle")
    String cwDisclosurePanelFormTitle();
    
    @DefaultStringValue("OK")
    String cwDialogBoxOK();
    
    @DefaultStringValue("Cancel")
    String cwDialogBoxCancel();
    
    @DefaultStringValue("Event")
    String cwEventLabel();
    
    @DefaultStringValue("Due")
    String cwEventDue();
    
    @DefaultStringValue("Add Event")
    String cwAddEvent();
    
    @DefaultStringValue("cw-TableWithHeader")
    String cwTableStyle();
    
    @DefaultStringArrayValue({"5","4","3","2","1"})
    String[] cwListBoxCategories();

}
