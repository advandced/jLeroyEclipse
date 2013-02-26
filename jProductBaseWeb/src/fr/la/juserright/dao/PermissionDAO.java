package fr.la.juserright.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.juserright.metier.Permission;

public class PermissionDAO implements ModelDAO<Permission> {

	private ConnectionUserRight cnxUserRight;

	public PermissionDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(Permission _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO permission (idPermission, name) " + "VALUES ("
							+ _object.getIdpermission() + ", '"
							+ _object.getName() + "');");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public Permission read(Permission _object) throws SQLException {
		Permission _permission = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM permission" + " WHERE idpermission= ?;");
			_stmt.setInt(1, _object.getIdpermission());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_permission = this.getPermission(_rs);
			} else {
				_permission = null;
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
			this.cnxUserRight.closeCnx();
		}
		return _permission;
	}

	public Permission read(String _name) throws SQLException {
		Permission _permission = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM permission" + " WHERE  name= ?;");
			_stmt.setString(1, _name);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_permission = this.getPermission(_rs);
			} else {
				_permission = null;
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
			this.cnxUserRight.closeCnx();
		}
		return _permission;
	}

	public Permission read(int _id) throws SQLException {
		Permission _permission = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM permission" + " WHERE idpermission= ?;");
			_stmt.setInt(1, _id);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_permission = this.getPermission(_rs);
			} else {
				_permission = null;
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
			this.cnxUserRight.closeCnx();
		}
		return _permission;
	}

	public List<Permission> readAll() throws SQLException {
		List<Permission> _permission = new ArrayList<Permission>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM permission");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Permission _permissiontmp = this.getPermission(_rs);
				_permission.add(_permissiontmp);
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
			this.cnxUserRight.closeCnx();
		}
		return _permission;
	}

	public void update(Permission _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"UPDATE permission " + "SET name = '" + _object.getName()
							+ "' " + "WHERE idPermission = "
							+ _object.getIdpermission() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public void delete(Permission _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"DELETE FROM permission " + "WHERE idpermission = "
							+ _object.getIdpermission() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}

	}

	private Permission getPermission(ResultSet _rs) throws SQLException {

		int _idPermission = _rs.getInt("idPermission");
		String _name = _rs.getString("name");

		Permission _permission = new Permission(_idPermission, _name);
		return _permission;
	}

}