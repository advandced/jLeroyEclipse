package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;

public class ElementChangedDaoImpl extends GenericDao implements ElementChangedDao {

	ConnectionProduct cnxProduct;

	public ElementChangedDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public List<ElementChanged> getElementsChanged(Failure failure) {
		List<ElementChanged> _elementsChanged = new ArrayList<ElementChanged>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM elementChanged WHERE idFailure=?");
			_stmt.setInt(1, failure.getIdFailure());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ElementChanged _elementChanged = this.getElementChanged(_rs);
				_elementsChanged.add(_elementChanged);

				// Update failure object
				//failure.addElementChanged(_elementChanged);
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _elementsChanged;
	}

	@Override
	public ElementChanged addElementChanged(ElementChanged elementChanged, Failure failure) {
		ElementChanged _elementChanged = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO elementChanged (topoRef, idFailure)"
							+ " VALUES (?, ?)");
			_stmt.setString(1, elementChanged.getCode());
			_stmt.setInt(2, failure.getIdFailure());
			_stmt.executeUpdate();

			// Retrieve elementChanged data
			_stmt = c.prepareStatement(
					"SELECT * FROM elementChanged" + " WHERE (idFailure=?)");
			_stmt.setInt(1, failure.getIdFailure());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_elementChanged = this.getElementChanged(_rs);
			} else {
				throw new IllegalStateException();
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _elementChanged;
	}

	@Override
	public void updateElementChanged(ElementChanged elementChanged,	Failure failure) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE elementChanged " + "SET topoRef=?"
							+ " WHERE (idElementChanged=?)");
			_stmt.setString(1, elementChanged.getCode());
			_stmt.setInt(2, elementChanged.getIdElementChanged());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM elementChanged"
							+ " WHERE (idElementChanged=?)");
			_stmt.setInt(1, elementChanged.getIdElementChanged());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getElementChanged(_rs);
			} else {
				throw new IllegalStateException();
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
	}

	@Override
	public void removeElementChanged(ElementChanged elementChanged) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM elementChanged "
							+ "WHERE (idElementChanged=?)");
			_stmt.setInt(1, elementChanged.getIdElementChanged());
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	/*
	 * Cr&eacute;er un &eacute;l&eacute;ent chang&eacute; &agrave; partir d'un
	 * enregistrement de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return El&eacute;ent chang&eacute;.
	 * 
	 * @throws SQLException
	 */
	private ElementChanged getElementChanged(ResultSet rs) throws SQLException {
		// ElementChanged
		int _idElementChanged = rs.getInt("idElementChanged");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		String _code = rs.getString("topoRef");
		ElementChanged _elementChanged = new ElementChanged(_idElementChanged,
				_timestamp, _code);

		return _elementChanged;
	}
}
