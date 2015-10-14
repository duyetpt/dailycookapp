package com.vn.dailycookapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.restmodel.model.NewFeedModel;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.TimeUtils;

public class RecipeDAO extends AbstractDAO<Recipe> {
	
	private static final RecipeDAO	instance	= new RecipeDAO();
	
	private RecipeDAO() {
		datastore.ensureIndexes();
	}
	
	public static RecipeDAO getInstance() {
		return instance;
	}
	
	public boolean increateFavoriteNumber(String recipeId) throws DAOException {
		return increaseForField(recipeId, 1, Recipe.class, "favorite_number");
	}
	
	public boolean decreateFavoriteNumber(String recipeId) throws DAOException {
		return increaseForField(recipeId, -1, Recipe.class, "favorite_number");
	}
	
	public Recipe get(String recipeId) throws DAOException {
		return get(recipeId, Recipe.class);
	}
	
	public List<Recipe> getRecipes(int skip, int take, String sort, List<String> followingIds) throws DAOException {
		try {
			Query<Recipe> query = datastore.createQuery(Recipe.class).offset(skip).limit(take);
			switch (sort) {
				case NewFeedModel.SORT_BY_FOLLOWING:
					if (followingIds != null) {
						query.field("owner").in(followingIds).order("-create_time");
					} else {
						return new ArrayList<>();
					}
					break;
				case NewFeedModel.SORT_BY_HOTEST:
					query.field("created_time").greaterThanOrEq(TimeUtils.getCurrentGMTTime() - TimeUtils.A_MONTH_MILI)
							.order("-favorite_number");
					break;
				case NewFeedModel.SORT_BY_NEWEST:
					query.order("-created_time");
					break;
			}
			
			return query.asList();
		} catch (Exception ex) {
			logger.error("get recipes error", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
}
