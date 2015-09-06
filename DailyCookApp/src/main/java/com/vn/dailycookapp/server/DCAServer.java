package com.vn.dailycookapp.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.vn.dailycookapp.utils.ConfigurationLoader;

public class DCAServer {
	
	public static void main(String[] args) {
		
		ResourceConfig resourceConfig = new ResourceConfig();
		// regist for @Path and @Provider with SPI
		resourceConfig.packages("com.vn.dailycookapp.service", 
								"com.vn.dailycookapp.auth",
								"com.vn.dailycookapp.ResponseHandler");
		resourceConfig.register(MultiPartFeature.class);	// Enable Multipart/form-data feature

		ServletContainer container = new ServletContainer(resourceConfig);
		
		ServletHolder holder = new ServletHolder(container);
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		contextHandler.setContextPath("/");
		contextHandler.addServlet(holder, "/*");
		
		Server server = new Server(ConfigurationLoader.getInstance().getServerPort());
		server.setHandler(contextHandler);
		
		try {
			server.start();
			System.out.println("Start server ....");
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
