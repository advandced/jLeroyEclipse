package fr.la.jproductbase.dao;

import java.io.Serializable;
import java.sql.Connection;

import fr.la.connection.ConnectionPool;

public class ConnectionTester implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectionPool connectionPool;

	public ConnectionTester() {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() {
		try {
			return connectionPool.getConnection("java:comp/env/jdbc/_leroyTesterPool");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
