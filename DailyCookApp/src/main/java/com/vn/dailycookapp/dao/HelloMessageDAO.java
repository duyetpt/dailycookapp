package com.vn.dailycookapp.dao;

import com.vn.dailycookapp.entity.HelloMessage;

public class HelloMessageDAO extends AbstractDAO{
	
	private static HelloMessageDAO instance;
	
	private HelloMessageDAO	(){
		
	}
	
	public static HelloMessageDAO getInstance() {
		if (instance == null) {
			instance = new HelloMessageDAO();
		}
		
		return instance;
	}
	
	public String getMessage() {
		HelloMessage hm = datastore.createQuery(HelloMessage.class).get();
		return hm.getMessage();
	}
}
