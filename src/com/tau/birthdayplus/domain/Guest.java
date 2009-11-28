package com.tau.birthdayplus.domain;

//import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.api.users.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
//import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.dto.client.GuestData;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Guest   {
		@PrimaryKey
		@Persistent
		private Key idKey;
		@Persistent
		private String id;
		@Persistent
		private String firstName;
		@Persistent
		private String lastName;
		@Persistent
		private Date birthday;
	
	    @Persistent (defaultFetchGroup="true") 
		List<Event> events;
	    @Persistent (defaultFetchGroup="true")
		List<WishlistItem> wishlistItems;

		
		public Guest(String googleId, String firstName, String lastName, Date birthday) {
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setBirthday(birthday);
			this.id = googleId;
			this.idKey = KeyFactory.createKey(Guest.class.getSimpleName(), googleId);
			this.events = new ArrayList<Event>();
			this.wishlistItems = new ArrayList<WishlistItem>();
		}
		
		public Guest(GuestData guestData){
			this(guestData.getId(),guestData.getFirstName(),guestData.getLastName(),guestData.getBirthday());
		}
		
		public String getId(){
			return this.id;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public Date getBirthday() {
			return birthday;
		}

		public List<Event> getEvents(){
			return events;
		}
		public void addEvent(Event e){
			if (this.events == null){
				this.events = new ArrayList<Event>();
			}
			this.events.add(e);
		}
		
		public void removeEvent(Event e){
			if ((events!=null) && (events.contains(e))){
				events.remove(e);
			}
		}
		
		public void addWishlistItem(WishlistItem item){
			if (this.wishlistItems == null){
				this.wishlistItems = new ArrayList<WishlistItem>();
			}
			this.wishlistItems.add(item);
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


