package com.vn.dailycookapp.restmodel;

public enum ModelDefine {
	
	LOGIN("login");
	
	private final String name;
	private ModelDefine(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
