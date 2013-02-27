package fr.la.juserright.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.User;

public class RoleDAO implements ModelDAO<Role> {

	private ConnectionUserRight cnxUserRight;

	public RoleDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(Role _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO role (name) " + "VALUES ('"
							+ _object.getName() + "');");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public Role read(Role _object) throws SQLException {
		return null;
	}

	public List<Role> readAll() throws SQLException {
		List<Role> _role = new ArrayList<Role>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM role");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Role _roletmp = this.getRole(_rs);
				_role.add(_roletmp);
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
		return _role;
	}

	public void update(Role _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"UPDATE role " + "SET name = '" + _object.getName() + "' "
							+ "WHERE idrole = " + _object.getIdrole() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public void delete(Role _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"DELETE FROM role " + "WHERE idrole = "
							+ _object.getIdrole() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public Role getRole(int idrole) throws SQLException {
		Role _role = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM role" + " WHERE idrole=?;");
			_stmt.setInt(1, idrole);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_role = this.getRole(_rs);
			} else {
				_role = null;
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
		return _role;
	}

	public Role getRole(String role) throws SQLException {
		Role _role = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM role" + " WHERE name=?;");
			_stmt.setString(1, role);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_role = this.getRole(_rs);
			} else {
				_role = null;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _role;
	}

	public Role updateRoleIfNotExist(Role _object) throws ErrorException,
			SQLException {
		Role _role = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight
					.getCnx()
					.prepareStatement(
							"SELECT * FROM role WHERE name LIKE ? AND idRole NOT LIKE ?;");
			_stmt.setString(1, _object.getName());
			_stmt.setInt(2, _object.getIdrole());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_role = this.getRole(_rs);
			} else {
				_role = null;
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
		return _role;
	}

	private Role getRole(ResultSet _rs) throws SQLException {
		int _idrole = _rs.getInt("idRole");
		String _name = _rs.getString("name");
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		List<User> _user = _userDao.getUserForARole(_idrole);
		Role _role = new Role(_idrole, _name, _user);
		return _role;
	}

}