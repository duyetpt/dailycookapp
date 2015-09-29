package com.vn.dailycookapp.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class Favorited {
	
	@Id
	private String			id;
	
	@Property("user_ids")
	private List<String>	userIds;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getUserIds() {
		return userIds;
	}
	
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
}
