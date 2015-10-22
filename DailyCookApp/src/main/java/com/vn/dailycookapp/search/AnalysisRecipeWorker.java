package com.vn.dailycookapp.search;

import java.util.LinkedList;
import java.util.List;

import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.Recipe.Ingredient;

public class AnalysisRecipeWorker extends Thread {
	
	public void run() {
		while (true) {
			Recipe recipe = RecipeManager.getRecipe();
			if (recipe == null) {
				try {
					Thread.sleep(1000 * 60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // sleep a minus
			}
			// analysis name
			{
				List<String> recipeIds = RecipeInfoCache.getInstance().getNameMap().get(recipe.getNormalizedTitle());
				if (recipeIds == null) {
					recipeIds = new LinkedList<>();
					recipeIds.add(recipe.getId());
				} else {
					recipeIds.add(recipe.getId());
				}
			} // end
			
			// analysis tags
			{
				for (String tag : recipe.getCategoryIds()) {
					// analysis for suggest key word
					Integer tagNumber = RecipeInfoCache.getInstance().getTagsMap().get(tag);
					if (tagNumber == null) {
						RecipeInfoCache.getInstance().getTagsMap().put(tag, 1);
					} else {
						RecipeInfoCache.getInstance().getTagsMap().put(tag, ++tagNumber);
					}
					
					// analysis for search
					List<String> recipeIds = RecipeInfoCache.getInstance().getTagRecipeIdMap().get(tag);
					if (recipeIds == null) {
						recipeIds = new LinkedList<>();
						recipeIds.add(recipe.getId());
					} else {
						recipeIds.add(recipe.getId());
					}
				}
				
			} // end
			
			// analysis ingredient
			{
				for (Ingredient ing : recipe.getIngredients()) {
					// analysis for suggest key word
					Integer tagNumber = RecipeInfoCache.getInstance().getIngredientMaps().get(ing.getNormalizedName());
					if (tagNumber == null) {
						RecipeInfoCache.getInstance().getIngredientMaps().put(ing.getNormalizedName(), 1);
					} else {
						RecipeInfoCache.getInstance().getIngredientMaps().put(ing.getNormalizedName(), ++tagNumber);
					}
					
					// analysis for search
					List<String> recipeIds = RecipeInfoCache.getInstance().getRecipeIngredientsMap().get(ing.getNormalizedName());
					if (recipeIds == null) {
						recipeIds = new LinkedList<>();
						recipeIds.add(recipe.getId());
					} else {
						recipeIds.add(recipe.getId());
					}
				}
			}
			
		}
	}
}
