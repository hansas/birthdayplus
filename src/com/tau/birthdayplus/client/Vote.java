package com.tau.birthdayplus.client;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Vote{
	@PrimaryKey
	private Key key;
	@Persistent
	private Integer rate;
	
	public Vote(Proposal p, Integer rate){
		this.key = p.getKey();
		this.setRate(rate);
	}

//	public void setKey(Key key) {
//		this.key = key;
//	}

	public Key getKey() {
		return key;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getRate() {
		return rate;
	}
}
