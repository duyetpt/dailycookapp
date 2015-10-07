package com.vn.dailycookapp.entity.response;

import java.util.List;

public class CommnetResponseData {
	
	private int					totalCommentNumber;
	private List<CommentInfo>	list;
	
	public static class CommentInfo {
		private String	commentId;
		private String	content;
		private String	userName;
		private String	avatarUrl;
		
		public String getCommentId() {
			return commentId;
		}
		
		public void setCommentId(String commentId) {
			this.commentId = commentId;
		}
		
		public String getContent() {
			return content;
		}
		
		public void setContent(String content) {
			this.content = content;
		}
		
		public String getUserName() {
			return userName;
		}
		
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public String getAvatarUrl() {
			return avatarUrl;
		}
		
		public void setAvatarUrl(String avatarUrl) {
			this.avatarUrl = avatarUrl;
		}
	}
	
	public int getTotalCommentNumber() {
		return totalCommentNumber;
	}
	
	public void setTotalCommentNumber(int totalCommentNumber) {
		this.totalCommentNumber = totalCommentNumber;
	}
	
	public List<CommentInfo> getList() {
		return list;
	}
	
	public void setList(List<CommentInfo> list) {
		this.list = list;
	}
	
}
