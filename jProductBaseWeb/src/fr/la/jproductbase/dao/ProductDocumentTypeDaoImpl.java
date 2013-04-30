package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.ProductDocumentType;

public class ProductDocumentTypeDaoImpl extends GenericDao implements ProductDocumentTypeDao {

	ConnectionProduct cnxProduct;

	public ProductDocumentTypeDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}
	
	
	@Override
	public ProductDocumentType addProductDocumentType(int state, String name) {
		ProductDocumentType _productDocumentType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO productDocumentType (state, name)"
							+ " VALUES (?, ?)");
			_stmt.setInt(1, state);
			_stmt.setString(2, name);
			_stmt.executeUpdate();

			// Retrieve ProductDocumentType data
			_stmt = c.prepareStatement(
					"SELECT * FROM productDocumentType" + " WHERE (name=?)");
			_stmt.setString(1, name);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
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
		return _productDocumentType;
	}

	@Override
	public ProductDocumentType updateProductDocumentType(ProductDocumentType productDocumentType) {
		ProductDocumentType _productDocumentType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE productDocumentType "
							+ "SET state=?, name=?"
							+ " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, productDocumentType.getState());
			_stmt.setString(2, productDocumentType.getName());
			_stmt.setInt(3, productDocumentType.getIdProductDocumentType());
			_stmt.executeUpdate();
			
			// Retrieve ProductDocumentType data
			_stmt = c.prepareStatement(
					"SELECT * FROM productDocumentType" + " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, productDocumentType.getIdProductDocumentType());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
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

		return _productDocumentType;
	}

	@Override
	public ProductDocumentType getProductDocumentType(int idProductDocumentType) {
		ProductDocumentType _productDocumentType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM productDocumentType " + " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, idProductDocumentType);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocumentType;
	}

	@Override
	public ProductDocumentType getProductDocumentType(String name) {
		ProductDocumentType _productDocumentType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocumentType WHERE (name=?)");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocumentType;
	}

	/*@Override
	public List<ProductDocumentType> getProductDocumentTypes(Product product)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<ProductDocumentType> getProductDocumentTypes() {
		List<ProductDocumentType> _productDocumentTypes = new ArrayList<ProductDocumentType>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocumentType ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocumentType _productDocumentType = this.getProductDocumentType(_rs);
				_productDocumentTypes.add(_productDocumentType);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocumentTypes;
	}

	@Override
	public List<ProductDocumentType> getActiveProductDocumentTypes() {
		List<ProductDocumentType> _productDocumentTypes = new ArrayList<ProductDocumentType>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocumentType WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocumentType _productDocumentType = this.getProductDocumentType(_rs);
				_productDocumentTypes.add(_productDocumentType);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocumentTypes;
	}
	
	/*
	 * Cr&eacute;er un type de document &agrave; partir d'un enregistrement de la base
	 * de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Type de document.
	 * 
	 * @throws SQLException
	 */
	private ProductDocumentType getProductDocumentType(ResultSet rs) throws SQLException {
		// Software
		int _idProductDocumentType = rs.getInt("idProductDocumentType");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		return new ProductDocumentType(_idProductDocumentType, _timestamp, _state, _name);
	}
	
}
