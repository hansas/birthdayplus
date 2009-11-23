package com.tau.birthdayplus.client;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Proposal{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String userId;
	@Persistent
	private String name;
	@Persistent
	private String link;
	@Persistent
	private Integer price;
	
	public Proposal(String userId, String name, String link, Integer price){
		this.setUserId(userId);
		this.setName(name);
		this.setLink(link);
		this.setPrice(price);
	}
	
	public Proposal(User googleAccount, String name, String link,Integer price) {
		   this(googleAccount.getUserId(),name,link,price);
	}
	
	public Proposal(Guest g, String name, String link,Integer price) {
		   this(g.getId(),name,link,price);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

//	public void setKey(Key key) {
//		this.key = key;
//	}

	public Key getKey() {
		return key;
	}
	   
}
