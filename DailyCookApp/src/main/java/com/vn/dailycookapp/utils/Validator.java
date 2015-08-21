package com.vn.dailycookapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private final String EMAIL_PATTEN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

	private Validator() {
		pattern = Pattern.compile(EMAIL_PATTEN);
	}

	/**
	 * @param obj
	 * @return
	 * @throws ValidateException
	 */
	public boolean isNotNull(Object obj) throws ValidateException {
		return obj != null;
	}

	/**
	 * @param obj
	 * @return
	 */
	public boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * @param email
	 * @param errorCode
	 * @param msg
	 * @return
	 */
	public boolean isValidEmail(String email, int errorCode, String msg) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
