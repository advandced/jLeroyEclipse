package fr.la.juserright.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.ConnectionPool;


public class ConnectionUserRight {
	private Connection cnx;
	private ConnectionPool connectionPool;

	public ConnectionUserRight() throws ConfigFileReaderException, IOException, SQLException {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() throws SQLException, NamingException {
		if (null == this.cnx) {
			this.cnx = connectionPool.getConnection("jdbc/_leroyUserRight");
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
