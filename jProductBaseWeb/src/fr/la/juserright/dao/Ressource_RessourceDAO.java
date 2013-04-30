package fr.la.juserright.dao;

import fr.la.jproductbase.dao.GenericDao;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Ressource_RessourceDAO extends GenericDao implements ModelDAO<Ressource_Ressource> {

	private ConnectionUserRight cnxUserRight;
	RessourceDAO _ressourceDao;

	//private ServiceUserRight moduleGlobal = new ServiceUserRight();

	public Ressource_RessourceDAO(ConnectionUserRight cnxUserRight, RessourceDAO ressourceDao) {
		this.cnxUserRight = cnxUserRight;
		_ressourceDao = ressourceDao;
	}

    @Override
	public void create(Ressource_Ressource _object) {
    	Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO ressource (idressource) " + "VALUES ('"
							+ _object.getIdressource() + "');");
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Ressource_Ressource create(int idressource) {
		Connection c = null;
		PreparedStatement _stmt = null;
		Ressource_Ressource _ressource_ressource = null;
		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO ressource_ressource (idressource) "
							+ "VALUES ('" + idressource + "');",
					Statement.RETURN_GENERATED_KEYS);
			_stmt.executeUpdate();
			ResultSet generatedKeys = _stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int i = (int) generatedKeys.getLong(1);
				_ressource_ressource = this.read(i);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
		return _ressource_ressource;
	}

    @Override
	public Ressource_Ressource read(Ressource_Ressource _object) {
    	Connection c = null;
		Ressource_Ressource _ressource_ressource = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource_ressource"
							+ " WHERE idressource = ?;");
			_stmt.setInt(1, _object.getIdressource().getIdressource());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource_ressource = this.getRessource_Ressource(_rs);
			} else {
				_ressource_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource_ressource;
	}

	public Ressource_Ressource read(int _idRessource_Ressource) {
		Ressource_Ressource _ressource_ressource = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM ressource_ressource"
							+ " WHERE idressource_ressource = ?;");
			_stmt.setInt(1, _idRessource_Ressource);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_ressource_ressource = this.getRessource_Ressource(_rs);
			} else {
				_ressource_ressource = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _ressource_ressource;
	}

    @Override
	public List<Ressource_Ressource> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
	public void update(Ressource_Ressource _object) {
		// TODO Auto-generated method stub

	}

    @Override
	public void delete(Ressource_Ressource _object) {
    	Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxUserRight.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM ressource_ressource WHERE idressource = "
							+ _object.getIdressource().getIdressource() + ";");
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	public Ressource_Ressource getRessource_Ressource(ResultSet _rs) throws SQLException {
		int _idRessource_Ressource = _rs.getInt("idressource_ressource");
		Ressource _idRessource = _ressourceDao.read(_rs.getInt("idressource"));
		Ressource_Ressource _rr = new Ressource_Ressource(_idRessource_Ressource, _idRessource);
		return _rr;
	}

}