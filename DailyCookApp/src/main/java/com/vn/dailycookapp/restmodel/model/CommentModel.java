package com.vn.dailycookapp.restmodel.model;

import com.vn.dailycookapp.dao.CommentDAO;
import com.vn.dailycookapp.dao.RecipeDAO;
import com.vn.dailycookapp.dao.UserDAO;
import com.vn.dailycookapp.entity.Comment;
import com.vn.dailycookapp.entity.Notification;
import com.vn.dailycookapp.entity.User;
import com.vn.dailycookapp.entity.request.CommentInfo;
import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.entity.response.ListCommnetResponseData.CommentResponseInfo;
import com.vn.dailycookapp.notification.NotificationActionImp;
import com.vn.dailycookapp.restmodel.AbstractModel;
import com.vn.dailycookapp.restmodel.InvalidParamException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.json.JsonTransformer;

public class CommentModel extends AbstractModel {
	private CommentInfo	commentInfo;
	
	@Override
	protected void preExecute(String... data) throws Exception {
		try {
			recipeId = data[0];
			userId = data[1];
			commentInfo = JsonTransformer.getInstance().unmarshall(data[2], CommentInfo.class);
		} catch (Exception ex) {
			throw new InvalidParamException();
		}
	}
	
	// TODO notification, Test
	@Override
	protected DCAResponse execute() throws Exception {
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		
		Comment comment = new Comment();
		comment.setContent(commentInfo.getContent());
		comment.setOwner(userId);
		comment.setRecipeId(recipeId);
		// save comment
		CommentDAO.getInstance().save(comment);
		
		// Increate number comment of this recipe
		RecipeDAO.getInstance().increateCommentNumber(recipeId);
		
		// Get user Info
		User user = UserDAO.getInstance().getUser(userId);
		
		CommentResponseInfo cri = new CommentResponseInfo();
		cri.setAvatarUrl(user.getAvatarUrl());
		cri.setCommentId(comment.getId());
		cri.setContent(comment.getContent());
		cri.setUserName(user.getDisplayName());
		
		// Notification
		NotificationActionImp.getInstance().addNotification(recipeId, userId, null, Notification.NEW_COMMENT_TYPE);
		response.setData(cri);
		return response;
	}
	
}
