package com.vn.dailycookapp.restmodel;

import java.util.Hashtable;

import com.vn.dailycookapp.restmodel.model.LoginModel;

public class ModelResolver {
	
	private static Hashtable<ModelDefine, AbstractModel> mapedApi;
	static {
		mapedApi = new Hashtable<>();
		mapedApi.put(ModelDefine.LOGIN, new LoginModel());
	}
	
	public static AbstractModel getApi(ModelDefine api) {
		return mapedApi.get(api);
	}
}
