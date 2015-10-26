package com.vn.dailycookapp.restmodel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vn.dailycookapp.dao.DAOException;
import com.vn.dailycookapp.dao.FavoriteDAO;
import com.vn.dailycookapp.dao.RecipeDAO;
import com.vn.dailycookapp.entity.Favorite;
import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.SearchRecipeResponseData;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.search.RecipeManager;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.Unicode;

public class SearchModel extends AbstractModel {
	private static final String	INGREDIENT_TYPE	= "ingredients";
	private static final String	TAG_TYPE		= "tags";
	private static final String	NAME_TYPE		= "name";
	
	private String				filter;
	private String				keyword;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		filter = data[0];
		keyword = Unicode.toAscii(data[1]);
		userId = data[2];
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		
		Set<String> recipeIds = null;
		Map<String, Integer> nIngredientMatchMap = null;
		switch (filter) {
			case INGREDIENT_TYPE:
				List<String> ingredients = parseKeyword();
				nIngredientMatchMap = RecipeManager.getInstance().getRecipeByIngredients(ingredients);
				recipeIds = nIngredientMatchMap.keySet();
				break;
			case TAG_TYPE:
				List<String> tags = parseKeyword();
				recipeIds = RecipeManager.getInstance().getRecipeByTags(tags);
				break;
			case NAME_TYPE:
				recipeIds = RecipeManager.getInstance().getRecipeByName(keyword);
				break;
		}
		
		List<SearchRecipeResponseData> result = getResult(recipeIds, nIngredientMatchMap);
		response.setData(result);
		return response;
	}
	
	private List<String> parseKeyword() {
		List<String> result = new ArrayList<String>();
		String[] keys = keyword.split(",");
		
		for (String key : keys) {
			result.add(key);
		}
		
		return result;
	}
	
	private List<SearchRecipeResponseData> getResult(Set<String> recipeIds, Map<String, Integer> nPercentMap)
			throws DAOException {
		List<SearchRecipeResponseData> result = new ArrayList<SearchRecipeResponseData>();
		
		List<Recipe> recipes = RecipeDAO.getInstance().getRecipes(recipeIds);
		Favorite favorite = null;
		if (userId != null) {
			favorite = FavoriteDAO.getInstance().get(userId, Favorite.class);
		}
		
		for (Recipe recipe : recipes) {
			SearchRecipeResponseData resData = new SearchRecipeResponseData();
			resData.setCreateTime(recipe.getCreatedTime());
			resData.setnFavorite(recipe.getFavoriteNumber());
			resData.setRecipeId(recipe.getId());
			resData.setTitlel(recipe.getTitle());
			resData.setRecipePicture(recipe.getPictureUrl());
			resData.setRecipeStory(recipe.getStory());
			resData.setFavorite(favorite == null ? false : favorite.getRecipeIds().contains(recipe.getId()));
			if (nPercentMap != null)
				resData.setPercentMatch(100 * nPercentMap.get(recipe.getId()) / recipe.getIngredients().size());
		}
		
		Collections.sort(result);
		return result;
	}
	
}
