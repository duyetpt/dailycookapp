package com.vn.dailycookapp.security.authentication;

import com.vn.dailycookapp.restapi.response.DCAResponse;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 * login process is here
 */

public final class Authenticator {
	
	public static DCAResponse login(String username, String password) {
		// TODO
		return null;
	}
	
	public static DCAResponse loginByFb(String fbId, String refreshToken) {
		FbToken fbToken = new FbToken();
		fbToken.setFbId(fbId);
		fbToken.setRefreshToken(refreshToken);
		
		DCAResponse response = new DCAResponse();
		try {
			CurrentUser cUser = new CurrentUser();
			cUser.login(fbToken);
			response.setData(cUser);
			response.setError(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		} catch (FbAuthException ex) {
			response.setError(ex.getErrorCode());
		}
		
		return response;
	}
}
