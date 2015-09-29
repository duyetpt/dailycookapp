package com.vn.dailycookapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationLoader {
	
	private static final String					DBKEY				= "DATABASE";
	private static final String					IMAGE_FOLDER_KEY	= "IMAGE_FOLDER";
	private static final String					SERVER_PORT			= "SERVER_PORT";
	private final Logger						logger				= LoggerFactory.getLogger(getClass());
	
	private String								dbName;
	private String								imageDirectory;
	private int									serverPort;
	private String								languagePath;
	
	private static final ConfigurationLoader	instance			= new ConfigurationLoader();
	
	private ConfigurationLoader() {
		init();
	}
	
	public static ConfigurationLoader getInstance() {
		return instance;
	}
	
	public void init() {
		InputStream file = ClassLoader.getSystemClassLoader().getResourceAsStream("dailycook_config.properties");
		Properties properties = new Properties();
		try {
			properties.load(file);
			dbName = properties.getProperty(DBKEY, "dailycook");
			imageDirectory = properties.getProperty(IMAGE_FOLDER_KEY, "opt");
			serverPort = Integer.parseInt(properties.getProperty(SERVER_PORT, "8181"));
		} catch (IOException e) {
			logger.error("Read config file error!", e);
		}
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public String getImageDirectory() {
		return imageDirectory;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public String getLanguagePath() {
		return languagePath;
	}
	
	public void setLanguagePath(String languagePath) {
		this.languagePath = languagePath;
	}
	
}
