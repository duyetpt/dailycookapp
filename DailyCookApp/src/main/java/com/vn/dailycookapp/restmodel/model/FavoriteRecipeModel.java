package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.dao.FavoriteDAO;
import com.vn.dailycookapp.dao.FavoritedDAO;
import com.vn.dailycookapp.dao.RecipeDAO;
import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.FavoriteResponseData;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class FavoriteRecipeModel extends AbstractModel {
	private static final String	FAVORITE_FLAG	= "1";
	private static final String	UNFAVORITE_FLAG	= "-1";
	
	private String				userId;
	private String				recipeId;
	private String				flag;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			recipeId = data[0];
			flag = data[1];
			userId = data[2];
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
	}
	
	// TODO : NOTIFICATION
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		
		switch (flag) {
			case FAVORITE_FLAG:
				/**
				 * Increate favoriteNumber in recipe
				 * Push into Favorite, Favorited
				 * Return
				 */
				FavoriteDAO.getInstance().push(userId, recipeId);
				FavoritedDAO.getInstance().push(recipeId, userId);
				RecipeDAO.getInstance().increateFavoriteNumber(recipeId);
				break;
			case UNFAVORITE_FLAG:
				/**
				 * Decrease favoriteNumber in recipe
				 * Pull into Favorite, Favorited
				 * Return
				 */
				FavoriteDAO.getInstance().pull(userId, recipeId);
				FavoritedDAO.getInstance().pull(recipeId, userId);
				RecipeDAO.getInstance().decreateFavoriteNumber(recipeId);
		}
		Recipe recipe = RecipeDAO.getInstance().get(recipeId);
		
		FavoriteResponseData data = new FavoriteResponseData();
		data.setFavoriteNumber(recipe.getFavoriteNumber());
		
		response.setData(data);
		return response;
	}
	
}
