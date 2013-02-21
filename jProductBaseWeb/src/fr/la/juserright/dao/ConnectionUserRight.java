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

	public ConnectionUserRight() throws ConfigFileReaderException, IOException,
			SQLException {
		connectionPool = new ConnectionPool();
	}

	public Connection getCnx() throws SQLException, NamingException {
		if(cnx == null){
		      try {
			cnx = connectionPool.getConnection("java:comp/env/jdbc/_leroyUserRight");
			return cnx;
		      } catch (SQLException e) {
		          e.printStackTrace();
		          System.out.print(e);
		          cnx = null;
		          return cnx;
		        }
		} else {
		      return cnx;
		}

	}

	public void closeCnx() {
		connectionPool.closeConnection(this.cnx);
		cnx = null;
	}
}
