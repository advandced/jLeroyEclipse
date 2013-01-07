package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ProductLine;

public class ProductLineDaoImpl implements ProductLineDao {
	// private static String exceptionMsg =
	// "Gamme de produit inconnue dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ProductLineDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductLine getProductLine(int idProductLine) throws SQLException {
		ProductLine _productLine = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productLine WHERE idProductLine=?");
			_stmt.setInt(1, idProductLine);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productLine = this.getProductLine(_rs);
			} else {
				_productLine = null;
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
