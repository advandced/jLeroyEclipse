package fr.la.juserright.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.UserRole;
import fr.la.juserright.service.ServiceUserRight;

public class AutorisationDAO implements ModelDAO<Autorisation> {

	private ConnectionUserRight cnxUserRight;

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	public AutorisationDAO(ConnectionUserRight _cnxUserRight) {
		this.cnxUserRight = _cnxUserRight;
	}

	public void create(Autorisation _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO autorisation (idpermission, idressource, idrole) "
							+ "VALUES ("
							+ _object.getPermission().getIdpermission() + ""
							+ ", " + _object.getRessource().getIdressource()
							+ "" + ", " + _object.getRole().getIdrole() + ");");
			_stmt.executeUpdate();

			System.out.println("Shakreat");
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public Autorisation read(Autorisation _object) {
		return null;
	}

	public List<Autorisation> readAll() throws SQLException {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM autorisation;");
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			} else {
				_autorisation = null;
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
		return _autorisation;
	}

	public void update(Autorisation _autorisation) throws SQLException {
		PreparedStatement _stmt = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"UPDATE autorisation SET idpermission = ? "
							+ "WHERE idressource = ? and idrole = ?;");
			_stmt.setInt(1, _autorisation.getPermission().getIdpermission());
			_stmt.setInt(2, _autorisation.getRessource().getIdressource());
			_stmt.setInt(3, _autorisation.getRole().getIdrole());
			_stmt.executeUpdate();
			
			System.out.println("Shakupdat");

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			
			try {
				this.cnxUserRight.getCnx().close();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delete(Autorisation _object) throws SQLException {
		PreparedStatement _stmt = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"DELETE FROM autorisation " + "WHERE idressource = ?"
							+ " AND idrole = ?;");
			_stmt.setInt(1, _object.getRessource().getIdressource());
			_stmt.setInt(2, _object.getRole().getIdrole());
			_stmt.executeUpdate();
			
			System.out.println("Shakdelet");
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public List<Autorisation> getAutorisationWithIdPermission(int idpermission)
			throws SQLException {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM autorisation" + " WHERE idpermission = ?;");
			_stmt.setInt(1, idpermission);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			} else {
				_autorisation = null;
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
		return _autorisation;
	}

	public List<Autorisation> getAutorisationWithIdRessource(int idressource)
			throws SQLException {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM autorisation" + " WHERE idressource = ?;");
			_stmt.setInt(1, idressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			} else {
				_autorisation = null;
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
		return _autorisation;
	}

	public List<Autorisation> getAutorisationWithIdRole(int idrole)
			throws SQLException {
		List<Autorisation> _autorisation = new ArrayList<Autorisation>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM autorisation WHERE idrole = ?;");
			_stmt.setInt(1, idrole);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Autorisation _autorisationtmp = this.getAutorisation(_rs);
				_autorisation.add(_autorisationtmp);
			}
			_rs.setFetchSize(100);
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
		return _autorisation;
	}

	public Autorisation checkAutorisationExists(Autorisation _autorisation)
			throws SQLException {
		Autorisation __autorisation = new Autorisation();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
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
		return __autorisation;
	}

	public List<Autorisation> getAutorisationByLogin(String login)
			throws SQLException {
		List<UserRole> _ur = moduleGlobal.getUserRoleWithLogin(login);
		List<Autorisation> _AutorisationTmp = new ArrayList<Autorisation>();
		List<Autorisation> _AutorisationReturn = new ArrayList<Autorisation>();
		if (_ur != null) {
			for (UserRole ur : _ur) {
				_AutorisationTmp = this.getAutorisationWithIdRole(ur.getRole()
						.getIdrole());
				if (_AutorisationTmp != null) {
					if (_AutorisationReturn == null) {
						_AutorisationReturn = _AutorisationTmp;
					} else {
						for (Autorisation tmp : _AutorisationTmp) {
							int found = 0;
							for (Autorisation rtn : _AutorisationReturn) {
								if (rtn.getRessource() == tmp.getRessource()) {
									found++;
									if (rtn.getPermission().getIdpermission() < tmp
											.getPermission().getIdpermission()) {
										rtn.getPermission().setIdpermission(
												tmp.getPermission()
														.getIdpermission());
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

		Permission _perm = moduleGlobal.getPermission(_idpermission);
		Ressource _ress = moduleGlobal.getRessource(_idressource);
		Role _role = moduleGlobal.getRole(_idrole);

		Autorisation _autorisation = new Autorisation(_perm, _ress, _role);
		return _autorisation;
	}
}