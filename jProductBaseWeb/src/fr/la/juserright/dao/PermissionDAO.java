package fr.la.juserright.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.dao.GenericDao;
import fr.la.juserright.metier.Permission;

public class PermissionDAO extends GenericDao implements ModelDAO<Permission> {

	private ConnectionUserRight cnxUserRight;

	public PermissionDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(Permission _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO permission (idPermission, name) " + "VALUES ("
							+ _object.getIdpermission() + ", '"
							+ _object.getName() + "');");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Permission read(Permission _object) {
		Permission _permission = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM permission" + " WHERE idpermission= ?;");
			_stmt.setInt(1, _object.getIdpermission());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_permission = this.getPermission(_rs);
			} else {
				_permission = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _permission;
	}

	public Permission read(String _name) {
		Connection c = null;
		Permission _permission = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM permission" + " WHERE  name= ?;");
			_stmt.setString(1, _name);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_permission = this.getPermission(_rs);
			} else {
				_permission = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _permission;
	}

	public Permission read(int _id) {
		Connection c = null;
		Permission _permission = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM permission" + " WHERE idpermission= ?;");
			_stmt.setInt(1, _id);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_permission = this.getPermission(_rs);
			} else {
				_permission = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _permission;
	}

	public List<Permission> readAll() {
		List<Permission> _permission = new ArrayList<Permission>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM permission");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Permission _permissiontmp = this.getPermission(_rs);
				_permission.add(_permissiontmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _permission;
	}

	public void update(Permission _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE permission " + "SET name = '" + _object.getName()
							+ "' " + "WHERE idPermission = "
							+ _object.getIdpermission() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void delete(Permission _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM permission " + "WHERE idpermission = "
							+ _object.getIdpermission() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}

	}

	private Permission getPermission(ResultSet _rs) throws SQLException {

		int _idPermission = _rs.getInt("idPermission");
		String _name = _rs.getString("name");

		Permission _permission = new Permission(_idPermission, _name);
		return _permission;
	}

}