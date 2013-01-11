package fr.la.jproductbase.dao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;

public class ConnectionInfoSchema implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection cnx;
	private ConnectionPool connectionPool;

	public ConnectionInfoSchema() throws ConfigFileReaderException, IOException, SQLException {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() throws SQLException, NamingException {
		if (null == this.cnx) {
			this.cnx = connectionPool.getConnection("java:comp/env/jdbc/_leroyInfoSchema");			
		} else {
			// Connexion already open
		}
		
		return cnx;
	}

	public void closeCnx() {
		connectionPool.closeConnection(this.cnx);
		this.cnx = null;
	}
}

