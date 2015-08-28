package com.vn.dailycookapp.service;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.vn.dailycookapp.security.authentication.CurrentUser;
import com.vn.dailycookapp.security.authentication.LoginMethod;
import com.vn.dailycookapp.utils.json.JsonTransformer;
public class UserServiceTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(UserService.class);
	}
	
	@Test
	public void testLogin() {
		String fbToken = "CAACEdEose0cBACLhn9gzwJm3sZA7sDBAZBrU3eXdecvnfy8ONWYZB1yg15O2bIGJy9XB7vRoZCCTElDPuoZBkIFh9tQaFiSi4NCYaaiyRvffSqgTV2ekbOw7lgIq8p7ZAdiRGQdTELtciwBC2N1xf0MNd1gsZAnVkj8IuvFxJ1Jr7oGitAPBn3ZCRJc65vc6c24ZCVGBZBMFTjgAZDZD";
		String token = LoginMethod.FACEBOOK + ":685194628264728:" + fbToken;
		String token64 = Base64.encodeAsString(token);
		String authInfo = "Basic " + token64;
		
		String response = target("dailycook/user/login").request(MediaType.APPLICATION_JSON_TYPE).header(HeaderField.AUTHORIZATION, authInfo).post(null, String.class);
		
		JSONObject jsonObj = new JSONObject(response);
		CurrentUser user =JsonTransformer.getInstance().unmarshall(jsonObj.getJSONObject("data").toString(), CurrentUser.class);
		assertEquals(26, user.getToken().length());
	}
	
}
