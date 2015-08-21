package com.vn.dailycookapp.security.authentication;


public class FbAuthException extends Exception {

	private static final long	serialVersionUID	= 8986508093313967128L;
	private int errorCode;
	
	public FbAuthException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	
}
