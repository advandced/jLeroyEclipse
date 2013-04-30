package fr.la.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolSQL {

	private String namePool;
	private DataSource ds;
	//private static ThreadLocal<Connection> localConnectionHolder = new ThreadLocal<Connection>();
	
	public ConnectionPoolSQL(String namePool) {
		this.namePool = namePool;
	}

	public Connection getConnection() throws SQLException {
		/*
		if ( localConnectionHolder.get() != null  )
			return localConnectionHolder.get();
		*/
		InitialContext ctx = null;
		Connection connection = null;
		if (ds == null) {
			synchronized (this) {
				try {
					ctx = new InitialContext();
					ds = (DataSource) ctx.lookup(this.namePool);
				} catch (NamingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		connection = ds.getConnection();
		//localConnectionHolder.set(connection);
		if (connection == null) {
			throw new SQLException("Error establishing connection!");
		}
		return connection;
	}

}
