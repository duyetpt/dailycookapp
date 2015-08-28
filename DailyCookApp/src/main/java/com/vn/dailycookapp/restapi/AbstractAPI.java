package com.vn.dailycookapp.restapi;

import com.vn.dailycookapp.restapi.response.DCAResponse;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.ValidateException;
import com.vn.dailycookapp.utils.json.JsonTransformer;

public abstract class AbstractAPI<T> {
	
	protected T	t;
	
	protected abstract void preExecute(String... data) throws Exception;
	
	protected abstract DCAResponse execute() throws Exception;
	
	public String doProcess(String... data) {
		DCAResponse response = null;
		try {
			preExecute(data);
			response = execute();
		} catch (Exception ex) {
			if (ex instanceof ValidateException) {
				ValidateException vEx = (ValidateException) ex;
				response = new DCAResponse(vEx.getError().getErrorCode());
			} else {
				response = new DCAResponse(ErrorCodeConstant.UNKNOW_ERROR.getErrorCode());
			}
		}
		
		return JsonTransformer.getInstance().marshall(response);
	}
}
