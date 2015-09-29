package com.vn.dailycookapp.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.Favorited;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 * recipe is favorited by
 */
public class FavoriteDAO extends AbstractDAO{
	
	private static final FavoriteDAO instance = new FavoriteDAO();
	private FavoriteDAO() {
		
	}
	
	public static FavoriteDAO getInstance() {
		return instance;
	}
	
	public void push(String userId, String recipeId) {
		
	}
	
	public void pull(String userId, String recipeId) {
		
	}
	
	public boolean isFavorited(String userId, String recipeId) throws DAOException{
		try {
			Query<Favorited> query = datastore.createQuery(Favorited.class).field("_id").equal(new ObjectId(userId));
			query.field("user_ids").hasThisElement(recipeId);
			
			Favorited fav = query.retrievedFields(true, "_id").get();
			if (fav != null ) {
				return fav.getId() != null;
			}
		} catch (Exception ex) {
			logger.error("check favorited fail", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
		
		return false;
	}
}
