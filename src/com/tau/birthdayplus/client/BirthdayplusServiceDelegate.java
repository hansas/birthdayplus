package com.tau.birthdayplus.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.GuestData;

public class BirthdayplusServiceDelegate {
	// Create a remote service proxy to talk to the server-side Event service.
	private final EventServiceAsync eventService = GWT.create(EventService.class); 
	// Create a remote service proxy to talk to the server-side Profile service.
	private final ProfileServiceAsync profileService = GWT.create(ProfileService.class); 
	// Create a remote service proxy to talk to the server-side Profile service.
	private final WishlistServiceAsync wishlistService = GWT.create(WishlistService.class); 
	//gui
	BirthdayplusGUI gui;
	
	
	
	///////////////////////////////////////Events//////////////////////////////////////////////// 
	    
	 
	    void listEvents(final ArrayList<String> uIdlist) {
	    	eventService.getEvents(uIdlist, new AsyncCallback<ArrayList<EventData>>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_eventListContactsFailed(caught);
	    		}
	    		public void onSuccess(ArrayList<EventData> result){
	    			gui.service_eventListRetrievedFromService(result);
	    		}
	    	}//end of inner class
	    	);//end of method call
	    	}
	       
	    
	    
	    void createEvent(final EventData event){
	    	eventService.createEvent(event, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_eventCreateEventFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			gui.service_eventCreateEventSuccessful();
	    		}
	    	}//end of inner class
	    );//end of method call
	    }
	    
	    void updateEvent(final String uId , final EventData event){
	    	eventService.updateEvent(uId, event, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_eventUpdateEventFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			gui.service_eventUpdateEventSuccessful();
	    		}
	    	}//end of inner class
	    	);//end of method call
	    }

	   void deleteEvent(final String uId, final EventData event){
		   eventService.deleteEvent(uId, event, new AsyncCallback<Void>(){
			   public void onFailure(Throwable caught){
				   gui.service_eventDeleteEventFailed(caught);
			   }
			   
			   public void onSuccess(Void result){
				   gui.service_eventDeleteEventSuccessful();
			   }
		   }//end of inner class
		   );//end of method call
	   }        
	    
////////////////////////////////////////EndEvents////////////////////////////////////

	   
	   
///////////////////////////////////////Profile//////////////////////////////////////////////// 
	       
	    
	    
	    void createProfile(final GuestData guest){
	    	profileService.createProfile(guest, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_createProfileFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			gui.service_createProfileSuccessful();
	    		}
	    	}//end of inner class
	    );//end of method call
	    }
	    
	    void updateProfile(final GuestData guest){
	    	profileService.updateProfile(guest, new AsyncCallback<Void>(){
	    		public void onFailure(Throwable caught){
	    			gui.service_updateProfileFailed(caught);
	    		}
	    		
	    		public void onSuccess(Void result){
	    			gui.service_updateProfileSuccessful();
	    		}
	    	}//end of inner class
	    	);//end of method call
	    }

////////////////////////////////////////EndProfile////////////////////////////////////
	   

}
