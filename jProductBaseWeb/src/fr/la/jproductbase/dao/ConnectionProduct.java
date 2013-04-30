package fr.la.jproductbase.dao;

import java.io.Serializable;
import java.sql.Connection;

import fr.la.connection.ConnectionPool;

public class ConnectionProduct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectionPool connectionPool;

	public ConnectionProduct() {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() {
		try {
			return connectionPool.getConnection("java:comp/env/jdbc/_leroyProductPool");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
