package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.la.jproductbase.metier.ProductLine;

public class ProductLineDaoImpl extends GenericDao implements ProductLineDao {

	ConnectionProduct cnxProduct;

	public ProductLineDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductLine getProductLine(int idProductLine) {
		ProductLine _productLine = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productLine WHERE idProductLine=?");
			_stmt.setInt(1, idProductLine);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productLine = this.getProductLine(_rs);
			} else {
				_productLine = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productLine;
	}

	/*
	 * Cr&eacute;er une gamme produit &agrave; partir d'un enregistement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Gamme produit.
	 */
	private ProductLine getProductLine(ResultSet rs) throws SQLException {
		int _idProductLine = rs.getInt("idProductLine");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _designation = rs.getString("designation");
		ProductLine _productLine = new ProductLine(_idProductLine, _timestamp,
				_state, _designation);

		return _productLine;
	}
}
