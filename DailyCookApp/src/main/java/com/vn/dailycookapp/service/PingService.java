package com.vn.dailycookapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.dao.HelloMessageDAO;
import com.vn.dailycookapp.restmodel.response.DCAResponse;
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
	
	@POST
	@Path("ping/post")
	@Produces(MediaType.TEXT_PLAIN)
	public String pingPostTest(String data) {
		return "Recived data: " + data;
	}
	
	@GET
	@Path("ping/get")
	@Produces(MediaType.TEXT_PLAIN)
	public String pingGetTest(@QueryParam("data") String data) {
		return "Recived data: " + data;
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello(@QueryParam(value = "name") String name) {
		String msg = "Hello, " + name + ". " + HelloMessageDAO.getInstance().getMessage();
		DCAResponse dcaResponse = new DCAResponse(0);
		dcaResponse.setData(msg);
		
		return Response.ok(JsonTransformer.getInstance().marshall(dcaResponse)).status(Response.Status.OK).build();
	}
		
}
