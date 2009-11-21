package com.example.friends.client;



import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.user.client.Window;







/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@ModulePrefs(title = "openSocial", author = "yournamehere", author_email = "yournamehere@gmail.com")

public class Friends extends Gadget<UserPreferences> implements OpenSocial{
	
	/*
	 * user for this gadget
	 */
     private Person user=new Person();
     /*
      * user friends
      */
     private List<Person> userFriends=new ArrayList<Person>();

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.gadgets.client.Gadget#init(com.google.gwt.gadgets.client.UserPreferences)
	 * start point in gadget, on load do it
	 */
	protected void init(UserPreferences preferences) {
		
		getSocialInfo();
		
		
		
		
	}
	
	/*
	 * return social info about the user and his friends
	 */
	private  native void getSocialInfo()/*-{
		  //global variable that points to this,important in javascript
		  that=this;
		  var req = $wnd.opensocial.newDataRequest();
		  req.add(req.newFetchPersonRequest('VIEWER'), 'viewer'); 
		  var viewerFriends = $wnd.opensocial.newIdSpec({"userId" : "VIEWER", "groupId" : "FRIENDS"});
		  var opt_params = {};
		  opt_params[$wnd.opensocial.DataRequest.PeopleRequestFields.MAX]=100;
		  req.add(req.newFetchPeopleRequest(viewerFriends, opt_params), 'viewerFriends');
		
		  req.send(this.@com.example.friends.client.Friends::onLoadInfo(Lcom/google/gwt/core/client/JavaScriptObject;));
		
	}-*/;
	
	


	/*
	 * when the open social information is ready call this function
	 */
	private  native void onLoadInfo(JavaScriptObject data)/*-{
		var user = data.get('viewer');
	
		//check permissions
		if(!user.hadError()){
			var viewer = data.get('viewer').getData();
			var viewerFriends = data.get('viewerFriends').getData();
			
			//fill the data about the user
			that.@com.example.friends.client.Friends::user.@com.example.friends.client.Person::set(Ljava/lang/String;Ljava/lang/String;)(viewer.getId(),viewer.getDisplayName());
            //fill the data about his friends
			viewerFriends.each(function(person){
	        	if(person.getId()){
	        		var temp=@com.example.friends.client.Person::new(Ljava/lang/String;Ljava/lang/String;)(person.getId(),person.getDisplayName());
	        		that.@com.example.friends.client.Friends::userFriends.@java.util.ArrayList::add(Ljava/lang/Object;)(temp);
	        	}
	        });
	        //print the data on the screen
        	that.@com.example.friends.client.Friends::print()();
		}else{
			if (user.getErrorCode() == 'forbidden' ||user.getErrorCode() == '403') {
                  // user needs to approve social data access
                  $wnd.document.write("Please adjust the gadgets settings to use social features");
            }else{
                  $wnd.document.write("An unexpected error occurred.");
			}			
		}
	}-*/;
	


  

	private  void print(){
		Window.alert(user.toString());
		for (int i=0;i<userFriends.size();i++){
			Window.alert("your friend id is : " +userFriends.get(i).getId()+ " and his name : "+userFriends.get(i).getName());
		}
		
	}

	public void initializeFeature(OpenSocialFeature feature) {
		// TODO Auto-generated method stub
		
	}
}
