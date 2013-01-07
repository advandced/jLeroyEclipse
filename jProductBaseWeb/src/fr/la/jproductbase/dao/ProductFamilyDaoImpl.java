package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;

public class ProductFamilyDaoImpl implements ProductFamilyDao {
	private static String exceptionMsg = "Famille de produit inconnue dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ProductFamilyDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductFamily getProductFamily(int idProductFamily)
			throws SQLException {
		ProductFamily _productFamily = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productFamily WHERE idProductFamily=?");
			_stmt.setInt(1, idProductFamily);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productFamily = this.getProductFamily(_rs);
			} else {
				_productFamily = null;
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

		return _productFamily;
	}

	@Override
	public ProductFamily getProductFamily(String name) throws SQLException {
		ProductFamily _productFamily = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productFamily WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productFamily = this.getProductFamily(_rs);
			} else {
				_productFamily = null;
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

		return _productFamily;
	}

	@Override
	public List<ProductFamily> getProductFamilies() throws SQLException {
		List<ProductFamily> _productFamilies = new ArrayList<ProductFamily>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productFamily");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductFamily _productFamily = this.getProductFamily(_rs);
				_productFamilies.add(_productFamily);
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

		return _productFamilies;
	}

	@Override
	public List<ProductFamily> getProductFamilies(int type) throws SQLException {
		List<ProductFamily> _productFamilies = new ArrayList<ProductFamily>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productFamily WHERE idProductType = ?");
			_stmt.setInt(1, type);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductFamily _productFamily = this.getProductFamily(_rs);
				_productFamilies.add(_productFamily);
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

		return _productFamilies;
	}

	@Override
	public List<ProductFamily> getActiveProductFamilies() throws SQLException {
		List<ProductFamily> _productFamilies = new ArrayList<ProductFamily>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productFamily " + " WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductFamily _productFamily = this.getProductFamily(_rs);
				_productFamilies.add(_productFamily);
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

		return _productFamilies;
	}

	/*
	 * Cr&eacute;er une famille de produit &agrave; partir d'un enregistement de
	 * la base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Famille de produit.
	 */
	private ProductFamily getProductFamily(ResultSet rs) throws SQLException {
		// Retreive productType
		int _idProductType = rs.getInt("idProductType");
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);
		ProductType _productType = _productTypeDao
				.getProductType(_idProductType);
		int _idProductFamily = rs.getInt("idProductFamily");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		ProductFamily _productFamily = new ProductFamily(_idProductFamily,
				_timestamp, _state, _name, _productType);

		return _productFamily;
	}

	@Override
	public ProductFamily addProductFamily(String name, int state,
			ProductType productType) throws SQLException,
			ProductFamilyDaoException {

		ProductFamily _productFamily = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO productFamily (name, state, idProductType)"
							+ " VALUES (?, ?, ?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.setInt(3, productType.getIdProductType());
			_stmt.executeUpdate();

			// Retrieve productFamily data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productFamily" + " WHERE (name=?)"
							+ " 	AND (state=?) AND (idProductType=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.setInt(3, productType.getIdProductType());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productFamily = this.getProductFamily(_rs);
			} else {
				throw new ProductFamilyDaoException(exceptionMsg);
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

		return _productFamily;
	}

	@Override
	public void updateProductFamily(ProductFamily productFamilyToUpdate)
			throws SQLException, ProductFamilyDaoException {

		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE productFamily "
							+ "SET name=?, state=?, idProductType=?"
							+ " WHERE (idProductFamily=?)");
			_stmt.setString(1, productFamilyToUpdate.getName());
			_stmt.setInt(2, productFamilyToUpdate.getState());
			_stmt.setInt(3, productFamilyToUpdate.getProductType()
					.getIdProductType());
			_stmt.setInt(4, productFamilyToUpdate.getIdProductFamily());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * " + "FROM productFamily "
							+ "WHERE (idProductFamily=?);");
			_stmt.setInt(1, productFamilyToUpdate.getIdProductFamily());
			
			ResultSet _rs = _stmt.executeQuery();
			if(_rs.next()) {
				
			} else {
				throw new ProductFamilyDaoException(exceptionMsg);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	@Override
	public void deleteProductFamily(ProductFamily productFamilyToDelete)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"DELETE FROM productFamily "
									+ " WHERE (idProductFamily=?)");
			_stmt.setInt(1, productFamilyToDelete.getIdProductFamily());
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
}
