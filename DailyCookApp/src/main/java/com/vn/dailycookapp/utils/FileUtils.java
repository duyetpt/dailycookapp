package com.vn.dailycookapp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	private final Logger	logger	= LoggerFactory.getLogger(getClass());
	public String readFile(String path) {
		if (path == null || path.isEmpty()) {
			logger.error("file not exist");
			return null;
		}
		
		BufferedReader br = null;
		String result = null;
		try {
			StringBuilder sb = new StringBuilder();
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
		} catch (FileNotFoundException e) {
			logger.error("file not found", e);
			result = null;
		} catch (IOException e) {
			logger.error("IO exception", e);
			result = null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("IO exception", e);
					result = null;
				}
			}
		}
		return result;
	}
	
	public String readFile(File file) {
		BufferedReader br = null;
		String result = null;
		try {
			StringBuilder sb = new StringBuilder();
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
		} catch (FileNotFoundException e) {
			logger.error("file not found", e);
			result = null;
		} catch (IOException e) {
			logger.error("IO exception", e);
			result = null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("IO exception", e);
					result = null;
				}
			}
		}
		return result;
	}
}
