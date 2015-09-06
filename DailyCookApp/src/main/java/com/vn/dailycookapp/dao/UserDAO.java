package com.vn.dailycookapp.dao;

import java.util.List;

import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;
import com.vn.dailycookapp.entity.User;

public class UserDAO extends AbstractDAO {
	
	private static final UserDAO	instance	= new UserDAO();
	
	private UserDAO() {
		datastore.ensureIndexes();
	}
	
	public static final UserDAO getInstance() {
		return instance;
	}
	
	public void save(User user) {
		synchronized (user) {
			datastore.save(user);
		}
	}
	
	public User getUserInfoByEmail(String email) {
		Query<User> query = datastore.createQuery(User.class).field("email").equal(email);
		User user = query.get();
		
		return user;
	}
	
	public User getUserInfoByFbId(String fbId) {
		Query<User> query = datastore.createQuery(User.class).field("fb_id").equal(fbId);
		datastore.getCollection(User.class).findOne(new BasicDBObject("fb_id", fbId));
		List<User> users = query.asList();
		
		return users == null || users.isEmpty() ? null : users.get(0);
	}
}
