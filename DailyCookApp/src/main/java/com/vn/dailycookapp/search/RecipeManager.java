package com.vn.dailycookapp.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.vn.dailycookapp.entity.Recipe;

public class RecipeManager implements Search {
	private static Queue<Recipe>	queueRecipes;
	
	static {
		queueRecipes = new ConcurrentLinkedDeque<>();
	}
	
	public static void addRecipe(Recipe recipe) {
		queueRecipes.add(recipe);
	}
	
	public static Recipe getRecipe() {
		return queueRecipes.poll();
	}
	
	@Override
	public List<String> suggestIngredients(String keyword) {
		List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>();
		Map<String, Integer> ingredientMap = RecipeInfoCache.getInstance().getIngredientMaps();
		for (Entry<String, Integer> entry : ingredientMap.entrySet()) {
			if (entry.getKey().contains(keyword)) {
				entryList.add(entry);
			}
		}
		Collections.sort(entryList, new IntegerComparator());
		List<String> result = new ArrayList<String>();
		for (Entry<String, Integer> entry : entryList) {
			result.add(entry.getKey());
		}
		return result.subList(0, result.size() < 10 ? result.size() : 10);
	}
	
	@Override
	public List<String> suggestTags(String keyword) {
		List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>();
		Map<String, Integer> tagMap = RecipeInfoCache.getInstance().getTagsMap();
		for (Entry<String, Integer> entry : tagMap.entrySet()) {
			if (entry.getKey().contains(keyword)) {
				entryList.add(entry);
			}
		}
		Collections.sort(entryList, new IntegerComparator());
		List<String> result = new ArrayList<String>();
		for (Entry<String, Integer> entry : entryList) {
			result.add(entry.getKey());
		}
		return result.subList(0, result.size() < 10 ? result.size() : 10);
	}
	
	@Override
	public List<String> suggestName(String keyword) {
		Map<String, List<String>> mapName = RecipeInfoCache.getInstance().getNameMap();
		
		List<String> result = new ArrayList<>();
		for (String name : mapName.keySet()) {
			if (result.size() >= 10) break;
			if (name.contains(keyword)) {
				result.add(name);
			}
		}
		
		return result;
	}
	
	@Override
	public List<String> getRecipeByIngredients(List<String> ingredients) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> getRecipeByTags(List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRecipeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
