package com.vn.dailycookapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class UserDAO extends AbstractDAO {
	
	private static final UserDAO	instance	= new UserDAO();
	
	private UserDAO() {
		datastore.ensureIndexes();
	}
	
	public static final UserDAO getInstance() {
		return instance;
	}
	
	public void save(User user) throws DCAException {
		try {
			synchronized (user) {
				datastore.save(user);
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
		return updateRecipeNumber(userId, 1);
	}
	
	public boolean decreaseRecipeNumber(String userId) throws DAOException {
		return updateRecipeNumber(userId, -1);
	}
	
	private boolean updateRecipeNumber(String userId, int number) throws DAOException {
		try {
			Query<User> query = datastore.createQuery(User.class).field("_id").equal(new ObjectId(userId));
			UpdateOperations<User> updateO = datastore.createUpdateOperations(User.class).inc("n_recipes", number);
			UpdateResults result = datastore.update(query, updateO);
			return result.getUpdatedCount() == 1;
		} catch (Exception ex) {
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
}
