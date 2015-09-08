package com.vn.dailycookapp.security.authentication;

import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.AccountInfo;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.security.session.SessionManager;
import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.EncryptHelper;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.validate.Validator;

public class CurrentUser {
	private String	displayName;
	private String	avatarUrl;
	private String	coverUrl;
	private String	dob;
	private String	token;
	
	public void login(FbToken fbToken) throws DCAException {
		// get data into database
		User user = UserDAO.getInstance().getUserInfoByFbId(fbToken.getFbId());
		// User user = null;
		if (user == null) {
			AccountInfo acc = VerifyFacebookAccount.getInstance().sentGet(fbToken.getRefreshToken());
			if (acc == null) {
				throw new LoginFailException(ErrorCodeConstant.LOGIN_FB_FAIL);
			} else {
				if (fbToken.getFbId().equals(acc.getFbId())) {
					displayName = acc.getDisplayName();
					avatarUrl = acc.getAvatarUrl();
					coverUrl = acc.getCoverUrl();
					dob = acc.getDob();
					
					// Update DATABASE
					String userId = saveToDB(fbToken);
					token = SessionManager.getInstance().addSession(userId);
				} else {
					throw new LoginFailException(ErrorCodeConstant.LOGIN_FB_FAIL);
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
	
	private String saveToDB(FbToken fbToken) throws DCAException{
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
	
	public void login(UsernamePasswordToken token) throws DCAException {
		Validator.getInstance().validateEmail(token.getUsername());
		Validator.getInstance().validatePassword(token.getPassword());
		
		User user = UserDAO.getInstance().getUserInfoByEmail(token.getUsername());
		if (user != null) {
			String password = EncryptHelper.encrypt(token.getPassword());
			if (password.equals(user.getPassword())) {
				displayName = user.getDisplayName();
				avatarUrl = user.getAvatarUrl();
				coverUrl = user.getCoverUrl();
				dob = user.getDob();
				this.token = SessionManager.getInstance().addSession(user.getId());
			} else {
				throw new LoginFailException(ErrorCodeConstant.PASSWORD_INCORRECT);
			}
		} else {
			throw new LoginFailException(ErrorCodeConstant.USER_NOT_FOUND);
		}
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
