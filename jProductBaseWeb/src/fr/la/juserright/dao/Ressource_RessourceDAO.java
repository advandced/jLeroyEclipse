package fr.la.juserright.dao;

import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import fr.la.juserright.service.ServiceUserRight;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.naming.NamingException;

public class Ressource_RessourceDAO implements ModelDAO<Ressource_Ressource> {

	private ConnectionUserRight cnxUserRight;

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	public Ressource_RessourceDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

    @Override
	public void create(Ressource_Ressource _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO ressource (idressource) " + "VALUES ('"
							+ _object.getIdressource() + "');");
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public Ressource_Ressource create(int idressource) throws SQLException {
		PreparedStatement _stmt = null;
		Ressource_Ressource _ressource_ressource = null;
		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO ressource_ressource (idressource) "
							+ "VALUES ('" + idressource + "');",
					Statement.RETURN_GENERATED_KEYS);
			_stmt.executeUpdate();
			ResultSet generatedKeys = _stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int i = (int) generatedKeys.getLong(1);
				_ressource_ressource = this.read(i);
			}
		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _ressource_ressource;
	}

    @Override
	public Ressource_Ressource read(Ressource_Ressource _object)
			throws SQLException {
		Ressource_Ressource _ressource_ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource_ressource"
							+ " WHERE idressource = ?;");
			_stmt.setInt(1, _object.getIdressource().getIdressource());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource_ressource = this.getRessource_Ressource(_rs);
			} else {
				_ressource_ressource = null;
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
		return _ressource_ressource;
	}

	public Ressource_Ressource read(int _idRessource_Ressource)
			throws SQLException {
		Ressource_Ressource _ressource_ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM ressource_ressource"
							+ " WHERE idressource_ressource = ?;");
			_stmt.setInt(1, _idRessource_Ressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource_ressource = this.getRessource_Ressource(_rs);
			} else {
				_ressource_ressource = null;
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
		return _ressource_ressource;
	}

    @Override
	public List<Ressource_Ressource> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
	public void update(Ressource_Ressource _object) throws SQLException {
		// TODO Auto-generated method stub

	}

    @Override
	public void delete(Ressource_Ressource _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"DELETE FROM ressource_ressource WHERE idressource = "
							+ _object.getIdressource().getIdressource() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public Ressource_Ressource getRessource_Ressource(ResultSet _rs)
			throws SQLException {
		int _idRessource_Ressource = _rs.getInt("idressource_ressource");
		Ressource _idRessource = moduleGlobal.getRessource(_rs
				.getInt("idressource"));
		Ressource_Ressource _rr = new Ressource_Ressource(
				_idRessource_Ressource, _idRessource);

		return _rr;
	}

}