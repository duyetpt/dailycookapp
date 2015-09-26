package com.vn.dailycookapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vn.dailycookapp.restmodel.ModelDefine;
import com.vn.dailycookapp.restmodel.ModelResolver;

@Path("/dailycook/recipe")
public class RecipeService {
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRecipe(@HeaderParam(HeaderField.USER_ID) String userId, String data) {
		// TODO
		return null;
	}
	
	@GET
	@Path("/{lang}/{parentId}/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories(@PathParam("parentId") String parentCat) {
		// TODO
		return null;
	}
	
	@GET
	@Path("/{lang}/ingredient_type")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIngredientType(@PathParam("lang") String language) {
		String data = ModelResolver.getApi(ModelDefine.GET_INGREDIENT_TYPE).doProcess(language);
		return Response.ok().entity(data).build();
	}
}
