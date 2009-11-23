package com.tau.birthdayplus.client;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)public class Guest {
		@PrimaryKey
		@Persistent
		private String id;
		@Persistent
		private User googleAccount;
		@Persistent
		private String firstName;
		@Persistent
		private String lastName;
		@Persistent
		private Date birthday;
		@Persistent
		List<Guest> friends;
		@Persistent
		List<Event> events;
		@Persistent
		List<WishlistItem> wishlistItems;
		
		public Guest(User googleAccount, String firstName, String lastName, Date birthday) {
			this.setGoogleAccount(googleAccount);
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setBirthday(birthday);
			this.id = googleAccount.getUserId();
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
		
		static Guest loadGuest(User googleAccount){
			Key k = KeyFactory.createKey(Guest.class.getSimpleName(), googleAccount.getUserId());
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Guest g = pm.getObjectById(Guest.class, k);
			return g;
		}
		
		public void saveGuest(Guest g){
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try {
	        	pm.makePersistent(g);
	        } finally {
	        	pm.close();
	        }
		}

		public void setGoogleAccount(User googleAccount) {
			this.googleAccount = googleAccount;
		}

		public User getGoogleAccount() {
			return googleAccount;
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

		/*public void setId(String id) {
			this.id = id;
		}*/

		public String getId() {
			return id;
		}
}


