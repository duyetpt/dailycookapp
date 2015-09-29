package com.vn.dailycookapp.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class Favorite {
	
	@Id
	private String			id;
	
	@Property("recipe_ids")
	private List<String>	recipeIds;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getRecipeIds() {
		return recipeIds;
	}
	
	public void setRecipeIds(List<String> recipeIds) {
		this.recipeIds = recipeIds;
	}
	
}