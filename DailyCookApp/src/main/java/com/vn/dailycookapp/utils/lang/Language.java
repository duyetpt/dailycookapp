package com.vn.dailycookapp.utils.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vn.dailycookapp.utils.FileUtils;

public class Language {
	
	public static final String			ENGLISH		= "en";
	public static final String			VIETNAMESE	= "vi";
	
	private Map<String, List<String>>	ingredientTypes;
	private Map<String, List<String>>	units;
	
	private final Logger				logger		= LoggerFactory.getLogger(getClass());
	private static final Language		instance	= new Language();
	
	private Language() {
		try {
			init();
		} catch (Exception e) {
			logger.error("init multi language support error", e);
		}
	}
	
	public static Language getInstance() {
		return instance;
	}
	
	private void init() throws Exception {
		File directory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		String langDirectoryPath = directory.getParent() + File.separator + "lang"; 
		System.out.println(langDirectoryPath);
		File langDir = new File(langDirectoryPath);
		File[] files = langDir.listFiles();
		Map<String, JSONObject> language = new HashMap<>();
		for (File file : files) {
			System.out.println(file.getPath());
			JSONObject json = readFile(file);
			String lang = json.getString("language");
			language.put(lang, json); 
		}
		getIngredientTyes(language);
		getUnits(language);
	}
	
	private JSONObject readFile(File file) throws Exception {
		FileUtils fileUtils = new FileUtils();
		String data = fileUtils.readFile(file);
		JSONObject json = new JSONObject(data);
		return json;
		
	}
	
	private void getIngredientTyes(Map<String, JSONObject> data) throws Exception {
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
	
	private void getUnits(Map<String, JSONObject> data) throws Exception {
		units = new HashMap<String, List<String>>();
		for (Entry<String, JSONObject> entry : data.entrySet()) {
			List<String> result = new ArrayList<>();
			String lang = entry.getKey();
			JSONArray arr = entry.getValue().getJSONArray("units");
			
			for (int i = 0; i < arr.length(); i++) {
				result.add(arr.getString(i));
			}
			units.put(lang, result);
		}
	}
	
	public List<String> listIngredientType(String language) throws Exception {
		return ingredientTypes.get(language);
	}
	
	public List<String> listUnit(String language) throws Exception {
		return units.get(language);
	}
	
	// private void getCategory(Map<String, JSONObject> data) throws Exception{
	// categories = new HashMap<String, JSONObject>();
	// for (Entry<String, JSONObject> entry : data.entrySet()) {
	// String lang = entry.getKey();
	// JSONObject obj = entry.getValue().getJSONObject("categories");
	// categories.put(lang, obj);
	// }
	// }
	
	// public String getCategoryName(String language, String key) throws
	// Exception{
	// JSONObject category = this.categories.get(language);
	// return category.getString(key);
	// }
	//
	// public Map<String, String> getCategoryNames(String language, List<String>
	// keys) throws Exception{
	// Map<String, String> names = new HashMap<String, String>();
	//
	// JSONObject categories = this.categories.get(language);
	// for (String key : keys) {
	// names.put(key, categories.getString(key));
	// }
	//
	// return names;
	// }
}
