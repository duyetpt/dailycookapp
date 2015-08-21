package com.vn.dailycookapp.restapi.response;

import com.vn.dailycookapp.utils.json.JsonProperty;

public class DCAResponse<T> {
	
	@JsonProperty("error_no")
	private int	error;
	
	@JsonProperty("data")
	private T	data;
	
	public DCAResponse() {
	}
	
	public DCAResponse(int error) {
		this.error = error;
	}
	
	public int getError() {
		return error;
	}
	
	public void setError(int error) {
		this.error = error;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
