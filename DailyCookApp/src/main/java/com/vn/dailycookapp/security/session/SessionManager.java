package com.vn.dailycookapp.security.session;

import java.util.Hashtable;
import java.util.Map;

import com.vn.dailycookapp.utils.ErrorCodeConstant;
import com.vn.dailycookapp.utils.TimeUtils;

public class SessionManager implements Runnable{
	
	private final int					TOKEN_LENGTH	= 26;
	private final int	SLEEP_TIME = 1000 * 60 * 10;
	
	private Hashtable<String, Session>	tokenMap;
	
	private static SessionManager instance = new SessionManager();
	private SessionManager() {
		
	}
	
	public static SessionManager getInstance() {
		return instance;
	}
	
 	public Session getSession(String token) throws TokenInvalidException, SessionClosedException {
		if (token == null || token.length() != TOKEN_LENGTH) {
			throw new TokenInvalidException(ErrorCodeConstant.TOKEN_INVALID.getErrorCode());
		}
		
		Session session = tokenMap.get(token);
		if (session == null) {
			throw new SessionClosedException(ErrorCodeConstant.SESSION_CLOSED.getErrorCode());
		}
		
		return session;
	}

	public void addSession(String token, String userId) throws TokenInvalidException {
		if (token == null || token.length() != TOKEN_LENGTH) {
			throw new TokenInvalidException(ErrorCodeConstant.TOKEN_INVALID.getErrorCode());
		}
		
		Session session = new Session();
		session.setToken(token);
		session.setUserId(userId);
		session.setLastActiveTime(TimeUtils.getCurrentGMTTime());
		
		tokenMap.put(token, session);
	}
	
	public void closeSessionOfUser(String userId) {
		// TODO
	}
	
	@Override
	public void run() {
		while(true) {
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
