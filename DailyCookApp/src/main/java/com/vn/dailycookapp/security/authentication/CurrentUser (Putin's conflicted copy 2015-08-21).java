package com.vn.dailycookapp.security.authentication;

import com.vn.dailycookapp.entity.AccountInfo;
import com.vn.dailycookapp.security.FbAuthException;

public class CurrentUser {
	private String	displayName;
	private String	avatarUrl;
	private String	coverUrl;
	private String	dob;
	
	public void login(FbToken fbToken) throws FbAuthException{
		AccountInfo acc = VerifyFacebookAccount.getInstance().sentGet(fbToken.getRefreshToken());
		if (acc == null) {
			throw new FbAuthException();
		} else {
			if (fbToken.getFbId().equals(acc.getFbId())) {
				displayName = acc.getDisplayName();
				avatarUrl = acc.getAvatarUrl();
				coverUrl = acc.getCoverUrl();
				dob = acc.getDob();
			}
		}
	}
	
	public void login(UsernamePasswordToken token) {
		// Todo
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
}
