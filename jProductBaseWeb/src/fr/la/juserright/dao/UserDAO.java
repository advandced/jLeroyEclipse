package fr.la.juserright.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.dao.GenericDao;
import fr.la.juserright.metier.User;

public class UserDAO extends GenericDao implements ModelDAO<User> {

	private ConnectionUserRight cnxUserRight;

	public UserDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(User _object) {
		Connection c = null;
		PreparedStatement _stmt = null;
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO user (actif, login, password, admin, nom, prenom, email) "
							+ "VALUES (" + _object.getActif() + ", '"
							+ _object.getLogin() + "', '"
							+ _object.getPassword() + "', "
							+ _object.getAdmin() + ", '" + _object.getNom()
							+ "', '" + _object.getPrenom() + "', '"
							+ _object.getEmail() + "');");
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public User read(User _object) {
		User _user = new User();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM user WHERE login LIKE ?;");
			_stmt.setString(1, _object.getLogin());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public List<User> readAll() {
		List<User> _user = new ArrayList<User>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM user");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				User _usertmp = this.getUser(_rs);
				_user.add(_usertmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public void update(User _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE user " + "SET login = '" + _object.getLogin()
							+ "', " + "actif = " + _object.getActif() + ", "
							+ "admin = " + _object.getAdmin() + ", "
							+ "nom = '" + _object.getNom() + "', "
							+ "prenom = '" + _object.getPrenom() + "', "
							+ "email = '" + _object.getEmail() + "' "
							+ "WHERE iduser = " + _object.getIduser() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void delete(User _object) {
		Connection c = null;
		PreparedStatement _stmt = null;
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("DELETE FROM user " + "WHERE iduser = "+ _object.getIduser() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public User getUser(int idUser) {
		User _user = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM user" + " WHERE iduser = ?;");
			_stmt.setInt(1, idUser);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public User login(User user) {
		User _user = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ? LIMIT 1;");
			_stmt.setString(1, user.getLogin());
			_stmt.setString(2, user.getPassword());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public User getUser(String login) {
		User _user = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM user" + " WHERE login = ?;");
			_stmt.setString(1, login);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		if (null != _user) {
			return _user;
		} else {
			return null;
		}
	}

	public List<User> getUserAddRole(int idrole) {
		List<User> _user = new ArrayList<User>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM user WHERE iduser NOT IN (SELECT iduser "
							+ "FROM userrole WHERE idrole = ?);");
			_stmt.setInt(1, idrole);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				User _usertmp = this.getUser(_rs);
				_user.add(_usertmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public List<User> getUserForARole(int idrole) {
		List<User> _user = new ArrayList<User>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM user WHERE iduser IN (SELECT iduser "
							+ "FROM userrole WHERE idrole = ?);");
			_stmt.setInt(1, idrole);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				User _usertmp = this.getUser(_rs);
				_user.add(_usertmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public User updateUserIfLoginNotExist(User _object) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		User _user = new User();
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM user WHERE login LIKE ? AND iduser NOT LIKE ?;");
			_stmt.setString(1, _object.getLogin());
			_stmt.setInt(2, _object.getIduser());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_user = this.getUser(_rs);
			} else {
				_user = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _user;
	}

	public void updateUserPassword(User _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE user SET password = '" + _object.getPassword()
							+ "' WHERE iduser = " + _object.getIduser() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
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