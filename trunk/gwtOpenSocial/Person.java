package com.example.friends.client;

public class Person {
	private String id;
	private String name;
	
	public Person(){
		id="";
		name="";
	}
	
	public Person(String id, String name){
		this.id=id;
		this.name=name;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setId(String id){
		this.id=id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void set(String id,String name){
		this.id=id;
		this.name=name;
	}
	
	public String toString(){
		return id +" "+ name;
	}
	
	

}
