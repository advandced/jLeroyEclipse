package fr.la.jproductbase.dao;

import fr.la.configfilereader.ConfigFileReaderException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ConnectionTester implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection cnx;
	private ConnectionPool connectionPool;

	public ConnectionTester() throws ConfigFileReaderException, IOException, SQLException {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() throws SQLException, NamingException {
		if (null == this.cnx) {
			this.cnx = connectionPool.getConnection("java:comp/env/jdbc/_leroyTesterPool");
			
		} else {
			// Connection already open
		}
		
		return cnx;
	}

	public void closeCnx() {
		connectionPool.closeConnection(this.cnx);

		this.cnx = null;
	}
}
