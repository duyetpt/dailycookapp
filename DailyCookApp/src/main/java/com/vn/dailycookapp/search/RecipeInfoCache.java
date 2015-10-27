package com.vn.dailycookapp.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class RecipeInfoCache{
	
	// Name -> list recipeId
	private Map<String, List<String>> nameMap;
	private Map<String, Integer> tagsMap;
	private Map<String, Integer> ingredientMaps;
	
	// tag -> list recipeId
	private Map<String, List<String>> tagRecipeIdMap;
	// recipeId -> list ingredient
	private Map<String, List<String>> recipeIngredientsMap;
	
	private RecipeInfoCache() {
		nameMap = new HashMap<>();
		tagsMap = new TreeMap<>();
		ingredientMaps = new TreeMap<>();
		tagRecipeIdMap = new TreeMap<String, List<String>>();
		recipeIngredientsMap = new TreeMap<String, List<String>>();
	}
	
	private static final RecipeInfoCache instance = new RecipeInfoCache();
	public static RecipeInfoCache getInstance() {
		return instance;
	}
	public Map<String, List<String>> getNameMap() {
		return nameMap;
	}
	public Map<String, Integer> getTagsMap() {
		return tagsMap;
	}
	public Map<String, Integer> getIngredientMaps() {
		return ingredientMaps;
	}
	public Map<String, List<String>> getTagRecipeIdMap() {
		return tagRecipeIdMap;
	}
	public Map<String, List<String>> getRecipeIngredientsMap() {
		return recipeIngredientsMap;
	}
}