package com.vn.dailycookapp.search;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.vn.dailycookapp.entity.Recipe;

public class RecipeManager{
	private static Queue<Recipe> queueRecipes;
	
	static {
		queueRecipes = new ConcurrentLinkedDeque<>();
	}
	
	public static void addRecipe(Recipe recipe) {
		queueRecipes.add(recipe);
	}
	
	public static Recipe getRecipe() {
		return queueRecipes.poll();
	}
}
