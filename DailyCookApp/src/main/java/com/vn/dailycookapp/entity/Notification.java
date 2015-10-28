package com.vn.dailycookapp.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import com.vn.dailycookapp.utils.TimeUtils;

@Entity(noClassnameStored = true)
public class Notification {
	
	// status define
	public static final int		UNREAD_STATUS					= 0;
	public static final int		READED_STATUS					= 1;
	
	// type define
	public static final String	NEW_COMMENT_TYPE				= "new_comment_noti";
	public static final String	NEW_FOLLOWER_TYPE				= "new_follower_noti";
	public static final String	NEW_RECIPE_FROM_FOLLOWING_TYPE	= "new_recipe_from_following_noti";
	public static final String	WARM_TYPE						= "warm_noti";
	public static final String	NEW_FAVORITE_TYPE				= "favorite_noti";
	
	@Id
	private String				id;
	private String				from;
	private String				to;
	private String				msg;
	private String				type;
	@Property("recipe_id")
	private String				recipeId;
	@Property("sent_date")
	private long				sentDate = TimeUtils.getCurrentGMTTime();
	private int					status = UNREAD_STATUS;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	
	public long getSentDate() {
		return sentDate;
	}
	
	public void setSentDate(long sentDate) {
		this.sentDate = sentDate;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
}
