package com.vn.dailycookapp.search;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Search {
	
	public List<String> suggestIngredients(String keyword);
	
	public List<String> suggestTags(String keyword);
	
	public List<String> suggestName(String keyword);
	
	public Map<String, Integer> getRecipeByIngredients(List<String> ingredients);
	
	public Set<String> getRecipeByTags(List<String> tags);
	
	public Set<String> getRecipeByName(String name);
}