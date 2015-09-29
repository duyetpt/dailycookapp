package com.vn.dailycookapp.utils.lang;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vn.dailycookapp.utils.FileUtils;

public class Language {
	
	public static final String			ENGLISH		= "en";
	public static final String			VIETNAMESE	= "vn";
	
	private Map<String, List<String>>	ingredientTypes;
	private Map<String, JSONObject>		categories;
	
	private static final Language		instance	= new Language();
	
	private Language() {
		init();
	}
	
	public static Language getInstance() {
		return instance;
	}
	
	private void init() {
		URL url = ClassLoader.getSystemClassLoader().getResource("lang/");
		File directory = new File(url.getFile());
		File[] files = directory.listFiles();
		Map<String, JSONObject> language = new HashMap<>();
		for (File file : files) {
			JSONObject json = readFile(file);
			String lang = json.getString("language");
			language.put(lang, json);
		}
		getIngredientTyes(language);
		getCategory(language);
	}
	
	private JSONObject readFile(File file) {
		FileUtils fileUtils = new FileUtils();
		String data = fileUtils.readFile(file);
		JSONObject json = new JSONObject(data);
		return json;
		
	}
	
	private void getIngredientTyes(Map<String, JSONObject> data) {
		ingredientTypes = new HashMap<String, List<String>>();
		for (Entry<String, JSONObject> entry : data.entrySet()) {
			List<String> result = new ArrayList<>();
			String lang = entry.getKey();
			JSONArray arr = entry.getValue().getJSONArray("ingredient_type");
			
			for (int i = 0; i < arr.length(); i++) {
				result.add(arr.getString(i));
			}
			ingredientTypes.put(lang, result);
		}
	}
	
	private void getCategory(Map<String, JSONObject> data) {
		categories = new HashMap<String, JSONObject>();
		for (Entry<String, JSONObject> entry : data.entrySet()) {
			String lang = entry.getKey();
			JSONObject obj = entry.getValue().getJSONObject("categories");
			categories.put(lang, obj);
		}
	}
	
	public List<String> listIngredientType(String language) {
		return ingredientTypes.get(language);
	}
	
	public String getCategoryName(String language, String key) {
		JSONObject category = this.categories.get(language);
		return category.getString(key);
	}
	
	public Map<String, String> getCategoryNames(String language, List<String> keys) {
		Map<String, String> names = new HashMap<String, String>();
		
		JSONObject categories = this.categories.get(language);
		for (String key : keys) {
			names.put(key, categories.getString(key));
		}
		
		return names;
	}
}
