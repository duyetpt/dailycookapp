package com.vn.dailycookapp.dao;

import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class DAOException extends DCAException {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -34994879673744419L;
	
	public DAOException(ErrorCodeConstant error) {
		setDescription(error.getMessage());
		setErrorCode(error.getErrorCode());
	}
}
