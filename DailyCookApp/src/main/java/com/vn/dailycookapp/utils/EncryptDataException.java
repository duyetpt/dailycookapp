package com.vn.dailycookapp.utils;

public class EncryptDataException extends DCAException {
	
	private static final long		serialVersionUID	= 1820559810050307273L;
	private final ErrorCodeConstant	error				= ErrorCodeConstant.ENCRYPT_EXCEPTION;
	
	public EncryptDataException() {
		setDescription(error.getMessage());
		setErrorCode(error.getErrorCode());
	}
}
