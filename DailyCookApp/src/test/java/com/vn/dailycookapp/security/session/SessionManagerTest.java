package com.vn.dailycookapp.security.session;

import org.bson.types.ObjectId;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.vn.dailycookapp.utils.TimeUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.DEFAULT)
public class SessionManagerTest {

	SessionManager sessionManager = SessionManager.getInstance();
	
	@Test
	public void testAddSession() {
		String token = sessionManager.addSession(new ObjectId().toString());
		
		assertEquals(26, token.length());
	}
	
	@Test
	public void testGetSession() throws TokenInvalidException, SessionClosedException {
		
		String userId = new ObjectId().toString();
		
		long beforeTime = TimeUtils.getCurrentGMTTime();
		String token =sessionManager.addSession(userId);
		
		Session session = sessionManager.getSession(token);
		
		assertNotNull(session);
		assertEquals(token, session.getToken());
		assertEquals(userId, session.getUserId());
		
		long currentTime = TimeUtils.getCurrentGMTTime();
		
		assertEquals(true, session.getLastActiveTime() <= currentTime);
		assertEquals(true, session.getLastActiveTime() >= beforeTime);
	}
}
