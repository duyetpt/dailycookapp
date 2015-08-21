package com.vn.dailycookapp.dao;

import org.mongodb.morphia.Datastore;

import com.vn.dailycookapp.entity.HelloMessage;

public class HelloMessageDAO {
	
	private final Datastore datastore = ConnectionDAO.getDataStore();
	private static HelloMessageDAO instance;
	
	private HelloMessageDAO	(){
		
	}
	
	public static HelloMessageDAO getInstance() {
		if (instance == null) {
			instance = new HelloMessageDAO();
		}
		
		return instance;
	}
	
//	public void addMessage(String msg) {
//		HelloMessage hm = new HelloMessage();
//		hm.setMessage(msg);
//		datastore.save(hm);
//	}
	
	public String getMessage() {
		HelloMessage hm = datastore.createQuery(HelloMessage.class).get();
		return hm.getMessage();
	}
}
