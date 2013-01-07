package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ProductDocumentType;

public class ProductDocumentTypeDaoImpl implements ProductDocumentTypeDao {
	private static String exceptionMsg = "Type de document produit inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ProductDocumentTypeDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}
	
	
	@Override
	public ProductDocumentType addProductDocumentType(int state, String name)
			throws SQLException, ProductDocumentTypeDaoException {
		ProductDocumentType _productDocumentType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO productDocumentType (state, name)"
							+ " VALUES (?, ?)");
			_stmt.setInt(1, state);
			_stmt.setString(2, name);
			_stmt.executeUpdate();

			// Retrieve ProductDocumentType data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocumentType" + " WHERE (name=?)");
			_stmt.setString(1, name);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
			} else {
				throw new ProductDocumentTypeDaoException(exceptionMsg);
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

		return _productDocumentType;
	}

	@Override
	public ProductDocumentType updateProductDocumentType(
			ProductDocumentType productDocumentType) throws SQLException,
			ProductDocumentTypeDaoException {
		ProductDocumentType _productDocumentType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE productDocumentType "
							+ "SET state=?, name=?"
							+ " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, productDocumentType.getState());
			_stmt.setString(2, productDocumentType.getName());
			_stmt.setInt(3, productDocumentType.getIdProductDocumentType());
			_stmt.executeUpdate();
			
			// Retrieve ProductDocumentType data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocumentType" + " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, productDocumentType.getIdProductDocumentType());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
			} else {
				throw new ProductDocumentTypeDaoException(exceptionMsg);
			}
						
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocumentType;
	}

	@Override
	public ProductDocumentType getProductDocumentType(int idProductDocumentType)
			throws SQLException {
		ProductDocumentType _productDocumentType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocumentType " + " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, idProductDocumentType);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
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

		return _productDocumentType;
	}

	@Override
	public ProductDocumentType getProductDocumentType(String name)
			throws SQLException {
		ProductDocumentType _productDocumentType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocumentType " + " WHERE (name=?)");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocumentType = this.getProductDocumentType(_rs);
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

		return _productDocumentType;
	}

	/*@Override
	public List<ProductDocumentType> getProductDocumentTypes(Product product)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<ProductDocumentType> getProductDocumentTypes()
			throws SQLException {
		List<ProductDocumentType> _productDocumentTypes = new ArrayList<ProductDocumentType>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocumentType ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocumentType _productDocumentType = this.getProductDocumentType(_rs);
				_productDocumentTypes.add(_productDocumentType);
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

		return _productDocumentTypes;
	}

	@Override
	public List<ProductDocumentType> getActiveProductDocumentTypes()
			throws SQLException {
		List<ProductDocumentType> _productDocumentTypes = new ArrayList<ProductDocumentType>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocumentType " + " WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocumentType _productDocumentType = this.getProductDocumentType(_rs);
				_productDocumentTypes.add(_productDocumentType);
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
		ProductDocumentType _productDocumentType = new ProductDocumentType(_idProductDocumentType, _timestamp, _state,
				_name);

		return _productDocumentType;
	}
	
}
