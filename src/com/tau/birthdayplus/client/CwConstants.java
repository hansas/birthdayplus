package com.tau.birthdayplus.client;

import com.google.gwt.i18n.client.Constants;

public interface CwConstants extends Constants {
//	@DefaultStringValue("cw-Tab")
//	String cwTabStyle();
	
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
    
   // @DefaultStringValue("cw-DialogBox")
   // String cwDialogBoxStyle();
    
  //  @DefaultStringValue("cw-TextBox")
  //  String cwTextBoxStyle();
    
  //  @DefaultStringValue("cw-Button")
  //  String cwButtonStyle();
    
  //  @DefaultStringValue("cw-ListBox")
   // String cwListBoxStyle();
    
  //  @DefaultStringValue("cw-DateBox")
  //  String cwDateBoxStyle();
    
   // @DefaultStringValue("cw-VerticalPanel")
  //  String cwVerticalPanelStyle();
    
 //   @DefaultStringValue("cw-CheckBox")
  //  String cwCheckBoxStyle();
    
    @DefaultStringValue("cw-TableWithHeader - active row")
    String cwActiveRowStyle();
    
    @DefaultStringValue("cw-TableWishHeader - inactive row ")
    String cwInactiveRowStyle();
    
    @DefaultStringArrayValue({"Birthday","Party"})
    String[] cwSuggestBoxEvents();
    
  //  @DefaultStringValue("cw-TextArea")
  //  String cwTextAreaStyle();
    
    
  //  @DefaultStringValue("cw-PopupPanel")
  //  String cwPopupPanelStyle();
    
    
    @DefaultStringValue("cw-LoadingPopupPanel")
    String cwLoadingPopupPanelStyle();
    
    @DefaultStringArrayValue({"5","4","3","2","1"})
    String[] cwListBoxCategories();

}
