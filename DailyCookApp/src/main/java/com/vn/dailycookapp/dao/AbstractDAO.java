package com.vn.dailycookapp.dao;

import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractDAO {
	protected final Logger logger = LoggerFactory.getLogger(getClass());	
	protected final Datastore	datastore	= ConnectionDAO.getDataStore();
}
