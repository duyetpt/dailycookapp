package com.vn.dailycookapp.entity.request;

public class RegisterInfo {
	
	private String	email;
	private String	password;
	private String	re_passowrd;
	private String	language;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRe_passowrd() {
		return re_passowrd;
	}
	
	public void setRe_passowrd(String re_passowrd) {
		this.re_passowrd = re_passowrd;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
