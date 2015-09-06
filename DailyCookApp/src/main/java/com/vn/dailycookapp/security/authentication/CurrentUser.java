package com.vn.dailycookapp.security.authentication;

import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.AccountInfo;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.security.session.SessionManager;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class CurrentUser {
	private String	displayName;
	private String	avatarUrl;
	private String	coverUrl;
	private String	dob;
	private String	token;
	
	public void login(FbToken fbToken) throws FbAuthException {
		// get data into database
		User user = UserDAO.getInstance().getUserInfoByFbId(fbToken.getFbId());
//		User user = null;
		if (user == null) {
			AccountInfo acc = VerifyFacebookAccount.getInstance().sentGet(fbToken.getRefreshToken());
			if (acc == null) {
				throw new FbAuthException(ErrorCodeConstant.LOGIN_FB_FAIL.getErrorCode());
			} else {
				if (fbToken.getFbId().equals(acc.getFbId())) {
					displayName = acc.getDisplayName();
					avatarUrl = acc.getAvatarUrl();
					coverUrl = acc.getCoverUrl();
					dob = acc.getDob();
					
					// Update DATABASE
					String userId = saveToDB(fbToken);
					token = SessionManager.getInstance().addSession(userId);
				}
			}
		} else {
			displayName = user.getDisplayName();
			avatarUrl = user.getAvatarUrl();
			coverUrl = user.getCoverUrl();
			dob = user.getDob();
			token = SessionManager.getInstance().addSession(user.getId());
		}
		
	}
	
	private String saveToDB(FbToken fbToken) {
		User user = new User();
		user = new User();
		user.setDisplayName(displayName);
		user.setAvatarUrl(avatarUrl);
		user.setCoverUrl(coverUrl);
		user.setDob(dob);
		user.setFbId(fbToken.getFbId());
		
		UserDAO.getInstance().save(user);
		return user.getId();
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
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
