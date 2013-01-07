package fr.la.jproductbase.dao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.connection.ConnectionPoolSQL;

/**
 * Classe d'acc&egrave;s &agrave; un pool de connexions.
 * 
 * @author stc
 * 
 */
public final class ConnectionPool implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ouvrir les connexions vers les bases de donn&eacute;s.
	 * 
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws SQLException
	 */
	public ConnectionPool() throws ConfigFileReaderException,
			IOException, SQLException {
		
		
	}


	
	/**
	 * R&eacute;cup&eacute;rer l'identifiant de la connexion &agrave; une base
	 * de donn&eacute;.
	 * 
	 * @param namePool
	 *            Nom de la base de donn&eacute;.
	 * 
	 * @return identifidant de la connection &agrave; la base de donn&eacute;.
	 * 
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public Connection getConnection(String namePool) throws SQLException, NamingException {
/*		Connection _connection = ConnectionWithTimeout.getConnection(
				"jdbc:mysql://" + bddAddress + ":" + bddPort + "/" + database,
				bddUser, bddPwd, connectionTimeout);
		if (null == _connection) {
			throw new SQLException("Connection timeout.");
		}

		return _connection;*/
		
		ConnectionPoolSQL _co = new ConnectionPoolSQL(namePool);
		
		
			return _co.getConnection();
		
			
		
		
		
	}

	/**
	 * Ferme la connexion &agrave; la base de donn&eacute;.
	 * 
	 * @param connection
	 *            Connexion.
	 * 
	 */
	public void closeConnection(Connection connection) {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// Connexion already closed
		}
	}
}
