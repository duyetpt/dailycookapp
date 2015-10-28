package com.vn.dailycookapp.dao;

import java.util.List;

import org.mongodb.morphia.query.Query;

import com.vn.dailycookapp.entity.Comment;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class CommentDAO extends AbstractDAO <Comment>{
	
	private static final CommentDAO	instance	= new CommentDAO();
	
	private CommentDAO() {
		datastore.ensureIndexes(Comment.class);
	}
	
	public static CommentDAO getInstance() {
		return instance;
	}
	
	public List<Comment> list(String recipeId, int skip, int take) throws DAOException {
		List<Comment> comments = null;
		try {
			Query<Comment> query = datastore.createQuery(Comment.class).field("recipe_id").equal(recipeId).offset(skip)
					.limit(take);
			comments = query.asList();
		} catch (Exception ex) {
			throw new DAOException(ErrorCodeConstant.DAO_EXCEPTION);
		}
		
		return comments;
	}
	
	public int getTotalNumber(String recipeId) throws DAOException{
		return (int) datastore.createQuery(Comment.class).field("recipe_id").equal(recipeId).countAll();
	}
}
