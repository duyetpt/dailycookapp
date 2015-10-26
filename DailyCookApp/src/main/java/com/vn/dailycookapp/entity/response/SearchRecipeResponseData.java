package com.vn.dailycookapp.entity.response;

import com.vn.dailycookapp.utils.json.JsonIgnoreProperty;

public class SearchRecipeResponseData implements Comparable<SearchRecipeResponseData> {
	/**
	 * {
	 * userName: String,
	 * recipeId: String,
	 * title: String,
	 * recipeStory: String,
	 * recipePicture: String,
	 * nFavorite: int,
	 * favorite: boolean
	 * }
	 */
	
	private String	recipeId;
	private String	username;
	private String	titlel;
	private String	recipeStory;
	private String	recipePicture;
	private int		nFavorite;
	private boolean	favorite;
	
	@JsonIgnoreProperty
	private int		percentMatch;
	
	@JsonIgnoreProperty
	private long	createTime;
	
	public String getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTitlel() {
		return titlel;
	}
	
	public void setTitlel(String titlel) {
		this.titlel = titlel;
	}
	
	public String getRecipeStory() {
		return recipeStory;
	}
	
	public void setRecipeStory(String recipeStory) {
		this.recipeStory = recipeStory;
	}
	
	public String getRecipePicture() {
		return recipePicture;
	}
	
	public void setRecipePicture(String recipePicture) {
		this.recipePicture = recipePicture;
	}
	
	public int getnFavorite() {
		return nFavorite;
	}
	
	public void setnFavorite(int nFavorite) {
		this.nFavorite = nFavorite;
	}
	
	public boolean isFavorite() {
		return favorite;
	}
	
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	public int getPercentMatch() {
		return percentMatch;
	}
	
	public void setPercentMatch(int percentMatch) {
		this.percentMatch = percentMatch;
	}
	
	public long getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	// Sort by number ingredient match => %match => favorite => number favorite
	// => following(pending) => time
	@Override
	public int compareTo(SearchRecipeResponseData other) {
		if (this.percentMatch != other.getPercentMatch()) {
			return this.percentMatch > other.percentMatch ? 1 : -1;
		}
		
		if (this.favorite != other.favorite) {
			return this.favorite ? 1 : -1;
		}
		
		if (this.nFavorite != other.nFavorite) {
			return this.nFavorite > other.nFavorite ? 1 : -1;
		}
		
		if (this.createTime != other.createTime) {
			return this.createTime > other.createTime ? 1 : -1;
		}
		
		return 0;
	}
	
}
