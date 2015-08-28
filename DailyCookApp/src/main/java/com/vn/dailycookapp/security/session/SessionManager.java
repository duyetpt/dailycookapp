package com.vn.dailycookapp.security.session;

import java.util.Hashtable;
import java.util.Map;

import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.TimeUtils;

public class SessionManager implements Runnable {
	
	private final int					TOKEN_LENGTH	= 26;
	private final int					SLEEP_TIME		= 1000 * 60 * 10;
	
	private Hashtable<String, Session>	tokenMap;
	
	private static SessionManager		instance		= new SessionManager();
	
	private SessionManager() {
		tokenMap = new Hashtable<>();
	}
	
	/**
	 * 
	 * @return instance of SessionManager
	 */
	public static SessionManager getInstance() {
		return instance;
	}
	
	/**
	 * 
	 * @param token
	 * @return Session of token
	 * @throws TokenInvalidException
	 * @throws SessionClosedException
	 */
	public Session getSession(String token) throws TokenInvalidException, SessionClosedException {
		if (token == null || token.length() != TOKEN_LENGTH) {
			throw new TokenInvalidException(ErrorCodeConstant.INVALID_TOKEN.getErrorCode());
		}
		
		Session session = tokenMap.get(token);
		if (session == null) {
			throw new SessionClosedException(ErrorCodeConstant.CLOSED_SESSION.getErrorCode());
		}
		
		return session;
	}
	
	/**
	 * 
	 * @param userId
	 * @return token of this session
	 */
	public String addSession(String userId) {
		// Generate token
		String token = TokenGenerator.getToken();
		
		Session session = new Session();
		session.setToken(token);
		session.setUserId(userId);
		session.setLastActiveTime(TimeUtils.getCurrentGMTTime());
		
		tokenMap.put(token, session);
		
		return token;
	}
	
	public void closeSessionOfUser(String userId) {
		// TODO
	}
	
	@Override
	public void run() {
		while (true) {
			long currentTime = TimeUtils.getCurrentGMTTime();
			for (Map.Entry<String, Session> entry : tokenMap.entrySet()) {
				Session session = entry.getValue();
				if (currentTime - session.getLastActiveTime() > Session.TTL) {
					tokenMap.remove(entry.getKey());
				}
			}
			
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO
			}
		}
	}
}
