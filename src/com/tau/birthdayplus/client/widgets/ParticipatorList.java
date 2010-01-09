package com.tau.birthdayplus.client.widgets;

public class ParticipatorList {
	private String htmlList;
	private Integer userPart;
	private Integer totalAmount;
	
	public ParticipatorList(String htmlList,Integer userPart,Integer totalAmount){
		this.htmlList = htmlList;
		this.userPart = userPart;
		this.totalAmount = totalAmount;
	}
	
	public String getHtmlList(){
		return this.htmlList;
	}
	
	public void setHtmlList(String htmlList){
		this.htmlList = htmlList;
	}
	
	public Integer getUserPart(){
		return this.userPart;
	}
	
	public void setUserPart(Integer userPart){
		this.userPart = userPart;
	}
	
	public Integer getTotalAmount(){
		return this.totalAmount;
	}
	
	public void setTotalAmount(Integer totalAmount){
		this.totalAmount = totalAmount;
	}

}
