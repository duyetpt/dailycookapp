package com.vn.dailycookapp.utils;

import com.vn.dailycookapp.utils.json.JsonProperty;

public enum ErrorCodeConstant {

	SUCCESSUL(0, "Ok"),
	UNKNOW_ERROR(-1, "External server is error"),
	EMAIL_INVALID(-2, "Email invalid"),
	LOGIN_FB_FAIL(-100, "Login in facebook fail"),
	PASSWORD_INCORRECT(-101, "Password is incorrect"),
	USERNAME_INCORRECT(-102, "User name is incorrect"),
	INVALID_PARAM(-103, "Incorrect param"),
	
	INVALID_TOKEN(-201, "token is invalid"),
	CLOSED_SESSION(-202, "Session is closed");
	
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
