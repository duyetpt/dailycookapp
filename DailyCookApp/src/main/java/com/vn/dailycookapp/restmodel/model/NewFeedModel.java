package com.vn.dailycookapp.restmodel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.vn.dailycookapp.dao.FavoriteDAO;
import com.vn.dailycookapp.dao.FollowingDAO;
import com.vn.dailycookapp.dao.RecipeDAO;
import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.Favorite;
import com.vn.dailycookapp.entity.Following;
import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.NewFeedResponseData;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 *         Get User info
 *         Search recipes
 *         Get owner of recipes
 *         do user favorite recipes;
 *         Response data
 */
public class NewFeedModel extends AbstractModel {
	public static final String	SORT_BY_NEWEST		= "new";
	public static final String	SORT_BY_HOTEST		= "hot";
	public static final String	SORT_BY_FOLLOWING	= "following";
	
	private String				userId;
	private int					skip;
	private int					take;
	private String				sort;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			userId = data[0];
			skip = Integer.parseInt(data[1]);
			take = Integer.parseInt(data[2]);
			sort = data[3];
		} catch (Exception ex) {
			
		}
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		
		List<NewFeedResponseData> datas = new ArrayList<NewFeedResponseData>();
		
		List<String> followingIds = null;
		if (SORT_BY_FOLLOWING.equals(sort) && userId != null) {
			Following following = FollowingDAO.getInstance().get(userId, Following.class);
			followingIds = following.getStarIds();
		}
		// Get recipes
		List<Recipe> recipes = RecipeDAO.getInstance().getRecipes(skip, take, sort, followingIds);
		if (!recipes.isEmpty()) {
			Set<String> userIds = new TreeSet<>();
			// get list owner of list recipe
			for (Recipe recipe : recipes) {
				userIds.add(recipe.getOwner());
			}
			
			// Get owners for list recipe
			List<User> users = UserDAO.getInstance().list(userIds);
			
			// Get list favorite recipe of this user
			Favorite favorite = null;
			if (userId != null) {
				favorite = FavoriteDAO.getInstance().get(userId, Favorite.class);
			}
			// Merger data for response
			for (Recipe recipe : recipes) {
				NewFeedResponseData data = new NewFeedResponseData();
				data.setnComment(recipe.getCommentNumber());
				data.setnFavorite(recipe.getFavoriteNumber());
				data.setRecipeName(recipe.getTitle());
				data.setRecipePicture(recipe.getPictureUrl());
				data.setRecipeId(recipe.getId());
				if (favorite != null)
					data.setIsFavorite(favorite.getRecipeIds().contains(recipe.getId()));
				
				for (User user : users) {
					if (user.getId().equals(recipe.getOwner())) {
						data.setAvatarUrl(user.getAvatarUrl());
						data.setUsername(user.getDisplayName());
					}
				}
				
				datas.add(data);
			}
		}
		
		response.setData(datas);
		return response;
	}
	
}
