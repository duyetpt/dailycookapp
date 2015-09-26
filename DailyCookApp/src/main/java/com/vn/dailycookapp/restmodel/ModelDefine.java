package com.vn.dailycookapp.restmodel;

import com.vn.dailycookapp.restmodel.model.GetCategories;
import com.vn.dailycookapp.restmodel.model.GetIngredientTypesModel;
import com.vn.dailycookapp.restmodel.model.LoginModel;

public enum ModelDefine {
	
	GET_INGREDIENT_TYPE("get_ingredient_type", GetIngredientTypesModel.class),
	GET_CATEGORY("get_category", GetCategories.class),
//	CREATE_RECIPE("create_recipe", null),
//	REGISTER("register", null);
	LOGIN("login", LoginModel.class);
	
	private final String name;
	private final Class<? extends AbstractModel> model;
	private ModelDefine(String name, Class<? extends AbstractModel> model) {
		this.name = name;
		this.model = model;
	}
	public String getName() {
		return name;
	}
	
	public Class<? extends AbstractModel> getModel() {
		return model;
	}
}
