package fr.la.jproductbase.dao;

import java.io.Serializable;
import java.sql.Connection;

import fr.la.connection.ConnectionPool;

public class ConnectionInfoSchema implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectionPool connectionPool;

	public ConnectionInfoSchema() {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() {
		try {
			return connectionPool.getConnection("java:comp/env/jdbc/_leroyInfoSchema");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}			

	}
}

