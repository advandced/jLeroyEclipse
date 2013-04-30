package fr.la.juserright.dao;

import java.sql.Connection;

import fr.la.connection.ConnectionPool;


public class ConnectionUserRight {
	private ConnectionPool connectionPool;

	public ConnectionUserRight() {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() {
		try {
			return connectionPool.getConnection("java:comp/env/jdbc/_leroyUserRight");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}