package com.vn.dailycookapp.dao;

import java.util.List;

import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.Category;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class CategoryDAO extends AbstractDAO {
	
	private static final CategoryDAO	instance	= new CategoryDAO();
	
	private CategoryDAO() {
		datastore.ensureIndexes();
	}
	
	public static CategoryDAO getInstance() {
		return instance;
	}
	
	public void save(Category category) throws DAOException {
		try {
			datastore.save(category);
		} catch (Exception ex) {
			logger.error("save Category fail", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
	
	public List<Category> getCategories(String parentId) throws DAOException {
		try {
			Query<Category> query = null;
			if (parentId != null) {
				query = datastore.createQuery(Category.class).field("parent_id").equal(parentId);
			} else {
				query = datastore.createQuery(Category.class).field("parent_id").doesNotExist();
			}
			
			return query.asList();
		} catch (Exception ex) {
			logger.error("get Categories fail", ex);
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
	}
}
