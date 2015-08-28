package com.vn.dailycookapp.restapi;

import org.glassfish.jersey.internal.util.Base64;

import com.vn.dailycookapp.restapi.response.DCAResponse;
import com.vn.dailycookapp.security.authentication.Authenticator;
import com.vn.dailycookapp.security.authentication.LoginMethod;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class LoginAPI extends AbstractAPI<String> {
	
	@Override
	protected void preExecute(String... data) throws Exception {
		t = data[0];
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = null;
		String[] infors = t.split(" ");
		if (infors.length != 2) {
			response = new DCAResponse(ErrorCodeConstant.INVALID_PARAM.getErrorCode());
		} else {
			String accString = Base64.decodeAsString(infors[1]);
			String[] m_username_pass = accString.split(":");
			if (m_username_pass.length != 3) {
				response = new DCAResponse(ErrorCodeConstant.INVALID_PARAM.getErrorCode());
			} else {
				switch (m_username_pass[0]) {
					case LoginMethod.EMAIL_PASSWORD:
						response = Authenticator.login(m_username_pass[1], m_username_pass[2]);
					case LoginMethod.FACEBOOK:
						response = Authenticator.loginByFb(m_username_pass[1], m_username_pass[2]);
				}
			}
		}
		return response;
	}
	
}
