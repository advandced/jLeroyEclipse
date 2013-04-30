package fr.la.juserright.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.dao.GenericDao;
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.User;
import fr.la.juserright.metier.UserRole;

public class UserRoleDAO extends GenericDao implements ModelDAO<UserRole> {

	private ConnectionUserRight cnxUserRight;

	RoleDAO _roleDao;
	UserDAO _userDao;

	public UserRoleDAO(ConnectionUserRight cnxUserRight, RoleDAO roleDao, UserDAO userDao) {
		this.cnxUserRight = cnxUserRight;
		_roleDao = roleDao;
		_userDao = userDao;
	}

	public void create(UserRole _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO userrole (iduser, idrole) " + "VALUES ("
							+ _object.getUser().getIduser() + "" + ", "
							+ _object.getRole().getIdrole() + ");");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public UserRole read(UserRole _object) {
		return null;
	}

	public List<UserRole> readAll() {
		Connection c = null;
		List<UserRole> _userrole = new ArrayList<UserRole>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM UserRole");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				UserRole _userroletmp = this.getUserRole(_rs);
				_userrole.add(_userroletmp);

			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _userrole;
	}

	public void update(UserRole _object) {
		this.delete(_object);
		this.create(_object);
	}

	public void delete(UserRole _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
							"DELETE FROM userrole " + "WHERE iduser = "
									+ _object.getUser().getIduser() + " AND "
									+ "idrole = "
									+ _object.getRole().getIdrole() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public List<UserRole> getUserRoleWithIdUser(int iduser) {
		List<UserRole> _userrole = new ArrayList<UserRole>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM userrole" + " WHERE iduser = ?;");
			_stmt.setInt(1, iduser);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				UserRole _userroletmp = this.getUserRole(_rs);
				_userrole.add(_userroletmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _userrole;
	}

	public List<UserRole> getUserRoleWithLogin(String _login) {
		List<UserRole> _userrole = new ArrayList<UserRole>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM userrole WHERE iduser = (SELECT iduser FROM user WHERE login = ?);");
			_stmt.setString(1, _login);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				UserRole _userroletmp = this.getUserRole(_rs);
				_userrole.add(_userroletmp);
			}
			_stmt.setFetchSize(100);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _userrole;
	}

	private UserRole getUserRole(ResultSet _rs) throws SQLException {
		Role _role = _roleDao.getRole(_rs.getInt("idrole"));
		User _user = _userDao.getUser(_rs.getInt("iduser"));

		UserRole _userrole = new UserRole(_role, _user);
		return _userrole;
	}

}