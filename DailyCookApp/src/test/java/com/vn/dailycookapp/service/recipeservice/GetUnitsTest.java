package com.vn.dailycookapp.service.recipeservice;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.vn.dailycookapp.AbstractTest;
import com.vn.dailycookapp.utils.lang.Language;

public class GetUnitsTest extends AbstractTest {
	
	@SuppressWarnings("unused")
	@Override
	public void test() {
		String lang = Language.VIETNAMESE;
		
		WebTarget webTarget = target("dailycook/recipe/" + lang + "/units");
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
		
		responseData = builder.get(String.class);
		
		JSONObject jsonObj = getResponse();
		System.out.println(responseData);
	}
}
