package com.vn.dailycookapp.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class RecipeDAO extends AbstractDAO<Recipe> {
	
	private static final RecipeDAO	instance	= new RecipeDAO();
	
	private RecipeDAO() {
		datastore.ensureIndexes();
	}
	
	public static RecipeDAO getInstance() {
		return instance;
	}
	
	public void save(Recipe recipe) throws DAOException {
		try {
			datastore.save(recipe);
		} catch (Exception ex) {
			logger.error("save recipe error", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
	
	public boolean increateFavoriteNumber(String recipeId) throws DAOException {
		return increaseForField(recipeId, 1, Recipe.class, "favorite_number");
	}
	
	public boolean decreateFavoriteNumber(String recipeId) throws DAOException {
		return increaseForField(recipeId, -1, Recipe.class, "favorite_number");
	}
	
	public Recipe getRecipe(String recipeId) throws DAOException {
		try {
			Query<Recipe> query = datastore.createQuery(Recipe.class).field("_id").equal(new ObjectId(recipeId));
			return query.get();
		} catch (Exception ex) {
			logger.error("get recipe error", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
}
