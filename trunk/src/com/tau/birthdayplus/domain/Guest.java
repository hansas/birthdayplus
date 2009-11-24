package com.tau.birthdayplus.domain;

//import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.api.users.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.tau.birthdayplus.dto.client.GuestData;


@PersistenceCapable(identityType = IdentityType.APPLICATION)public class Guest
extends GuestData implements IsSerializable {

//		@Persistent
//		List<Guest> friends;
		@Persistent
		List<Event> events;
		@Persistent
		List<WishlistItem> wishlistItems;
		
		/*
	     * constructor for isSerializable
	     * A user-defined class that serializable  must have a default (zero argument) constructor 
	     * (with any access modifier) or no constructor at all.
	     */
		private Guest(){}
		
		public Guest(String googleId, String firstName, String lastName, Date birthday) {
			super(googleId,firstName,lastName,birthday);
			this.events = new ArrayList<Event>();
		}
		
		public void addEvent(Event e){
			events.add(e);
		}
		
		public void removeEvent(Event e){
			events.remove(e);
		}
		
		public void addWishlistItem(WishlistItem item){
			wishlistItems.add(item);
		}
		
		public void removeWishlistItem(WishlistItem item){
			wishlistItems.remove(item);
		}
		
		/*
		static Guest loadGuest(User googleAccount){
			Key k = KeyFactory.createKey(Guest.class.getSimpleName(), googleAccount.getUserId());
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Guest g = pm.getObjectById(Guest.class, k);
			return g;
		}
		*/
		/*
		public void saveGuest(Guest g){
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try {
	        	pm.makePersistent(g);
	        } finally {
	        	pm.close();
	        }
		}

*/

}


