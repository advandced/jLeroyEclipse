package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ProductSupply;

public class ProductSupplyDaoImpl implements ProductSupplyDao {
	private static String exceptionMsg = "Tension d'alimentation de produit inconnue dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ProductSupplyDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductSupply getProductSupply(int idProductSupply)
			throws SQLException {
		ProductSupply _productSupply = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productSupply WHERE idProductSupply=?");
			_stmt.setInt(1, idProductSupply);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productSupply = this.getProductSupply(_rs);
			} else {
				_productSupply = null;
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

		return _productSupply;
	}

	@Override
	public ProductSupply getProductSupply(String name) throws SQLException {
		ProductSupply _productSupply = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productSupply WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productSupply = this.getProductSupply(_rs);
			} else {
				_productSupply = null;
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

		return _productSupply;
	}

	@Override
	public List<ProductSupply> getProductSupplies() throws SQLException {
		List<ProductSupply> _productSupplies = new ArrayList<ProductSupply>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productSupply");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductSupply _productSupply = this.getProductSupply(_rs);
				_productSupplies.add(_productSupply);
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
	public List<ProductSupply> getActiveProductSupplies() throws SQLException {
		List<ProductSupply> _activeProductSupplies = new ArrayList<ProductSupply>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productSupply " + " WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductSupply _productSupply = this.getProductSupply(_rs);
				_activeProductSupplies.add(_productSupply);
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

		return _activeProductSupplies;
	}

	/*
	 * Cr&eacute;er une alimentation produit &agrave; partir d'un enregistement
	 * de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Alimentation produit.
	 */
	private ProductSupply getProductSupply(ResultSet rs) throws SQLException {
		int _idProductSupply = rs.getInt("idProductSupply");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		ProductSupply _productSupply = new ProductSupply(_idProductSupply,
				_timestamp, _state, _name);

		return _productSupply;
	}

	@Override
	public ProductSupply addProductSupply(String name, int state)
			throws SQLException, ProductDaoException {

		ProductSupply _productSupply = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO productSupply (name, state)"
							+ " VALUES (?, ?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.executeUpdate();

			// Retrieve productSupply data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productSupply" + " WHERE (name=?)"
							+ " 	AND (state=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productSupply = this.getProductSupply(_rs);
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

		return _productSupply;
	}

	@Override
	public void updateProductSupply(ProductSupply productSupplyToUpdate)
			throws SQLException, ProductDaoException {

		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE productSupply "
							+ "SET name=?, state=?"
							+ " WHERE (idProductSupply=?)");
			_stmt.setString(1, productSupplyToUpdate.getName());
			_stmt.setInt(2, productSupplyToUpdate.getState());
			_stmt.setInt(3, productSupplyToUpdate.getIdProductSupply());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productSupply WHERE (idProductSupply = ?)");
			_stmt.setInt(1, productSupplyToUpdate.getIdProductSupply());

			ResultSet _rs = _stmt.executeQuery();
			if (!_rs.next()) {
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
	public void deleteProductSupply(ProductSupply productSupplyToDelete)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"DELETE FROM productSupply "
									+ " WHERE (idProductSupply=?)");
			_stmt.setInt(1, productSupplyToDelete.getIdProductSupply());
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
