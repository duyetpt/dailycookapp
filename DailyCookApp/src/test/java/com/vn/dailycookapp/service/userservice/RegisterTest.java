package com.vn.dailycookapp.service.userservice;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.junit.Test;

import com.vn.dailycookapp.AbstractTest;
import com.vn.dailycookapp.entity.request.RegisterInfo;
import com.vn.dailycookapp.security.authentication.CurrentUser;
import com.vn.dailycookapp.utils.json.JsonTransformer;
import com.vn.dailycookapp.utils.lang.Language;

public class RegisterTest extends AbstractTest{
	
	@Test
	public void test() {
		String email = "duyetpt" + System.currentTimeMillis() + "@dailycook.vn";
		String password = "979565268772394:";
		String re_passowrd = password;
		
		RegisterInfo regInfo = new RegisterInfo();
		regInfo.setEmail(email);
//		regInfo.setLanguage(Language.ENGLISH);
		regInfo.setLanguage(Language.VIETNAMESE);
		regInfo.setPassword(password);
		regInfo.setRe_passowrd(re_passowrd);
		
		String userInfo = JsonTransformer.getInstance().marshall(regInfo);
		Entity<String> entity = Entity.entity(userInfo, MediaType.APPLICATION_JSON_TYPE); 
		responseData = target("dailycook/user/register").request().post(entity, String.class);
		
		JSONObject jsonObj = getResponse();
		CurrentUser user =JsonTransformer.getInstance().unmarshall(jsonObj.getJSONObject("data").toString(), CurrentUser.class);
		assertEquals(26, user.getToken().length());
	}	
}
