package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.ProductionFailureReport;

public class CustomerCommentDaoImpl extends GenericDao implements CustomerCommentDao {

	ConnectionProduct cnxProduct;

	public CustomerCommentDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public CustomerComment getCustomerComment(ProductionFailureReport productionFailureReport) {
		CustomerComment _customerComment = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
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

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _customerComment;
	}

	@Override
	public CustomerComment addCustomerComment(ProductionFailureReport productionFailureReport, String comment) {
		CustomerComment _customerComment = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO customerComment (comment, idProductionFailureReport)"
							+ " VALUES (?, ?)");
			_stmt.setString(1, comment);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Retrieve failureReportComment data
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM customerComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_customerComment = this.getCustomerComment(_rs);
			} else {
				throw new IllegalStateException();
			}

			// Update failureReport object
			productionFailureReport.setCustomerComment(_customerComment);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _customerComment;
	}

	@Override
	public void updateCustomerComment(ProductionFailureReport productionFailureReport,String customerComment) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE customerComment " + "SET comment=? "
							+ "WHERE (idProductionFailureReport=?)");
			_stmt.setString(1, customerComment);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public void removeCustomerComment(ProductionFailureReport productionFailureReport) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM customerComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update failureReport object
			productionFailureReport.setCustomerComment(null);

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
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
	private CustomerComment getCustomerComment(ResultSet rs) throws SQLException {
		int _idCustomerComment = rs.getInt("idCustomerComment");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		String _comment = rs.getString("comment");
		CustomerComment _customerComment = new CustomerComment(
				_idCustomerComment, _timestamp, _comment);

		return _customerComment;
	}
}
