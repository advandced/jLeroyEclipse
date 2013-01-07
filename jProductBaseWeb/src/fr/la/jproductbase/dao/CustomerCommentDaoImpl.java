package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.ProductionFailureReport;

public class CustomerCommentDaoImpl implements CustomerCommentDao {
	private static String exceptionMsg = "Commentaire client inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public CustomerCommentDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public CustomerComment getCustomerComment(ProductionFailureReport productionFailureReport)
			throws SQLException {
		CustomerComment _customerComment = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM customerComment WHERE idProductionFailureReport=?");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_customerComment = this.getCustomerComment(_rs);
			} else {
				// Pas de commentaire client pour ce rapport de défauts
			}

			// Update failureReport object
			productionFailureReport.setCustomerComment(_customerComment);
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

		return _customerComment;
	}

	@Override
	public CustomerComment addCustomerComment(ProductionFailureReport productionFailureReport,
			String comment) throws SQLException, CustomerCommentDaoException {
		CustomerComment _customerComment = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO customerComment (comment, idProductionFailureReport)"
							+ " VALUES (?, ?)");
			_stmt.setString(1, comment);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Retrieve failureReportComment data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM customerComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_customerComment = this.getCustomerComment(_rs);
			} else {
				throw new CustomerCommentDaoException(exceptionMsg);
			}

			// Update failureReport object
			productionFailureReport.setCustomerComment(_customerComment);
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

		return _customerComment;
	}

	@Override
	public void updateCustomerComment(ProductionFailureReport productionFailureReport,
			String customerComment) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE customerComment " + "SET comment=? "
							+ "WHERE (idProductionFailureReport=?)");
			_stmt.setString(1, customerComment);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
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

	@Override
	public void removeCustomerComment(ProductionFailureReport productionFailureReport)
			throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM customerComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update failureReport object
			productionFailureReport.setCustomerComment(null);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	/*
	 * Cr&eacute;er un commentaire client d'un rapport de d&eacute;fauts
	 * &agrave; partir d'un enregistrement de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Commentaire client de rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 */
	private CustomerComment getCustomerComment(ResultSet rs)
			throws SQLException {
		int _idCustomerComment = rs.getInt("idCustomerComment");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		String _comment = rs.getString("comment");
		CustomerComment _customerComment = new CustomerComment(
				_idCustomerComment, _timestamp, _comment);

		return _customerComment;
	}
}
