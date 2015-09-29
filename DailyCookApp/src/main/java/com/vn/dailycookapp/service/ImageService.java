package com.vn.dailycookapp.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.vn.dailycookapp.entity.response.DCAResponse;
import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.StreamUtils;
import com.vn.dailycookapp.utils.json.JsonTransformer;

@Path("/dailycook/file")
public class ImageService {
	
	/**
	 * Add image
	 * @param inputStream
	 * @return
	 * @throws FileNotFoundException
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	// @FormDataParam("image") FormDataContentDisposition contentDisposition
	public Response addImage(@FormDataParam("image") InputStream inputStream) {
		String pathFile = StreamUtils.saveImage(inputStream, "jpg");
		DCAResponse response = new DCAResponse(ErrorCodeConstant.SUCCESSUL.getErrorCode());
		response.setData(pathFile);
		return Response.ok(JsonTransformer.getInstance().marshall(response)).build();
	}
}
