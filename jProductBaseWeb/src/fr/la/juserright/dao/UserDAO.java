package fr.la.juserright.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.juserright.metier.User;

public class UserDAO implements ModelDAO<User> {

	private ConnectionUserRight cnxUserRight;

	public UserDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(User _object) throws SQLException {
		PreparedStatement _stmt = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO user (actif, login, password, admin, nom, prenom, email) "
							+ "VALUES (" + _object.getActif() + ", '"
							+ _object.getLogin() + "', '"
							+ _object.getPassword() + "', "
							+ _object.getAdmin() + ", '" + _object.getNom()
							+ "', '" + _object.getPrenom() + "', '"
							+ _object.getEmail() + "');");
			_stmt.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public User read(User _object) throws SQLException {
		User _user = new User();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user WHERE login LIKE ?;");
			_stmt.setString(1, _object.getLogin());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public List<User> readAll() throws SQLException {
		List<User> _user = new ArrayList<User>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				User _usertmp = this.getUser(_rs);
				_user.add(_usertmp);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public void update(User _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"UPDATE user " + "SET login = '" + _object.getLogin()
							+ "', " + "actif = " + _object.getActif() + ", "
							+ "admin = " + _object.getAdmin() + ", "
							+ "nom = '" + _object.getNom() + "', "
							+ "prenom = '" + _object.getPrenom() + "', "
							+ "email = '" + _object.getEmail() + "' "
							+ "WHERE iduser = " + _object.getIduser() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public void delete(User _object) throws SQLException {
		PreparedStatement _stmt = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"DELETE FROM user " + "WHERE iduser = "
							+ _object.getIduser() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public User getUser(int idUser) throws SQLException {
		User _user = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user" + " WHERE iduser = ?;");
			_stmt.setInt(1, idUser);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public User login(User user) throws SQLException {
		User _user = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user"
							+ " WHERE login = ? AND password = ? LIMIT 1;");
			_stmt.setString(1, user.getLogin());
			_stmt.setString(2, user.getPassword());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public User getUser(String login) throws SQLException {
		User _user = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user" + " WHERE login = ?;");
			_stmt.setString(1, login);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		if (null != _user) {
			return _user;
		} else {
			return null;
		}
	}

	public List<User> getUserAddRole(String nom_role) throws SQLException {
		List<User> _user = new ArrayList<User>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user WHERE login NOT IN (SELECT login "
							+ "FROM user_role WHERE nom_role = ?);");
			_stmt.setString(1, nom_role);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				User _usertmp = this.getUser(_rs);
				_user.add(_usertmp);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public List<User> getUserForARole(String login) throws SQLException {
		List<User> _user = new ArrayList<User>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM user WHERE login IN (SELECT login FROM user_role WHERE nom_role = ?);");
			_stmt.setString(1, login);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				User _usertmp = this.getUser(_rs);
				_user.add(_usertmp);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public User updateUserIfLoginNotExist(User _object) throws SQLException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		User _user = new User();
		try {
			_stmt = this.cnxUserRight
					.getCnx()
					.prepareStatement(
							"SELECT * FROM user WHERE login LIKE ? AND iduser NOT LIKE ?;");
			_stmt.setString(1, _object.getLogin());
			_stmt.setInt(2, _object.getIduser());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _user;
	}

	public void updateUserPassword(User _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"UPDATE user SET password = '" + _object.getPassword()
							+ "' WHERE iduser = " + _object.getIduser() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	private User getUser(ResultSet _rs) throws SQLException {

		int _iduser = _rs.getInt("iduser");
		String _login = _rs.getString("login");
		String _password = _rs.getString("password");
		int _admin = _rs.getInt("admin");
		String _nom = _rs.getString("nom");
		String _prenom = _rs.getString("prenom");
		String _email = _rs.getString("email");
		int _actif = _rs.getInt("actif");

		User _user = new User(_iduser, _login, _password, _admin, _nom,
				_prenom, _email, _actif);
		return _user;
	}

}