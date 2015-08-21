package com.vn.dailycookapp.utils;

public class ValidateException extends Exception {

	private static final long serialVersionUID = 685859616686041976L;
	
	private ErrorCodeConstant error;

	public ValidateException(ErrorCodeConstant error) {
		super();
		this.error = error;
	}

	public ErrorCodeConstant getError() {
		return error;
	}

	public void setError(ErrorCodeConstant error) {
		this.error = error;
	}
		
}
