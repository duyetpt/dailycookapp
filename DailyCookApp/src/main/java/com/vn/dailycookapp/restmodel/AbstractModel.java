package com.vn.dailycookapp.restmodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.utils.DCAException;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.json.JsonTransformer;

public abstract class AbstractModel {
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());
	
	protected abstract void preExecute(String... data) throws Exception;
	
	protected abstract DCAResponse execute() throws Exception;
	
	public String doProcess(String... data) {
		logger.info("AbstractModel:doProcess = " + data.toString());
		DCAResponse response = null;
		try {
			preExecute(data);
			response = execute();
		} catch (Exception ex) {
			if (ex instanceof DCAException) {
				DCAException vEx = (DCAException) ex;
				response = new DCAResponse(vEx.getErrorCode());
			} else {
				response = new DCAResponse(ErrorCodeConstant.UNKNOW_ERROR.getErrorCode());
			}
			
			logger.error("Execute api error", ex);
		}
		
		return JsonTransformer.getInstance().marshall(response);
	}
}
