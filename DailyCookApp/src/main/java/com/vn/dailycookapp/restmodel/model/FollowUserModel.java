package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.cache.user.CompactUserInfo;
import com.vn.dailycookapp.cache.user.UserCache;
import com.vn.dailycookapp.dao.FollowingDAO;
import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.FollowResponseData;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

public class FollowUserModel extends AbstractModel {
	private static final String	FOLLOW_FLAG		= "1";
	private static final String	UNFOLLOW_FLAG	= "-1";
	
	private String				owner;
	private String				flag;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			owner = data[0];
			userId = data[1];
			flag = data[2];
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
		
	}
	
	// 	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		switch (flag) {
			case FOLLOW_FLAG:
				// add following
				FollowingDAO.getInstance().following(owner, userId);
				// add follower
				FollowingDAO.getInstance().addFollower(userId, owner);
				
				// increase following number
				UserDAO.getInstance().increateFollowingNumber(owner);
				// increase follower number
				UserDAO.getInstance().increateFollowerNumber(userId);
				// Cache update
				UserCache.getInstance().get(owner).increaseNumberFollowing();
				UserCache.getInstance().get(userId).increaseNumberFollower();
				break;
			case UNFOLLOW_FLAG:
				// add following
				FollowingDAO.getInstance().unfollow(owner, userId);
				// add follower
				FollowingDAO.getInstance().removeFollower(userId, owner);
				
				// increase following number
				UserDAO.getInstance().decreaseFollowingNumber(owner);
				// increase follower number
				UserDAO.getInstance().decreaseFollowerNumber(userId);
				// Cache update
				UserCache.getInstance().get(owner).decreaseNumberFollowing();
				UserCache.getInstance().get(userId).decreaseNumberFollower();
				break;
		}
		
		CompactUserInfo user = UserCache.getInstance().get(userId);
		FollowResponseData data = new FollowResponseData();
		data.setFollowingNumber(user.getNumberFollowing());
		
		return response;
	}
	
}
