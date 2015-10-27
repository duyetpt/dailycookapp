package com.vn.dailycookapp.cache.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.utils.Unicode;

// TODO
public class UserCache {
	private Map<String, CompactUserInfo>	userMap;
	private Map<String, String>				emailMap;
	
	private static final UserCache			instance	= new UserCache();
	
	private UserCache() {
		userMap = new TreeMap<>();
		emailMap = new TreeMap<String, String>();
	}
	
	public static UserCache getInstance() {
		return instance;
	}
	
	public void cache(User user) {
		CompactUserInfo cUser = new CompactUserInfo();
		cUser.setAvatarUrl(user.getAvatarUrl());
		cUser.setCoverUrl(user.getCoverUrl());
		cUser.setDisplayName(user.getDisplayName());
		cUser.setEmail(user.getEmail());
		cUser.setNumberFollower(user.getNumberFollower());
		cUser.setNumberFollowing(user.getNumberFollowing());
		cUser.setNumberRecipes(user.getNumberRecipes());
		cUser.setUserId(user.getId());
		cUser.setIntroduce(user.getIntroduce());
		
		userMap.put(user.getId(), cUser);
		emailMap.put(user.getEmail(), user.getId());
	}
	
	/**
	 * Get user info by email
	 * 
	 * @param userId
	 * @return
	 */
	public CompactUserInfo get(String userId) {
		return userMap.get(userId);
	}
	
	/**
	 * Get user info by email
	 * 
	 * @param email
	 * @return
	 */
	public CompactUserInfo getInfoByEmail(String email) {
		String id = emailMap.get(email);
		return id == null ? null : userMap.get(id);
	}
	
	/**
	 * Get list user info by username
	 * 
	 * @param username
	 * @return
	 */
	public List<CompactUserInfo> list(String username) {
		List<String> userIds = new ArrayList<String>();
		for (CompactUserInfo cUser : userMap.values()) {
			if (Unicode.toAscii(cUser.getDisplayName()).toLowerCase().contains(username)) {
				userIds.add(cUser.getUserId());
			}
			if (cUser.getEmail().contains(username)) {
				userIds.add(cUser.getUserId());
			}
		}
		
		List<CompactUserInfo> cUsers = new ArrayList<CompactUserInfo>();
		for (String userId : userIds) {
			cUsers.add(userMap.get(userId));
		}
		
		return cUsers;
	}
}
