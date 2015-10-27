package com.vn.dailycookapp.restmodel.model;

import org.bson.types.ObjectId;

import com.vn.dailycookapp.cache.user.CompactUserInfo;
import com.vn.dailycookapp.cache.user.UserCache;
import com.vn.dailycookapp.dao.FavoriteDAO;
import com.vn.dailycookapp.dao.FollowingDAO;
import com.vn.dailycookapp.dao.RecipeDAO;
import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.RecipeResponseData;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 *         Check recipe id
 *         Get recipe by id
 *         Get owner information
 *         Do user favour this recipe
 *         Do user following this owner
 */
public class GetRecipeModel extends AbstractModel {
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			userId = data[0];
			recipeId = data[1];
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
		validateData();
		
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		Recipe recipe = RecipeDAO.getInstance().get(recipeId);
		CompactUserInfo owner = UserCache.getInstance().get(recipe.getOwner());
		
		// check user favorite recipe
		recipe.setIsFavorite(FavoriteDAO.getInstance().isFavorited(userId, recipeId));
		// check userid is following owner of recipe
		boolean isFollowingOwner = FollowingDAO.getInstance().isFollowing(userId, owner.getUserId());
		
		RecipeResponseData data = new RecipeResponseData(recipe, owner);
		data.setIsFollowing(isFollowingOwner);
		
		// TODO
		response.setData(data);
		return response;
	}
	
	private void validateData() throws InvalidParamException {
		if (!ObjectId.isValid(recipeId)) {
			throw new InvalidParamException();
		}
	}
}
