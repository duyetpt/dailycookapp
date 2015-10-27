package com.vn.dailycookapp.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.security.authorization.AuthorizationException;
import com.vn.dailycookapp.security.authorization.Authorizer;
import com.vn.dailycookapp.service.HeaderField;
import com.vn.dailycookapp.utils.json.JsonTransformer;

// mark implement interface JAX-RS need scan
@Provider
@Priority(Priorities.AUTHORIZATION)
public class RequestHandler implements ContainerRequestFilter {
	
	private final Logger	logger	= LoggerFactory.getLogger(RequestHandler.class);
	
	public void filter(ContainerRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("Accept", "*/*");
		logger.info("			=====================  Start reqeust  =====================			");
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		JSONObject json = new JSONObject(headers);
		logger.info("=> => => Request header => " + json.toString());
		
		String url = requestContext.getUriInfo().getPath();
		logger.info("=> => => Request Url => " + url);
		
		String query = requestContext.getUriInfo().getRequestUri().getQuery();
		
		if (query != null && query.endsWith("testMode=true")) {
			logger.info("run in test mode...");
		} else {
			if (NotAuthUrls.isNotAuth(url)) {
				logger.info("user login or register...");
			} else {
				logger.info("authorzation...");
				String token = requestContext.getHeaderString(HeaderField.TOKEN);
				try {
					String userId = Authorizer.getInstance().authorize(token);
					requestContext.getHeaders().add(HeaderField.USER_ID, userId);
				} catch (AuthorizationException e) {
					logger.error("authorzation exception", e);
					DCAResponse response = new DCAResponse(e.getErrorCode());
					requestContext.abortWith(Response.ok(JsonTransformer.getInstance().marshall(response)).build());
				}
			}
		}
		
	}
	
}
