package com.vn.dailycookapp.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.Following;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 * recipe is favorited by
 */
public class FollowingDAO extends AbstractDAO{
	
	private static final FollowingDAO instance = new FollowingDAO();
	private FollowingDAO() {
		
	}
	
	public static FollowingDAO getInstance() {
		return instance;
	}
	
	public void push(String ownerId, String starId) {
		
	}
	
	public void pull(String ownerId, String starId) {
		
	}
	
	public boolean isFollowing(String ownerId, String starId) throws DAOException{
		try {
			Query<Following> query = datastore.createQuery(Following.class).field("_id").equal(new ObjectId(ownerId));
			query.field("user_ids").hasThisElement(ownerId);
			
			Following foll = query.retrievedFields(true, "_id").get();
			if (foll != null ) {
				return foll.getOwner() != null;
			}
		} catch (Exception ex) {
			logger.error("check following fail", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
		
		return false;
	}
}
