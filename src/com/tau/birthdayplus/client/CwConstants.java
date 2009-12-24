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
    
    @DefaultStringValue("inactiveRow")
    String cwInactiveRowStyle();
    
    @DefaultStringArrayValue({"Anniversary","Baby Shower","Bachelor Party","Bachelorette Party","","Birthday","Engagement","Graduation","Party","Wedding"})
    String[] cwSuggestBoxEvents();
    
    @DefaultStringArrayValue({"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"})
    String[] cwListBoxMonths();
    
    @DefaultStringArrayValue({"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"})
    String[] cwListBoxDays();
    
  //  @DefaultStringValue("cw-TextArea")
  //  String cwTextAreaStyle();
    
    
  //  @DefaultStringValue("cw-PopupPanel")
  //  String cwPopupPanelStyle();
    
    
    @DefaultStringValue("cw-LoadingPopupPanel")
    String cwLoadingPopupPanelStyle();
    
    @DefaultStringArrayValue({"5","4","3","2","1"})
    String[] cwListBoxCategories();

}
