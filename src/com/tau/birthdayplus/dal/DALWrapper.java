package com.tau.birthdayplus.dal;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.tau.birthdayplus.Email.ParticipatorEmail;
import com.tau.birthdayplus.Email.SendEmail;
import com.tau.birthdayplus.client.Services.UserException;
import com.tau.birthdayplus.client.Services.UserNotFoundException;
import com.tau.birthdayplus.dal.BusinessObjectDAL.GroupStatus;
import com.tau.birthdayplus.domain.Event;
import com.tau.birthdayplus.domain.Guest;
import com.tau.birthdayplus.domain.Participator;
import com.tau.birthdayplus.domain.WishlistItem;
import com.tau.birthdayplus.dto.client.EventData;
import com.tau.birthdayplus.dto.client.WishlistItemData;
import com.tau.birthdayplus.dto.client.WishlistItemNewData;
import com.tau.birthdayplus.dto.client.WishlistItemPolaniData;

public class DALWrapper {
	
	private PersistenceManager pm;
	
	public DALWrapper(){
		pm = PMF.get().getPersistenceManager();
	}

	public void close(){
		pm.close();
		pm=null;
	}
	
	public List<Guest> getGuestsById(ArrayList<String> UserIdList) throws UserException{
		return BusinessObjectDAL.getGuestsById(UserIdList, this.pm);
	}
	
	public Guest getGuestByKey(Key parentKey){
		return pm.getObjectById(Guest.class, parentKey);
	}
	
	public Participator getParticipatorByKey(Key pKey){
		return pm.getObjectById(Participator.class, pKey);
	}
	
	public WishlistItem getWishlistItem(String itemId) throws UserException{
		return BusinessObjectDAL.loadWishlistItem(itemId, pm);
	}
	
	public List<WishlistItem> getWishlist(String userId) throws UserNotFoundException, UserException{
		return BusinessObjectDAL.getWishlist(userId, this.pm);
	}
	
	public WishlistItem deleteWishlistItem(WishlistItemData itemD) throws UserException{
		return BusinessObjectDAL.deleteWishlistItem(itemD, pm);
	}
	
	public void makePersistantItem(WishlistItem item){
		pm.makePersistent(item);
	}
	
	public List<WishlistItem> getWishlistForEvent(String userId,String eventId) throws UserNotFoundException{
		return BusinessObjectDAL.getWishlistForEvent(userId,eventId,this.pm);
	}
	
	public Guest getGuestById(String userId) throws UserNotFoundException{
		return BusinessObjectDAL.loadGuest(userId, pm);
	}
	
	public Guest loadGuestByGmail(String gmail) throws UserException{
		return BusinessObjectDAL.loadGuestByGmail(gmail, pm);
	}
	
	public Guest loadGuestByGoogleId(String googleId){
		return BusinessObjectDAL.loadGuestByGoogleId(googleId, pm);
	}
	
	public Event getEventByKey(Key eventKey){
		return pm.getObjectById(Event.class, eventKey);
	}
	
	public void newCreateEvent(EventData eventD,Guest user) throws UserException{
		BusinessObjectDAL.newCreateEvent(eventD, user, pm);
	}
	
	public void newCreateWishlistItem(WishlistItemData itemData,Guest user) throws UserException{
		BusinessObjectDAL.newCreateWishlistItem(itemData, user, pm);
	}
	
	public List<WishlistItem> getWishlistItemById(List<Key> keys) throws UserException{
		return  BusinessObjectDAL.getWishlistItemById(keys, pm);
	}
	
	public List<WishlistItem> getBookedWishlistItems2(String userId) throws UserNotFoundException, UserException{
		return BusinessObjectDAL.getBookedWishlistItems2(userId, pm);
	}
	
	public ArrayList<WishlistItemPolaniData> getLastItemsForUser(String myUserId,
			String anotherUserId) throws UserNotFoundException {
		return BusinessObjectDAL.getLastItemsForUser(myUserId, anotherUserId, pm);
	}
	
	public void bookItemForGroup(String itemId, String userId) throws UserNotFoundException, UserException{
		BusinessObjectDAL.bookItemForGroup(itemId, userId, pm);
	}
	
	public void cancelBookItemForGroup(String itemId, String userId) throws UserNotFoundException, UserException{
		BusinessObjectDAL.cancelBookItemForGroup(itemId, userId);
	}
	
//	public void sendEmailCloseGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE,Double actualPrice) throws Exception{
//		BusinessObjectDAL.sendEmailCloseGroup(itemId, userId, message, participatorsE,actualPrice, pm);
//	}
	
	public void sendEmailToGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE,Double actualPrice,GroupStatus status,SendEmail.CancelFor cancelFor,Event e) throws Exception{
		BusinessObjectDAL.sendEmailToGroup(itemId, userId, message, participatorsE, pm, actualPrice, status,cancelFor,e);
	}
	
	public void deleteParticipator(Participator p){
		pm.deletePersistent(p);
	}
	
	public void cronDeleteEventAndUpdateRecurrent(ArrayList<WishlistItem> itemParticipatorDelete,Event e) throws UserException{
		BusinessObjectDAL.cronDeleteEventAndUpdateRecurrent(itemParticipatorDelete,e,pm);
	}
	
//	public void sendEmailOpenGroup(String itemId, String userId,String message,ArrayList<ParticipatorEmail> participatorsE) throws Exception{
//		BusinessObjectDAL.sendEmailOpenGroup(itemId, userId, message, participatorsE, pm);
//	}
	
	
}
