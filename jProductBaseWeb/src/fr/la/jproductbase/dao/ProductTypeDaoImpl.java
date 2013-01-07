package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ProductType;

public class ProductTypeDaoImpl implements ProductTypeDao {
	private static String exceptionMsg = "Configuration produit inconnue dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ProductTypeDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductType getProductType(int idProductType) throws SQLException {
		ProductType _productType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productType WHERE idProductType=?");
			_stmt.setInt(1, idProductType);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productType = this.getProductType(_rs);
			} else {
				_productType = null;
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

		return _productType;
	}

	@Override
	public ProductType getProductType(String name) throws SQLException {
		ProductType _productType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productType WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productType = this.getProductType(_rs);
			} else {
				_productType = null;
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

		return _productType;
	}

	@Override
	public List<ProductType> getProductTypes() throws SQLException {
		List<ProductType> _productSupplies = new ArrayList<ProductType>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productType");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductType _productType = this.getProductType(_rs);
				_productSupplies.add(_productType);
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

		return _productSupplies;
	}

	@Override
	public List<ProductType> getActiveProductTypes() throws SQLException {
		List<ProductType> _productSupplies = new ArrayList<ProductType>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productType " + " WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductType _productType = this.getProductType(_rs);
				_productSupplies.add(_productType);
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

		return _productSupplies;
	}

	/*
	 * Cr&eacute;er un mod&eacute;le de produit &agrave; partir d'un
	 * enregistement de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Mod&eacute;le de produit.
	 */
	private ProductType getProductType(ResultSet rs) throws SQLException {
		int _idProductType = rs.getInt("idProductType");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		ProductType _productType = new ProductType(_idProductType, _timestamp,
				_state, _name);

		return _productType;
	}

	@Override
	public ProductType addProductType(String name, int state)
			throws SQLException, ProductDaoException {

		ProductType _productType = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO productType (name, state)" + " VALUES (?, ?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.executeUpdate();

			// Retrieve productType data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productType" + " WHERE (name=?)"
							+ " 	AND (state=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productType = this.getProductType(_rs);
			} else {
				throw new ProductDaoException(exceptionMsg);
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

		return _productType;
	}

	@Override
	public void updateProductType(ProductType operatorToUpdate)
			throws SQLException, ProductDaoException {

		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE productType " + "SET name=?, state=?"
							+ " WHERE (idProductType=?)");
			_stmt.setString(1, operatorToUpdate.getName());
			_stmt.setInt(2, operatorToUpdate.getState());
			_stmt.setInt(3, operatorToUpdate.getIdProductType());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * " + "FROM productType "
							+ "WHERE (idProductType = ?);");

			ResultSet _rs = _stmt.executeQuery();
			if (_rs.next()) {

			} else {
				throw new ProductDaoException(exceptionMsg);
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
	public void deleteProductType(ProductType operatorToDelete)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM productType " + " WHERE (idProductType=?)");
			_stmt.setInt(1, operatorToDelete.getIdProductType());
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
