package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;

public class ElementChangedDaoImpl implements ElementChangedDao {
	private static String exceptionMsg = "Elément de composant inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ElementChangedDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public List<ElementChanged> getElementsChanged(Failure failure)
			throws SQLException {
		List<ElementChanged> _elementsChanged = new ArrayList<ElementChanged>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM elementChanged WHERE idFailure=?");
			_stmt.setInt(1, failure.getIdFailure());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ElementChanged _elementChanged = this.getElementChanged(_rs);
				_elementsChanged.add(_elementChanged);

				// Update failure object
				//failure.addElementChanged(_elementChanged);
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

		return _elementsChanged;
	}

	@Override
	public ElementChanged addElementChanged(ElementChanged elementChanged,
			Failure failure) throws SQLException, ElementChangedDaoException {
		ElementChanged _elementChanged = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		
		System.out.println("addFailure");
		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO elementChanged (topoRef, idFailure)"
							+ " VALUES (?, ?)");
			_stmt.setString(1, elementChanged.getCode());
			_stmt.setInt(2, failure.getIdFailure());
			_stmt.executeUpdate();

			// Retrieve elementChanged data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM elementChanged" + " WHERE (idFailure=?)");
			_stmt.setInt(1, failure.getIdFailure());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_elementChanged = this.getElementChanged(_rs);
			} else {
				throw new ElementChangedDaoException(exceptionMsg);
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

		return _elementChanged;
	}

	@Override
	public void updateElementChanged(ElementChanged elementChanged,
			Failure failure) throws SQLException, ElementChangedDaoException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE elementChanged " + "SET topoRef=?"
							+ " WHERE (idElementChanged=?)");
			_stmt.setString(1, elementChanged.getCode());
			_stmt.setInt(2, elementChanged.getIdElementChanged());
			_stmt.executeUpdate();

			// Update object
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM elementChanged"
							+ " WHERE (idElementChanged=?)");
			_stmt.setInt(1, elementChanged.getIdElementChanged());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getElementChanged(_rs);
			} else {
				throw new ElementChangedDaoException(exceptionMsg);
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
	}

	@Override
	public void removeElementChanged(ElementChanged elementChanged)
			throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM elementChanged "
							+ "WHERE (idElementChanged=?)");
			_stmt.setInt(1, elementChanged.getIdElementChanged());
			_stmt.executeUpdate();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
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
