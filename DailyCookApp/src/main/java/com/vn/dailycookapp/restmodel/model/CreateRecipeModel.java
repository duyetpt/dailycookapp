package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.dao.RecipeDAO;
import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.RecipeResponseData;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.Unicode;
import com.vn.dailycookapp.utils.json.JsonTransformer;
import com.vn.dailycookapp.utils.validate.Validator;

public class CreateRecipeModel extends AbstractModel {
	private Recipe	recipe;
	
	@Override
	protected void preExecute(String... data) throws Exception {
			userId = data[0];
			recipe = JsonTransformer.getInstance().unmarshall(data[1], Recipe.class);
			validateRecipe();
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		recipe.setOwner(userId);
		// normalize title, ingredient
		recipe.setNormalizedTitle(Unicode.toAscii(recipe.getTitle()));
		for (Recipe.Ingredient ing : recipe.getIngredients()) {
			ing.setNormalizedName(Unicode.toAscii(ing.getName()));
		}
		
		RecipeDAO.getInstance().save(recipe);
		recipe.setIsFavorite(false);
		// get user info
		User user = UserDAO.getInstance().getUser(userId);
		RecipeResponseData data = new RecipeResponseData(recipe, user);
		data.setIsFollowing(true);
		response.setData(data);
		
		// increate recipe number of user
		UserDAO.getInstance().increateRecipeNumber(userId);
		
		// TODO notification, add recipe to user_recipe
		
		return response;
	}
	
	private void validateRecipe() throws DCAException {
		if (Validator.getInstance().isNull(recipe.getTitle())
				|| Validator.getInstance().isNull(recipe.getCategoryIds())
				|| Validator.getInstance().isNull(recipe.getPictureUrl())
				|| Validator.getInstance().isNull(recipe.getIngredients())
				|| Validator.getInstance().isNull(recipe.getSteps())) {
			logger.error("Recipe title is null or empty error");
			throw new InvalidParamException();
		}
		
	}
}
