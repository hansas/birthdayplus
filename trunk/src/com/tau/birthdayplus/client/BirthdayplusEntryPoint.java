package com.tau.birthdayplus.client;

import com.google.gwt.core.client.EntryPoint;

public class BirthdayplusEntryPoint implements EntryPoint {
private BirthdayplusGUI gui;
private BirthdayplusServiceDelegate delegate;

/**
 * This is the entry point method.
 */
public void onModuleLoad() {
    
    gui = new BirthdayplusGUI();
    delegate = new BirthdayplusServiceDelegate();
    gui.BplusService = delegate;
    delegate.gui = gui;
   // gui.init();
   // delegate.listContacts();
    wireGUIEvents();
}
private void wireGUIEvents() {
    

}



}
