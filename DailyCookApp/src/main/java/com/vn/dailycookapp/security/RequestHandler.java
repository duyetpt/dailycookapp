package com.vn.dailycookapp.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// mark implement interface JAX-RS need scan
@Provider
@Priority(Priorities.AUTHORIZATION)
public class RequestHandler implements ContainerRequestFilter{
	
	private final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
	
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO
		System.out.println("Processing authentization ...");
		logger.error("Processing authentization ...");
		
		if (requestContext.getUriInfo().getPath().equals("user/login")) {
			// TODO
			// LOGIN API
			requestContext
					.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User is not authorized!").build());
		} else {
			// TODO
			// Other api
		}
	}

}
