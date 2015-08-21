package com.vn.dailycookapp.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.dao.HelloMessageDAO;
import com.vn.dailycookapp.restapi.response.DCAResponse;
import com.vn.dailycookapp.utils.json.JsonTransformer;

@Path("/dailycook")
public class Ping {
	
	@GET
	@Path("/ping")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ping() {
		
		String msg = "t-" + System.currentTimeMillis();
		DCAResponse<String> dcaResponse = new DCAResponse<String>(0);
		dcaResponse.setData(msg);
		
		return Response.ok(JsonTransformer.getInstance().marshall(dcaResponse)).status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sayHello(@QueryParam(value = "name") String name) {
		String msg = "Hello, " + name + ". " + HelloMessageDAO.getInstance().getMessage();
		DCAResponse<String> dcaResponse = new DCAResponse<String>(0);
		dcaResponse.setData(msg);
		
		return Response.ok(JsonTransformer.getInstance().marshall(dcaResponse)).status(Response.Status.OK).build();
	}
}
