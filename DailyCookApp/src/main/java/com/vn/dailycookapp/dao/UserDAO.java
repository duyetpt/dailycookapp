package com.vn.dailycookapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class UserDAO extends AbstractDAO<User> {
	
	private static final UserDAO	instance	= new UserDAO();
	
	private UserDAO() {
		datastore.ensureIndexes();
	}
	
	public static final UserDAO getInstance() {
		return instance;
	}
	
	public void saveWithSynchronized(User user) throws DCAException {
		try {
			synchronized (user) {
				save(user);
			}
		} catch (Exception ex) {
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
		
	}
	
	public User getUserInfoByEmail(String email) throws DAOException {
		try {
			Query<User> query = datastore.createQuery(User.class).field("email").equal(email);
			User user = query.get();
			
			return user;
		} catch (Exception ex) {
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
	
	public User getUser(String userId) throws DAOException {
		try {
			Query<User> query = datastore.createQuery(User.class).field("_id").equal(new ObjectId(userId));
			User user = query.get();
			
			return user;
		} catch (Exception ex) {
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
	
	public List<User> list(List<String> userIds) throws DAOException {
		List<ObjectId> objIds = new ArrayList<>();
		for (String userId : userIds) {
			ObjectId objId = new ObjectId(userId);
			objIds.add(objId);
		}
		
		Query<User> query = datastore.createQuery(User.class).field("_id").in(objIds);
		
		return query.asList();
	}
	
	public boolean increateRecipeNumber(String userId) throws DAOException {
		return increaseForField(userId, 1, User.class, "n_recipe");
	}
	
	public boolean decreaseRecipeNumber(String userId) throws DAOException {
		// return updateRecipeNumber(userId, -1);
		return increaseForField(userId, -1, User.class, "n_recipe");
	}
	
	public boolean increateFollowingNumber(String userId) throws DAOException {
		return increaseForField(userId, 1, User.class, "n_following");
	}
	
	public boolean decreaseFollowingNumber(String userId) throws DAOException {
		// return updateRecipeNumber(userId, -1);
		return increaseForField(userId, -1, User.class, "n_following");
	}
	
	public boolean increateFollowerNumber(String userId) throws DAOException {
		return increaseForField(userId, 1, User.class, "n_follower");
	}
	
	public boolean decreaseFollowerNumber(String userId) throws DAOException {
		// return updateRecipeNumber(userId, -1);
		return increaseForField(userId, -1, User.class, "n_follower");
	}
}
