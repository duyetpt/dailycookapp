package com.vn.dailycookapp.restmodel.model;

import org.glassfish.jersey.internal.util.Base64;

import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.response.DCAResponse;
import com.vn.dailycookapp.security.authentication.Authenticator;
import com.vn.dailycookapp.security.authentication.CurrentUser;
import com.vn.dailycookapp.security.authentication.LoginMethod;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.ValidateException;

public class LoginModel extends AbstractModel {
	
	private String	accountInfo;
	private String	loginMethod;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		if (data == null || data.length < 2) {
			throw new ValidateException(ErrorCodeConstant.INVALID_PARAM);
		}
		accountInfo = data[0];
		loginMethod = data[1];
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = null;
		String[] infors = accountInfo.split(" ");
		if (infors.length != 2) {
			response = new DCAResponse(ErrorCodeConstant.INVALID_PARAM.getErrorCode());
		} else {
			String accString = Base64.decodeAsString(infors[1]);
			String[] m_username_pass = accString.split(":");
			if (m_username_pass.length != 2) {
				response = new DCAResponse(ErrorCodeConstant.INVALID_PARAM.getErrorCode());
			} else {
				CurrentUser cUser = null;
				switch (loginMethod) {
					case LoginMethod.EMAIL_PASSWORD:
						cUser = Authenticator.login(m_username_pass[0], m_username_pass[1]);
					case LoginMethod.FACEBOOK:
						cUser = Authenticator.loginByFb(m_username_pass[0], m_username_pass[1]);
				}
				
				response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
				response.setData(cUser);
			}
		}
		return response;
	}
	
}
