package com.vn.dailycookapp.dao;


/**
 * 
 * @author duyetpt
 * recipe is favorited by
 */
public class FavoritedDAO extends AbstractDAO{
	
	private static final FavoritedDAO instance = new FavoritedDAO();
	private FavoritedDAO() {
		
	}
	
	public static FavoritedDAO getInstance() {
		return instance;
	}
	
	public void push(String recipeId, String userId) {
		
	}
	
	public void pull(String recipeId, String userId) {
		
	}
}
