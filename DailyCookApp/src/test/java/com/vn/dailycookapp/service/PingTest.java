package com.vn.dailycookapp.service;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class PingTest extends JerseyTest {
	
	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(PingService.class);
	}
	
	@Test
	public void test() {
		final String helloYou = target("dailycook/ping").request().get(String.class);
		assertNotNull(helloYou);
	}
}
