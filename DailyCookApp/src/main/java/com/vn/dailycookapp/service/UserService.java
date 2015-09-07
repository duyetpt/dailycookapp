package com.vn.dailycookapp.service;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.restmodel.ModelDefine;
import com.vn.dailycookapp.restmodel.ModelResolver;

@Path("/dailycook/user")
public class UserService {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@HeaderParam(HeaderField.AUTHORIZATION) String authInfo, @HeaderParam(HeaderField.LOGIN_METHOD) String loginMethod) {
		String data = ModelResolver.getApi(ModelDefine.LOGIN).doProcess(authInfo, loginMethod);
		
		return Response.ok(data).build();
	}
}
