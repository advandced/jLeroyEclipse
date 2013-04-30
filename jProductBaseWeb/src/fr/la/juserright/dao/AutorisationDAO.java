package fr.la.juserright.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.dao.GenericDao;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.UserRole;

public class AutorisationDAO extends GenericDao implements ModelDAO<Autorisation> {

	ConnectionUserRight cnxUserRight;
	UserRoleDAO _userroleDao;
	PermissionDAO _permissionDao;
	RessourceDAO _ressourceDao;
	RoleDAO _roleDao;

	public AutorisationDAO(ConnectionUserRight cnxUserRight, UserRoleDAO userroleDao, PermissionDAO permissionDao, RessourceDAO ressourceDao, RoleDAO roleDao) {
		this.cnxUserRight = cnxUserRight;
		_userroleDao = userroleDao;
		_permissionDao = permissionDao;
		_ressourceDao = ressourceDao;
		_roleDao = roleDao;
	}

	public void create(Autorisation _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO autorisation (idpermission, idressource, idrole) "
							+ "VALUES ("
							+ _object.getPermission().getIdpermission() + ""
							+ ", " + _object.getRessource().getIdressource()
							+ "" + ", " + _object.getRole().getIdrole() + ");");
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Autorisation read(Autorisation _object) {
		return null;
	}

	public List<Autorisation> readAll() {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM autorisation;");
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			} else {
				_autorisation = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _autorisation;
	}

	public void update(Autorisation _autorisation) {
		Connection c = null;
		PreparedStatement _stmt = null;
		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE autorisation SET idpermission = ? "
							+ "WHERE idressource = ? and idrole = ?;");
			_stmt.setInt(1, _autorisation.getPermission().getIdpermission());
			_stmt.setInt(2, _autorisation.getRessource().getIdressource());
			_stmt.setInt(3, _autorisation.getRole().getIdrole());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void delete(Autorisation _object) {
		Connection c = null;
		PreparedStatement _stmt = null;
		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM autorisation " + "WHERE idressource = ?"
							+ " AND idrole = ?;");
			_stmt.setInt(1, _object.getRessource().getIdressource());
			_stmt.setInt(2, _object.getRole().getIdrole());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public List<Autorisation> getAutorisationWithIdPermission(int idpermission) {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM autorisation" + " WHERE idpermission = ?;");
			_stmt.setInt(1, idpermission);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			} else {
				_autorisation = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _autorisation;
	}

	public List<Autorisation> getAutorisationWithIdRessource(int idressource) {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM autorisation" + " WHERE idressource = ?;");
			_stmt.setInt(1, idressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			} else {
				_autorisation = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _autorisation;
	}

	public List<Autorisation> getAutorisationWithIdRole(int idrole) {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM autorisation WHERE idrole = ?;");
			_stmt.setInt(1, idrole);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			}
			_rs.setFetchSize(100);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _autorisation;
	}

	public Autorisation checkAutorisationExists(Autorisation _autorisation) {
		Autorisation __autorisation = new Autorisation();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM autorisation"
							+ " WHERE idrole = ? AND idressource = ?;");
			_stmt.setInt(1, _autorisation.getRole().getIdrole());
			_stmt.setInt(2, _autorisation.getRessource().getIdressource());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				__autorisation = this.getAutorisation(_rs);
			} else {
				__autorisation = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return __autorisation;
	}

	public List<Autorisation> getAutorisationByLogin(String login) {
		List<UserRole> _ur = _userroleDao.getUserRoleWithLogin(login);
		List<Autorisation> _AutorisationTmp = new ArrayList<Autorisation>();
		List<Autorisation> _AutorisationReturn = null;
		if (_ur != null) {
			for (UserRole ur : _ur) {
				_AutorisationTmp = this.getAutorisationWithIdRole(ur.getRole().getIdrole());
				if (_AutorisationTmp != null) {
					if (_AutorisationReturn == null) {
						_AutorisationReturn = _AutorisationTmp;
					} else {
						for (Autorisation tmp : _AutorisationTmp) {
							int found = 0;
							for (Autorisation rtn : _AutorisationReturn) {
								if (rtn.getRessource() == tmp.getRessource()) {
									found++;
									if (rtn.getPermission().getIdpermission() < tmp.getPermission().getIdpermission()) {
										rtn.getPermission().setIdpermission(tmp.getPermission().getIdpermission());
									}
								}
							}
							if (found == 0) {
								_AutorisationReturn.add(tmp);
							}
						}
					}
				}
			}
		}
		return _AutorisationReturn;
	}

	private Autorisation getAutorisation(ResultSet _rs) throws SQLException {

		int _idpermission = _rs.getInt("idpermission");
		int _idressource = _rs.getInt("idressource");
		int _idrole = _rs.getInt("idrole");

		Permission _perm = _permissionDao.read(_idpermission);
		Ressource _ress = _ressourceDao.read(_idressource);
		Role _role = _roleDao.getRole(_idrole);

		Autorisation _autorisation = new Autorisation(_perm, _ress, _role);
		return _autorisation;
	}
}