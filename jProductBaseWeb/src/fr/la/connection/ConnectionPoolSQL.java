package fr.la.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolSQL {

	private String namePool;
	@SuppressWarnings("unused")
	private String addressServer;

	public ConnectionPoolSQL(String namePool) {
		this.namePool = namePool;
	}

	public Connection getConnection() throws NamingException, SQLException {

		InitialContext ctx = null;
		Connection connection = null;

		ctx = new InitialContext();

		// The JDBC Data source that we just created
		DataSource ds = null;

		ds = (DataSource) ctx.lookup(this.namePool);

		connection = ds.getConnection();

		if (connection == null) {

			throw new SQLException("Error establishing connection!");

		}

		return connection;

	}

}
