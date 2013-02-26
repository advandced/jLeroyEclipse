package fr.la.juserright.dao;

import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class RessourceDAO implements ModelDAO<Ressource> {
	
	private ConnectionUserRight cnxUserRight;

	public RessourceDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(Ressource _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO ressource (path, menu, managedbean, description) "
							+ "VALUES ('" + _object.getPath() + "', '"
							+ _object.getMenu() + "', '"
							+ _object.getManagedBean() + "', '"
							+ _object.getDescription() + "');");
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public Ressource read(int _idRessource) throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource" + " WHERE idressource = ?;");
			_stmt.setInt(1, _idRessource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public List<Ressource> readAll() throws SQLException {
		List<Ressource> __ressource = new ArrayList<Ressource>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Ressource _ressourcetmp = this.getRessource(_rs);
				__ressource.add(_ressourcetmp);
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return __ressource;
	}

	public void update(Ressource _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			String _sql = "UPDATE ressource " + "SET path = '"
					+ _object.getPath() + "' ," + "menu = '"
					+ _object.getMenu() + "' ," + "managedbean = '"
					+ _object.getManagedBean() + "' ," + "description = '"
					+ _object.getDescription() + "' ";
			if (_object.getRessource_Ressource() != null) {
				_sql += ", idressource_ressource = '"
						+ _object.getRessource_Ressource()
								.getIdressource_ressource() + "' ";
			} else {
				_sql += ", idressource_ressource = null ";
			}
			_sql += "WHERE idressource = " + _object.getIdressource() + ";";
			_stmt = this.cnxUserRight.getCnx().prepareStatement(_sql);
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public void updateidRR(Ressource _object, Ressource_Ressource _rr)
			throws SQLException {
		PreparedStatement _stmt = null;

		try {
			String _sql = "UPDATE ressource SET idressource_ressource = '"
					+ _rr.getIdressource_ressource() + "' WHERE idressource = "
					+ _object.getIdressource() + ";";
			_stmt = this.cnxUserRight.getCnx().prepareStatement(_sql);
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public void updateToNullIDRR(int idressource_ressource) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			String _sql = "UPDATE ressource SET idressource_ressource = NULL WHERE idressource_ressource = "
					+ idressource_ressource + "; ";
			_stmt = this.cnxUserRight.getCnx().prepareStatement(_sql);
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public void delete(Ressource _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"DELETE FROM ressource WHERE idressource = "
							+ _object.getIdressource() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
	}

	public Ressource read(Ressource _object) throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource" + " WHERE menu = ?;");
			_stmt.setString(1, _object.getMenu());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public List<Ressource> selectRessourceNotUsedByRole(
			List<Autorisation> _listAutorisation) throws SQLException {
		List<Ressource> _ressource = new ArrayList<Ressource>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		String rqt = "";
		if (null != _listAutorisation && _listAutorisation.size() > 0) {
			rqt += "SELECT * FROM ressource WHERE idressource NOT IN (";
			for (int j = 0; _listAutorisation.size() > j; j++) {
				if (j == 0) {
					rqt += _listAutorisation.get(j).getRessource()
							.getIdressource();
				} else {
					rqt += ", "
							+ _listAutorisation.get(j).getRessource()
									.getIdressource();
				}
			}
			rqt += ");";
		} else {
			rqt += "SELECT * FROM ressource;";
		}
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(rqt);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Ressource _ressourcetmp = this.getRessource(_rs);
				_ressource.add(_ressourcetmp);
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public Ressource createWithRR(Ressource _object) throws SQLException {
		PreparedStatement _stmt = null;
		Ressource _ressource = new Ressource();
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO ressource (path, menu, description) "
							+ "VALUES ('" + _object.getPath() + "', '"
							+ _object.getMenu() + "', '"
							+ _object.getDescription() + "');",
					Statement.RETURN_GENERATED_KEYS);
			_stmt.executeUpdate();
			ResultSet generatedKeys = _stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int i = (int) generatedKeys.getLong(1);
				_ressource = this.read(i);
			}
		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public String getMenuWithRR(int idressource_ressource) throws SQLException {
		String _menu = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT menu FROM ressource " + "WHERE idressource like "
							+ "(SELECT idressource "
							+ "FROM ressource_ressource WHERE "
							+ "idressource_ressource like ?);");
			_stmt.setInt(1, idressource_ressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_menu = _rs.getString("menu");
			} else {
				_menu = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _menu;
	}

	public Ressource getRessourceByPath(String Path) throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource" + " WHERE path = ?;");
			_stmt.setString(1, Path);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public Ressource getRessourceByMenu(String Menu) throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource" + " WHERE menu = ?;");
			_stmt.setString(1, Menu);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public Ressource getRessourceByMenuForUpdate(String Menu, int idressource)
			throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight
					.getCnx()
					.prepareStatement(
							"SELECT * FROM ressource WHERE menu like ? and idressource not like ?;");
			_stmt.setString(1, Menu);
			_stmt.setInt(2, idressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	public Ressource getRessourceByPathForUpdate(String Path, int idressource)
			throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight
					.getCnx()
					.prepareStatement(
							"SELECT * FROM ressource WHERE path like ? and idressource not like ?;");
			_stmt.setString(1, Path);
			_stmt.setInt(2, idressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
			this.cnxUserRight.closeCnx();
		}
		return _ressource;
	}

	private Ressource getRessourceForChild(int _idChild) throws SQLException {
		Ressource _ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource " + "WHERE idressource = ("
							+ "SELECT idressource "
							+ "FROM ressource_ressource "
							+ "WHERE idressource_ressource = ?);");
			_stmt.setInt(1, _idChild);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _ressource;
	}

	private List<Ressource> getRessourceForMOM(int _idmere) throws SQLException {
		List<Ressource> _ressource = new ArrayList<Ressource>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		String _sql = "SELECT * FROM ressource "
				+ "WHERE idressource_ressource = ("
				+ "SELECT idressource_ressource " + "FROM ressource_ressource "
				+ "WHERE idressource = " + _idmere + ");";
		try {

			_stmt = this.cnxUserRight.getCnx().prepareStatement(_sql);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				_ressource.add(this.getRessource(_rs));
			}
		} catch (NamingException e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _ressource;
	}

	private Ressource getRessource(ResultSet _rs) throws SQLException {

		int _idRessource = _rs.getInt("idressource");
		String _path = _rs.getString("path");
		String _menu = _rs.getString("menu");
		String _bean = _rs.getString("managedbean");
		String _description = _rs.getString("description");
		int _idressource_ressource = _rs.getInt("idressource_ressource");
		Ressource _ressource;
		if (_idressource_ressource != 0) {
			_ressource = new Ressource(_idRessource, _path, _menu, _bean,
					_description,
					this.getRessourceForChild(_idressource_ressource));
		} else {
			List<Ressource> _ressourceList = this
					.getRessourceForMOM(_idressource_ressource);
			_ressource = new Ressource(_idRessource, _path, _menu,
					_bean, _description, _ressourceList);
		}
		return _ressource;
	}
}