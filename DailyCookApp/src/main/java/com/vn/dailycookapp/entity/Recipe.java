package com.vn.dailycookapp.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

public class Recipe {
	@Id
	private String				id;
	
	private String				title;
	
	@Property(value = "normalize_title")
	private String				normalizedTitle;
	
	private String				owner;
	
	@Property(value = "comment_number")
	private int					commentNumber;
	
	@Property(value = "favorite_number")
	private int					favoriteNumber;
	
	@Property(value = "picture_url")
	private String				pictureUrl;
	
	@Property(value = "categories")
	private String[]			categoryIds;
	
	@Property(value = "status_flag")
	private int					statusFlag;
	
	@Property(value = "interval_cook")
	private int					intervalCook;
	
	private String				story;
	
	private boolean				selected;
	
	@Property(value = "deleted_time")
	private Long				deletedTime;
	
	@Property(value = "created_time")
	private Long				createdTime;
	
	private List<Ingredient>	ingredients;
	
	private List<Step>			steps;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNormalizedTitle() {
		return normalizedTitle;
	}
	
	public void setNormalizedTitle(String normalizedTitle) {
		this.normalizedTitle = normalizedTitle;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public int getCommentNumber() {
		return commentNumber;
	}
	
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	
	public int getFavoriteNumber() {
		return favoriteNumber;
	}
	
	public void setFavoriteNumber(int favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String[] getCategoryIds() {
		return categoryIds;
	}
	
	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	public int getStatusFlag() {
		return statusFlag;
	}
	
	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}
	
	public int getIntervalCook() {
		return intervalCook;
	}
	
	public void setIntervalCook(int intervalCook) {
		this.intervalCook = intervalCook;
	}
	
	public String getStory() {
		return story;
	}
	
	public void setStory(String story) {
		this.story = story;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Long getDeletedTime() {
		return deletedTime;
	}
	
	public void setDeletedTime(Long deletedTime) {
		this.deletedTime = deletedTime;
	}
	
	public Long getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<Step> getSteps() {
		return steps;
	}
	
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	public boolean isFavorite() {
		return isFavorite;
	}
	
	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	
	@Transient
	private boolean	isFavorite;
	
	public class Ingredient {
		private String	name;
		
		@Property(value = "normalize_name")
		@Indexed(background = true)
		private String	normalizedName;
		
		private String	unit;
		private int		quantity;
		private String	group;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getUnit() {
			return unit;
		}
		
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
		public int getQuantity() {
			return quantity;
		}
		
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		public String getGroup() {
			return group;
		}
		
		public void setGroup(String group) {
			this.group = group;
		}
		
		public String getNormalizedName() {
			return normalizedName;
		}
		
		public void setNormalizedName(String normalizedName) {
			this.normalizedName = normalizedName;
		}
		
	}
	
	public class Step {
		private int		stepNo;
		private String	description;
		@Property(value = "picture_url")
		private String	pictureUrl;
		
		public int getStepNo() {
			return stepNo;
		}
		
		public void setStepNo(int stepNo) {
			this.stepNo = stepNo;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getPictureUrl() {
			return pictureUrl;
		}
		
		public void setPictureUrl(String pictureUrl) {
			this.pictureUrl = pictureUrl;
		}
		
	}
	
}
