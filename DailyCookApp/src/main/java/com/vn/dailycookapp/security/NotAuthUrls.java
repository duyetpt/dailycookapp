package com.vn.dailycookapp.security;

import java.util.ArrayList;
import java.util.List;

class NotAuthUrls {
	
	private static List<String>	list;
	
	static {
		list = new ArrayList<String>();
		list.add("dailycook/user/login");
		list.add("dailycook/user/newfeed");
		list.add("dailycook/user/register");
		list.add("dailycook/ping");
		list.add("dailycook/hello");
		list.add("dailycook/policy");
	}
	
	/**
	 * 
	 * @param url
	 * @return true if url contain in notAuthUrls list otherwise return true
	 */
	public static boolean isNotAuth(String url) {
		return list.contains(url);
	}
}
