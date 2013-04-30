package fr.la.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;


public final class ConnectionPool implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Connection getConnection(String namePool) throws SQLException, NamingException {
		ConnectionPoolSQL _co = new ConnectionPoolSQL(namePool);
		return _co.getConnection();
	}

}
