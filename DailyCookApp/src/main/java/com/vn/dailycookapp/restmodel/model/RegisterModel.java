package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.entity.request.RegisterInfo;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.security.authentication.CurrentUser;
import com.vn.dailycookapp.security.session.SessionManager;
import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.EncryptHelper;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.json.JsonTransformer;
import com.vn.dailycookapp.utils.validate.Validator;
/**
 * 
 * @author duyetpt
 * transform data to RegisterInfo
 * Validate data
 * Encrypt password
 * Save to DB
 * Get session
 * Response
 */
public class RegisterModel extends AbstractModel {
	
	private User			user;
	private RegisterInfo	regInfo;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		String userInfo = null;
		try {
			userInfo = data[0];
			regInfo = JsonTransformer.getInstance().unmarshall(userInfo, RegisterInfo.class);
			validateInfo();
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
		
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		String encryptPass = EncryptHelper.encrypt(regInfo.getPassword());
		
		user = new User();
		user.setEmail(regInfo.getEmail());
		user.setPassword(encryptPass);
		user.setDisplayName(regInfo.getEmail().split("@")[0]);
		user.setLanguage(regInfo.getLanguage());
		// save to db
		UserDAO.getInstance().saveWithSynchronized(user);
		// get session token
		String token = SessionManager.getInstance().addSession(user.getId());
		
		CurrentUser cUser = new CurrentUser();
		cUser.setDisplayName(user.getDisplayName());
		cUser.setLanguage(user.getLanguage());
		cUser.setToken(token);
		
		// response
		response.setData(cUser);
		return response;
	}
	
	private void validateInfo() throws DCAException {
		Validator.getInstance().validateEmail(regInfo.getEmail());
		Validator.getInstance().validatePassword(regInfo.getPassword());
		if (!regInfo.getPassword().equals(regInfo.getRe_password())) {
			throw new InvalidParamException();
		}
	}
	
}
