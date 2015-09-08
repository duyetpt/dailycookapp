package com.vn.dailycookapp.utils;

import com.vn.dailycookapp.utils.json.JsonProperty;

public enum ErrorCodeConstant {

	SUCCESSUL(0, "Ok"),
	UNKNOW_ERROR(-1, "External server is error"),
	INVALID_EMAIL(-2, "Email invalid"),
	INVALID_PASSWORD(-3, "invalid password"),
	LOGIN_FB_FAIL(-100, "Login in facebook fail"),
	USER_NOT_FOUND(-101, "not found user in db"),
	
	PASSWORD_INCORRECT(-101, "Password is incorrect"),
	USERNAME_INCORRECT(-102, "User name is incorrect"),
	INVALID_DATA(-103, "Incorrect param"),
	
	INVALID_TOKEN(-201, "token is invalid"),
	CLOSED_SESSION(-202, "Session is closed"),
	
	DAO_EXCEPTION(-1100, "DAO exception"),
	
	ENCRYPT_EXCEPTION(-10000, "Encrypt data excetpiton");
	
	@JsonProperty("error_code")
	private int errorCode;
	
	@JsonProperty("msg")
	private String message;

	private ErrorCodeConstant(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

}
