package com.vn.dailycookapp.search;

import java.util.List;

public interface Search {
	
	public List<String> suggestIngredients(String keyword);
	
	public List<String> suggestTags(String keyword);
	
	public List<String> suggestName(String keyword);
	
	public List<String> getRecipeByIngredients(List<String> ingredients);
	
	public List<String> getRecipeByTags(List<String> tags);
	
	public List<String> getRecipeByName(String name);
}
