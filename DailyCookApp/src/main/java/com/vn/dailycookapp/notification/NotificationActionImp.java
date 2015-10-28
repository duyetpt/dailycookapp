package com.vn.dailycookapp.notification;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vn.dailycookapp.entity.Notification;

public class NotificationActionImp implements NotificationAction {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final NotificationActionImp	instance	= new NotificationActionImp();
	
	private final Queue<Notification>			queue;
	
	private NotificationActionImp() {
		queue = new ConcurrentLinkedDeque<Notification>();
	}
	
	public static NotificationActionImp getInstance() {
		return instance;
	}
	
	@Override
	public void addNotification(String recipeId, String from, String to, String notiType) {
		synchronized (queue) {
			Notification noti = new Notification();
			noti.setFrom(from);
			noti.setTo(to);
			noti.setRecipeId(recipeId);
			noti.setType(notiType);
			queue.add(noti);
			queue.notify();
		}
	}
	
	@Override
	public Notification getNoti() {
		synchronized (queue) {
			while (queue.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
			return queue.poll();
		}
	}
	
}
