package com.vn.dailycookapp.restapi;

import java.util.Hashtable;

public class APIDispatcher {
	
	@SuppressWarnings("rawtypes")
	private static Hashtable<ApiDefine, AbstractAPI> mapedApi;
	static {
		mapedApi = new Hashtable<>();
		mapedApi.put(ApiDefine.LOGIN, new LoginAPI());
	}
	
	@SuppressWarnings("rawtypes")
	public static AbstractAPI getApi(ApiDefine api) {
		return mapedApi.get(api);
	}
}
