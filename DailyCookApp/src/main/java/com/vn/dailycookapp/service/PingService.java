package com.vn.dailycookapp.service;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.service.mediatypeopen.MediaTypeWithUtf8;
import com.vn.dailycookapp.utils.FileUtils;
import com.vn.dailycookapp.utils.json.JsonTransformer;

@Path("/dailycook")
public class PingService {
	
	@GET
	@Path("/ping")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		
		String msg = "t-" + System.currentTimeMillis();
		DCAResponse dcaResponse = new DCAResponse(0);
		dcaResponse.setData(msg);
		
		return Response.ok(JsonTransformer.getInstance().marshall(dcaResponse)).status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello(@QueryParam(value = "name") String name) {
		String msg = null;
		if (name != null && !name.isEmpty()) {
			msg = "Hello, " + name + ". How are you?";
		} else {
			msg = "Hello, What is your name?";
		}
		DCAResponse dcaResponse = new DCAResponse(0);
		dcaResponse.setData(msg);
		
		return Response.ok(JsonTransformer.getInstance().marshall(dcaResponse)).status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/policy")
	@Produces(MediaTypeWithUtf8.TEXT_HTML_UTF8)
	public Response demo() {
		FileUtils fileUtils = new FileUtils();
		String policy = fileUtils.readFile(ClassLoader.getSystemClassLoader().getResource("policy.txt").getPath().substring(1));
		
		return Response.ok(policy).status(Response.Status.OK).build();
	}
}
