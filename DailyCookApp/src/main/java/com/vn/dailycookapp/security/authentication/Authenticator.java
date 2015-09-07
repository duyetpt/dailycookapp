package com.vn.dailycookapp.security.authentication;

import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 *         login process is here
 */

public final class Authenticator {
	
	public static CurrentUser login(String username, String password) {
		// TODO
		return null;
	}
	
	public static CurrentUser loginByFb(String fbId, String refreshToken) throws FbAuthException {
		FbToken fbToken = new FbToken();
		fbToken.setFbId(fbId);
		fbToken.setRefreshToken(refreshToken);
		
		try {
			CurrentUser cUser = new CurrentUser();
			cUser.login(fbToken);
			return cUser;
		} catch (FbAuthException ex) {
			throw new FbAuthException(ErrorCodeConstant.LOGIN_FB_FAIL.getErrorCode());
		}
	}
}
