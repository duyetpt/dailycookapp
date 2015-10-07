package com.vn.dailycookapp.restmodel.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vn.dailycookapp.dao.CommentDAO;
import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.Comment;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.entity.response.CommnetResponseData;
import com.vn.dailycookapp.entity.response.CommnetResponseData.CommentInfo;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

/**
 * 
 * @author duyetpt
 *         Get list comment by recipeId
 *         Get owner information of this comment
 *         Get total number of comment of this recipe
 *         Convert to data for response
 */
public class GetCommentModel extends AbstractModel {
	private String	recipeId;
	private int		skip;
	private int		take;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			recipeId = data[0];
			skip = Integer.parseInt(data[1]);
			take = Integer.parseInt(data[2]);
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
	}
	
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		List<Comment> listComment = CommentDAO.getInstance().list(recipeId, skip, take);
		
		List<String> userIds = new ArrayList<String>();
		Map<String, String> userComment = new HashMap<>();
		
		List<CommentInfo> listCommentInfo = new ArrayList<CommnetResponseData.CommentInfo>();
		// Get comment info
		for (Comment comment : listComment) {
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setCommentId(comment.getId());
			commentInfo.setContent(comment.getContent());
			
			listCommentInfo.add(commentInfo);
			userComment.put(comment.getId(), comment.getOwner());
			
			if (!userIds.contains(comment.getOwner())) {
				userIds.add(comment.getOwner());
			}
		}
		
		// get User Info
		List<User> users = UserDAO.getInstance().list(userIds);
		for (CommentInfo info : listCommentInfo) {
			String userId = userComment.get(info.getCommentId());
			User user = null;
			for (User usr : users) {
				if (usr.getId().equals(userId)) {
					user = usr;
					break;
				}
			}
			
			info.setAvatarUrl(user.getAvatarUrl());
			info.setUserName(user.getDisplayName());
		}
		
		CommnetResponseData responseData = new CommnetResponseData();
		responseData.setList(listCommentInfo);
		responseData.setTotalCommentNumber(CommentDAO.getInstance().getTotalNumber(recipeId));
		
		response.setData(responseData);
		return response;
	}
	
}
