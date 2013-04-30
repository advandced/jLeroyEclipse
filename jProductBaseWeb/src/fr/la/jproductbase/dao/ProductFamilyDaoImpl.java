package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;

public class ProductFamilyDaoImpl extends GenericDao implements ProductFamilyDao {

	ConnectionProduct cnxProduct;
	
	ProductTypeDao _productTypeDao;

	public ProductFamilyDaoImpl(ConnectionProduct cnxProduct, ProductTypeDao productTypeDao) {
		this.cnxProduct = cnxProduct;
		
		_productTypeDao = productTypeDao;
	}

	@Override
	public ProductFamily getProductFamily(int idProductFamily) {
		ProductFamily _productFamily = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productFamily WHERE idProductFamily=?");
			_stmt.setInt(1, idProductFamily);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productFamily = this.getProductFamily(_rs);
			} else {
				_productFamily = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productFamily;
	}

	@Override
	public ProductFamily getProductFamily(String name) {
		ProductFamily _productFamily = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productFamily WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productFamily = this.getProductFamily(_rs);
			} else {
				_productFamily = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productFamily;
	}

	@Override
	public List<ProductFamily> getProductFamilies() {
		List<ProductFamily> _productFamilies = new ArrayList<ProductFamily>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productFamily");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductFamily _productFamily = this.getProductFamily(_rs);
				_productFamilies.add(_productFamily);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productFamilies;
	}

	@Override
	public List<ProductFamily> getProductFamilies(int type) {
		List<ProductFamily> _productFamilies = new ArrayList<ProductFamily>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productFamily WHERE idProductType = ?");
			_stmt.setInt(1, type);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductFamily _productFamily = this.getProductFamily(_rs);
				_productFamilies.add(_productFamily);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productFamilies;
	}

	@Override
	public List<ProductFamily> getActiveProductFamilies()  {
		List<ProductFamily> _productFamilies = new ArrayList<ProductFamily>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productFamily " + " WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductFamily _productFamily = this.getProductFamily(_rs);
				_productFamilies.add(_productFamily);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
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
		ProductType _productType = _productTypeDao.getProductType(_idProductType);
		int _idProductFamily = rs.getInt("idProductFamily");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		return new ProductFamily(_idProductFamily, _timestamp, _state, _name, _productType);
	}

	@Override
	public ProductFamily addProductFamily(String name, int state, ProductType productType) {
		ProductFamily _productFamily = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("INSERT INTO productFamily (name, state, idProductType) VALUES (?, ?, ?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.setInt(3, productType.getIdProductType());
			_stmt.executeUpdate();

			// Retrieve productFamily data
			_stmt = c.prepareStatement("SELECT * FROM productFamily" + " WHERE (name=?)	AND (state=?) AND (idProductType=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.setInt(3, productType.getIdProductType());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productFamily = this.getProductFamily(_rs);
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

		return _productFamily;
	}

	@Override
	public void updateProductFamily(ProductFamily productFamilyToUpdate) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE productFamily "
							+ "SET name=?, state=?, idProductType=?"
							+ " WHERE (idProductFamily=?)");
			_stmt.setString(1, productFamilyToUpdate.getName());
			_stmt.setInt(2, productFamilyToUpdate.getState());
			_stmt.setInt(3, productFamilyToUpdate.getProductType()
					.getIdProductType());
			_stmt.setInt(4, productFamilyToUpdate.getIdProductFamily());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * " + "FROM productFamily "
							+ "WHERE (idProductFamily=?);");
			_stmt.setInt(1, productFamilyToUpdate.getIdProductFamily());
			
			ResultSet _rs = _stmt.executeQuery();
			if(_rs.next()) {
				
			} else {
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public void deleteProductFamily(ProductFamily productFamilyToDelete) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("DELETE FROM productFamily WHERE (idProductFamily=?)");
			_stmt.setInt(1, productFamilyToDelete.getIdProductFamily());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}
}
