package com.vn.dailycookapp.restapi;

import java.util.Hashtable;

import com.vn.dailycookapp.restapi.model.LoginAPI;

public class APIDispatcher {
	
	private static Hashtable<ApiDefine, AbstractAPI> mapedApi;
	static {
		mapedApi = new Hashtable<>();
		mapedApi.put(ApiDefine.LOGIN, new LoginAPI());
	}
	
	public static AbstractAPI getApi(ApiDefine api) {
		return mapedApi.get(api);
	}
}
