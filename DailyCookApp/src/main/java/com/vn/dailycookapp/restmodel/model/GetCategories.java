package com.vn.dailycookapp.restmodel.model;

import java.util.List;

import com.vn.dailycookapp.dao.CategoryDAO;
import com.vn.dailycookapp.entity.Category;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.restmodel.response.DCAResponse;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.lang.Language;

public class GetCategories extends AbstractModel {
	
	private String	language;
	private String	parentId;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			language = data[0];
			parentId = data[1];
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
		
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse();
		List<Category> categories = CategoryDAO.getInstance().getCategories(parentId);
		for (Category cat : categories) {
			String value = Language.getInstance().getCategoryName(language, cat.getName());
			cat.setValue(value);
		}
		
		response.setData(categories);
		response.setError(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		return response;
	}
	
}
