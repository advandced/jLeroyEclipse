package fr.la.juserright.dao;

import fr.la.jproductbase.dao.GenericDao;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class RessourceDAO extends GenericDao implements ModelDAO<Ressource> {

	private ConnectionUserRight cnxUserRight;

	public RessourceDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(Ressource _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO ressource (path, menu, managedbean, description) "
							+ "VALUES ('" + _object.getPath() + "', '"
							+ _object.getMenu() + "', '"
							+ _object.getManagedBean() + "', '"
							+ _object.getDescription() + "');");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Ressource read(int _idRessource) {
		Ressource _ressource = new Ressource();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource" + " WHERE idressource = ?;");
			_stmt.setInt(1, _idRessource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public List<Ressource> readAll() {
		List<Ressource> __ressource = new ArrayList<Ressource>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;


		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Ressource _ressourcetmp = this.getRessource(_rs);
				__ressource.add(_ressourcetmp);
			}
			_stmt.setFetchSize(500);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return __ressource;
	}

	public void update(Ressource _object) {
		Connection c = null;
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
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(_sql);
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void updateidRR(Ressource _object, Ressource_Ressource _rr) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			String _sql = "UPDATE ressource SET idressource_ressource = '"
					+ _rr.getIdressource_ressource() + "' WHERE idressource = "
					+ _object.getIdressource() + ";";
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(_sql);
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void updateToNullIDRR(int idressource_ressource) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			String _sql = "UPDATE ressource SET idressource_ressource = NULL WHERE idressource_ressource = "
					+ idressource_ressource + "; ";
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(_sql);
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public void delete(Ressource _object) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM ressource WHERE idressource = "
							+ _object.getIdressource() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Ressource read(Ressource _object) {
		Ressource _ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource" + " WHERE menu = ?;");
			_stmt.setString(1, _object.getMenu());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public List<Ressource> selectRessourceNotUsedByRole(List<Autorisation> _listAutorisation) {
		List<Ressource> _ressource = new ArrayList<Ressource>();
		Connection c = null;
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
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(rqt);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				Ressource _ressourcetmp = this.getRessource(_rs);
				_ressource.add(_ressourcetmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public Ressource createWithRR(Ressource _object) {
		Connection c = null;
		PreparedStatement _stmt = null;
		Ressource _ressource = new Ressource();
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
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
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public String getMenuWithRR(int idressource_ressource) {
		String _menu = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
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
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _menu;
	}

	public Ressource getRessourceByPath(String Path) {
		Ressource _ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource" + " WHERE path = ?;");
			_stmt.setString(1, Path);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public Ressource getRessourceByMenu(String Menu) {
		Ressource _ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource" + " WHERE menu = ?;");
			_stmt.setString(1, Menu);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public Ressource getRessourceByMenuForUpdate(String Menu, int idressource) {
		Ressource _ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM ressource WHERE menu like ? and idressource not like ?;");
			_stmt.setString(1, Menu);
			_stmt.setInt(2, idressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	public Ressource getRessourceByPathForUpdate(String Path, int idressource) {
		Ressource _ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM ressource WHERE path like ? and idressource not like ?;");
			_stmt.setString(1, Path);
			_stmt.setInt(2, idressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			} else {
				_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	private Ressource getRessourceForChild(int _idChild) {
		Ressource _ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource " + "WHERE idressource = ("
							+ "SELECT idressource "
							+ "FROM ressource_ressource "
							+ "WHERE idressource_ressource = ?);");
			_stmt.setInt(1, _idChild);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource = this.getRessource(_rs);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource;
	}

	private List<Ressource> getRessourceForMOM(int _idmere) {
		List<Ressource> _ressource = new ArrayList<Ressource>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		String _sql = "SELECT * FROM ressource "
				+ "WHERE idressource_ressource = ("
				+ "SELECT idressource_ressource " + "FROM ressource_ressource "
				+ "WHERE idressource = " + _idmere + ");";
		try {
			c = this.cnxUserRight.getCnx();
			_stmt = c.prepareStatement(_sql);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				_ressource.add(this.getRessource(_rs));
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
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