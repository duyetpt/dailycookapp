package com.vn.dailycookapp.restmodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vn.dailycookapp.restmodel.response.DCAResponse;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.ValidateException;
import com.vn.dailycookapp.utils.json.JsonTransformer;

public abstract class AbstractModel {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
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
			
			logger.error("Execute api error", ex);
		}
		
		return JsonTransformer.getInstance().marshall(response);
	}
}
