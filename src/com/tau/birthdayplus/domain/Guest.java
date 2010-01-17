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
import javax.jdo.listener.StoreCallback;
//import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tau.birthdayplus.dto.client.GuestData;
 

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Guest  implements StoreCallback {
		@PrimaryKey  
		@Persistent
		private Key idKey;
		@Persistent
		private String id;
		@Persistent
		private String googleId;
		@Persistent
		private String firstName;
		@Persistent
		private String lastName;
		@Persistent
		private Date birthday;
		@Persistent
		private String email;
		@Persistent
		private String uppercaseEmail;
	
	    @Persistent (defaultFetchGroup="true")
		List<Event> events;
	    @Persistent (defaultFetchGroup="true")
		List<WishlistItem> wishlistItems;
		
		public Guest(String id, String firstName, String lastName, Date birthday ,String email,String googleId) {
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setBirthday(birthday);
			this.id = id;
			this.idKey = KeyFactory.createKey(Guest.class.getSimpleName(), id);
			this.events = new ArrayList<Event>();
			this.wishlistItems = new ArrayList<WishlistItem>();
			//this.iBuyItems = new ArrayList<Key>();
			this.email = email;
			this.googleId = googleId;
		}
		
		public Guest(GuestData guestData){
			this(guestData.getId(),guestData.getFirstName(),guestData.getLastName(),guestData.getBirthday(),guestData.getEmail(),null);
		}
		
		public void copyFromGuestData(GuestData guestData){
			this.setBirthday(guestData.getBirthday());
			this.setFirstName(guestData.getFirstName());
			this.setLastName(guestData.getLastName());
			this.setEmail(guestData.getEmail());
		}
		
		public String getId(){
			return this.id;
		}
		public Key getIdKey(){
			return this.idKey;
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
		
		public List<WishlistItem> getWishlistItems(){
			return this.wishlistItems;
		}
		
//		public List<Key> getIBuyItems(){
//			return this.iBuyItems;
//		}
//		public void addIBuyItem(WishlistItem item){
//			if (this.iBuyItems == null){
//				this.iBuyItems = new ArrayList<Key>();
//			}
//			this.iBuyItems.add(item.getKey());
//		}
//		
//		public void removeIBuyItem(WishlistItem item){
//			if ((iBuyItems!=null) && (iBuyItems.contains(item.getKey()))){
//				iBuyItems.remove(item.getKey());
//			}
//		}   
		
		public void setEmail(String email){
			this.email = email;
		}
		
		public String getEmail(){
			return this.email;
		}
		
		public String getGoogleId(){
			return this.googleId;
		}
		
		public void setGoogleId(String googleId){
			this.googleId= googleId;
		}

		public void jdoPreStore() {
			if (email != null) {
			      setUppercaseEmail(email.toUpperCase());
			    } else {
			    setUppercaseEmail(null);
			  }
		}

		public void setUppercaseEmail(String uppercaseEmail) {
			this.uppercaseEmail = uppercaseEmail;
		}

		public String getUppercaseEmail() {
			return uppercaseEmail;
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


