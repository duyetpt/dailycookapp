package com.vn.dailycookapp.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Test;

import com.vn.dailycookapp.security.authentication.CurrentUser;
import com.vn.dailycookapp.security.authentication.LoginMethod;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.json.JsonTransformer;
public class UserServiceTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(UserService.class);
	}
	
	@Test
	public void testLogin() {
		String fbToken = "CAACEdEose0cBALlRqLRvo104ufl6Wx6lOZB1LxNMsJ91OPHNhwQ0rcbPxz2EQALD8Dr1BORnq0XLxefrWCE8L4D8d1L8Xkaip7VjwpZASRMvxZAORJtdP61WGn6gegYMaxtenfCcOSkmgjGOpfMUAmJksW9KsZApT98cwrJx4W7tR0ZBmuNJAFihLitKYIYeZBXgiAXzYNnAZDZD";
		String token = "685194628264728:" + fbToken;
		String token64 = Base64.encodeAsString(token);
		String authInfo = "Basic " + token64;
		
		String response = target("dailycook/user/login").request(MediaType.APPLICATION_JSON_TYPE).header(HeaderField.AUTHORIZATION, authInfo).header(HeaderField.LOGIN_METHOD, LoginMethod.FACEBOOK).post(null, String.class);
		
		JSONObject jsonObj = new JSONObject(response);
		int errorCode = jsonObj.getInt("error_no");
		assertEquals(ErrorCodeConstant.SUCCESSUL.getErrorCode(), errorCode);
		CurrentUser user =JsonTransformer.getInstance().unmarshall(jsonObj.getJSONObject("data").toString(), CurrentUser.class);
		assertEquals(26, user.getToken().length());
	}
	
}
