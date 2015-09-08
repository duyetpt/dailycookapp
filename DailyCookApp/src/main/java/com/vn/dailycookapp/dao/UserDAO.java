package com.vn.dailycookapp.dao;

import org.mongodb.morphia.query.Query;

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
	
	public User getUserInfoByFbId(String fbId) throws DAOException {
		try {
			Query<User> query = datastore.createQuery(User.class).field("fb_id").equal(fbId);
			User user = query.get();
			
			return user;
		} catch (Exception ex) {
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
}
