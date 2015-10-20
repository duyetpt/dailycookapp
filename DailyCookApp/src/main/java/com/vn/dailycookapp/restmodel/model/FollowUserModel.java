package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.dao.FollowingDAO;
import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.User;
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
	
	// TODO : notification
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
				break;
		}
		
		User user = UserDAO.getInstance().getUser(userId);
		FollowResponseData data = new FollowResponseData();
		data.setFollowingNumber(user.getNumberFollowing());
		
		return response;
	}
	
}
