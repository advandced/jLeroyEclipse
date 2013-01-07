package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.la.jproductbase.metier.Chrono;

/**
 * Classe de gestion de connection avec timeout.
 * 
 * @author stc
 * 
 */
public class ConnectionWithTimeout extends Thread {
	public volatile Connection connection = null;

	private final String path;
	private final String user;
	private final String passwd;

	public ConnectionWithTimeout(String path, String user, String passwd) {
		this.path = path;
		this.user = user;
		this.passwd = passwd;
	}

	@Override
	public final void run() {
		try {
			connection = DriverManager.getConnection(path, user, passwd);
		} catch (SQLException e) {
			System.out.println("Echec connection.");
		}
	}

	public static Connection getConnection(String path, String user,
			String passwd, int timeoutMs) {
		Chrono _chrono = new Chrono();
		_chrono.start();
		ConnectionWithTimeout connectionWithTimeout = new ConnectionWithTimeout(
				path, user, passwd);
		connectionWithTimeout.start();
		try {
			if (0 == timeoutMs) {
				connectionWithTimeout.join();
			} else {
				connectionWithTimeout.join(timeoutMs);
			}
		} catch (InterruptedException e) {
			// Thread interrupted
		}
		_chrono.stop();
		
		return connectionWithTimeout.connection;
	}
}
