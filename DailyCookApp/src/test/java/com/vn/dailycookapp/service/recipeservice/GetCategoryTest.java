package com.vn.dailycookapp.service.recipeservice;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.junit.Test;

import com.vn.dailycookapp.AbstractTest;
import com.vn.dailycookapp.service.RecipeService;
import com.vn.dailycookapp.utils.lang.Language;

public class GetCategoryTest extends AbstractTest{
	
	
	@SuppressWarnings("unused")
	@Test
	public void test() {
		try {
			importData("Category", "E:\\mongodb-win32-i386-3.1.8-pre-\\bin\\Category.json");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String parentId = "";
		{
			responseData = target("dailycook/recipe/" + Language.VIETNAMESE + "/categories").queryParam("parentId", parentId).request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
			JSONObject responseObj = getResponse();
			parentId = responseObj.getJSONArray("data").getJSONObject(0).getString("id");
			System.out.println(responseData);
		}
		
		{
			responseData = target("dailycook/recipe/" + Language.VIETNAMESE + "/categories").queryParam("parentId", parentId).request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
			JSONObject responseObj = getResponse();
			System.out.println("parent:"  + parentId + " => " + responseData);
		}
	}


	@Override
	public Class<?> getService() {
		return RecipeService.class;
	}
	
}
