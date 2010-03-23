package com.tau.birthdayplus.cache;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.cache.Cache;
//import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
import com.tau.birthdayplus.client.Services.UserException;

public class Caching {
	private static final Logger log = Logger.getLogger(Caching.class.getName());
	
	private static Cache guestCache;
	
	public static Cache getGuestCache() throws UserException{
		if (guestCache == null){
		    try {
		    	CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
		        Map props = new HashMap();
		        props.put(GCacheFactory.EXPIRATION_DELTA, 7200);
		        guestCache = cacheFactory.createCache(props);
		    }
		    catch (Exception ex){
		    	log.log(Level.SEVERE, "the log from getGuestCache", ex);
				throw new UserException("can't get your profile, try again later");
		    }
		}
		return guestCache;
	}
	
	public static String generateGuestIDKey(String guestId){
		return "GuestId="+guestId;
	}
	
private static Cache wishlistForEventCache;
	
	public static Cache getWishlistForEventCache() throws UserException{
		if (wishlistForEventCache == null){
		    try {
		    	CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
		        Map props = new HashMap();
		        props.put(GCacheFactory.EXPIRATION_DELTA, 60);
		        wishlistForEventCache = cacheFactory.createCache(props);
		    }
		    catch (Exception ex){
		    	log.log(Level.SEVERE, "the log from getWishlistForEventCache", ex);
				throw new UserException("can't get the wishlist , please try again later");
		    }
		}
		return wishlistForEventCache;
	}
	
	public static String generateWishlistForEventKey(Key eventKey){
		return "WishlistForEventCache="+KeyFactory.keyToString(eventKey);
	}
	
	public static String generateWishlistForEventId(String eventId){
		return "WishlistForEventCache="+eventId;
	}
	
	private static Cache bookedWishlistItemCache;
	
	public static Cache getBookedWishlistItemsCache() throws UserException{
		if (bookedWishlistItemCache == null){
		    try {
		    	CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
		        Map props = new HashMap();
		        props.put(GCacheFactory.EXPIRATION_DELTA, 60);
		        bookedWishlistItemCache = cacheFactory.createCache(props);
		    }
		    catch (Exception ex){
		    	log.log(Level.SEVERE, "the log from getBookedWishlistItemsCache", ex);
				throw new UserException("can't get the wishlist, try again later");
		    }
		}
		return bookedWishlistItemCache;
	}
	
	public static String generateBookedWishlistItemsId(String userId){
		return "BookedWishlistItemsCache="+userId;
	}
}
