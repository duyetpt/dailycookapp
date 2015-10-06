package com.vn.dailycookapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.restmodel.ModelDefine;
import com.vn.dailycookapp.restmodel.ModelResolver;
import com.vn.dailycookapp.service.mediatypeopen.MediaTypeWithUtf8;

@Path("/dailycook/recipe")
public class RecipeService {
	
	@POST
	@Path("/add")
	@Produces(MediaTypeWithUtf8.APPLICATION_JSON_UTF8)
	public Response addRecipe(@HeaderParam(HeaderField.USER_ID) String userId, String data) {
		String responseData = ModelResolver.getApi(ModelDefine.CREATE_RECIPE).doProcess(userId, data);
		return Response.ok().entity(responseData).build();
	}
	
	@GET
	@Path("/get")
	@Produces(MediaTypeWithUtf8.APPLICATION_JSON_UTF8)
	public Response getRecipe(@HeaderParam(HeaderField.USER_ID) String userId, @QueryParam("recipe") String recipeId) {
		String responseData = ModelResolver.getApi(ModelDefine.GET_RECIPE).doProcess(userId, recipeId);
		return Response.ok().entity(responseData).build();
	}
	
	@GET
	@Path("/{lang}/ingredient_type")
	@Produces(MediaTypeWithUtf8.APPLICATION_JSON_UTF8)
	public Response getIngredientType(@PathParam("lang") String language) {
		String data = ModelResolver.getApi(ModelDefine.GET_INGREDIENT_TYPE).doProcess(language);
		return Response.ok().entity(data).build();
	}
	
	@GET
	@Path("/{lang}/units")
	@Produces(MediaTypeWithUtf8.APPLICATION_JSON_UTF8)
	public Response getUnits(@PathParam("lang") String language) {
		String data = ModelResolver.getApi(ModelDefine.GET_UNITS).doProcess(language);
		return Response.ok().entity(data).build();
	}
	
	@GET
	@Path("{recipeId}/comment/get")
	@Produces(MediaTypeWithUtf8.APPLICATION_JSON_UTF8)
	public Response getComment(@PathParam("recipeId") String recipeId, @QueryParam("skip") String skip, @QueryParam("take") String take) {
		String data = ModelResolver.getApi(ModelDefine.GET_COMMENT).doProcess(recipeId, skip, take);
		return Response.ok().entity(data).build();
	}
}
