package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.restmodel.AbstractModel;

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
	private String	userId;
	private int		skip;
	private int		take;
	private String	sort;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
