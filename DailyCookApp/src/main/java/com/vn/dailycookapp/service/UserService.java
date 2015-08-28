package com.vn.dailycookapp.service;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.restapi.APIDispatcher;
import com.vn.dailycookapp.restapi.ApiDefine;

@Path("/dailycook/user")
public class UserService {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@HeaderParam(HeaderField.AUTHORIZATION) String authInfo) {
		String data = APIDispatcher.getApi(ApiDefine.LOGIN).doProcess(authInfo);
		
		return Response.ok(data).build();
	}
}
