package com.vn.dailycookapp.security.authentication;

import com.vn.dailycookapp.entity.AccountInfo;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

class CurrentUser {
	private String	displayName;
	private String	avatarUrl;
	private String	coverUrl;
	private String	dob;
	private String token;
	
	public void login(FbToken fbToken) throws FbAuthException{
		AccountInfo acc = VerifyFacebookAccount.getInstance().sentGet(fbToken.getRefreshToken());
		if (acc == null) {
			throw new FbAuthException(ErrorCodeConstant.LOGIN_FB_FAIL.getErrorCode());
		} else {
			if (fbToken.getFbId().equals(acc.getFbId())) {
				displayName = acc.getDisplayName();
				avatarUrl = acc.getAvatarUrl();
				coverUrl = acc.getCoverUrl();
				dob = acc.getDob();
				token = TokenGenerator.getToken();
			}
		}
	}
	
	public void login(UsernamePasswordToken token) {
		
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}
	
	public String getCoverUrl() {
		return coverUrl;
	}
	
	public String getDob() {
		return dob;
	}
	
	public String getToken() {
		return token;
	}
}
