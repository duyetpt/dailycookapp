package com.vn.dailycookapp.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

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
		
//		InetSocketAddress inetSocket = new InetSocketAddress("localhost", 8189);
//		Server server = new Server(inetSocket);
//		server.setHandler(contextHandler);
		
		Server server = new Server(8181);
		server.setHandler(contextHandler);
		
//		ServerConnector sConnector = new ServerConnector(server);
//		sConnector.setHost("localhost");
//		sConnector.setPort(2134);
//		sConnector.setIdleTimeout(1800); // set timeout (second) before close connect
//		sConnector.setAcceptQueueSize(1500);  // max request in queue
		
//		server.setConnectors(new Connector[]{sConnector});
		
		
		try {
			server.start();
			System.out.println("Start server ....");
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
