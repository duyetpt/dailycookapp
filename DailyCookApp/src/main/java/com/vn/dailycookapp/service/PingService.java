package com.vn.dailycookapp.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.restmodel.response.DCAResponse;
import com.vn.dailycookapp.utils.json.JsonTransformer;

@Path("/dailycook")
public class PingService {
	
	@GET
	@Path("/ping")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		
		String msg = "t-" + System.currentTimeMillis();
		DCAResponse dcaResponse = new DCAResponse(0);
		dcaResponse.setData(msg);
		
		return Response.ok(JsonTransformer.getInstance().marshall(dcaResponse)).status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/hello")
	@Consumes(MediaType.APPLICATION_JSON)
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
	@Path("/demo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response demo() {
		return Response.ok("demo").status(Response.Status.OK).build();
	}
}
