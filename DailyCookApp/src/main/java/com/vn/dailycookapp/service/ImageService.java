package com.vn.dailycookapp.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.vn.dailycookapp.utils.StreamUtils;

@Path("/dailycook/file")
public class ImageService {
	
	/**
	 * Add image
	 * 
	 * @param inputStream
	 * @return
	 * @throws FileNotFoundException
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	// @FormDataParam("image") FormDataContentDisposition contentDisposition
	public Response addImage(@FormDataParam("image") InputStream inputStream) {
		StreamUtils.saveImage(inputStream, "png");
		return Response.ok().build();
	}
}
