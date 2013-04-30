package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.ProductSupply;

public class ProductSupplyDaoImpl extends GenericDao implements ProductSupplyDao {

	ConnectionProduct cnxProduct;

	public ProductSupplyDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductSupply getProductSupply(int idProductSupply) {
		ProductSupply _productSupply = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productSupply WHERE idProductSupply=?");
			_stmt.setInt(1, idProductSupply);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productSupply = this.getProductSupply(_rs);
			} else {
				_productSupply = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productSupply;
	}

	@Override
	public ProductSupply getProductSupply(String name) {
		ProductSupply _productSupply = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM productSupply WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productSupply = this.getProductSupply(_rs);
			} else {
				_productSupply = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productSupply;
	}

	@Override
	public List<ProductSupply> getProductSupplies() {
		List<ProductSupply> _productSupplies = new ArrayList<ProductSupply>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productSupply");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductSupply _productSupply = this.getProductSupply(_rs);
				_productSupplies.add(_productSupply);
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
	public List<ProductSupply> getActiveProductSupplies() {
		List<ProductSupply> _activeProductSupplies = new ArrayList<ProductSupply>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productSupply WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductSupply _productSupply = this.getProductSupply(_rs);
				_activeProductSupplies.add(_productSupply);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
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
	public ProductSupply addProductSupply(String name, int state) {

		ProductSupply _productSupply = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO productSupply (name, state)"
							+ " VALUES (?, ?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.executeUpdate();

			// Retrieve productSupply data
			_stmt = c.prepareStatement(
					"SELECT * FROM productSupply" + " WHERE (name=?)"
							+ " 	AND (state=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productSupply = this.getProductSupply(_rs);
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

		return _productSupply;
	}

	@Override
	public void updateProductSupply(ProductSupply productSupplyToUpdate) {

		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE productSupply "
							+ "SET name=?, state=?"
							+ " WHERE (idProductSupply=?)");
			_stmt.setString(1, productSupplyToUpdate.getName());
			_stmt.setInt(2, productSupplyToUpdate.getState());
			_stmt.setInt(3, productSupplyToUpdate.getIdProductSupply());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * FROM productSupply WHERE (idProductSupply = ?)");
			_stmt.setInt(1, productSupplyToUpdate.getIdProductSupply());

			ResultSet _rs = _stmt.executeQuery();
			if (!_rs.next()) {
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
	public void deleteProductSupply(ProductSupply productSupplyToDelete) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"DELETE FROM productSupply "
									+ " WHERE (idProductSupply=?)");
			_stmt.setInt(1, productSupplyToDelete.getIdProductSupply());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}
}
