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

public class RoleDAO extends GenericDao implements ModelDAO<Role> {

	private ConnectionUserRight cnxUserRight;
	UserDAO _userDao;
	
	public RoleDAO(ConnectionUserRight cnxUserRight, UserDAO userDao) {
		this.cnxUserRight = cnxUserRight;
		_userDao = userDao;
	}

	public void create(Role _object) {
		Connection c = null;
		PreparedStatement _stmt = null;
		
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO role (name) " + "VALUES ('"
							+ _object.getName() + "');");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Role read(Role _object) {
		return null;
	}

	public List<Role> readAll() {
		List<Role> _role = new ArrayList<Role>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM role");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Role _roletmp = this.getRole(_rs);
				_role.add(_roletmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _role;
	}

	public void update(Role _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE role " + "SET name = '" + _object.getName() + "' "
							+ "WHERE idrole = " + _object.getIdrole() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void delete(Role _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM role " + "WHERE idrole = "
							+ _object.getIdrole() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Role getRole(int idrole) {
		Role _role = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM role WHERE idrole=?");
			_stmt.setInt(1, idrole);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_role = this.getRole(_rs);
			} else {
				_role = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _role;
	}

	public Role getRole(String role) {
		Role _role = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM role" + " WHERE name=?;");
			_stmt.setString(1, role);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_role = this.getRole(_rs);
			} else {
				_role = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _role;
	}

	public Role updateRoleIfNotExist(Role _object) {
		Role _role = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM role WHERE name LIKE ? AND idRole NOT LIKE ?;");
			_stmt.setString(1, _object.getName());
			_stmt.setInt(2, _object.getIdrole());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_role = this.getRole(_rs);
			} else {
				_role = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _role;
	}

	private Role getRole(ResultSet _rs) throws SQLException {
		int _idrole = _rs.getInt("idRole");
		String _name = _rs.getString("name");
		List<User> _user = _userDao.getUserForARole(_idrole);
		Role _role = new Role(_idrole, _name, _user);
		return _role;
	}

}