package com.vn.dailycookapp.restapi;

public enum ApiDefine {
	
	LOGIN("login");
	
	private final String name;
	private ApiDefine(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
