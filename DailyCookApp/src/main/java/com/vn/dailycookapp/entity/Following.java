package com.vn.dailycookapp.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * 
 * @author duyetpt
 *         contain all users who owner is following
 */
public class Following {
	
	@Id
	private String			owner;
	
	@Property("following")
	private List<String>	starIds;
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public List<String> getStarIds() {
		return starIds;
	}
	
	public void setStarIds(List<String> starIds) {
		this.starIds = starIds;
	}
	
}
