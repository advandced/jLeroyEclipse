package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.ProductType;

public class ProductTypeDaoImpl extends GenericDao implements ProductTypeDao {

	ConnectionProduct cnxProduct;

	public ProductTypeDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductType getProductType(int idProductType) {
		ProductType _productType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productType WHERE idProductType=?");
			_stmt.setInt(1, idProductType);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productType = this.getProductType(_rs);
			} else {
				_productType = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productType;
	}

	@Override
	public ProductType getProductType(String name)  {
		ProductType _productType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productType WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productType = this.getProductType(_rs);
			} else {
				_productType = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productType;
	}

	@Override
	public List<ProductType> getProductTypes() {
		List<ProductType> _productSupplies = new ArrayList<ProductType>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productType");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductType _productType = this.getProductType(_rs);
				_productSupplies.add(_productType);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productSupplies;
	}

	@Override
	public List<ProductType> getActiveProductTypes() {
		List<ProductType> _productSupplies = new ArrayList<ProductType>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productType WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductType _productType = this.getProductType(_rs);
				_productSupplies.add(_productType);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
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
	public ProductType addProductType(String name, int state) {

		ProductType _productType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("INSERT INTO productType (name, state) VALUES (?, ?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.executeUpdate();

			// Retrieve productType data
			_stmt = c.prepareStatement("SELECT * FROM productType WHERE (name=?) AND (state=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productType = this.getProductType(_rs);
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

		return _productType;
	}

	@Override
	public void updateProductType(ProductType operatorToUpdate) {

		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("UPDATE productType SET name=?, state=? WHERE (idProductType=?)");
			_stmt.setString(1, operatorToUpdate.getName());
			_stmt.setInt(2, operatorToUpdate.getState());
			_stmt.setInt(3, operatorToUpdate.getIdProductType());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement("SELECT * FROM productType WHERE (idProductType = ?);");

			ResultSet _rs = _stmt.executeQuery();
			if (_rs.next()) {

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
	public void deleteProductType(ProductType operatorToDelete) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(	"DELETE FROM productType WHERE (idProductType=?)");
			_stmt.setInt(1, operatorToDelete.getIdProductType());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

}
